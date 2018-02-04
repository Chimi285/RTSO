import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.Serializable;


import static java.lang.Thread.sleep;

public class Main extends Application implements Serializable {
    public static Scanner read;
    public static Scanner inMessage;
    public static PrintWriter outMessage;
    public static String message = "X";
    public boolean allOk = true;
    public boolean work = true;
    public static String nickname = "";
    public static String authorizationResult = "Not";
    public static int X = 200;
    public static int Y = 200;
    public static LinkedList<Hero> characters;
    public static LinkedList<item> items;
    public static Socket socket;
    public static boolean Alive = true;
    public static boolean inventoryMenu = false;
    LinkedList<Hero> chart = new LinkedList<>();


    @Override
    public void start(Stage primaryStage) throws Exception{

        int serverPort = 6969;
        String address = "127.0.0.1";

        try{
            InetAddress ipAddress = InetAddress.getByName(address);
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
            socket = new Socket(ipAddress, serverPort);
            read = new Scanner(System.in);
            outMessage = new PrintWriter(socket.getOutputStream());
            inMessage = new Scanner(socket.getInputStream());
            System.out.println("Yes! I just got hold of the program.");
        }catch (Exception e){System.out.println("No, I didn't hear."); allOk = false;}

        Parent root = FXMLLoader.load(getClass().getResource("sceneReg.fxml"));
        Scene sceneReg = new Scene(root);
        Scene sceneConnectError = new Scene(FXMLLoader.load(getClass().getResource("sceneConnectError.fxml")));
        primaryStage.setTitle("Hello World");
        if(allOk) {primaryStage.setScene(sceneReg);}
        else {primaryStage.setScene(sceneConnectError);}
        primaryStage.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //try {
                    while (work) {
                        if (inMessage.hasNext()) {
                            //System.out.println("Have a new message!");
                            message = inMessage.nextLine();
                            //System.out.println(message);
                            if (message.equalsIgnoreCase("Authorization Successful!")) {
                                System.out.println("Authorization Successful from Client.");
                                authorizationResult = "Ok";
                                break;
                            }
                        }
                    }
                //} catch (Exception e) {System.out.println("Change Error");}
                ObjectInputStream oin = null;
                try {
                    oin = new ObjectInputStream(socket.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (work){
                try {
                    Data dt = new Data((Data) oin.readObject());
                    characters = (dt.getPlayers());
                    items = dt.getItems();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("error2");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error1");
                }
                }
            }
        }).start();

        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(authorizationResult.equals("Ok") && Alive) {
                    //System.out.println(ke.getCode());
                    if (ke.getCode() == KeyCode.W) outMessage.println("Forward");
                    if (ke.getCode() == KeyCode.A) outMessage.println("Left");
                    if (ke.getCode() == KeyCode.S) outMessage.println("Back");
                    if (ke.getCode() == KeyCode.D) outMessage.println("Right");
                    if (ke.getCode() == KeyCode.Q) outMessage.println("Hit");
                    outMessage.flush();
                }else if(authorizationResult.equals("Ok")){
                    outMessage.println("Died");
                    System.out.println("DiedX");
                    outMessage.flush();
                }
            }
        });

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if(allOk){
                    System.out.println("Stage is closing");
                    outMessage.println("##session##end##");
                    outMessage.flush();
                    work = false;

                }
            }
        });


    }

    public static void authorization(String nick){
        outMessage.println(nick);
        outMessage.flush();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
