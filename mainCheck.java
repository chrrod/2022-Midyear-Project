import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class mainCheck extends Application {
  // public boolean ifVertical = false;
  // public boolean ifSquare = false;
  // public boolean iflBlock = false;
  // public boolean ifoBlock = false;
  // public boolean ifsBlock = false;
  // public boolean ifzBlock = false;
  // public boolean ifjBlock = false;
  // public boolean iftBlock = false;
  boolean ifVertical = false;
  boolean iflBlock = false;
  boolean ifoBlock = false;
  boolean ifTBlock = false;
  boolean ifZBlock = false;
  boolean ifsBlock = false;
  boolean ifJBlock = false;

  // @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create rectangles
    Rectangle r1 = new Rectangle(25, 10, 300, 600);
    r1.setStroke(Color.BLACK);
    r1.setFill(Color.BLACK);
    Rectangle[] new1 = vertical();

    // Rectangle r2 = new Rectangle(25, 50, 60, 30);
    // Rectangle r3 = new Rectangle(25, 90, 60, 30);
    // r3.setArcWidth(15);
    // r3.setArcHeight(25);
    // Create a group and add nodes to the group
    // Rectangle vertical1 = new Rectangle();

    // Setting the properties of the rectangle

    Group group = new Group();
    group.getChildren().addAll(new Text(10, 27, ""), r1, new Text(10, 67, "r2"), new1[0], new1[1], new1[2], new1[3]);/*
                                                                                                                    * ,
                                                                                                                    * new
                                                                                                                    * Text
                                                                                                                    * (
                                                                                                                    * 10,
                                                                                                                    * 67,
                                                                                                                    * "r2"
                                                                                                                    * ),
                                                                                                                    * r2,
                                                                                                                    * new
                                                                                                                    * Text
                                                                                                                    * (
                                                                                                                    * 10,
                                                                                                                    * 107,
                                                                                                                    * "r3"
                                                                                                                    * ),
                                                                                                                    * r3
                                                                                                                    */

    // for (int i = 0; i < 4; i++){
    // Rectangle r = new Rectangle(100, 50, 100, 30);
    // r.setRotate(i*360/8);
    // r.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
    // r.setFill(Color.WHITE);
    // group.getChildren().add(r);
    // }

    Scene scene = new Scene(new BorderPane(group), 500, 600);
    primaryStage.setTitle("ShowRectangle");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public Rectangle[] vertical() {

    Rectangle[] pieces = new Rectangle[4];
    Rectangle vertical1 = new Rectangle();

    vertical1.setWidth(20);
    vertical1.setHeight(20);
    vertical1.setX(100);
    vertical1.setY(10);
    vertical1.setStroke(Color.BLACK);
    vertical1.setFill(Color.AQUA);

    pieces[0] = vertical1;
    Rectangle vertical2 = new Rectangle();

    vertical2.setWidth(20);
    vertical2.setHeight(20);
    vertical2.setX(120);
    vertical2.setY(10);
    vertical2.setStroke(Color.BLACK);
    vertical2.setFill(Color.AQUA);

    pieces[1] = vertical2;
    Rectangle vertical3 = new Rectangle();

    vertical3.setWidth(20);
    vertical3.setHeight(20);
    vertical3.setX(140);
    vertical3.setY(10);
    vertical3.setStroke(Color.BLACK);
    vertical3.setFill(Color.AQUA);
    pieces[2] = vertical3;

    Rectangle vertical4 = new Rectangle();

    vertical4.setWidth(20);
    vertical4.setHeight(20);
    vertical4.setX(160);
    vertical4.setY(10);
    vertical4.setStroke(Color.BLACK);
    vertical4.setFill(Color.AQUA);
    pieces[3] = vertical4;
   ifVertical = true;
   iflBlock = false;
   ifoBlock = false;
   ifTBlock = false;
   ifZBlock = false;
   ifsBlock = false;
   ifJBlock = false;
    return pieces;
  }

  public Rectangle[] oBlock() {
    Rectangle[] pieces = new Rectangle[4];
    Rectangle vertical1 = new Rectangle();

    vertical1.setWidth(20);
    vertical1.setHeight(20);
    vertical1.setX(120);// wa 100
    vertical1.setY(10);
    vertical1.setStroke(Color.BLACK);
    vertical1.setFill(Color.YELLOW);

    pieces[0] = vertical1;
    Rectangle vertical2 = new Rectangle();

    vertical2.setWidth(20);
    vertical2.setHeight(20);
    vertical2.setX(140);// was 120
    vertical2.setY(10);
    vertical2.setStroke(Color.BLACK);
    vertical2.setFill(Color.YELLOW);

    pieces[1] = vertical2;
    Rectangle vertical3 = new Rectangle();

    vertical3.setWidth(20);
    vertical3.setHeight(20);
    vertical3.setX(120);// was 100
    vertical3.setY(30);
    vertical3.setStroke(Color.BLACK);
    vertical3.setFill(Color.YELLOW);
    pieces[2] = vertical3;

    Rectangle vertical4 = new Rectangle();

    vertical4.setWidth(20);
    vertical4.setHeight(20);
    vertical4.setX(140);// was 120
    vertical4.setY(30);
    vertical4.setStroke(Color.BLACK);
    vertical4.setFill(Color.YELLOW);
    pieces[3] = vertical4;
     ifVertical = false;
     iflBlock = false;
     ifoBlock = true;
     ifTBlock = false;
     ifZBlock = false;
     ifsBlock = false;
     ifJBlock = false;
    return pieces;
  }

  public Rectangle[] jBlock() {
    Rectangle[] pieces = new Rectangle[4];
    Rectangle vertical1 = new Rectangle();

    vertical1.setWidth(20);
    vertical1.setHeight(20);
    vertical1.setX(140);// was 100
    vertical1.setY(10);
    vertical1.setStroke(Color.BLACK);
    vertical1.setFill(Color.BLUE);

    pieces[0] = vertical1;
    Rectangle vertical2 = new Rectangle();

    vertical2.setWidth(20);
    vertical2.setHeight(20);
    vertical2.setX(140);// was 100
    vertical2.setY(30);
    vertical2.setStroke(Color.BLACK);
    vertical2.setFill(Color.BLUE);

    pieces[1] = vertical2;
    Rectangle vertical3 = new Rectangle();

    vertical3.setWidth(20);
    vertical3.setHeight(20);
    vertical3.setX(140);// was 120
    vertical3.setY(50);// was 30
    vertical3.setStroke(Color.BLACK);
    vertical3.setFill(Color.BLUE);
    pieces[2] = vertical3;

    Rectangle vertical4 = new Rectangle();

    vertical4.setWidth(20);
    vertical4.setHeight(20);
    vertical4.setX(120);// was 140
    vertical4.setY(50);// was 30
    vertical4.setStroke(Color.BLACK);
    vertical4.setFill(Color.BLUE);
    pieces[3] = vertical4;
     ifVertical = false;
     iflBlock = false;
     ifoBlock = false;
     ifTBlock = false;
     ifZBlock = false;
     ifsBlock = false;
     ifJBlock = true;
    return pieces;
  }

  public Rectangle[] lBlock() {
    Rectangle[] pieces = new Rectangle[4];
    Rectangle vertical1 = new Rectangle();

    vertical1.setWidth(20);
    vertical1.setHeight(20);
    vertical1.setX(120);// was 140
    vertical1.setY(10);
    vertical1.setStroke(Color.BLACK);
    vertical1.setFill(Color.ORANGE);

    pieces[0] = vertical1;
    Rectangle vertical2 = new Rectangle();

    vertical2.setWidth(20);
    vertical2.setHeight(20);
    vertical2.setX(120);// was 140
    vertical2.setY(30);
    vertical2.setStroke(Color.BLACK);
    vertical2.setFill(Color.ORANGE);

    pieces[1] = vertical2;
    Rectangle vertical3 = new Rectangle();

    vertical3.setWidth(20);
    vertical3.setHeight(20);
    vertical3.setX(120);
    vertical3.setY(50); // was 30
    vertical3.setStroke(Color.BLACK);
    vertical3.setFill(Color.ORANGE);
    pieces[2] = vertical3;

    Rectangle vertical4 = new Rectangle();

    vertical4.setWidth(20);
    vertical4.setHeight(20);
    vertical4.setX(140);// was 100
    vertical4.setY(50);// was 20
    vertical4.setStroke(Color.BLACK);
    vertical4.setFill(Color.ORANGE);
    pieces[3] = vertical4;
     ifVertical = false;
   iflBlock = true;
   ifoBlock = false;
   ifTBlock = false;
   ifZBlock = false;
   ifsBlock = false;
   ifJBlock = false;
    return pieces;
  }

  public Rectangle[] sBlock() {
    Rectangle[] pieces = new Rectangle[4];
    Rectangle vertical1 = new Rectangle();

    vertical1.setWidth(20);
    vertical1.setHeight(20);
    vertical1.setX(140);// was 120
    vertical1.setY(10);
    vertical1.setStroke(Color.BLACK);
    vertical1.setFill(Color.CHARTREUSE);

    pieces[0] = vertical1;
    Rectangle vertical2 = new Rectangle();

    vertical2.setWidth(20);
    vertical2.setHeight(20);
    vertical2.setX(120);// was 100
    vertical2.setY(10);
    vertical2.setStroke(Color.BLACK);
    vertical2.setFill(Color.CHARTREUSE);

    pieces[1] = vertical2;
    Rectangle vertical3 = new Rectangle();

    vertical3.setWidth(20);
    vertical3.setHeight(20);
    vertical3.setX(120);// was 100
    vertical3.setY(30);
    vertical3.setStroke(Color.BLACK);
    vertical3.setFill(Color.CHARTREUSE);
    pieces[2] = vertical3;

    Rectangle vertical4 = new Rectangle();

    vertical4.setWidth(20);
    vertical4.setHeight(20);
    vertical4.setX(100);// was 80
    vertical4.setY(30);
    vertical4.setStroke(Color.BLACK);
    vertical4.setFill(Color.CHARTREUSE);
    pieces[3] = vertical4;
     ifVertical = false;
   iflBlock = false;
   ifoBlock = false;
   ifTBlock = false;
   ifZBlock = false;
   ifsBlock = true;
   ifJBlock = false;
    return pieces;
  }

  public Rectangle[] tBlock() {
    Rectangle[] pieces = new Rectangle[4];
    Rectangle vertical1 = new Rectangle();

    vertical1.setWidth(20);
    vertical1.setHeight(20);
    vertical1.setX(120);// was 100
    vertical1.setY(10);
    vertical1.setStroke(Color.BLACK);
    vertical1.setFill(Color.PURPLE);

    pieces[0] = vertical1;
    Rectangle vertical2 = new Rectangle();

    vertical2.setWidth(20);
    vertical2.setHeight(20);
    vertical2.setX(120);// was 100
    vertical2.setY(30);
    vertical2.setStroke(Color.BLACK);
    vertical2.setFill(Color.PURPLE);

    pieces[1] = vertical2;
    Rectangle vertical3 = new Rectangle();

    vertical3.setWidth(20);
    vertical3.setHeight(20);
    vertical3.setX(140);// was 120
    vertical3.setY(30);
    vertical3.setStroke(Color.BLACK);
    vertical3.setFill(Color.PURPLE);
    pieces[2] = vertical3;

    Rectangle vertical4 = new Rectangle();

    vertical4.setWidth(20);
    vertical4.setHeight(20);
    vertical4.setX(100);// was 80
    vertical4.setY(30);
    vertical4.setStroke(Color.BLACK);
    vertical4.setFill(Color.PURPLE);
    pieces[3] = vertical4;
     ifVertical = false;
   iflBlock = false;
   ifoBlock = false;
   ifTBlock = true;
   ifZBlock = false;
   ifsBlock = false;
   ifJBlock = false;
    return pieces;
  }

  public Rectangle[] zBlock() {
    Rectangle[] pieces = new Rectangle[4];
    Rectangle vertical1 = new Rectangle();

    vertical1.setWidth(20);
    vertical1.setHeight(20);
    vertical1.setX(100);// was 80
    vertical1.setY(10);
    vertical1.setStroke(Color.BLACK);
    vertical1.setFill(Color.RED);

    pieces[0] = vertical1;
    Rectangle vertical2 = new Rectangle();

    vertical2.setWidth(20);
    vertical2.setHeight(20);
    vertical2.setX(120);// was 100
    vertical2.setY(10);
    vertical2.setStroke(Color.BLACK);
    vertical2.setFill(Color.RED);

    pieces[1] = vertical2;
    Rectangle vertical3 = new Rectangle();

    vertical3.setWidth(20);
    vertical3.setHeight(20);
    vertical3.setX(120);// was 100
    vertical3.setY(30);
    vertical3.setStroke(Color.BLACK);
    vertical3.setFill(Color.RED);
    pieces[2] = vertical3;

    Rectangle vertical4 = new Rectangle();

    vertical4.setWidth(20);
    vertical4.setHeight(20);
    vertical4.setX(140);// was 120
    vertical4.setY(30);
    vertical4.setStroke(Color.BLACK);
    vertical4.setFill(Color.RED);
    pieces[3] = vertical4;
     ifVertical = false;
   iflBlock = false;
   ifoBlock = false;
   ifTBlock = false;
   ifZBlock = true;
   ifsBlock = false;
   ifJBlock = false;
    return pieces;
  }

  public Rectangle[] random() {
    Rectangle r[];
    Random rand = new Random();
    int rn = rand.nextInt(7);
    if (rn == 0) {
      r = lBlock();
    } else if (rn == 1) {
      r = jBlock();
    } else if (rn == 2) {
      r = oBlock();
    } else if (rn == 3) {
      r = vertical();
    } else if (rn == 4) {
      r = zBlock();
    } else if (rn == 5) {
      r = sBlock();
    } else {
      r = tBlock();
    }
    for (Rectangle rect : r) {
      rect.setStroke(Color.BLACK);
    }
    return r;
  }

  public static void main(String[] args) {
    launch(args);

  }
}
