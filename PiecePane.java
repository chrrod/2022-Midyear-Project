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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import javafx.scene.shape.*;

public class PiecePane extends Pane {
    public final double radius = 20;
    boolean firstMove = true;
    private double x = radius, y = radius;
    private double dx = 1, dy = 1;
    private ArrayList<Rectangle[]> rArray;
    // private Rectangle r1 = new Rectangle(25, 25, 50, 50);
    private Rectangle[] r1;
    private Timeline animation;
    private double speed;
    private int pos = 0;
    public mainCheck blocks = new mainCheck();
    int waitTime = 0;
    Group root = new Group();// try ignoring for now
    private Image spreadsheetImage;
    private ImageView imageView;
    int rotateCounter = 0;

    public PiecePane() {
        spreadsheetImage = new Image("VsCode.png");
        imageView = new ImageView();
        imageView.setImage(spreadsheetImage);
        rArray = new ArrayList<>();
        // r1.setFill(Color.GREEN); // Set ball color
        //// getChildren().add(r1); // Place a ball into this pane

        // r1 = blocks.random();
        // rArray.add(r1);
        // getChildren().addAll(rArray.get(rArray.size() - 1));
        end();
        firstPos();
        // Create an animation for moving the ball
        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> move()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
        speed = animation.getRate()*5;//was *1
        animation.setRate(speed);
        System.out.println(speed);
    }

    public void play() {
        animation.play();
        getChildren().remove(imageView);
    }

    public void pause() {
        animation.pause();
        getChildren().add(imageView);
    }

    public void down() {
        animation.setRate(speed*5);
    }

    public void reset() {
        animation.setRate(speed);
    }

    private void checkBounds(Rectangle[] r) {
        boolean collisionDetected = false;

        for(int i = 0; i< rArray.size()-1; i++){
            for(int j = 0; j < 4; j++){
                for(int l = 0; l<4; l++){
                    if(r[j].getY() <= rArray.get(i)[l].getY()&&r[j].getY()>=rArray.get(i)[l].getY()-20&&r[j].getX()<=rArray.get(i)[l].getX()&&r[j].getX()>=rArray.get(i)[l].getX()){// -20 changed to -21 and +-10 changed to 5//then changed to 0
                        collisionDetected = true;
                    }
                }
            }
        }

        if (collisionDetected) {
            // r.setFill(Color.BLUE);
            end();
        } else {
            // r.setFill(Color.GREEN);
        }
    }

