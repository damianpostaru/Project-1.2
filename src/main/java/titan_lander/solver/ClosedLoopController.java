package titan_lander.solver;

import titan.space.Vector3d;
import titan_lander.interfaces.AbstractLander;
import titan_lander.interfaces.ControllerInterface;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;

public class ClosedLoopController implements ControllerInterface {

    private final double minVel = 10.0;
    private final double maxTheta = 0.25*PI;
    private double currentU;
    //private List<Double> allEngineAcc; // TO: have an object-specific allEngineAcc to maybe know how to adjust based on prev calls - like as if we're closer, half the prev call

    public ClosedLoopController() {
        //this.allEngineAcc = new LinkedList<Double>();
    }

    // TO: this one shouldn't need time, since it is not dependent on a pre-defined function over time.
    // For now, i'll leave it since it is defined on the interface.
    // + about the u, we might need to call it just once everytime we generate a new vector
    // (and have it as a class variable, since it's value is not time-dependent anymore)
    // + (and as you'll see, if we call it sequentially like we did when we had time,
    // the u would be different when applied to y, than what it was when prev applied to x,
    // bcs it depends on current pos and vel)
    @Override
    public double getX(AbstractLander lander, double t) {
        currentU = findU(lander);
        return currentU * sin(lander.getPosition().getZ());
    }

    @Override
    public double getY(AbstractLander lander, double t) {
        return currentU * cos(lander.getPosition().getZ()) - TITAN_G;
    }

    @Override
    public double getTheta(AbstractLander lander, double t) {
        return findV(lander);
    }


    // Also the initial factor that is assigned honestly depends on the frequency we call the controller - like a time-step maybe
    // (aka the frequency we want to adjust the landing - i.e get feedback and come up with new values based on that)
    private double findU(AbstractLander lander) {

        double engineAcc = 2.7;

        // Positive x value, out of goal
        if(abs(lander.getPosition().getX()) > 0.1 && lander.getPosition().getX() > 0) {
            if(lander.getVelocity().getX() > -minVel) {
                if(angleWithinError(lander.getPosition().getZ(),0.2,3*PI/2)) {
                    return engineAcc;
                } else {
                    return 0;
                }
            }

        // Negative x value, out of goal
        } else if(abs(lander.getPosition().getX()) > 0.1 && lander.getPosition().getX() < 0) {
            if(lander.getVelocity().getX() < minVel) {
                if(angleWithinError(lander.getPosition().getZ(),0.2,PI/2)) {
                    return engineAcc;
                } else {
                    return 0;
                }
            }

        // Free fall, once we get within the x goal
        } else if(abs(lander.getPosition().getX()) <= 0.1 && abs(lander.getPosition().getX()) >= 0) {
            double maxA = engineAcc - TITAN_G;
            double v0 = lander.getVelocity().getY();
            double dist = (v0*v0) / (2*maxA);

            if(lander.getPosition().getY() < dist && angleWithinError(lander.getPosition().getZ(),0.02,0)) {
                return engineAcc;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private double findV(AbstractLander lander) {

        double torque = 0.1 * PI;
        double targetAngle;

        // Positive x value, out of goal
        if(abs(lander.getPosition().getX()) > 0.1 && lander.getPosition().getX() > 0) {
            if(lander.getVelocity().getX() > -minVel) {
                targetAngle = 3*PI/2;
                return returnV(lander,targetAngle,torque);
            }

        // Negative x value, out of goal
        } else if(abs(lander.getPosition().getX()) > 0.1 && lander.getPosition().getX() < 0) {
            if(lander.getVelocity().getX() < minVel) {
                targetAngle = PI/2;
                return returnV(lander,targetAngle,torque);
            }

        // Free fall, once we get within the x goal
        } else if(abs(lander.getPosition().getX()) <= 0.1 && abs(lander.getPosition().getX()) >= 0) {
            double maxA = torque - TITAN_G;
            double v0 = lander.getVelocity().getY();
            double dist = (v0*v0) / (2*maxA);

            targetAngle = 0;
            if(lander.getPosition().getY() < dist && angleWithinError(lander.getPosition().getZ(),0.02,targetAngle)) {
                return 0;
            } else {
                return returnV(lander,targetAngle,torque);
            }
        }
        return 0;
    }

    private boolean angleWithinError(double angle, double error, double target) {
        //reduce angle to something less than 2 * PI
        while (angle >= 2 * PI) {
            angle -= 2 * PI;
        }

        while (angle <= - 2 * PI) {
            angle += 2 * PI;
        }
        //if the angle is within the error bounds
        return abs(abs(angle) - target) < error;
    }

    private double returnV(AbstractLander lander, double targetAngle, double torque) {
        if(angleWithinError(lander.getPosition().getZ(),0.1,targetAngle)) {
            return 0;
        } else {
            if(lander.getPosition().getZ() < targetAngle) {
                if(lander.getVelocity().getZ() >= maxTheta) {
                    return 0;
                }
                return torque;
            } else {
                if(lander.getVelocity().getZ() <= -maxTheta) {
                    return 0;
                }
                return -torque;
            }
        }
    }
}
