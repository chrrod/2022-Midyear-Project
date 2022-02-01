import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class pieceControl extends Application {
    public boolean isPaused;

    public void start(Stage primaryStage) {
        PiecePane piecePane = new PiecePane();

        // Load the pause image
        Image image = new Image("Spreadsheet.png");
        ImageView imageView = new ImageView(image);

        // Pause and resume animation
        piecePane.setOnMousePressed(e -> piecePane.pause());
        piecePane.setOnMouseReleased(e -> piecePane.play());
        // Increase and decrease animation
        piecePane.setOnKeyPressed(e -> {
            {
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
                if (e.getCode() == KeyCode.RIGHT) {
                    piecePane.right();
                }
                if (e.getCode() == KeyCode.LEFT) {
                    piecePane.left();
                }
                if (e.getCode() == KeyCode.UP) {
                    piecePane.up();
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
        // Create a scene and place it in the stage
        Scene scene = new Scene(piecePane, 250, 500);
        primaryStage.setTitle("PieceMovement"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        // Must request focus after the primary stage is displayed
        piecePane.requestFocus();
    }
}