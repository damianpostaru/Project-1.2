package io.group8.titan.simulator;

import io.group8.titan.interfaces.ProbeSimulatorInterface;
import io.group8.titan.interfaces.Vector3dInterface;
import io.group8.titan.solver.Function;
import io.group8.titan.solver.Solver;
import io.group8.titan.solver.State;
import io.group8.titan.space.SolarSystemData;
import io.group8.titan.space.Vector3d;

public class ProbeSimulator implements ProbeSimulatorInterface {
    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface initialPosition, Vector3dInterface initialVelocity, double[] outputTimes) {
        Vector3dInterface[] trajectory = new Vector3d[outputTimes.length];
        State initialState = new State(initialPosition, initialVelocity);
        Solver solver = new Solver();
        State[] states = (State[]) solver.solve(new Function(), initialState, outputTimes);
        for (int i = 0; i < states.length; i++) {
            trajectory[i] = states[i].getSolarSystem().getShuttle().getPosition();
        }
        return trajectory;
    }

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface initialPosition, Vector3dInterface initialVelocity, double finalTime, double stepSize) {
        Vector3dInterface[] trajectory = new Vector3d[(int) Math.ceil(finalTime / stepSize) + 1];
        State initialState = new State(initialPosition, initialVelocity);
        System.out.println("Probe starting position: " + initialPosition);
        System.out.println("Probe starting velocity: " + initialVelocity);
        Solver solver = new Solver();
        State[] states = (State[]) solver.solve(new Function(), initialState, finalTime, stepSize);
        double bestDist = Double.MAX_VALUE;
        double bestTime = 0;
        int bestIndex = -1;
        int planetID = 8;
        for (int i = 0; i < states.length; i++) {
            trajectory[i] = states[i].getSolarSystem().getShuttle().getPosition();
//            System.out.println(trajectory[i]);

            double dist = states[i].getSolarSystem().get(planetID).getPosition().dist(states[i].getSolarSystem().getShuttle().getPosition());
            if (dist < bestDist) {
                bestIndex = i;
                bestDist = dist;
                bestTime = i * stepSize;
            }

        }
//        System.out.println(Arrays.toString(trajectory));

        System.out.println("Time of closest approach: " + bestTime);
        System.out.println("Distance of closest approach: " + bestDist);
        System.out.println("Shuttle position of closest approach: " + states[bestIndex].getSolarSystem().get(planetID).getPosition());
        System.out.println("Body position of closest approach: " + states[bestIndex].getSolarSystem().getShuttle().getPosition());

        if (bestDist <= SolarSystemData.radii[planetID]) {
            System.out.println("Titan has been hit");
        }
        return trajectory;
    }
}