    public void decreaseSpeed() {
        animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public void left(){
        if(waitTime <=0){
            dx = -20;   
            waitTime = 0;//was 5 
        }        
    }

    public void right(){
        if(waitTime <= 0){
            dx = 20;    
            waitTime = 0;//was 5
        }
    }
    
    public void space(){
        double maxHeight = getHeight();
        double heightDifference = 100000;
        int pl = 0;
        for(int i = 0; i<4; i++){
            // if(rArray.get(rArray.size()-1)[i].getY()<heightDifference){
            //     heightDifference = rArray.get(rArray.size()-1)[i].getY();
            //     if(rArray.get(rArray.size()-1)[i].getY()<rArray.get(rArray.size()-1)[pl].getY()){
            //         pl = i;
            //     }
            // }
            if(heightDifference>getHeight()-rArray.get(rArray.size()-1)[i].getY()){
                heightDifference = getHeight()-rArray.get(rArray.size()-1)[i].getY();
            }
        }
        heightDifference-=25;//new
        // for(Rectangle rect : rArray){
        // if(rect!=rArray.get(rArray.size()-1)){
        // if(rect.getX() == rArray.get(rArray.size()-1).getX()){
        // maxHeight = rect.getY();
        // }
        // }
        // }
        // rArray.get(rArray.size()-1).setY(maxHeight-50);
        for(Rectangle[] rect: rArray){
            if(!(rect.equals(rArray.get(rArray.size()-1)))){
                for(Rectangle r : rect){
                    for(Rectangle l : rArray.get(rArray.size()-1)){
                        if(r.getX() == l.getX()){
                            if(r.getY()-l.getY()< heightDifference){
                                heightDifference = r.getY() - l.getY();
                            }
                        }
                    }
                }
            }
        }
        for(Rectangle r : rArray.get(rArray.size()-1)){
            r.setY(r.getY()+heightDifference-20);//was -20
        }
        end();
    }

    

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    public void end() {
        dx = 0;
        // dy = 0;
        // Rectangle copy = new Rectangle(25, 25, 50, 50);
        // copy.setFill(Color.GREEN); // Set ball color
        // getChildren().add(r1);
        //trying something new

        rArray.add(blocks.random());
        pos++;
        getChildren().addAll(rArray.get(rArray.size() - 1));
        firstPos();

        waitTime = 0;
    }

    public void firstPos() {
        for(Rectangle r : rArray.get(rArray.size()-1)){
            if(!(r.equals(rArray.get(rArray.size()-1)[0]))){
                r.setX(getWidth()/2/*-20*/+(r.getX()-rArray.get(rArray.size()-1)[0].getX()));
                r.setY(84+(r.getY()-rArray.get(rArray.size()-1)[0].getY()));
            }
        }
        rArray.get(rArray.size()-1)[0].setX(getWidth()/2/*-20*/);
        rArray.get(rArray.size()-1)[0].setY(84);//was 38
    }

    protected void move() {
        if(firstMove){
            firstPos();
            firstMove = false;
        }
        checkBounds(rArray.get(rArray.size() - 1));
        // Check boundaries
        boolean needToCheck = true;
        for(Rectangle r : rArray.get(rArray.size()-1)){
            if(r.getY()>=getHeight()-46&&needToCheck){//changed -20 to -21//changed from -21 to -46
                space();//end();
                needToCheck = false;
            }
            // r.setX(r.getX() + dx);
            // r.setY(r.getY() + 3);
        }
        if(needToCheck){
            for(Rectangle r : rArray.get(rArray.size()-1)){
                r.setX(r.getX()+dx);
                r.setY(r.getY()+1);
            }
        }

        // if (y > getHeight() - 25) {
        // //dy *= 0; // Change ball move direction
        // end();
        // }
        // Adjust ball position
        // x += dx;
        // y += dy;
        // if (x < -0 || x > getWidth() - 50) {
        // x -=dx; // Change ball move direction
        // }

        // for(Rectangle r : rArray){// checks to see if trying to move into another
        // object
        // if (r.getX()>=x-49&&r.getX()<=x+49&&r!=rArray.get(rArray.size()-1)) {
        // if (r.getY()>=y-49&&r.getY()<=y+49) {
        // x -=dx; // Change ball move direction
        // }
        // }
        // }
        boolean changePos = false;
        for(Rectangle[] r: rArray){
            if(!(r.equals(rArray.get(rArray.size()-1)))){
                for(int i = 0; i<4; i++){
                    for(int j = 0; j<4; j++){
                        if(r[j].getX()/*<*/==rArray.get(rArray.size()-1)[i].getX()/*-0&&r[j].getX()<=rArray.get(rArray.size()-1)[i].getX()+0*/){//changed from 19 to 0
                            if(r[j].getY()>=rArray.get(rArray.size()-1)[i].getY()-10/*-19*/&&r[j].getY()<=rArray.get(rArray.size()-1)[i].getY()+19){////changed from 19 to 18
                                changePos = true;
                            }
                        }
                    }
                }
            }
        }
        for(Rectangle r : rArray.get(rArray.size()-1)){
            if(r.getX()>getWidth()/2+100|| r.getX()<getWidth()/2-80){
                changePos = true;
            }
        }
        if (changePos) {
            for (Rectangle r : rArray.get(rArray.size() - 1)) {
                r.setX(r.getX() - dx);
            }
        }
        dx = 0;
        dy = 0;
        // rArray.get(rArray.size()-1).setX(x);
        // rArray.get(rArray.size()-1).setY(y);
        waitTime -= 1;
    }

    public void rotation(){ 
        if(blocks.ifVertical == true){
            if(rotateCounter ==0){
                r1[0].setX(r1[0].getX()+(r1[2].getY()-r1[0].getY()));
                //100 + (10-10)= 100; x=100
                r1[1].setX(r1[0].getX()+(r1[2].getY()-r1[0].getY()));
                r1[2].setX(r1[0].getX()+(r1[2].getY()-r1[0].getY()));
                r1[3].setX(r1[0].getX()+(r1[2].getY()-r1[0].getY()));
                r1[1].setY(r1[0].getY()+(20));
                r1[2].setY(r1[1].getY()+(20));
                r1[3].setY(r1[1].getY()+(20));
                rotateCounter = 1;

            }
            else if(rotateCounter ==1){
                r1[1].setX(r1[0].getX()+(20));
                r1[2].setX(r1[1].getX()+(20));
                r1[3].setX(r1[2].getX()+(20));
                r1[1].setY(r1[0].getY());
                r1[2].setY(r1[0].getY());
                r1[3].setY(r1[0].getY());

            }
        }
        else if(blocks.ifSquare == true){
            
        }
        else if(blocks.iflBlock == true){
            if(rotateCounter ==0){
                r1[0].setX(r1[0].getX()+(r1[2].getY()-r1[0].getY()));
                //120+(50-10)= 160
                //r1[0].setX(r1[0].getX()+20);
                //r1[1].setY(r1[0].getY()+10);
                //r1[2].setX(r1[0].getX()+20);
                //r1[3].setY(r1[0].getY()+10);
                //rotateCounter = 1;
            }
            if(rotateCounter ==1){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 2;
            }
            if(rotateCounter ==2){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 3;
            }
            if(rotateCounter ==3){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 0;
            }
        }
        else if(blocks.ifsBlock == true){
            if(rotateCounter ==0){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 1;
            }
            if(rotateCounter ==1){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 2;
            }
            if(rotateCounter ==2){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 3;
            }
            if(rotateCounter ==3){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 0;
            }
        }
        else if(blocks.ifoBlock == true){
            if(rotateCounter ==0){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 1;
            }
            if(rotateCounter ==1){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 2;
            }
            if(rotateCounter ==2){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 3;
            }
            if(rotateCounter ==3){
                r1[0].setX(r1[0].getX()+20);
                r1[1].setY(r1[0].getY()+10);
                r1[2].setX(r1[0].getX()+20);
                r1[3].setY(r1[0].getY()+10);
                rotateCounter = 0;
            }
        }
    }

}