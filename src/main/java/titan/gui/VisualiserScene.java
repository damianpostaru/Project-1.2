package titan.gui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import static titan.gui.Util.startTimerTask;

public class VisualiserScene extends GuiMain {
    public static void setVisualiserScene() {

        Util.setCelestialBodies();
        timeText = new Text();
        timeText.setId("timeText");

        probeLaunch = new Button("Launch Probe!");
        exitButton = new Button("Exit Simulation!");
        probeLaunch.setOnAction(e -> {
            startTimerTask();
            PlanetTransition.transition(mercury, mercuryPath);
            PlanetTransition.transition(venus, venusPath);
            PlanetTransition.transition(earth, earthPath);
            PlanetTransition.transition(moon, moonPath);
            PlanetTransition.transition(mars, marsPath);
            PlanetTransition.transition(jupiter, jupiterPath);
            PlanetTransition.transition(saturn, saturnPath);
            PlanetTransition.transition(titan, titanPath);
            PlanetTransition.transition(probe, probePath);
            PlanetTransition.transition(neptune, neptunePath);
            PlanetTransition.transition(uranus, uranusPath);
        });

        exitButton.setOnAction(e -> System.exit(0));

        InfoScreen.run();

        scrollScale = new SimpleDoubleProperty(1.0);
        zoomPane = new Pane();
        zoomPane.scaleXProperty().bind(scrollScale);
        zoomPane.scaleYProperty().bind(scrollScale);
        zoomPane.getChildren().addAll(sun.getBody(), earth.getBody(), mercury.getBody(), venus.getBody(), moon.getBody(), mars.getBody(), jupiter.getBody(), saturn.getBody(), uranus.getBody(), neptune.getBody(), titan.getBody(), probe.getBody());
        // Initially displayed Solar System position
        zoomPane.setTranslateX(-screenBounds.getWidth() / 4);
        zoomPane.setTranslateY(-screenBounds.getHeight() / 4);

        zoomPane.setOnScroll(scrollHandler);
        zoomPane.setOnMousePressed(pressHandler);
        zoomPane.setOnMouseDragged(dragHandler);

        BorderPane root = new BorderPane();
        root.setRight(infoBox);
        BorderPane.setAlignment(timeText, Pos.BOTTOM_RIGHT);
        root.setBottom(timeText);
        root.setCenter(zoomPane);
        // Make the infoBox always appear on top in relation to the zoomPane
        // This allows the the buttons 'probeLaunch' and 'exitButton' to be clickable at all times.
        zoomPane.toBack();

        visualiserScene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        visualiserScene.getStylesheets().add("Stylesheet.css");
    }
}
