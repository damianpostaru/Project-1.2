package titan.gui;

import javafx.animation.PathTransition;
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
        addStartPos(sunPath, sun);
        addStartPos(mercuryPath, mercury);
        addStartPos(venusPath, venus);
        addStartPos(earthPath, earth);
        addStartPos(moonPath, moon);
        addStartPos(marsPath, mars);
        addStartPos(jupiterPath, jupiter);
        addStartPos(saturnPath, saturn);
        addStartPos(titanPath, titan);
        addStartPos(probePath, probe);
        addStartPos(neptunePath, neptune);
        addStartPos(uranusPath, uranus);
    }

    public static void addStartPos(Path planetPath, CelestialBody planet) {
        planetPath.getElements().add(new MoveTo(planet.getX(), planet.getY()));
    }

    private static void instantiatePaths() {
        sunPath = new Path();
        mercuryPath = new Path();
        venusPath = new Path();
        earthPath = new Path();
        moonPath = new Path();
        marsPath = new Path();
        jupiterPath = new Path();
        saturnPath = new Path();
        titanPath = new Path();
        probePath = new Path();
        neptunePath = new Path();
        uranusPath = new Path();
    }


    public static void addPath(State position) {
        SolarSystem solar = position.getSolarSystem();

        addPath(solar, 0, sunPath);
        addPath(solar, 1, mercuryPath);
        addPath(solar, 2, venusPath);
        addPath(solar, 3, earthPath);
        addPath(solar, 4, moonPath);
        addPath(solar, 5, marsPath);
        addPath(solar, 6, jupiterPath);
        addPath(solar, 7, saturnPath);
        addPath(solar, 8, titanPath);
        addPath(solar, 9, neptunePath);
        addPath(solar, 10, uranusPath);
        addPath(solar, 11, probePath);

    }

    private static void addPath(SolarSystem solar, int solarPosition, Path planetPath) {
        Planet planet = solar.get(solarPosition);
        Vector3d planetVec = (Vector3d) planet.getPosition();
        planetPath.getElements().add(new LineTo(centerX + (planetVec.getX() / 1e9) / distancePixel, centerY - (planetVec.getY() / 1e9) / distancePixel));
    }

    public static void transition(CelestialBody node, Path nodePath) {
        PathTransition bodyTransition = new PathTransition();
        bodyTransition.setDuration(Duration.seconds(15));
        bodyTransition.setNode(node.getBody());
        bodyTransition.setPath(nodePath);
        bodyTransition.play();
    }
}
