package application;

import javafx.scene.shape.*;

public class Controls {

    public static final int Move = tetris.MOVE;
    public static final int Size = tetris.SIZE;
    public static int XMAX = tetris.XMAX;
    public static int YMAX = tetris.YMAX;
    public static int [][] MESH = tetris.MESH;

    public static int XBounds; // 

    public static void MoveRight(Form form){
        XBounds = ((XMAX - Size) - Move);
        if(form.a.getX() <= XBounds && form.b.getX() <= XBounds && 
            form.c.getX() <= XBounds && form.d.getX() <= XBounds){
                int movea = MESH[form.a.getX(0)][];
        }
    }
    public static void MoveLeft(Form form){
        if(form.a.getX() - Move <= 0 && form.b.getX()- Move <= 0 && 
            form.c.getX()- Move <= 0 && form.d.getX()- Move <= 0){

                
        }
    }



    
}
