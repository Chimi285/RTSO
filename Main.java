import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main extends Application {
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
    LinkedList<Hero> chart = new LinkedList<>();


    @Override
    public void start(Stage primaryStage) throws Exception{

        int serverPort = 6969;
        String address = "127.0.0.1";

        try{
            InetAddress ipAddress = InetAddress.getByName(address);
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort);
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

                            } else if (authorizationResult.equals("Ok")) {
                                chart = new LinkedList<Hero>();
                                for (int ii = 0; ii < message.length(); ii++) {
                                    int mode = 0;
                                    String str = "";
                                    String name = "";
                                    int x = -1;
                                    int y = -1;
                                    int health = -1;
                                    int side = -1;
                                    for (int i = ii; i < message.length(); i++) {
                                        if (message.charAt(i) != '.') {
                                            str += message.charAt(i);
                                        } else if (mode == 0) {
                                            name = str;
                                            str = "";
                                            mode++;
                                        } else if (mode == 1) {
                                            x = Integer.parseInt(str);
                                            str = "";
                                            mode++;
                                        } else if(mode == 2){
                                            y = Integer.parseInt(str);
                                            str = "";
                                            mode++;
                                        }else if(mode == 3){
                                            health = Integer.parseInt(str);
                                            str = "";
                                            mode++;
                                        }else {
                                            side = Integer.parseInt(str);
                                            str = "";
                                            Hero xxx = new Hero(name, x, y, health, side);
                                            //System.out.print(xxx.getName() + ":" + xxx.getX() + ":" + xxx.getY()+ ":" + xxx.getSide()+"/");
                                            chart.add(xxx);
                                            i = message.length();
                                        }
                                        ii++;
                                    }
                                    ii--;
                                }
                                //System.out.println();
                                if (characters != null) {
                                    for (Hero o : chart) { //оптимизировать
                                        boolean has = false;
                                        for (Hero oo : characters) {
                                            //System.out.println(o.getName() +":"+ oo.getName());
                                            if (o.getName().equals(oo.getName())) {
                                                if(oo.getName().equals(nickname)) {X = oo.getX(); Y = oo.getY();}
                                                oo.setXY(o.getX(), o.getY());
                                                oo.setSide(o.getSide());
                                                oo.setHealth(o.getHealth());
                                                has = true;
                                                //System.out.println(o.getName() +":"+ oo.getName() +" is");
                                            }else{
                                                //System.out.println(o.getName() +":"+ oo.getName() + " isn't");
                                            }
                                        }
                                        if (!has) {
                                            characters.add(new Hero(o.getName(), o.getX(), o.getY(), o.getHealth(), o.getSide()));
                                        }
                                    }
                                } else {
                                    if(chart != null) {
                                        characters =  new LinkedList<Hero>();
                                        for (Hero o : chart) {
                                            characters.add(new Hero(o.getName(), o.getX(), o.getY(), o.getHealth(), o.getSide()));
                                        }
                                    }
                                    //System.out.println(X + "-" + Y);
                                }
                            }
                        }
                    }
                //} catch (Exception e) {System.out.println("Change Error");}
            }
        }).start();

        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(authorizationResult.equals("Ok")) {
                    //System.out.println(ke.getCode());
                    if (ke.getCode() == KeyCode.W) outMessage.println("Forward");
                    if (ke.getCode() == KeyCode.A) outMessage.println("Left");
                    if (ke.getCode() == KeyCode.S) outMessage.println("Back");
                    if (ke.getCode() == KeyCode.D) outMessage.println("Right");
                    if (ke.getCode() == KeyCode.Q) outMessage.println("Hit");
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
