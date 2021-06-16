package titan_lander.controller;

import titan.space.EngineBurnsData;
import titan.space.Vector3d;
import titan_lander.interfaces.AbstractLander;
import titan_lander.interfaces.ControllerInterface;

public class Controller implements ControllerInterface {

    private final double TITAN_G = 1.352;
    private double xValue;
    private double yValue;
    private double thetaValue;
    private double currentTime;
    // Define functions that change u (acc by main) and v (torque by side) - over time?
    // and keep updating the motion equations?
    // Might be useless, and having an actual function may be best:
    private static final EngineBurnsData[] u = new EngineBurnsData[] {
            // fill? where does the thrust from the lander come from? not fuel?
    };
    private static final EngineBurnsData[] v =  new EngineBurnsData[] {
            // same as u, yet lateral
    };

    public Controller(double t) {
        // what will we need to construct the controller?
        this.currentTime = t;
        this.xValue = ;
        this.yValue = ;
        this.thetaValue = ;
    }

    public void calcAcc(double t) {

        // Motion equations - see how to choose correct value of u and v, depending on the time.
        // if using an array, choose correct index for u and v
        double xMotion = (u[0] * Math.sin(/*theta*/);
        // maybe -> double xMotion = (u * Math.sin(this.getController().getTheta()));
        double yMotion = (u[0] * Math.cos(/*theta*/) - TITAN_G;
        double thetaMotion = v[0];

        xValue = (/*current x value plus*/ xMotion);
        yValue = (/*current y value plus*/ yMotion);
        thetaValue = (/*current z value plus*/ thetaMotion);
    }

    // how to use t?
    @Override
    public double getX(AbstractLander lander, double t) { // maybe no need for parameters
        //return lander.getPosition().getX();
        return xValue;
    }

    @Override
    public double getY(AbstractLander lander, double t) { // maybe no need for parameters
        //return lander.getPosition().getY();
        return yValue;
    }

    @Override
    public double getTheta(AbstractLander lander, double t) { // maybe no need for parameters
        //return lander.getPosition().getZ();
        return thetaValue;
    }
}
