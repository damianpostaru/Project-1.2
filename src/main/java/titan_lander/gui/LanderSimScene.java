package titan_lander.gui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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

        BorderPane root = new BorderPane();
        root.setCenter(landerPath);
        root.setRight(buttonBox);
        root.getStyleClass().add("landerBackground");;

        visualiserScene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        visualiserScene.getStylesheets().add("Stylesheet.css");

    }

    public static void drawLanderPath()
    {
        Vector3d firstVector = metersToPixels(landerPathVectors[0]);
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
        //animate lander
    }
}
