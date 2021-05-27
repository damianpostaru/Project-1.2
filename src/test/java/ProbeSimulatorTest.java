import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import titan.ProbeSimulator;
import titan.interfaces.ProbeSimulatorInterface;
import titan.interfaces.Vector3dInterface;
import titan.space.EngineBurnsData;
import titan.space.Shuttle;
import titan.space.Vector3d;
import titan.solver.State;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;

class ProbeSimulatorTest {

    @Test void testFuelMassNotNegative() {
        Vector3dInterface probe_relative_position = new Vector3d(6371e3,0,0);
        Vector3dInterface probe_relative_velocity = new Vector3d(0,0,0);
        State initialState = new State(probe_relative_position,probe_relative_velocity);
        Shuttle shuttle = initialState.getSolarSystem().getShuttle();
        double time = Double.MAX_VALUE;//test for a long time to make sure all engine burns have been executed
        assertEquals(0, Math.min(0,shuttle.calcFuelMass(time)));

    }

    @Test void testFuelMassNotTooMuchLeft() {
        Vector3dInterface probe_relative_position = new Vector3d(6371e3,0,0);
        Vector3dInterface probe_relative_velocity = new Vector3d(0,0,0);
        State initialState = new State(probe_relative_position,probe_relative_velocity);
        Shuttle shuttle = initialState.getSolarSystem().getShuttle();
        double time = Double.MAX_VALUE;//test for a long time to make sure all engine burns have been executed
        double maxFuelLeft = 150000;//the maximum amount of fuel we want left at the end of the mission
        assertEquals(maxFuelLeft, Math.max(maxFuelLeft,shuttle.calcFuelMass(time)));

    }

    @Test void testForceNotTooMuch() {
        State initialState = new State(new Vector3d(0,0,0),new Vector3d(0,0,0));
        Shuttle shuttle = initialState.getSolarSystem().getShuttle();
        double force = 0;
        EngineBurnsData[] engineBurns = shuttle.getBurnData();
        for (int i = 0; i < engineBurns.length; i++) {
            force += engineBurns[i].getForce();
        }
        double maxForce = 30e6;
        assertEquals(maxForce, Math.max(maxForce,force/ engineBurns.length));
    }

    @Test void testForceNotNegative() {
        State initialState = new State(new Vector3d(0,0,0),new Vector3d(0,0,0));
        Shuttle shuttle = initialState.getSolarSystem().getShuttle();
        double minForce = 0;
        EngineBurnsData[] engineBurns = shuttle.getBurnData();
        for (int i = 0; i < engineBurns.length; i++) {
            if(engineBurns[i].getForce() < minForce)
            minForce = engineBurns[i].getForce();
        }
        double maxForce = 30e6;
        assertEquals(0, Math.max(0,minForce));
    }




}
