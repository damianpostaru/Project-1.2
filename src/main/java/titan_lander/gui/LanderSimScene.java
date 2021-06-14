package titan_lander.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import titan.gui.PlanetTransition;

import static titan.gui.Util.startTimerTask;

public class LanderSimScene extends LanderVisualizer
{
    public static void setVisualizerScene()
    {
        probeLaunch = new Button("Launch Lander!");
        exitButton = new Button("Exit Simulation!");

        probeLaunch.setOnAction(e -> {
            //animate lander
        });

        exitButton.setOnAction(e -> System.exit(0));

        buttonBox = new VBox(25);
        buttonBox.getChildren().addAll(probeLaunch,exitButton);

        BorderPane root = new BorderPane();
        root.setRight(buttonBox);
        root.getStyleClass().add("landerBackground");;

        visualiserScene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        visualiserScene.getStylesheets().add("Stylesheet.css");

    }
}
