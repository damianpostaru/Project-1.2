package GUI;

import javafx.scene.shape.Circle;

public class CelestialBodyModel {

    private String name;
    private double posX, posY;
    private Circle celestialBodyModel;

    public CelestialBodyModel(String name, double posX, double posY, int radius) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        celestialBodyModel = new Circle(posX, posY, radius);
    }

    /*
        Method to return the name of the CelestialBody.
        @return the String containing the name
     */
    public String getName() {
        return name;
    }

    /*
        Method to return the X-position of the CelestialBody.
        @return the x-position
     */
    public double getX() {
        return posX;
    }

    /*
        Method to return the Y-position of the CelestialBody.
        @return the y-position
     */
    public double getY() {
        return posY;
    }

    /*
        Method to return the circle object that represents the CelestialBody.
        @return the circle object
     */
    public Circle getBody() {
        return celestialBodyModel;
    }
}