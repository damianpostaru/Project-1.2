package GUI;

import javafx.scene.shape.Circle;

public class CelestialBody {

    private String name;
    private double posX, posY;
    private Circle celestialBody;

    public CelestialBody(String name, double posX, double posY) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        celestialBody = new Circle(posX, posY, 10);
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
        return celestialBody;
    }
}
