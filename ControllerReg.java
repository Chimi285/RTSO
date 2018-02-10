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
    public static boolean[] choisedOM = new boolean[9];
    public static boolean[] choisedIM = new boolean[3];
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
    private void mouseEnterOM(MouseEvent event) throws IOException{
        if(Main.inventoryMenu && Main.neo == -1) {
            ImageView imgur = (ImageView) event.getSource();
            choisedOM[Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(3)).toString()) - 1] = true;
        }
    }
    @FXML
    private void mouseExitOM(MouseEvent event) throws IOException{
        if(Main.inventoryMenu && Main.neo == -1) {
            ImageView imgur = (ImageView) event.getSource();
            choisedOM[Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(3)).toString()) - 1] = false;
        }
    }
    @FXML
    private void chooseOM(MouseEvent event){
        ImageView imgur = (ImageView) event.getSource();
        //System.out.println("choose");
        if(Main.inventoryMenu){
            switch (Main.neoN) {
                case -1:
                if (Main.outInventory[Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(3)).toString())-1] != null){
                    if (Main.neo != Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(3)).toString())-1){
                        Main.neoN = 1;
                        Main.neo = Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(3)).toString())-1;
                        //System.out.println(Main.neo);
                    }
                }
                break;
                case 1:
                    if (Main.neo == Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(3)).toString())-1){
                        Main.neo = -1;
                        Main.neoN = -1;
                        choisedIM = new boolean[3];
                    }
                    break;
                case 0:
                    Main.outMessage.println("movement0/" + Main.neo + "/" + (Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(3)).toString()) - 1));
                    Main.outMessage.flush();
                    Main.neo = -1;
                    Main.neoN = -1;
                    break;
            }
        }
    }

    @FXML
    private void chooseIM(MouseEvent event){
        ImageView imgur = (ImageView) event.getSource();
        //System.out.println("choose");
        if(Main.inventoryMenu){
            switch (Main.neoN) {
                case -1:
                    if (Main.outInventory[Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(2)).toString())-1] != null){
                        if (Main.neo != Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(2)).toString())-1) {
                            Main.neoN = 0;
                            Main.neo = Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(2)).toString())-1;
                            //System.out.println(Main.neo);
                        }
                    }
                    break;
                case 1:
                    Main.outMessage.println("movement1/" + Main.neo + "/" + (Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(2)).toString())-1));
                    Main.outMessage.flush();
                    Main.neo = -1;
                    Main.neoN = -1;
                    choisedOM = new boolean[9];
                    break;
                case 0:
                    if (Main.neo == Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(2)).toString())-1){
                        Main.neo = -1;
                        Main.neoN = -1;
                    }
                    break;
            }
        }
    }

    @FXML
    private void mouseEnterIM(MouseEvent event) throws IOException{
        if(Main.inventoryMenu && Main.neo == -1) {
            ImageView imgur = (ImageView) event.getSource();
            choisedIM[Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(2)).toString()) - 1] = true;
        }
    }

    @FXML
    private void mouseExitIM(MouseEvent event) throws IOException{
        if(Main.inventoryMenu && Main.neo == -1) {
            ImageView imgur = (ImageView) event.getSource();
            choisedIM[Integer.parseInt(new StringBuilder().append(imgur.getId().charAt(2)).toString()) - 1] = false;
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
                                            gc.drawImage(new Image(oo.getTexture()), oo.getX(), oo.getY(), oo.getWidth(), oo.getHeight());
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


                                            if(Main.inventory != null && Main.inventory[0] != null){
                                                if(choisedIM[0] == false) im1.setImage(new Image(Main.inventory[0].getTexture()));
                                                else im1.setImage(new Image(Main.inventory[0].getChoisedTexture()));
                                            }else{
                                                if(choisedIM[0] == false) im1.setImage(new Image("null.png"));
                                                else im1.setImage(new Image("choisedNull.png"));
                                            }

                                            if(Main.inventory != null && Main.inventory[1] != null){
                                                if(choisedIM[1] == false) im2.setImage(new Image(Main.inventory[1].getTexture()));
                                                else im2.setImage(new Image(Main.inventory[1].getChoisedTexture()));
                                            }else{
                                                if(choisedIM[1] == false) im2.setImage(new Image("null.png"));
                                                else im2.setImage(new Image("choisedNull.png"));
                                            }

                                            if(Main.inventory != null && Main.inventory[2] != null){
                                                if(choisedIM[2] == false) im3.setImage(new Image(Main.inventory[2].getTexture()));
                                                else im3.setImage(new Image(Main.inventory[2].getChoisedTexture()));
                                            }else{
                                                if(choisedIM[2] == false) im3.setImage(new Image("null.png"));
                                                else im3.setImage(new Image("choisedNull.png"));
                                            }

                                        }
                                }
                                    if(Main.inventoryMenu){
                                        if(Main.outInventory != null && Main.outInventory[0] != null){
                                            if(choisedOM[0] == false) lot1.setImage(new Image(Main.outInventory[0].getTexture()));
                                            else lot1.setImage(new Image(Main.outInventory[0].getChoisedTexture()));
                                        }else{
                                            if(choisedOM[0] == false) lot1.setImage(new Image("null.png"));
                                            else lot1.setImage(new Image("choisedNull.png"));
                                        }


                                        if(Main.outInventory != null && Main.outInventory[1] != null){
                                            if(choisedOM[1] == false) lot2.setImage(new Image(Main.outInventory[1].getTexture()));
                                            else lot2.setImage(new Image(Main.outInventory[1].getChoisedTexture()));
                                        }else{
                                            if(choisedOM[1] == false) lot2.setImage(new Image("null.png"));
                                            else lot2.setImage(new Image("choisedNull.png"));
                                        }


                                        if(Main.outInventory != null && Main.outInventory[2] != null){
                                            if(choisedOM[2] == false) lot3.setImage(new Image(Main.outInventory[2].getTexture()));
                                            else lot3.setImage(new Image(Main.outInventory[2].getChoisedTexture()));
                                        }else{
                                            if(choisedOM[2] == false) lot3.setImage(new Image("null.png"));
                                            else lot3.setImage(new Image("choisedNull.png"));
                                        }


                                        if(Main.outInventory != null && Main.outInventory[3] != null){
                                            if(choisedOM[3] == false) lot4.setImage(new Image(Main.outInventory[3].getTexture()));
                                            else lot4.setImage(new Image(Main.outInventory[3].getChoisedTexture()));
                                        }else{
                                            if(choisedOM[3] == false) lot4.setImage(new Image("null.png"));
                                            else lot4.setImage(new Image("choisedNull.png"));
                                        }


                                        if(Main.outInventory != null && Main.outInventory[4] != null){
                                            if(choisedOM[4] == false) lot5.setImage(new Image(Main.outInventory[4].getTexture()));
                                            else lot5.setImage(new Image(Main.outInventory[4].getChoisedTexture()));
                                        }else{
                                            if(choisedOM[4] == false) lot5.setImage(new Image("null.png"));
                                            else lot5.setImage(new Image("choisedNull.png"));
                                        }


                                        if(Main.outInventory != null && Main.outInventory[5] != null){
                                            if(choisedOM[5] == false) lot6.setImage(new Image(Main.outInventory[5].getTexture()));
                                            else lot6.setImage(new Image(Main.outInventory[5].getChoisedTexture()));
                                        }else{
                                            if(choisedOM[5] == false) lot6.setImage(new Image("null.png"));
                                            else lot6.setImage(new Image("choisedNull.png"));
                                        }


                                        if(Main.outInventory != null && Main.outInventory[6] != null){
                                            if(choisedOM[6] == false) lot7.setImage(new Image(Main.outInventory[6].getTexture()));
                                            else lot7.setImage(new Image(Main.outInventory[6].getChoisedTexture()));
                                        }else{
                                            if(choisedOM[6] == false) lot7.setImage(new Image("null.png"));
                                            else lot7.setImage(new Image("choisedNull.png"));
                                        }


                                        if(Main.outInventory != null && Main.outInventory[7] != null){
                                            if(choisedOM[7] == false) lot8.setImage(new Image(Main.outInventory[7].getTexture()));
                                            else lot8.setImage(new Image(Main.outInventory[7].getChoisedTexture()));
                                        }else{
                                            if(choisedOM[7] == false) lot8.setImage(new Image("null.png"));
                                            else lot8.setImage(new Image("choisedNull.png"));
                                        }


                                        if(Main.outInventory != null && Main.outInventory[8] != null){
                                            if(choisedOM[8] == false) lot9.setImage(new Image(Main.outInventory[8].getTexture()));
                                            else lot9.setImage(new Image(Main.outInventory[8].getChoisedTexture()));
                                        }else{
                                            if(choisedOM[8] == false) lot9.setImage(new Image("null.png"));
                                            else lot9.setImage(new Image("choisedNull.png"));
                                        }
                                    }else if(lot1.getImage() != null){
                                        lot1.setImage(null);
                                        lot2.setImage(null);
                                        lot3.setImage(null);
                                        lot4.setImage(null);
                                        lot5.setImage(null);
                                        lot6.setImage(null);
                                        lot7.setImage(null);
                                        lot8.setImage(null);
                                        lot9.setImage(null);

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
