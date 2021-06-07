package io.group8.titan.simulator;
/* THIS CLASS WAS USED TO APPROXIMATE THE REQUIRED INITIAL CONDITIONS, TO USE IT SOME LINES IN SOLVER RELATED TO THE GUI NEED TO BE COMMENTED OUT (ACCESSTIMES METHODS).*/

import io.group8.titan.interfaces.Vector3dInterface;
import io.group8.titan.space.Vector3d;

public class SimMain {
    public static void main(String[] args) {
//        ProbeSimulator probeSimulator = new ProbeSimulator();
//        Vector3d[] trajectory = (Vector3d[]) probeSimulator.trajectory(new Vector3d(0.1, -6371e3, 0.1), new Vector3d(18000, -29350, -1200), 31556926, 60);
////        for (Vector3d position : trajectory) {
////        System.out.println(position);
////        }
//        System.out.println(trajectory[trajectory.length-1]);
//        Vector3d best = null;
//        double bestDist = Double.MAX_VALUE;
//        Vector3d titanPos = new Vector3d(8.790383753640466E11,-1.2038032274305574E12,-1.440177234267432E10);
//        double titanR = 2.5755e6;
//        double bestX = Double.MAX_VALUE;
//        double bestY = Double.MAX_VALUE;
//        double bestZ = Double.MAX_VALUE;
//
//        double x = 18044.5;
//        double y = -29351.0;
//        double z = -819.35;
//
//        while (bestDist > 1E5) {
//            int count = 1;
//            for (int i = -2; i <= 2; i+=0.5) {
//                System.out.println("Jere");
//                for (int j = -2; j <= 2; j += 0.5) {
//                    for (int l = -2; l <= 2; l += 0.5) {
//                        x += i;
//                        y += j;
//                        z += l;
//                        ProbeSimulator probeSimulator = new ProbeSimulator();
//                        Vector3d vel0 = new Vector3d(x, y, z);
//                        Vector3d[] trajectory = (Vector3d[]) probeSimulator.trajectory(new Vector3d(0.1, -6371e3, 0.1), vel0, 31556926, 60);
//                        double dist = titanPos.dist(trajectory[trajectory.length - 1]) - 2.5755e6;
//                        if (dist < bestDist) {
//                            if (dist < 0) {
//                                System.out.println("We in the zone");
//                            }
//                            best = new Vector3d(x, y, z);
//                            bestDist = dist;
//                            bestX = best.getX();
//                            bestY = best.getY();
//                            bestZ = best.getZ();
//                            System.out.println("new best: " + bestDist + " vector: " + best);
//                            System.out.println("new best diff: " + titanPos.sub(trajectory[trajectory.length - 1]));
//                        }
//
//                        //radius of titan: 2.5755e6
//                    }
//                }
//            }
//            count++;
//            System.out.println("Iteration: " + count);
//            x = bestX;
//            y = bestY;
//            z = bestZ;
//        }
//        System.out.println("Done");

        Vector3dInterface initialPosition = new Vector3d(0.1, -6371e3, 0.1);
        Vector3dInterface initialVelocity = new Vector3d(18044.44, -29351.0, -819.35);

        ProbeSimulator probeSimulator = new ProbeSimulator();
        Vector3d[] trajectory = (Vector3d[]) probeSimulator.trajectory(initialPosition, initialVelocity, 31556926, 60);
        System.out.println(trajectory.length);
    }
}
