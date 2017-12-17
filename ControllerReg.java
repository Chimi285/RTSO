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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ControllerReg{
    @FXML
    private Label lb;
    @FXML
    private Canvas tf;
    @FXML
    private TextField TF1;
    private GraphicsContext gc;
    @FXML
    //comment fo git
        private void mouseClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) TF1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("sceneField.fxml"));
 	tf = (Canvas)root.getChildrenUnmodifiable().get(0);// �������� ������ �� ������
//        lb = (Label)root.getChildrenUnmodifiable().get(0);
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

                          /*  DoubleProperty x  = new SimpleDoubleProperty();
                            DoubleProperty y  = new SimpleDoubleProperty();
                            int xz = 50;
                            int yz = 50;
                            Timeline timeline = new Timeline(
                                    new KeyFrame(Duration.seconds(0),
                                            new KeyValue(x, Main.X),
                                            new KeyValue(y, Main.Y)
                                    )
                            );
                            timeline.setAutoReverse(true);
                            timeline.setCycleCount(Timeline.INDEFINITE);*/
                            AnimationTimer timer = new AnimationTimer() {
                                @Override
                                public void handle(long now) {
                                    tf.setLayoutX(-Main.X + stage.getWidth() / 2);
                                    tf.setLayoutY(-Main.Y + stage.getHeight() / 2);
                                    //System.out.println(tf.getLayoutX() + ":" + Main.X + ":" + tf.getWidth());
                                    GraphicsContext gc = tf.getGraphicsContext2D();
                                    gc.setFill(Color.CORNSILK);
                                    gc.fillRect(0, 0, 2000, 2000);
                                    if (Main.characters != null){
                                        for (Hero oo : Main.characters) {
                                           /* if(Main.nickname != null && oo.getName().equals(Main.nickname)){
                                                lb.setText(String.valueOf(oo.getHealth()));
                                            }*/
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
                                    //System.out.println();
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
