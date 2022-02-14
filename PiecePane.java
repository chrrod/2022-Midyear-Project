import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
    int rotateCounterJ = 0;
    int rotateCounterL  =0;
    int rotateCounterS  =0;
    int rotateCounterT  =0;
    int rotateCounterZ  =0;
    Rectangle[] next = blocks.random();
    private ArrayList<Rectangle[]> nexts = new ArrayList<>();
    //private Color col = new Color();

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
        // System.out.println(speed);
        Rectangle[] tempV = blocks.vertical();
        Rectangle[] tempT = blocks.tBlock();
        Rectangle[] tempO = blocks.oBlock();
        Rectangle[] tempS = blocks.sBlock();
        Rectangle[] tempZ = blocks.zBlock();
        Rectangle[] tempL = blocks.lBlock();
        Rectangle[] tempJ = blocks.jBlock();

        for(Rectangle r : tempT){
            if(!(r.equals(tempT[1]))){
                r.setX(100+(r.getX()-tempT[1].getX()));
                r.setY(180+(r.getY()-tempT[1].getY()));
            }
        }
        tempT[1].setX(100);
        tempT[1].setY(180);

        for(Rectangle r : tempJ){
            if(!(r.equals(tempJ[1]))){
                r.setX(100+(r.getX()-tempJ[1].getX()));
                r.setY(225+(r.getY()-tempJ[1].getY()));
            }
        }
        tempJ[1].setX(100);
        tempJ[1].setY(225);

        for(Rectangle r : tempZ){
            if(!(r.equals(tempZ[1]))){
                r.setX(100+(r.getX()-tempZ[1].getX()));
                r.setY(270+(r.getY()-tempZ[1].getY()));
            }
        }
        tempZ[1].setX(100);
        tempZ[1].setY(270);

        for(Rectangle r : tempO){
            if(!(r.equals(tempO[1]))){
                r.setX(100+(r.getX()-tempO[1].getX()));
                r.setY(315+(r.getY()-tempO[1].getY()));
            }
        }
        tempO[1].setX(100);
        tempO[1].setY(315);

        for(Rectangle r : tempS){
            if(!(r.equals(tempS[1]))){
                r.setX(100+(r.getX()-tempS[1].getX()));
                r.setY(360+(r.getY()-tempS[1].getY()));
            }
        }
        tempS[1].setX(100);
        tempS[1].setY(360);

        for(Rectangle r : tempL){
            if(!(r.equals(tempL[1]))){
                r.setX(100+(r.getX()-tempL[1].getX()));
                r.setY(405+(r.getY()-tempL[1].getY()));
            }
        }
        tempL[1].setX(100);
        tempL[1].setY(405);

        for(Rectangle r : tempV){
            if(!(r.equals(tempV[1]))){
                r.setX(100+(r.getX()-tempV[1].getX()));
                r.setY(450+(r.getY()-tempV[1].getY()));
            }
        }
        tempV[1].setX(100);
        tempV[1].setY(450);

        rotate(tempT);
        rotate(tempT);
        rotate(tempJ);
        rotate(tempJ);
        rotate(tempJ);
        rotate(tempL);

        getChildren().addAll(tempT);
        getChildren().addAll(tempJ);
        getChildren().addAll(tempZ);
        getChildren().addAll(tempO);
        getChildren().addAll(tempS);
        getChildren().addAll(tempL);
        getChildren().addAll(tempV);

        Text t = new Text (70, 68, "A-TYPE");//was 83
        t.setFill(Color.WHITE);
        t.setStyle("-fx-font: 35 arial;");
        getChildren().add(t);
        Text l = new Text (300, 50, "LINES-");
        l.setFill(Color.WHITE);
        l.setStyle("-fx-font: 40 arial;");
        getChildren().add(l);
        Text s = new Text (48, 163, "STATISTICS");//was 59
        s.setFill(Color.WHITE);
        s.setStyle("-fx-font: 30 arial;");
        getChildren().add(s);
        Text top = new Text (520, 68, "TOP");//was 510
        top.setFill(Color.WHITE);
        top.setStyle("-fx-font: 30 arial;");
        getChildren().add(top);
        Text score = new Text (520, 130, "SCORE");//was 510
        score.setFill(Color.WHITE);
        score.setStyle("-fx-font: 30 arial;");
        getChildren().add(score);

        Text n = new Text (535, 250, "NEXT");//was 505
        n.setFill(Color.WHITE);
        n.setStyle("-fx-font: 15 arial;");
        getChildren().add(n);
        nexts.add(next);
        for(Rectangle r : next){
            if(!(r.equals(next[1]))){
                r.setX(560+(next[1].getX()-r.getX()));
                r.setY(280+(next[1].getY()-r.getY()));
            }            
        }

        next[1].setX(560);//was 550
        next[1].setY(280);
        nexts.add(next);

        //getChildren().addAll(nexts.get(nexts.size()-1));
        Text level = new Text (515, 385, "LEVEL");//was 505
        level.setFill(Color.WHITE);
        level.setStyle("-fx-font: 30 arial;");
        getChildren().add(level);
        // System.out.println(nexts.size());
    }

    public void play() {
        animation.play();
        getChildren().remove(imageView);
    }

    public void pause() {
        animation.pause();
        getChildren().add(imageView);
    }

    public void gamesOver() {
        animation.pause();
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
        for(Rectangle l : rArray.get(rArray.size()-1)){
            for(Rectangle r : rArray.get(rArray.size()-1)){
                if(r.getY()>84){
                    r.setOpacity(1);
                }
            }
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

        //rArray.add(blocks.random())
        if(rArray.size()>1){
            for(Rectangle r : rArray.get(rArray.size()-1)){
                // System.out.print(r.getY());
                if(r.getY()<getHeight()-46-400){
                    Text ended = new Text (getWidth()/2-50, getHeight()/2+20, "GameOver");
                    ended.setFill(Color.WHITE);
                    ended.setStyle("-fx-font: 35 arial;");
                    getChildren().add(ended);
                    gamesOver();
                }
            }
        }
        Rectangle[] tempRect = new Rectangle[4];
        for(int i = 0; i<4;i++){
            tempRect[i] = new Rectangle();
            tempRect[i].setWidth(20);
            tempRect[i].setHeight(20);
            tempRect[i].setX(next[i].getX());
            tempRect[i].setY(next[i].getY());
            tempRect[i].setFill(next[i].getFill());
            tempRect[i].setStroke(Color.BLACK);
        }

        // System.out.println(nexts.size());
        if(nexts.size()>0){
            getChildren().removeAll(nexts.get(nexts.size()-1)); 
            nexts.remove(nexts.get(nexts.size()-1));
        }
        rArray.add(tempRect);
        pos++;
        getChildren().addAll(rArray.get(rArray.size() - 1));
        Rectangle[] x = blocks.random();
        nexts.add(x);
        for(Rectangle r : nexts.get(nexts.size()-1)){
            if(!(r.equals(nexts.get(nexts.size()-1)[1]))){
                r.setX(560+(nexts.get(nexts.size()-1)[1].getX()-r.getX()));
                r.setY(280+(nexts.get(nexts.size()-1)[1].getY()-r.getY()));
            }            
        }
        nexts.get(nexts.size()-1)[1].setX(560);//was 565
        nexts.get(nexts.size()-1)[1].setY(280);
        next = nexts.get(nexts.size()-1);
        getChildren().addAll(nexts.get(nexts.size()-1));
        firstPos();
        waitTime = 0;
        //col = rArray.get(rArray.size()-1)[0].getColor();
        for(Rectangle r : rArray.get(rArray.size()-1)){
            r.setOpacity(0);
        }
    }

    public void firstPos() {
        for(Rectangle r : rArray.get(rArray.size()-1)){
            if(!(r.equals(rArray.get(rArray.size()-1)[0]))){
                r.setX(getWidth()/2/*-20*/+(r.getX()-rArray.get(rArray.size()-1)[0].getX()));
                r.setY(34+(r.getY()-rArray.get(rArray.size()-1)[0].getY()));//was 84
            }
        }
        rArray.get(rArray.size()-1)[0].setX(getWidth()/2/*-20*/);
        rArray.get(rArray.size()-1)[0].setY(34);//was 38//was 84
    }

    protected void move() {
        if(firstMove){
            firstPos();
            firstMove = false;
        }
        for(Rectangle r : rArray.get(rArray.size()-1)){
            if(r.getY()>84){
                r.setOpacity(1);
            }
        }
        checkBounds(rArray.get(rArray.size() - 1));
        // Check boundaries
        boolean needToCheck = true;
        for(Rectangle r : rArray.get(rArray.size()-1)){
            if(r.getY()>=getHeight()-46&&needToCheck){//changed -20 to -21//changed from -21 to -46
                //space();//end();
                double heightDifference = 100000;
                int pl = 0;
                for(int i = 0; i<4; i++){
                    if(heightDifference>getHeight()-rArray.get(rArray.size()-1)[i].getY()){
                        heightDifference = getHeight()-rArray.get(rArray.size()-1)[i].getY();
                    }
                }
                heightDifference-=25;
                for(Rectangle q : rArray.get(rArray.size()-1)){
                    q.setY(q.getY()+heightDifference-20);//was -20
                }
                end();
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

    public void rotate(){
        boolean nor = false;
        for(Rectangle r : rArray.get(rArray.size()-1)){
            if(!(r.equals(rArray.get(rArray.size()-1)[1]))){
                double xShift = rArray.get(rArray.size()-1)[1].getX()-r.getX();
                double yShift = rArray.get(rArray.size()-1)[1].getY()-r.getY();
                r.setY(rArray.get(rArray.size()-1)[1].getY()-xShift);
                r.setX(rArray.get(rArray.size()-1)[1].getX()+yShift);
                if(r.getX()>getWidth()/2+100|| r.getX()<getWidth()/2-80){
                    nor = true;
                }
                if(r.getY()>getHeight()-46){
                    nor = true;
                }
                for(Rectangle[] l : rArray){
                    if(!(l.equals(rArray.get(rArray.size()-1)))){
                        for(Rectangle t : l){
                            if(r.getBoundsInParent().intersects(t.getBoundsInParent())){
                                nor = true;
                            }
                        }
                    }
                }
            }
        }
        if(nor){
            for(Rectangle r : rArray.get(rArray.size()-1)){
                if(!(r.equals(rArray.get(rArray.size()-1)[1]))){
                    double xShift = rArray.get(rArray.size()-1)[1].getX()-r.getX();
                    double yShift = rArray.get(rArray.size()-1)[1].getY()-r.getY();
                    r.setY(rArray.get(rArray.size()-1)[1].getY()+xShift);
                    r.setX(rArray.get(rArray.size()-1)[1].getX()-yShift);
                }
            }
        }
    }

    public void rotate(Rectangle[] rect){
        // boolean nor = false;
        for(Rectangle r : rect){
            if(!(r.equals(rect[1]))){
                double xShift = rect[1].getX()-r.getX();
                double yShift = rect[1].getY()-r.getY();
                r.setY(rect[1].getY()-xShift);
                r.setX(rect[1].getX()+yShift);
                // if(r.getX()>getWidth()/2+100|| r.getX()<getWidth()/2-80){
                //     nor = true;
                // }
                // if(r.getY()>getHeight()-46){
                //     nor = true;
                // }
                // for(Rectangle[] l : rArray){
                //     if(!(l.equals(rArray.get(rArray.size()-1)))){
                //         for(Rectangle t : l){
                //             if(r.getBoundsInParent().intersects(t.getBoundsInParent())){
                //                 nor = true;
                //             }
                //         }
                //     }
                // }
            }
        }
        // if(nor){
        //     for(Rectangle r : rArray.get(rArray.size()-1)){
        //         if(!(r.equals(rArray.get(rArray.size()-1)[1]))){
        //             double xShift = rArray.get(rArray.size()-1)[1].getX()-r.getX();
        //             double yShift = rArray.get(rArray.size()-1)[1].getY()-r.getY();
        //             r.setY(rArray.get(rArray.size()-1)[1].getY()+xShift);
        //             r.setX(rArray.get(rArray.size()-1)[1].getX()-yShift);
        //         }
        //     }
        // }
    }

    public void rotation(){ 
        
        if(blocks.ifVertical == true){
                if(rotateCounter == 0){
                int hold = 2;
                int counter = -20;
                int toCheck = 0;
                for(Rectangle r : rArray.get(rArray.size()-1)){
                    r.setX(rArray.get(rArray.size()-1)[hold].getX());
                    r.setY(rArray.get(rArray.size()-1)[toCheck].getY() + (counter+=20));
                    toCheck++;
                }
                rotateCounter = 1;
            }
            else{
                int hold = 0;
                int counter = -20;
                int toCheck = 0;
                for(Rectangle r : rArray.get(rArray.size()-1)){
                    r.setX(rArray.get(rArray.size()-1)[toCheck].getX() + (counter+=20));
                    r.setY(rArray.get(rArray.size()-1)[hold].getY());
                }
                rotateCounter = 0;
            }

        }
        else if(blocks.ifoBlock == true){
            
        }
        else if(blocks.ifJBlock == true){
            if(rotateCounterJ == 0){
                
                rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[0].getX()-20);
                rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY());

                rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[2].getX()+20);
                rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY());

                rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[2].getX());
                rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY()+20);

                rotateCounterJ = 1;
            }
            else if(rotateCounterJ ==1){

                rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[1].getX());
                rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()+20);

                rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[1].getX());
                rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()-20);

                rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[1].getX()+20);
                rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY()-20);
                rotateCounterJ = 2;
            }
            else if(rotateCounterJ ==2){
                rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[1].getX()+20);
                rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY());

                rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[1].getX()-20);
                rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY());

                rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[1].getX()-20);
                rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY()-20);
                rotateCounterJ = 3;
            }
            else if(rotateCounterJ ==3){
                rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[1].getX()-20);
                rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()-20);

                rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[1].getX()+20);
                rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()+20);

                rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[1].getX());
                rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY()+40);
                rotateCounterJ = 0;
            }
        }
        else if(blocks.iflBlock == true){
            if(rotateCounterL ==0){
                rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[0].getX()-20);
                rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()+20);

                rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[2].getX()+20);
                rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()-20);

                rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[2].getX());
                rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY()-40);

                rotateCounterL = 1;
            }
            if(rotateCounterL ==1){
                rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[0].getX()+20);
                rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()+20);

                rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[2].getX()-20);
                rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()-20);

                rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[2].getX()-40);
                rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY());

                rotateCounterL = 2;
            }
            if(rotateCounterL ==2){
                rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[0].getX()+20);
                rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()-20);

                rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[2].getX()-20);
                rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()+20);

                rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[2].getX());
                rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY()+40);

                rotateCounterL = 1;
            }
            if(rotateCounter ==3){
                rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[0].getX()-20);
                rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()-20);

                rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[2].getX()+20);
                rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()+20);

                rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[2].getX()+40);
                rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY());

                rotateCounterL = 0;
            }
            else if(blocks.ifsBlock == true){
                if(rotateCounterS ==0){
                    rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[0].getX()-20);
                    rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()-20);
    
                    rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[2].getX()+20);
                    rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()-20);
    
                    rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[2].getX()+40);
                    rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY());
    
                    rotateCounterS = 1;
                }
                if(rotateCounterS ==1){
                    rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[0].getX()+20);
                    rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()+20);
    
                    rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[2].getX()-20);
                    rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()+20);
    
                    rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[2].getX()-40);
                    rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY());
    
                    rotateCounterS = 0;
                }
                
            }

            else if(blocks.ifTBlock == true){
                if(rotateCounterT == 0){
                    
                    rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[0].getX()+20);
                    rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()+20);
    
                    rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[2].getX()-20);
                    rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()+20);
    
                    rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[2].getX()+20);
                    rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY()-20);
    
                    rotateCounterT = 1;
                }
                else if(rotateCounterT ==1){
    
                    rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[1].getX()-20);
                    rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()+20);
    
                    rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[1].getX()-20);
                    rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()-20);
    
                    rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[1].getX()+20);
                    rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY()+20);
                    rotateCounterT = 2;
                }
                else if(rotateCounterT ==2){
                    rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[1].getX()-20);
                    rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()-20);
    
                    rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[1].getX()+20);
                    rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()-20);
    
                    rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[1].getX()-20);
                    rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY()+20);
                    rotateCounterT = 3;
                }
                else if(rotateCounterT ==3){
                    rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[1].getX()+20);
                    rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()-20);
    
                    rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[1].getX()-20);
                    rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()+20);
    
                    rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[1].getX()-20);
                    rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY()-20);
                    rotateCounterT = 0;
                }
            }

            else if(blocks.ifZBlock == true){
                if(rotateCounterZ ==0){
                    rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[0].getX()+20);
                    rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()-20);
    
                    rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[2].getX()-20);
                    rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()-20);
    
                    rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[2].getX()-40);
                    rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY());
    
                    rotateCounterZ = 1;
                }
                if(rotateCounterZ ==1){
                    rArray.get(rArray.size()-1)[0].setX(rArray.get(rArray.size()-1)[0].getX()-20);
                    rArray.get(rArray.size()-1)[0].setY(rArray.get(rArray.size()-1)[1].getY()+20);
    
                    rArray.get(rArray.size()-1)[2].setX(rArray.get(rArray.size()-1)[2].getX()+20);
                    rArray.get(rArray.size()-1)[2].setY(rArray.get(rArray.size()-1)[1].getY()+20);
    
                    rArray.get(rArray.size()-1)[3].setX(rArray.get(rArray.size()-1)[2].getX()+40);
                    rArray.get(rArray.size()-1)[3].setY(rArray.get(rArray.size()-1)[1].getY());
    
                    rotateCounterZ = 0;
                }
                
            }
        }
        
           
          
    }

}