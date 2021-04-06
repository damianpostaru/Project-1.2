package titan.solver;

import titan.interfaces.RateInterface;
import titan.interfaces.StateInterface;
import titan.interfaces.Vector3dInterface;
import titan.space.SolarSystem;
import titan.space.Vector3d;

public class State implements StateInterface {

    private final SolarSystem solarSystem;
    private final Vector3dInterface initialPosition;
    private final Vector3dInterface initialVelocity;

    public State(Vector3dInterface initialPosition, Vector3dInterface initialVelocity) {
        this.initialPosition = initialPosition;
        this.initialVelocity = initialVelocity;
        solarSystem = new SolarSystem(initialPosition, initialVelocity);
    }

    public StateInterface addMul(double step, RateInterface rate) {
        State nextState = cloneState();
        SolarSystem solarSystem = nextState.getSolarSystem();
        Rate r = (Rate) rate;
        Vector3d[] acceleration = r.getAcceleration();
        for (int i = 0; i < solarSystem.size(); i++) {
            solarSystem.get(i).update(acceleration[i], step);
        }
        return nextState;
    }

    public String toString() {
        return "Solar system: " + solarSystem.toString();
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    private State cloneState() {
        State newState = new State(initialPosition, initialVelocity);
        SolarSystem solarSystem = newState.getSolarSystem();
        for (int i = 0; i < this.solarSystem.size(); i++) {
            Vector3dInterface position = this.solarSystem.get(i).getPosition();
            Vector3dInterface velocity = this.solarSystem.get(i).getVelocity();
            solarSystem.get(i).setPosition(new Vector3d(position.getX(), position.getY(), position.getZ()));
            solarSystem.get(i).setVelocity(new Vector3d(velocity.getX(), velocity.getY(), velocity.getZ()));
        }
        return newState;
    }
}
