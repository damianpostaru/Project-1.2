package titan_lander.solver;

import java.util.*;

//class to generate 1D perlin noise

public class Noise {
    
    private double[] ypoints;
    private double[] xpoints;
    private Random random;
    private int start;
    private int end;

    public Noise(double amplitude, int x0 , int xEnd, double stepSize)
    {
        start = x0;
        end = xEnd;
        random = new Random();
        int delta = xEnd - x0;
        ypoints = new double[(int)(delta/stepSize) + 1];
        xpoints = new double[(int)(delta/stepSize) + 1];
        generatePoints(amplitude, start, stepSize);
    }
    //interpolates the value of a function between the points a and b,
    //with 0 <= x <= 1 specifying where between a and b
    //using cosine interpolation
    private double interpolateGraph(double a, double b, double x)
    {
        double x2 = (1 - Math.cos(x * Math.PI))/2.0;
        return (a * (1 - x2) + b*x2);
    }
    //get the value of the function at a point x
    public double getValue(double x)
    {
        if(x > end || x < start)
        {
            System.out.println();
            throw new IllegalArgumentException("X : " + x + " is not wihin range of the function");
        }else
        {
            double value = 0;
            double a = 0;
            double b = 0;
            double percentage =0;

            //find the two points that x is between and calculate the percantage where x is located
            for (int i = 0; i < xpoints.length - 1; i++) 
            {
                if(xpoints[i] <= x && x <= xpoints[i+1])
                {
                    a = ypoints[i];
                    b = ypoints[i + 1];
                    percentage = (x - xpoints[i])/(xpoints[i+1] - xpoints[i]);
                }   
            }
            //interpolate the value and include overtones
            value = interpolateGraph(a, b, percentage);
            return value;
        }
    }
    //returns a random double between -1 and 1
    private double randomDouble()
    {
        return random.nextDouble() * 2 - 1;
    }

    private void generatePoints(double amp, double x0, double step)
    {
        for (int i = 0; i < ypoints.length; i++) 
        {
            ypoints[i] = randomDouble() * amp;    
            xpoints[i] = x0 + i*step;
        }
    }
}