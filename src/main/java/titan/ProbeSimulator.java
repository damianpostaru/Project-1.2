package titan;

import titan.interfaces.ProbeSimulatorInterface;
import titan.interfaces.Vector3dInterface;
import titan.solver.*;
import titan.space.SolarSystemData;
import titan.space.Vector3d;

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
//        System.out.println("Probe trajectory: ");
        Solver solver = new Solver();
        State[] states = (State[]) solver.solve(new Function(), initialState, finalTime, stepSize);
        double bestDist = Double.MAX_VALUE;
        double bestTime = 0;
        int bestIndex = -1;
        Vector3d bestDiff = null;
        int planetID = 3;
        for (int i = 0; i < states.length; i++) {
            trajectory[i] = states[i].getSolarSystem().getShuttle().getPosition();


            double dist = states[i].getSolarSystem().get(planetID).getPosition().dist(states[i].getSolarSystem().getShuttle().getPosition());
            if (dist < bestDist) {
                bestIndex = i;
                bestDist = dist;
                bestTime = i * stepSize;
                bestDiff = (Vector3d) states[i].getSolarSystem().get(planetID).getPosition().sub(states[i].getSolarSystem().getShuttle().getPosition());
            }
            if(i > 590425 && i < 590450) {
                //System.out.println((Vector3d) states[i].getSolarSystem().getShuttle().getPosition());
                //System.out.println((Vector3d) states[i].getSolarSystem().get(planetID).getPosition());
                System.out.println((Vector3d) states[i].getSolarSystem().getShuttle().getPosition().sub(states[i].getSolarSystem().get(planetID).getPosition()));
            }
        }
        Vector3d extraDiff = (Vector3d) states[bestIndex-1].getSolarSystem().get(planetID).getPosition().sub(states[bestIndex-1].getSolarSystem().getShuttle().getPosition());
        System.out.println("relative velocity: " + (Vector3d) bestDiff.sub(extraDiff));
        System.out.println("Time of closest approach: " + bestTime);
        System.out.println("Distance of closest approach: " + bestDist);
        System.out.println("Diff of closest approach: " + bestDiff);
        System.out.println("Shuttle position of closest approach: " + states[bestIndex].getSolarSystem().get(planetID).getPosition());
        System.out.println("Body position of closest approach: " + states[bestIndex].getSolarSystem().getShuttle().getPosition());

        if (bestDist <= SolarSystemData.radii[planetID]) {
            System.out.println("Titan has been hit");
        }
        return trajectory;
    }
}
