import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Bubble Frame
 * Created by IntelliJ IDEA.
 * User: sthirumuru
 * Date: 5/23/14
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleFrame {
    private Group group = null;
    private List<Circle> bubbles = new ArrayList<>();
    private Random randomizer = new Random();
    private Stage stage = null;

    private int sceneWidth = 1280, sceneHeight = 960;

    public BubbleFrame() {

    }

    public void start(Stage primaryStage) {
        sceneWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
        sceneHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();
        stage = primaryStage;
        stage.setTitle("Bubble Burst");
        group = new Group();
        Scene scene = new Scene(group, sceneWidth, sceneHeight, Color.BLUE);
        stage.setScene(scene);
        stage.setMinWidth(sceneWidth);
        stage.setMinHeight(sceneHeight);
        stage.setMaxWidth(sceneWidth);
        stage.setMaxHeight(sceneHeight);
        scene.setCursor(Cursor.HAND);
        stage.show();
    }


    public void addBubble() {
        Circle bubble = new Circle();
        bubble.setCenterX(getRandomInt((int) stage.getScene().getWidth()));
        bubble.setCenterY(stage.getScene().getHeight());
        bubble.setRadius(10);
        BubbleBurstHandler bubbleBurstHandler = new BubbleBurstHandler(this, bubble);
        bubble.setOnMouseClicked(bubbleBurstHandler);
        bubble.setOnMouseEntered(bubbleBurstHandler);
        bubbles.add(bubble);
        group.getChildren().add(bubble);
    }

    public void moveBubbles() {
        for (Circle bubble : bubbles) {
            bubble.setCenterY(bubble.getCenterY() - getRandomInt(2));

        }
    }





    public void removeBubbles() {
        List<Circle> bubblesToBeRemoved = new ArrayList<>();
        for (Circle bubble : bubbles) {
            if (bubble.getCenterY() <= 10) {
                group.getChildren().remove(bubble);
            }

        }
        bubbles.removeAll(bubblesToBeRemoved);
    }

    public boolean anyBubbleReachedOtherEnd() {
        for (Circle bubble : bubbles) {
            if (bubble.getCenterY() <= 10) {

                return true;
            }
        }
        return false;
    }

    public void removeBubble() {
        for (Circle bubble : bubbles) {
            if (bubble.getCenterY() <= 10) {
                burstTheBubble(bubble);
                break;
            }
        }
    }


    public void burstTheBubble(Circle bubble) {
        group.getChildren().remove(bubble);
        bubbles.remove(bubble);
    }

    public int getRandomInt(int range) {
        int randomInt = randomizer.nextInt() % range;
        if (randomInt < 0) {
            randomInt = -randomInt;
        }
        return randomInt;
    }


    public void clearBubbles() {
        group.getChildren().removeAll(bubbles);
        bubbles.clear();
    }

}
