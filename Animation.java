//T Harvey
// Loosely based on https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development/blob/master/Example3.java 
//BennyLi CISC275_lab2
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;


// Animation of Orc walking
public class Animation extends Application {
    final int canvasCount = 10;
    int picInd = 0;
    BufferedImage[] pics;
    double xloc = 0;
    double yloc = 0;
    double xIncr = 8;
    double yIncr = 2;
    final static int canvasWidth = 500;
    final static int canvasHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    boolean GoEast;
    boolean GoSouth;
    
    // TODO: Change the code so that at least eight orc animation pngs are loaded

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Orc");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image orc_imgSE = createImage();
        Image orc_imgSW = createImage2();
        Image orc_imgNW = createImage3();
        Image orc_imgNE = createImage4();
        
        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1e9;
                	       
                if(GoEast && GoSouth) {
                	xloc += xIncr;
                    yloc += yIncr;

                    // Clear the canvas
                    gc.clearRect(0, 0, canvasWidth, canvasHeight);
                    // draw the subimage from the original png to animate the orc's motion
                    gc.drawImage(orc_imgSE, imgWidth*picInd, 0, imgWidth, imgHeight,
                                        xloc, yloc, imgWidth, imgHeight);}
                else if(!GoEast && GoSouth) {
                	xloc -= xIncr;
                    yloc += yIncr;
                    gc.clearRect(0, 0, canvasWidth, canvasHeight);
                    gc.drawImage(orc_imgSW, imgWidth*picInd, 0, imgWidth, imgHeight,
                                            xloc, yloc, imgWidth, imgHeight);}
                else if(!GoEast && !GoSouth) {
                    xloc -= xIncr;
                    yloc -= yIncr;
                    gc.clearRect(0, 0, canvasWidth, canvasHeight);
                    gc.drawImage(orc_imgNW, imgWidth*picInd, 0, imgWidth, imgHeight,
                                            xloc, yloc, imgWidth, imgHeight);}
                else if(GoEast && !GoSouth) {
                    xloc += xIncr;
                    yloc -= yIncr;
                    gc.clearRect(0, 0, canvasWidth, canvasHeight);
                    gc.drawImage(orc_imgNE, imgWidth*picInd, 0, imgWidth, imgHeight,
                                            xloc, yloc, imgWidth, imgHeight);
                    } 
                
                picInd = (picInd + 1) % canvasCount;
                
                if(xloc<0) {
                	GoEast = true;
                }
                else if(xloc >= (canvasWidth - imgWidth)) {	
                	GoEast = false;
                }
                
                if(yloc <0){
                	GoSouth = true;
                	
                }else if(yloc >= (canvasHeight - imgHeight)){
                	GoSouth = false;
                }
               
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               
                // TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
                //Be sure that animation picture direction matches what is happening on screen.
            }
        }.start();
        theStage.show();
    }

    //Read image from file and return
    private Image createImage() {
        Image img = new Image("orc_animation/orc_forward_southeast.png");
        return img;   	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
    private Image createImage2() {
        Image img = new Image("orc_animation/orc_forward_southwest.png");
        return img;
    }
    private Image createImage3() {
        Image img = new Image("orc_animation/orc_forward_northwest.png");
        return img;
    }
    private Image createImage4() {
        Image img = new Image("orc_animation/orc_forward_northeast.png");
        return img;
    }

}


