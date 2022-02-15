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
///
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

import java.util.ArrayList;
import javafx.scene.text.Text;
//import javax.swing.text.html.AccessibleHTML.TextElementInfo.TextAccessibleContext;

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
    public int rotateCounter = 0;
    public int rotateCounterJ = 0;
    public int rotateCounterL  =0;
    public int rotateCounterS  =0;
    public int rotateCounterT  =0;
    public int rotateCounterZ  =0;
    boolean ifVertical = blocks.ifVertical;
    boolean iflBlock = blocks.iflBlock;
    boolean ifoBlock = blocks.ifoBlock;
    boolean ifTBlock = blocks.ifTBlock;
    boolean ifZBlock = blocks.ifZBlock;
    boolean ifsBlock = blocks.ifsBlock;
    boolean ifJBlock = blocks.ifJBlock;
    Rectangle[] next = blocks.random();
    Rectangle checker = new Rectangle(0, 0, 20, 20);
    Text numV;
    Text numL;
    Text numO;
    Text numT;
    Text numZ;
    Text numS;
    Text numJ;
    public int linesDone = 0;
    public Text linesText;
    public int score = 0;
    Text scoreText;
    String mFile = "tetris.mp4";
    Media sound = new Media(new File(mFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    private ArrayList<Rectangle[]> nexts = new ArrayList<>();
    public boolean gameDone = false;
    //private Color col = new Color();

    public PiecePane() {
        spreadsheetImage = new Image("VsCode.png");
        imageView = new ImageView();
        imageView.setImage(spreadsheetImage);
        rArray = new ArrayList<>();
        
        // r1.setFill(Color.GREEN); // Set ball color
        //// getChildren().add(r1); // Place a ball into this pane

        scoreText = new Text(520, 160, "0");
        scoreText.setFill(Color.WHITE);
        scoreText.setStyle("-fx-font: 15 arial;");
        getChildren().add(scoreText);

        linesText = new Text(445, 40, "0");
        linesText.setFill(Color.WHITE);
        linesText.setStyle("-fx-font: 15 arial;");
        getChildren().add(linesText);

        numV = new Text (180, 470, Integer.toString(blocks.numV));//was 510
        numV.setFill(Color.WHITE);
        numV.setStyle("-fx-font: 15 arial;");
        getChildren().add(numV);

        numT = new Text (180, 200, Integer.toString(blocks.numT));//was 510
        numT.setFill(Color.WHITE);
        numT.setStyle("-fx-font: 15 arial;");
        getChildren().add(numT);

        numO = new Text (180, 335, Integer.toString(blocks.numO));//was 510
        numO.setFill(Color.WHITE);
        numO.setStyle("-fx-font: 15 arial;");
        getChildren().add(numO);

        numS = new Text (180, 380, Integer.toString(blocks.numS));//was 510
        numS.setFill(Color.WHITE);
        numS.setStyle("-fx-font: 15 arial;");
        getChildren().add(numS);

        numZ = new Text (180, 290, Integer.toString(blocks.numZ));//was 510
        numZ.setFill(Color.WHITE);
        numZ.setStyle("-fx-font: 15 arial;");
        getChildren().add(numZ);

        numL = new Text (180, 425, Integer.toString(blocks.numL));//was 510
        numL.setFill(Color.WHITE);
        numL.setStyle("-fx-font: 15 arial;");
        getChildren().add(numL);

        numJ = new Text (180, 245, Integer.toString(blocks.numJ));//was 510
        numJ.setFill(Color.WHITE);
        numJ.setStyle("-fx-font: 15 arial;");
        getChildren().add(numJ);
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
        Text l = new Text (300, 50, "LINES");
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
    
        // Line line = new Line(25, 10, 60, 30);
		// Text scoretext = new Text("Score: ");
		// scoretext.setStyle("-fx-font: 20 arial;");
		// scoretext.setY(50);
		// scoretext.setX(XMAX + 5);
		// Text level = new Text("Lines: ");
		// level.setStyle("-fx-font: 20 arial;");
		// level.setY(100);
		// level.setX(XMAX + 5);
		// level.setFill(Color.GREEN);
		// group.getChildren().addAll(scoretext, line, level);

    
    
        // System.out.println(nexts.size());
    }

    public void play() {
        animation.play();
        if(gameDone){
            gamesOver();
        }
        getChildren().remove(imageView);
    }

    public void pause() {
        animation.pause();
        getChildren().add(imageView);
        mediaPlayer.pause();
    }

    public void gamesOver() {
        animation.pause();
        gameDone = true;
        mediaPlayer.pause();
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
        // for(Rectangle r: rArray.get(rArray.size()-1)){
        //     System.out.println(r.getX());    
        // }
        end();
        lineClear();         
    }

    

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    public void end() {
         ifVertical = blocks.ifVertical;
         iflBlock = blocks.iflBlock;
         ifoBlock = blocks.ifoBlock;
         ifTBlock = blocks.ifTBlock;
         ifZBlock = blocks.ifZBlock;
         ifsBlock = blocks.ifsBlock;
         ifJBlock = blocks.ifJBlock;
        dx = 0;
        numV.setOpacity(0);
        getChildren().remove(numV);
        numV = new Text (180, 470, Integer.toString(blocks.numV));//was 510
        numV.setFill(Color.WHITE);
        numV.setStyle("-fx-font: 15 arial;");
        getChildren().add(numV);
        numV.setOpacity(1);

        numT.setOpacity(0);
        getChildren().remove(numT);
        numT = new Text (180, 200, Integer.toString(blocks.numT));//was 510
        numT.setFill(Color.WHITE);
        numT.setStyle("-fx-font: 15 arial;");
        getChildren().add(numT);
        numT.setOpacity(1);

        numO.setOpacity(0);
        getChildren().remove(numO);
        numO = new Text (180, 335, Integer.toString(blocks.numO));//was 510
        numO.setFill(Color.WHITE);
        numO.setStyle("-fx-font: 15 arial;");
        getChildren().add(numO);
        numO.setOpacity(1);

        numS.setOpacity(0);
        getChildren().remove(numS);
        numS = new Text (180, 380, Integer.toString(blocks.numS));//was 510
        numS.setFill(Color.WHITE);
        numS.setStyle("-fx-font: 15 arial;");
        getChildren().add(numS);
        numS.setOpacity(1);

        numZ.setOpacity(0);
        getChildren().remove(numZ);
        numZ = new Text (180, 290, Integer.toString(blocks.numZ));//was 510
        numZ.setFill(Color.WHITE);
        numZ.setStyle("-fx-font: 15 arial;");
        getChildren().add(numZ);
        numZ.setOpacity(1);

        numL.setOpacity(0);
        getChildren().remove(numL);
        numL = new Text (180, 425, Integer.toString(blocks.numL));//was 510
        numL.setFill(Color.WHITE);
        numL.setStyle("-fx-font: 15 arial;");
        getChildren().add(numL);
        numL.setOpacity(1);

        numJ.setOpacity(0);
        getChildren().remove(numJ);
        numJ = new Text (180, 245, Integer.toString(blocks.numJ));//was 510
        numJ.setFill(Color.WHITE);
        numJ.setStyle("-fx-font: 15 arial;");
        getChildren().add(numJ);
        numJ.setOpacity(1);
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
        lineClear();
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
        //lineClear();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run(){
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        if(!gameDone){
            mediaPlayer.play();     
        }
               
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
        if(nor || ifoBlock){
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

    public void lineClear(){
        checker.setOpacity(0);
        checker.setHeight(20);
        checker.setWidth(20);
        int linesCleared = 0;
        for(double i = 465.0; i>=465-(20*20);i-=20){
            int count = 0;
            checker.setY(i);
            for(double j = 266.0; j<266+(20*10);j+=20){
                checker.setX(j);
                for(Rectangle[] rect : rArray){
                    for(Rectangle r : rect){
                        if(r.getX()==checker.getX()&&r.getY()==checker.getY()){
                            count++;
                        }
                    }
                }
            }
            if(count==10){
                score+=100;
                for(Rectangle[] rect: rArray){
                    for(Rectangle r: rect){
                        if(r.getY()==i){
                            r.setX(500);
                            r.setY(500);
                            r.setOpacity(0);
                            getChildren().remove(r);
                        }else if(r.getY()<i){
                            r.setY(r.getY()+20);
                        }
                    }
                }
                linesCleared++;
                i+=20;
            }
        }
        if(linesCleared == 2){
            score+=300;
        }else if(linesCleared == 3){
            score+=900;
        }else if(linesCleared == 4){
            score+=1200;
        }

        linesDone+=linesCleared;

        scoreText.setOpacity(0);
        getChildren().remove(scoreText);
        scoreText = new Text(520, 160, Integer.toString(score));
        scoreText.setFill(Color.WHITE);
        scoreText.setStyle("-fx-font: 15 arial;");
        getChildren().add(scoreText);
        scoreText.setOpacity(1);

        linesText.setOpacity(0);
        getChildren().remove(linesText);
        linesText = new Text(445, 40, Integer.toString(linesDone));
        linesText.setFill(Color.WHITE);
        linesText.setStyle("-fx-font: 15 arial;");
        getChildren().add(linesText);
        linesText.setOpacity(1);
    }

    public void rotation(){ 
        
        if(ifVertical == true){
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
        else if(ifoBlock == true){
            
        }
        else if(ifJBlock == true){
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
        else if(iflBlock == true){
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

                rotateCounterL = 3;
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
            else if(ifsBlock == true){
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

            else if(ifTBlock == true){
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

            else if(ifZBlock == true){
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