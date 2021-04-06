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
        sunPath = new Path();
        sunPath.getElements().add(new MoveTo(sun.getX(), sun.getY()));

        mercuryPath = new Path();
        mercuryPath.getElements().add(new MoveTo(mercury.getX(), mercury.getY()));

        venusPath = new Path();
        venusPath.getElements().add(new MoveTo(venus.getX(), venus.getY()));

        earthPath = new Path();
        earthPath.getElements().add(new MoveTo(earth.getX(), earth.getY()));

        moonPath = new Path();
        moonPath.getElements().add(new MoveTo(moon.getX(), moon.getY()));

        marsPath = new Path();
        marsPath.getElements().add(new MoveTo(mars.getX(), mars.getY()));

        jupiterPath = new Path();
        jupiterPath.getElements().add(new MoveTo(jupiter.getX(), jupiter.getY()));

        saturnPath = new Path();
        saturnPath.getElements().add(new MoveTo(saturn.getX(), saturn.getY()));

        titanPath = new Path();
        titanPath.getElements().add(new MoveTo(titan.getX(), titan.getY()));

        probePath = new Path();
        probePath.getElements().add(new MoveTo(probe.getX(), probe.getY()));
    }


    public static void addPath(State position) {
        SolarSystem solar = position.getSolarSystem();

        Planet sunPlanet = solar.get(0);
        Planet mercuryPlanet = solar.get(1);
        Planet venusPlanet = solar.get(2);
        Planet earthPlanet = solar.get(3);
        Planet moonPlanet = solar.get(4);
        Planet marsPlanet = solar.get(5);
        Planet jupiterPlanet = solar.get(6);
        Planet saturnPlanet = solar.get(7);
        Planet titanPlanet = solar.get(8);
        Planet probePlanet = solar.get(11);

        Vector3d newSun = (Vector3d) sunPlanet.getPosition();
        Vector3d newMercury = (Vector3d) mercuryPlanet.getPosition();
        Vector3d newVenus = (Vector3d) venusPlanet.getPosition();
        Vector3d newEarth = (Vector3d) earthPlanet.getPosition();
        Vector3d newMoon = (Vector3d) moonPlanet.getPosition();
        Vector3d newMars = (Vector3d) marsPlanet.getPosition();
        Vector3d newJupiter = (Vector3d) jupiterPlanet.getPosition();
        Vector3d newSaturn = (Vector3d) saturnPlanet.getPosition();
        Vector3d newTitan = (Vector3d) titanPlanet.getPosition();
        Vector3d newProbe = (Vector3d) probePlanet.getPosition();

        sunPath.getElements().add(new LineTo(centerX + (newSun.getX() / 1e9) / distancePixel, centerY - (newSun.getY() / 1e9) / distancePixel));

        mercuryPath.getElements().add(new LineTo(centerX + (newMercury.getX() / 1e9) / distancePixel, centerY - (newMercury.getY() / 1e9) / distancePixel));

        venusPath.getElements().add(new LineTo(centerX + (newVenus.getX() / 1e9) / distancePixel, centerY - (newVenus.getY() / 1e9) / distancePixel));

        earthPath.getElements().add(new LineTo(centerX + (newEarth.getX() / 1e9) / distancePixel, centerY - (newEarth.getY() / 1e9) / distancePixel));

        moonPath.getElements().add(new LineTo(centerX + (newMoon.getX() / 1e9) / distancePixel, centerY - (newMoon.getY() / 1e9) / distancePixel));

        marsPath.getElements().add(new LineTo(centerX + (newMars.getX() / 1e9) / distancePixel, centerY - (newMars.getY() / 1e9) / distancePixel));

        jupiterPath.getElements().add(new LineTo(centerX + (newJupiter.getX() / 1e9) / distancePixel, centerY - (newJupiter.getY() / 1e9) / distancePixel));

        saturnPath.getElements().add(new LineTo(centerX + (newSaturn.getX() / 1e9) / distancePixel, centerY - (newSaturn.getY() / 1e9) / distancePixel));

        titanPath.getElements().add(new LineTo(centerX + (newTitan.getX() / 1e9) / distancePixel, centerY - (newTitan.getY() / 1e9) / distancePixel));

        probePath.getElements().add(new LineTo(centerX + (newProbe.getX() / 1e9) / distancePixel, centerY - (newProbe.getY() / 1e9) / distancePixel));
    }


    public static void transition(CelestialBody node, Path nodePath) {
        PathTransition bodyTransition = new PathTransition();
        bodyTransition.setDuration(Duration.seconds(12));
        bodyTransition.setNode(node.getBody());
        bodyTransition.setPath(nodePath);
        bodyTransition.play();
    }
}
