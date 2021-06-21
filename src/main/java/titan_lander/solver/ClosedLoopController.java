package titan_lander.solver;

import titan.space.Vector3d;
import titan_lander.interfaces.AbstractLander;
import titan_lander.interfaces.ControllerInterface;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;

public class ClosedLoopController implements ControllerInterface {

    // TAKE OUT (TO) - Notes to remove later
    // TO: We shouldn't rly need to consider time on this one, u and v are obtained in a different manner
    LanderBurnsData[] landerBurnsData = {};
    private final double TITAN_G = 1.352;
    private Vector3d currentPosition;
    private Vector3d currentVelocity;
    private double currentU; // TO: reason for this class variable explained line 29-33
    private final List<Double> allEngineAcc = new LinkedList<>(); // TO: have an object-specific allEngineAcc to maybe know how to adjust based on prev calls - like as if we're closer, half the prev call

    public ClosedLoopController() {
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
        //
        currentU = findU(lander);
        return currentU * sin(lander.getPosition().getZ());
    }

    @Override
    public double getY(AbstractLander lander, double t) {
        double u = findU(lander);
        return currentU * cos(lander.getPosition().getZ()) - TITAN_G;
    }

    @Override
    public double getTheta(AbstractLander lander, double t) {
        return findV(lander);
    }

    // TO: maybe no need for this, just a way to get the current pos/vel
    // (put in line 36 if needed - if we keep the same method of getting the vector:
    // Vector3d controllerAcc = new Vector3d(controller.getX(this, t), controller.getY(this, t), controller.getTheta(this, t));)
    private void setCurrentPosition(AbstractLander lander) {
        currentPosition.setX(lander.getPosition().getX());
        currentPosition.setZ(lander.getPosition().getY());
        currentPosition.setZ(lander.getPosition().getZ());
    }

    // TO: same note as in setCurrentPosition(...)
    private void setCurrentVelocity(AbstractLander lander) {
        currentVelocity.setX(lander.getVelocity().getX());
        currentVelocity.setY(lander.getVelocity().getY());
        currentVelocity.setZ(lander.getVelocity().getZ());
    }

    // TO: This new u is dependent on the *current position and velocity*
    // So it should result in a value that ponders both of these ^^^^^
    // Also the initial factor that is assigned honestly depends on the frequency we call the controller - like a time-step maybe
    // (aka the frequency we want to adjust the landing - i.e get feedback and come up with new values based on that)
    private double findU(AbstractLander lander) {
        // TO <-> MAIN ISSUE <->
        // Basically, we need to find a good way to come up with u, using all the info (current pos and vel) we might need
        // Positive x value, out of goal
        if(abs(currentPosition.getX()) > 0.1 && currentPosition.getX() > 0) {
            allEngineAcc.add(/*the u we came up with - create a series of if conditions that comes up with u based on all the info??*/);
            return /*the u we came up with*/;
        }
        return 0;
    }

    // TO: Same logic as in findU(), but adjusted to torque
    private double findV(AbstractLander lander) {
        // TO: Same logic as in findU()
        return 0;
    }
}
