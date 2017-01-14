import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import twitter4j.*;

import javax.swing.*;
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
    private List<GridPane> bubbles = new ArrayList<>();
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
        Scene scene = new Scene(group, sceneWidth, sceneHeight, Color.WHITE);
        stage.setScene(scene);
        stage.setMinWidth(sceneWidth);
        stage.setMinHeight(sceneHeight);
        stage.setMaxWidth(sceneWidth);
        stage.setMaxHeight(sceneHeight);
        scene.setCursor(Cursor.HAND);
        scene.getStylesheets().add(BubbleFrame.class.getResource("shield.css").toExternalForm());
        stage.show();
    }

    public void addBubble(String author,String text) {

        GridPane grid = new GridPane();
        BubbleBurstHandler bubbleBurstHandler = new BubbleBurstHandler(this, grid);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setMinHeight(300);
        grid.setMinWidth(150);
        grid.setVgap(5);
        grid.setHgap(5);

        grid.setAlignment(Pos.TOP_LEFT);
        grid.getStyleClass().add("textured-shape");


        grid.setLayoutX(getRandomInt((int) (stage.getScene().getWidth()))/1.1);
        grid.setLayoutY(stage.getScene().getHeight());
        grid.setOnMouseClicked(bubbleBurstHandler);
        grid.setOnMouseEntered(bubbleBurstHandler);

        grid.setRotate(randomizer.nextInt(25+1+25)-25);

        Label user = new Label();
        user.setText(author);
        user.setWrapText(true);
        user.setStyle("" +
                "-fx-background-radius: 10px;" +
                "-fx-background-color: cornflowerblue; " +
                "-fx-padding: 10px;");

        Label tweet = new Label();
        tweet.setMaxWidth(150);
        tweet.setWrapText(true);
        tweet.setText(text);
        tweet.setStyle("" +
                "-fx-background-radius: 10px;" +
                "-fx-background-color: coral; " +
                "-fx-padding: 10px;");



        grid.add(user,0,1);
        grid.add(tweet,1,1);

        bubbles.add(grid);

        bubbles.add(grid);
        group.getChildren().add(grid);
    }

    public void moveBubbles() {
        for (GridPane bubble : bubbles) {
            bubble.setLayoutY(bubble.getLayoutY() - 1);
        }
    }


    public void removeBubbles() {
        List<GridPane> bubblesToBeRemoved = new ArrayList<>();
        for (GridPane bubble : bubbles) {
            if (bubble.getLayoutY() <= 10) {
                group.getChildren().remove(bubble);
            }

        }
        bubbles.removeAll(bubblesToBeRemoved);
    }

    public boolean anyBubbleReachedOtherEnd() {
        for (GridPane bubble : bubbles) {
            if (bubble.getLayoutY() <= 10) {
                burstTheBubble(bubble);
                return true;
            }
        }
        return false;
    }

    public void removeBubble() {
        for (GridPane bubble : bubbles) {
            if (bubble.getLayoutY() <= 10) {
                burstTheBubble(bubble);
                break;
            }
        }
    }

    public void burstTheBubble(final GridPane bubble) {
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
