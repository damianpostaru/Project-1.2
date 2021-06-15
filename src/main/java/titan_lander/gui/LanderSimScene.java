package titan_lander.gui;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.util.Duration;
import titan.gui.PlanetTransition;
import titan.space.Vector3d;

import static titan.gui.Util.startTimerTask;

public class LanderSimScene extends LanderVisualizer
{
    public static void setVisualizerScene()
    {
        probeLaunch = new Button("Launch Lander!");
        exitButton = new Button("Exit Simulation!");

        probeLaunch.setOnAction(e -> {
            LanderSimScene.animateLander();
        });

        exitButton.setOnAction(e -> System.exit(0));

        buttonBox = new VBox(25);
        buttonBox.getChildren().addAll(probeLaunch,exitButton);

        drawLanderPath();
        pathLines = new Pane(landerPath,node);

        BorderPane root = new BorderPane();
        root.setCenter(pathLines);
        root.setRight(buttonBox);
        root.getStyleClass().add("landerBackground");;

        visualiserScene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        visualiserScene.getStylesheets().add("Stylesheet.css");

    }

    public static void drawLanderPath()
    {
        MoveTo firstMove = new MoveTo(firstVector.getX(),firstVector.getY());
        landerPath = new Path(firstMove);

        for (int i = 1; i < landerPathVectors.length; i++)
        {
            Vector3d end = metersToPixels(landerPathVectors[i]);
            LineTo line = new LineTo(end.getX(),end.getY());
            landerPath.getElements().add(line);
        }
    }

    public static void animateLander()
    {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        for (int i = 1; i < landerPathVectors.length; i++)
        {
            Vector3d v = metersToPixels(landerPathVectors[i]);
            Vector3d v2 = metersToPixels(landerPathVectors[i-1]);
            KeyValue rotation = new KeyValue(landerView.rotateProperty(),v.getZ());
            KeyValue xPos = new KeyValue(landerView.xProperty(),v.getX());
            KeyValue yPos = new KeyValue(landerView.yProperty(),v.getY());
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),rotation,xPos,yPos));
        }
        System.out.println("OI");
        timeline.play();
    }
}
