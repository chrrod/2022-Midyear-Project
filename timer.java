import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class timer {
    private static final Integer STARTTIME = 15;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private Integer timeSeconds = STARTTIME;
 
    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) {
 
        primaryStage.setTitle("Timer");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
 

        timerLabel.setText(timeSeconds.toString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");
        Button button = new Button();
        button.setText("Start Timer");
        button.setOnAction(new EventHandler() {
    }
 

}
