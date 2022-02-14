import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage; 
import javafx.scene.shape.Rectangle;

public class RectangleChecker extends Application { 
   public Rectangle[] pieces = new Rectangle[4]; 
   @Override 
   public void start(Stage stage) { 
      //vertical piece 1 
       Rectangle vertical1 = new Rectangle();  
      
      //Setting the properties of the rectangle 
      
      vertical1.setWidth(20);
      vertical1.setHeight(20);
      vertical1.setX(300);
      vertical1.setY(100);  
      vertical1.setStroke(Color.BLUE);
      vertical1.setFill(Color.BLUE); 

      pieces[0] = vertical1;
      Rectangle vertical2 = new Rectangle();  
      
      vertical2.setWidth(20);
      vertical2.setHeight(20);
      vertical2.setX(320);
      vertical2.setY(100);  
      vertical2.setStroke(Color.BLUE);
      vertical2.setFill(Color.BLUE);
      
      pieces[1] = vertical2;
      Rectangle vertical3 = new Rectangle();  
      
      vertical3.setWidth(20);
      vertical3.setHeight(20);
      vertical3.setX(340);
      vertical3.setY(100);  
      vertical3.setStroke(Color.BLUE);
      vertical3.setFill(Color.BLUE); 
      pieces[2] = vertical3;

      
      Rectangle vertical4 = new Rectangle();  
      
      vertical4.setWidth(20);
      vertical4.setHeight(20);
      vertical4.setX(360);
      vertical4.setY(100);  
      vertical4.setStroke(Color.BLUE);
      vertical4.setFill(Color.BLUE); 
      pieces[3] = vertical4;





         
        
      //Group root = new Group(vertical1); 

      //Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      //stage.setTitle("Drawing a Rectangle"); 
         
      //Adding scene to the stage 
      //stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      //stage.show(); 
   }      
   //public static void main(String args[]){ 
      //launch(args); 
  // } 
} 
