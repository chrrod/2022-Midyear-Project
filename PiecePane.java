import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.scene.shape.*;

public class PiecePane extends Pane {
    public final double radius = 20;
    private double x = radius, y = radius;
    private double dx = 1, dy = 1;
    private ArrayList <Rectangle> rArray;
    private Rectangle r1 = new Rectangle(x, y, 50, 50);
    private Timeline animation;
    private double speed;
    private int pos = 0;
    Group root = new Group();//try ignoring for now

    public PiecePane() {
        rArray = new ArrayList<>();
        r1.setFill(Color.GREEN); // Set ball color
        //getChildren().add(r1); // Place a ball into this pane
        rArray.add(r1);
        getChildren().addAll(rArray);
        // Create an animation for moving the ball
        animation = new Timeline(
            new KeyFrame(Duration.millis(50), e -> move()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
        speed = animation.getRate();
        System.out.println(speed);
    }

    public void play() {
        animation.play();
    }

    public void pause() {
        animation.pause();
    }

    public void increaseSpeed() {//not ready to be implemented yet, need to add checker for multple key press.
        animation.setRate(speed + 15);
    }

    public void reset(){
        animation.setRate(speed);
    }

    private void checkBounds(Rectangle r) {
        boolean collisionDetected = false;
        for (Rectangle rect : rArray) {
          if (rect != r) {
            rect.setFill(Color.GREEN);
      
            if (r.getBoundsInParent().intersects(rect.getBoundsInParent())&&r.getY()==rect.getY()-50/*&&r.getX()==rect.getX()*/) {
              collisionDetected = true;
            }
          }
        }
      
        if (collisionDetected) {
          r.setFill(Color.BLUE);
          end();
        } else {
          r.setFill(Color.GREEN);
        }
    }

    public void decreaseSpeed() {
        animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public void left(){
        dx = -3;
    }

    public void right(){
        dx = 3;
    }

    public void up(){
        double maxHeight = getHeight();
        for(Rectangle rect : rArray){
            if(rect!=rArray.get(rArray.size()-1)){
                if(rect.getX() == rArray.get(rArray.size()-1).getX()){
                    maxHeight = rect.getY();
                }
            }
        }
        rArray.get(rArray.size()-1).setY(maxHeight-50);
        end();
    }

    public void stop(){
        dx = 0;
        dy = 0;
        Rectangle copy = new Rectangle(x, y, 50, 50);
        copy.setFill(Color.GREEN); // Set ball color
        //getChildren().add(r1);
        rArray.add(copy);
        pos++;
        getChildren().add(rArray.get(rArray.size()-1));
        x = rArray.get(rArray.size()-1).getX();
        y = rArray.get(rArray.size()-1).getX();
        System.out.println(pos+" " + rArray.size());
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    public void end(){
        dx = 0;
        //dy = 0;
        Rectangle copy = new Rectangle(x, y, 50, 50);
        copy.setFill(Color.GREEN); // Set ball color
        //getChildren().add(r1);
        rArray.add(copy);
        pos++;
        getChildren().add(rArray.get(rArray.size()-1));
        x = rArray.get(rArray.size()-1).getX();
        y = rArray.get(rArray.size()-1).getX();
    }

    protected void move() {
        checkBounds(rArray.get(rArray.size()-1));
        // Check boundaries
        
        if (y > getHeight() - 50) {
             //dy *= 0; // Change ball move direction
             end();
         }
        // Adjust ball position
        x += dx;
        y += dy;
        if (x < 0 || x > getWidth() - 50) {
            x -=dx; // Change ball move direction
        }
        dx = 0;
        rArray.get(rArray.size()-1).setX(x);
        rArray.get(rArray.size()-1).setY(y);
    }
}