package application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.css.Size;
//import javafx.application.Platfrom;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.stage.*;
public class tetris extends Application{
   
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static int XMAX = SIZE * 12;
    public static int YMAX = SIZE * 24;
    //divides board based on size of the individual rectangle
    // divides board into the grid like structure
    public static int [][] MESH = new int [XMAX/SIZE][YMAX/SIZE];

    private static Pane groupe = new Pane();
    private static Form object;  
    private static Scene scene = new Scene(groupe, XMAX + 150, YMAX);
    //score count
    public static int score = 0;
    private static boolean game = true;
    private static Form nextObj = Controls.makeRect();
    //line count
    private static int linesNo = 0;

    public static void main(String[] args) throws Exception{
        
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        
    }


}
