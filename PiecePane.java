import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

public class PiecePane extends Pane {
    public final double radius = 20;
    private double x = radius, y = radius;
    private double dx = 1, dy = 1;
    private Rectangle r1 = new Rectangle(x, y, 50, 50);
    private Timeline animation;

    public PiecePane() {
        r1.setFill(Color.GREEN); // Set ball color
        getChildren().add(r1); // Place a ball into this pane
        
        // Create an animation for moving the ball
        animation = new Timeline(
            new KeyFrame(Duration.millis(50), e -> move()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
    }

    public void play() {
        animation.play();
    }

    public void pause() {
        animation.pause();
    }

    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
        animation.setRate(
            animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public void left(){
        dx = -1;
    }

    public void right(){
        dx = 1;
    }

    public void stop(){
        dx = 0;
        dy = 0;
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    protected void move() {
        // Check boundaries
        // if (x < radius || x > getWidth() - radius) {
        //     dx *= -1; // Change ball move direction
        // }
        if (y > getHeight() - 50) {
             dy *= 0; // Change ball move direction
         }
        // Adjust ball position
        x += dx;
        y += dy;
        dx = 0;
        r1.setX(x);
        r1.setY(y);
    }

  
}