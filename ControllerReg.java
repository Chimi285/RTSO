import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ControllerReg{
    @FXML
    private GridPane Gr;
    @FXML
    private ImageView im1;
    @FXML
    private ImageView im2;
    @FXML
    private ImageView im3;
    @FXML
    private Label lb;
    @FXML
    private Canvas tf;
    @FXML
    private TextField TF1;
    private GraphicsContext gc;
    @FXML
    private ImageView lot1, lot2, lot3, lot4, lot5, lot6, lot7, lot8, lot9;
    @FXML
    private void mouseEnter(MouseEvent event) throws IOException{
        if(Main.inventoryMenu) {
            ImageView imgur = (ImageView) event.getSource();
            imgur.setImage(new Image("choisedNull.png"));
        }
    }
    @FXML
    private void mouseExit(MouseEvent event) throws IOException{
        if(Main.inventoryMenu) {
            ImageView imgur = (ImageView) event.getSource();
            imgur.setImage(new Image("null.png"));
        }
    }
    @FXML
        private void mouseClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) TF1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("sceneField.fxml"));
 	tf = (Canvas)root.getChildrenUnmodifiable().get(0);
        System.out.println(root.getChildrenUnmodifiable());
        lb = (Label)root.getChildrenUnmodifiable().get(1);
        im1 = (ImageView) root.getChildrenUnmodifiable().get(2);
        im2 = (ImageView) root.getChildrenUnmodifiable().get(3);
        im3 = (ImageView) root.getChildrenUnmodifiable().get(4);
        Gr = (GridPane) root.getChildrenUnmodifiable().get(5);
        System.out.println(Gr.getChildrenUnmodifiable());
        lot1 = (ImageView) Gr.getChildrenUnmodifiable().get(0);
        lot2 = (ImageView) Gr.getChildrenUnmodifiable().get(1);
        lot3 = (ImageView) Gr.getChildrenUnmodifiable().get(2);
        lot4 = (ImageView) Gr.getChildrenUnmodifiable().get(3);
        lot5 = (ImageView) Gr.getChildrenUnmodifiable().get(4);
        lot6 = (ImageView) Gr.getChildrenUnmodifiable().get(5);
        lot7 = (ImageView) Gr.getChildrenUnmodifiable().get(6);
        lot8 = (ImageView) Gr.getChildrenUnmodifiable().get(7);
        lot9 = (ImageView) Gr.getChildrenUnmodifiable().get(8);
        gc = tf.getGraphicsContext2D();
        try{
            if(!TF1.getText().replaceAll(" ", "").equals("")) {
                System.out.println("Authorizating...");
                Main.nickname = TF1.getText();
                Main.authorization(Main.nickname);
                boolean repeat = true;
                while (repeat) {
                    switch(Main.authorizationResult){
                        case "Ok":
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setMaximized(true);
                            stage.show();
                            repeat = false;
                            gc = tf.getGraphicsContext2D();

                            AnimationTimer timer = new AnimationTimer() {
                                @Override
                                public void handle(long now) {
                                    //System.out.println(tf.getLayoutX() + ":" + Main.X + ":" + tf.getWidth());
                                    //GraphicsContext gc = tf.getGraphicsContext2D();
                                    gc.setFill(Color.CORNSILK);
                                    gc.fillRect(0, 0, 2000, 2000);
                                    Hero player = null;
                                    if (Main.characters != null){
                                        for (Hero oo : Main.characters) {
                                            if (oo.getName() != null) {
                                                if (Main.nickname != null && oo.getName().equals(Main.nickname)) {
                                                    if (oo.getHealth() <= 0) Main.Alive = false;
                                                    else    Main.Alive = true;
                                                    lb.setText(String.valueOf(oo.getHealth()));
                                                    Main.X = oo.getX();
                                                    Main.Y = oo.getY();
                                                    tf.setLayoutX(-Main.X + stage.getWidth() / 2);
                                                    tf.setLayoutY(-Main.Y + stage.getHeight() / 2);
                                                    player = oo;
                                                }
                                                if (oo.getHealth() > 0) {
                                                    gc.setFill(Color.FORESTGREEN);
                                                    //System.out.print(oo.getName()+":"+oo.getX()+":"+oo.getY()+"/");
                                                    gc.fillOval(
                                                            oo.getX(),
                                                            oo.getY(),
                                                            100,
                                                            100
                                                    );
                                                    gc.setFill(Color.BLACK);
                                                    gc.setFont(new Font(20));
                                                    gc.fillText(oo.getName(), oo.getX() + 35, oo.getY() + 45);
                                                    gc.setFill(Color.RED);
                                                    gc.setFont(new Font(20));
                                                    gc.fillText(String.valueOf(oo.getHealth()), oo.getX() + 35, oo.getY() + 65);
                                                    gc.setFill(Color.BLACK);
                                                    gc.setFont(new Font(20));
                                                    gc.fillText(String.valueOf(oo.getSide()), oo.getX() + 35, oo.getY() + 85);
                                                }
                                            }
                                        }
                                        for (Build oo: Main.buildings){
                                            //System.out.println(oo.getTexture());
                                            gc.drawImage(new Image("choisedNull.png"), oo.getX(), oo.getY(), oo.getWidth(), oo.getHeight());
                                            gc.setFill(Color.RED);
                                            gc.fillOval(oo.getEnterX(), oo.getEnterY(), 10, 10);
                                            //System.out.println(oo.getTexture() + " - ok");
                                        }
                                        if(player != null) {
                                            gc.setFill(Color.RED);
                                            switch (player.getSide()){
                                                case 0:
                                                    gc.strokeRect(
                                                            player.getX(),
                                                            player.getY()-50,
                                                            100,
                                                            100
                                                    );
                                                    break;
                                                case 1:
                                                    gc.strokeRect(
                                                            player.getX()+50,
                                                            player.getY(),
                                                            100,
                                                            100
                                                    );
                                                    break;
                                                case 2:
                                                    gc.strokeRect(
                                                            player.getX(),
                                                            player.getY()+50,
                                                            100,
                                                            100
                                                    );
                                                    break;
                                                case 3:
                                                    gc.strokeRect(
                                                            player.getX()-50,
                                                            player.getY(),
                                                            100,
                                                            100
                                                    );
                                                    break;
                                            }
                                            if(player.getHealth() > 0) {
                                                gc.setFill(Color.FORESTGREEN);
                                                gc.fillOval(
                                                        player.getX(),
                                                        player.getY(),
                                                        100,
                                                        100
                                                );
                                                gc.setFill(Color.BLACK);
                                                gc.setFont(new Font(20));
                                                gc.fillText(player.getName(), player.getX() + 35, player.getY() + 45);
                                                gc.setFill(Color.RED);
                                                gc.setFont(new Font(20));
                                                gc.fillText(String.valueOf(player.getHealth()), player.getX() + 35, player.getY() + 65);
                                                gc.setFill(Color.BLACK);
                                                gc.setFont(new Font(20));
                                                gc.fillText(String.valueOf(player.getSide()), player.getX() + 35, player.getY() + 85);
                                                lb.setText(String.valueOf(player.getHealth()));
                                            }
                                                if(player.getInventory() [0] != null){
                                            }else{
                                                im1.setImage(new Image("null.png"));
                                            }
                                            if(player.getInventory() [1] != null){

                                            }else{
                                                im2.setImage(new Image("null.png"));
                                            }
                                            if(player.getInventory() [2] != null){

                                            }else{
                                                im3.setImage(new Image("null.png"));
                                            }
                                        }
                                }
                                    if(Main.inventoryMenu){
                                        if(Main.outInventory != null && Main.outInventory[0] != null){
                                            lot1.setImage(new Image(Main.outInventory[0].getTexture()));
                                        }else{
                                            lot1.setImage(new Image("null.png"));
                                        }
                                    }
                                }
                            };
                            timer.start();
                            //timeline.play();
                            break;

                        case "Error": System.out.println("Authorization Failed");
                            repeat = false;
                            break;
                    }
                }

            }else{System.out.println("Plese, fill the field");}
        }catch (Exception w){}
    }

}
