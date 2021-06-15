package titan_lander.solver;

import titan.space.Vector3d;

import java.util.Random;

public class Wind
{
    private boolean leftOrRight;//true is wind speed to positive x, false to negative x
    private Random random;
    private double deltaFactor;
    private final double DENSITY = 1;
    private final double DRAG_COEFF = 0.47;
    private final double AREA = 3.52*3.52*Math.PI;//pi * r^2
    private double minFlipHeight;
    private double maxFlipHeight;
    Noise noise;

    public Wind(double deltaFactor,int maxHeight)
    {
        random = new Random();
        noise = new Noise(1,0, maxHeight , 500);
        this.deltaFactor = deltaFactor;
        if(randomDouble() < 0)
        {
            leftOrRight = true;
        }else
        {
            leftOrRight = false;
        }


        //choose random heights
        double height1 = maxHeight * random.nextDouble();
        double height2 = maxHeight * random.nextDouble();
        if(height1 < height2)
        {
            minFlipHeight = height1;
            maxFlipHeight = height2;
        }else
        {
            minFlipHeight = height2;
            maxFlipHeight = height1;
        }
    }

    //get the force of the wind
    public Vector3d getWindForce(Vector3d landerVelocity,double altitude)
    {
        //get the velocity without the angular velocity
        Vector3d landerSpeed = new Vector3d(landerVelocity.getX(), landerVelocity.getY(),0);
        double windVelocity = getWind(altitude);
        Vector3d windSpeedVector = new Vector3d(windVelocity,0,0);//new Vector3d(100,0,0);
        Vector3d deltaVelocity = (Vector3d) windSpeedVector.sub(landerSpeed);

        Vector3d force = (Vector3d) squareVector(deltaVelocity).mul( DENSITY * DRAG_COEFF * AREA * 0.5);

        return  force;
    }

    //returns the wind at an altitude with random fluctuations added
    public double getWind(double altitude)
    {
        double windSpeed = getWindAtAltitude(altitude);
        windSpeed *= getRandomFactor(altitude);
        boolean direction = leftOrRight;
        if(minFlipHeight <= altitude && altitude <= maxFlipHeight)//let the wind have a chance to change direction
        {
            direction = !leftOrRight;
        }

        return direction ? windSpeed : windSpeed*-1;
    }

    //returns the windspeed at an altitude using a function made using the data from the Huygens mission
    //approximating the data using a combination of linear functions
    private double getWindAtAltitude(double altitude)
    {
        double value = 0;
        if(altitude >= 120000)
        {
            value = 100.0;
        }else if(altitude < 120000 && altitude >= 73000)
        {
            value = (95.0/47000) * altitude + (100 - (95.0/47000)*120000);
        }else if(altitude < 73000 && altitude >= 65000)
        {
            value = (-30.0/8000) * altitude + (35 - (-30.0/8000)*65000);
        }
        else
        {
            value = 35.0/65000 * altitude;
        }
        return value;
    }

    //returns a random number with the value 1 +- deltafactor
    private double getRandomFactor(double x)
    {
        double factor = 1 + noise.getValue(x) * deltaFactor;
        return factor;
    }

    //returns a random double between -1 and 1
    private double randomDouble()
    {
        return random.nextDouble() * 2 - 1;
    }

    private Vector3d squareVector(Vector3d v)
    {
        double norm = v.norm();
        Vector3d dir = (Vector3d) v.mul(1/norm);
        norm *= norm;//get the square of the length

        return (Vector3d) dir.mul(norm);
    }
}