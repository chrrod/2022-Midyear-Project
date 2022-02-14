import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;

public class tetrisPieces1 extends Application{
    Rectangle[] holdingTogetherPieces = new Rectangle[4];
    @Override
    public void start(Stage primaryStage){
        // TODO Auto-generated method stub
        Rectangle vertical1  = new Rectangle();
        vertical1.setWidth(100);
        vertical1.setHeight(1);
        vertical1.setX(200);
        vertical1.setY(1);

        Rectangle vertical2  = new Rectangle();
        vertical1.setWidth(100);
        vertical1.setHeight(1);
        vertical1.setX(201);
        vertical1.setY(1);
        

        Group root = new Group();   
        root.getChildren().add(vertical1);
        
        holdingTogetherPieces[0] = vertical1;
    
        
    }
    
}
