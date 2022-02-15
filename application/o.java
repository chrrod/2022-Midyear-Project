package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form {
    //tiny recs that make up big piece
    Rectangle a; 
    Rectangle b; 
    Rectangle c;
    Rectangle d;
    Color color;
    private String name;
    public int form = 1;

    public Form (Rectangle a, Rectangle b, Rectangle c, Rectangle d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Form (Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;
    
    if(name.equals("j")){
        color = Color.BLUE;
    }
    else if(name.equals("l")){
        color = Color.ORANGE;
    }
    else if(name.equals("o")){
        color = Color.YELLOW;
    }
    else if (name.equals("z")){
        color = Color.RED;
    }
    else if (name.equals("s")){
        color = Color.GREEN;
    }
    else if (name.equals("s")){
        color = Color.LIGHTBLUE;
    }
    //      color = Color.LIGHTCYAN;
    else if (name.equals("t")){
        color = Color.PURPLE;
    }
    //     case "t":
    //         color = Color.PURPLE;
    //         break;
    // }

    this.a.setFill(color);
    this.b.setFill(color);
    this.c.setFill(color);
    this.d.setFill(color);}
    

    public String getName(){
        return name;
    }

    public void changeForm(){
        if(form !=4){
            form++;
        }
        else{
            form = 1;
        }      
}
}