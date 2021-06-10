package io.group8.titan.solver;

import io.group8.titan.interfaces.RateInterface;
import io.group8.titan.interfaces.StateInterface;
import io.group8.titan.interfaces.Vector3dInterface;
import io.group8.titan.space.Planet;
import io.group8.titan.space.SolarSystem;
import io.group8.titan.space.Vector3d;

public class State implements StateInterface {

    public static SolarSystem solarSystem;
    private int stateIndex;

    public State() {
        stateIndex = 0;
        solarSystem = SolarSystem.getInstance();
    }

    public State newState() {
        State newState = new State();
        newState.stateIndex = this.stateIndex + 1;
        return newState;
    }

    public StateInterface addMul(double step, RateInterface rate) {
        State nextState = new State();
        Rate r = (Rate) rate;
        Vector3d[] acceleration = r.getAcceleration();
        for (int i = 0; i < solarSystem.size(); i++) {
            solarSystem.get(i).update(step, acceleration[i]);
        }
        nextState.stateIndex = this.stateIndex + 1;
        return nextState;
    }

    public StateInterface addMulRunge(double step, RateInterface rate) {
        State nextState = new State();
        Rate r = (Rate) rate;
        Vector3d[] acceleration = r.getAcceleration();
        for (int i = 0; i < solarSystem.size(); i++) {
            solarSystem.get(i).update(step, acceleration[i]);
        }
        return nextState;
    }

    public Planet getPlanet(int planetIndex) {
        return solarSystem.get(planetIndex);
    }

    public Vector3dInterface getPlanetPosition(int planetIndex) {
        return solarSystem.get(planetIndex).getPosition(stateIndex);
    }

    public void addPosition(int planetIndex, Vector3dInterface position){
        solarSystem.get(planetIndex).setPosition((Vector3d) position, stateIndex);
    }

    public String toString() {
        return "Solar system: " + solarSystem.toString();
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }
}
