import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

public class pieceControl extends Application {
    public boolean isPaused;
    private boolean down = false;

    public void start(Stage primaryStage) throws Exception{
        
        PiecePane piecePane = new PiecePane();

        // Media test = new Media(getClass().getResource("./tetris.mp4").toExternalForm());

        // MediaPlayer mediaPlayer = new MediaPlayer(test);
        // mediaPlayer.play();

        // String path = "";
        // Media meda = new Media("tetris.mp4");
        // File f = new File("C:/Users/Ckr/Desktop/midyearProject-Tetris/2022-Midyear-Project/tetris.mp4");
        // Media media = new Media(f.toURI().toURL().toString());
        // MediaPlayer mediaPlayer = new MediaPlayer(media);
        // mediaPlayer.setAutoPlay(true); 
        // mediaPlayer.play();

        // String mFile = "tetris.mp4";
        // Media sound = new Media(new File(mFile).toURI().toString());
        // MediaPlayer mediaPlayer = new MediaPlayer(sound);
        // mediaPlayer.play();

        //Media song = new Media("/2022-Midyear-Project/tetris_music.mp4"); //replace /Movies/test.mp3 with your file
      // MediaPlayer toPlay = new MediaPlayer(song); 
       //toPlay.play();

        // Boss key from Solitare
        // Load the pause game
        Image image = new Image("VsCode.png");
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(false);
        // Dimensions for board is ====> Scene scene = new Scene(piecePane, 692, 510);

        // Pause and resume animation
        piecePane.setOnMousePressed(e -> piecePane.pause());
        piecePane.setOnMouseReleased(e -> piecePane.play());
        // Increase and decrease animation
        piecePane.setOnKeyPressed(e -> {
            {
                if(!piecePane.gameDone){
                    if (down) {
                        piecePane.down();
                    }
                }
                
                // if (e.getCode() == KeyCode.UP) {
                // piecePane.increaseSpeed();
                // }
                // else if (e.getCode() == KeyCode.DOWN) {
                // piecePane.decreaseSpeed();
                // }
                // if (e.getCode() == KeyCode.DOWN) {
                // piecePane.increaseSpeed();
                // }else{
                // piecePane.reset();
                // }
            }
            if (!isPaused) {
                if(!piecePane.gameDone){
                    if (e.getCode() == KeyCode.RIGHT) {
                        piecePane.right();
                    }
                    if (e.getCode() == KeyCode.LEFT) {
                        piecePane.left();
                    }
                    if (e.getCode() == KeyCode.UP) {
                        piecePane.rotate();
                    }

                    if (e.getCode() == KeyCode.SPACE) {
                        piecePane.space();
                    }
                    if (e.getCode() == KeyCode.DOWN) {
                        down = true;
                        piecePane.down();
                    }    
                }
                
            }

            if (e.getCode() == KeyCode.ESCAPE) {
                isPaused = !isPaused;

                if (isPaused) {
                    piecePane.pause();
                    imageView.setImage(image);
                } else {
                    piecePane.play();
                    imageView.setImage(null);
                }
            }

        });
        piecePane.setOnKeyReleased(e -> {
            {

            }
            if(!piecePane.gameDone){
                if (e.getCode() == KeyCode.DOWN) {
                    down = false;
                    piecePane.reset();
                }    
            }
            
        });

        System.out.println(down);
        // Create a scene and place it in the stage
        Scene scene = new Scene(piecePane, 692, 510);// was 250 by 500
        primaryStage.setTitle("PieceMovement"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        // Must request focus after the primary stage is displayed
        piecePane.requestFocus();

        BackgroundImage myBI = new BackgroundImage(new Image("tetrisBoardBlank.png", 692, 510, false, true), // was 705
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        // then you set to your node
        piecePane.setBackground(new Background(myBI));
    }

    public static void main(String[] args) {
        launch(args);
    }

}