package titan_lander.solver;

import titan.space.Vector3d;
import titan_lander.interfaces.AbstractLander;

import java.util.Random;

public class Wind
{
    private boolean leftOrRight;//true is wind speed to positive x, false to negative x
    private Random random;
    private double windFlipChance;
    private double deltaFactor;
    private final double DENSITY = 1;
    private final double DRAG_COEFF = 0.47;
    private final double AREA = 3.52*3.52*Math.PI;//pi * r^2
    public Wind(double flipChance,double deltaFactor)
    {
        this.deltaFactor = deltaFactor;
        windFlipChance = flipChance;
        leftOrRight = true;
        random = new Random();
    }
    //get the force of the wind
    public Vector3d getWindForce(AbstractLander lander)
    {
        //get the velocity without the angular velocity
        Vector3d landerSpeed = new Vector3d(lander.getPosition().getX(), lander.getPosition().getY(),0);

        Vector3d windSpeed = new Vector3d(getWind(lander.getPosition().getY()),0,0);
        Vector3d deltaVelocity = (Vector3d) windSpeed.sub(lander.getVelocity());

        Vector3d force = (Vector3d) squareVector(deltaVelocity).mul( DENSITY * DRAG_COEFF * AREA * 0.5);
        return  force;
    }

    //returns the wind at an altitude with random fluctuations added
    private double getWind(double altitude)
    {
        double windSpeed = getWindAtAltitude(altitude);
        windSpeed *= getRandomFactor();
        if(random.nextDouble() < windFlipChance)//let the wind have a chance to change direction
        {
            leftOrRight = !leftOrRight;
        }
        return leftOrRight ? windSpeed : windSpeed*-1;
    }

    //returns the windspeed at an altitude using a function made using the data from the Huygens mission
    //approximating the data using a combination of linear functions
    private double getWindAtAltitude(double altitude)
    {
        double value = 0;
        if(altitude >= 120)
        {
            value = 100.0;
        }else if(altitude < 120 && altitude >= 73)
        {
            value = (95.0/47) * altitude + (100 - (95.0/47)*120);
        }else if(altitude < 73 && altitude >= 65)
        {
            value = (-30.0/8) * altitude + (35 - (-30.0/8)*65);
        }
        else
        {
            value = 35.0/65 * altitude;
        }
        return value;
    }

    //returns a random number with the value 1 +- deltafactor
    private double getRandomFactor()
    {
        double factor = 1 + randomDouble() * deltaFactor;
        return factor;
    }

    //returns a random double between -1 and 1
    private double randomDouble()
    {
        return random.nextDouble() * 2 - 1;
    }

    private Vector3d squareVector(Vector3d v)
    {
        double x = v.getX() * v.getX();
        double y = v.getY() * v.getY();
        double z = v.getZ() * v.getZ();

        return new Vector3d(x,y,z);
    }
}