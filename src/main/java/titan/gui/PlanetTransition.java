package titan.gui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import titan.solver.State;
import titan.space.Planet;
import titan.space.SolarSystem;
import titan.space.Vector3d;

public class PlanetTransition extends GuiMain {

    public static void createPath() {
        instantiatePaths();
        addAllStartPos();
    }

    private static void addAllStartPos() {
        for (int i = 0; i < planetPaths.length ; i++) {
            addStartPos(planetPaths[i], planetBodies[i]);
        }
    }

    public static void addStartPos(Timeline planetPath, CelestialBody planet) {
        Circle body = planet.getBody();
        KeyValue X = new KeyValue(body.centerXProperty(),planet.getX());
        KeyValue Y = new KeyValue(body.centerYProperty(),planet.getY());
        planetPath.getKeyFrames().add(new KeyFrame(Duration.ZERO,X,Y));
    }

    private static void instantiatePaths() {
        for (int i = 0; i < planetPaths.length; i++) {
            planetPaths[i] = new Timeline();
        }
    }


    public static void addPath(State position) {
        SolarSystem solar = position.getSolarSystem();

        for (int i = 0; i < planetPaths.length; i++) {
            addPath(solar, 0, planetPaths[i], planetBodies[i]);
        }
    }

    private static void addPath(SolarSystem solar, int solarPosition, Timeline planetPath, CelestialBody circle) {
        if (planetPath != null) {
            Planet planet = solar.get(solarPosition);
            Vector3d planetVec = (Vector3d) planet.getLatestPosition();

            Circle body = circle.getBody();
            KeyValue X = new KeyValue(body.centerXProperty(),centerX + (planetVec.getX() / 1e9) / distancePixel);
            KeyValue Y = new KeyValue(body.centerYProperty(),centerY - (planetVec.getY() / 1e9) / distancePixel);
            int size = planetPath.getKeyFrames().size();
            planetPath.getKeyFrames().add(new KeyFrame(Duration.millis(size * keyTime),X,Y));
        }
    }

    public static void transition(Timeline nodePath) {
        nodePath.setCycleCount(1);
        nodePath.setAutoReverse(false);
        nodePath.play();
    }
}
