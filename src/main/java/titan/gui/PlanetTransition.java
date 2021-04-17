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

    public static Planet sunPlanet, mercuryPlanet, venusPlanet, earthPlanet, moonPlanet,
    marsPlanet, jupiterPlanet, saturnPlanet, titanPlanet, probePlanet;
    public static Vector3d newSun, newMercury, newVenus, newEarth, newMoon, newMars, newJupiter,
    newSaturn, newTitan, newProbe;

    public static void createPath() {
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

        sunPath.getElements().add(new MoveTo(sun.getX(), sun.getY()));
        mercuryPath.getElements().add(new MoveTo(mercury.getX(), mercury.getY()));
        venusPath.getElements().add(new MoveTo(venus.getX(), venus.getY()));
        earthPath.getElements().add(new MoveTo(earth.getX(), earth.getY()));
        moonPath.getElements().add(new MoveTo(moon.getX(), moon.getY()));
        marsPath.getElements().add(new MoveTo(mars.getX(), mars.getY()));
        jupiterPath.getElements().add(new MoveTo(jupiter.getX(), jupiter.getY()));
        saturnPath.getElements().add(new MoveTo(saturn.getX(), saturn.getY()));
        titanPath.getElements().add(new MoveTo(titan.getX(), titan.getY()));
        probePath.getElements().add(new MoveTo(probe.getX(), probe.getY()));
    }


    public static void addPath(State position) {
        SolarSystem solar = position.getSolarSystem();

        addPath(solar, 0, sunPlanet, newSun, sunPath);
        addPath(solar, 1, mercuryPlanet, newMercury, mercuryPath);
        addPath(solar, 2, venusPlanet, newVenus, venusPath);
        addPath(solar, 3, earthPlanet, newEarth, earthPath);
        addPath(solar, 4, moonPlanet, newMoon, moonPath);
        addPath(solar, 5, marsPlanet, newMars, marsPath);
        addPath(solar, 6, jupiterPlanet, newJupiter, jupiterPath);
        addPath(solar, 7, saturnPlanet, newSaturn, saturnPath);
        addPath(solar, 8, titanPlanet, newTitan, titanPath);
        addPath(solar, 11, probePlanet, newProbe, probePath);

    }

    private static void addPath(SolarSystem solar, int solarPosition, Planet planet,
                               Vector3d planetVec, Path planetPath) {
        planet = solar.get(solarPosition);
        planetVec = (Vector3d) planet.getPosition();
        planetPath.getElements().add(new LineTo(centerX + (planetVec.getX() / 1e9) / distancePixel,
                centerY - (planetVec.getY() / 1e9) / distancePixel));
    }

    public static void transition(CelestialBody node, Path nodePath) {
        PathTransition bodyTransition = new PathTransition();
        bodyTransition.setDuration(Duration.seconds(12));
        bodyTransition.setNode(node.getBody());
        bodyTransition.setPath(nodePath);
        bodyTransition.play();
    }
}
