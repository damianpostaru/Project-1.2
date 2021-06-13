package io.group8.titan.solver.velocity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.group8.titan.interfaces.Vector3dInterface;
import io.group8.titan.solver.Function;
import io.group8.titan.solver.Solver;
import io.group8.titan.solver.State;
import io.group8.titan.space.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanetVelocityOneDayVerletTest {

    private static final List<Vector3d> velocitiesAfterOneDay = new ArrayList<>();
    private static final Solver solver = new Solver();
    private static final double finalTime = 31536000;
    private static final double stepSize = 500;
    private final double ACCURACY = 2380;
    private static final Vector3d initialPosition = new Vector3d(-6371e3, 0.1, 0.1);
    private static final Vector3d initialVelocity = new Vector3d(0, 0, 0);
    private static final State initialState = new State(initialPosition, initialVelocity);
    private static State[] verletStates;
    private static double biggestDifference = 0;
    private static int count = 0;

    @BeforeAll
    static void setUp() {
        verletStates = (State[]) solver.solve(new Function(), initialState, finalTime, stepSize);
        setVelocitiesAfterOneDay();
    }

    @Test
    void verletSolverSunTest() {
        checkVelocityAfterOneDay(0);
    }

    @Test
    void verletSolverMercuryTest() {
        checkVelocityAfterOneDay(1);
    }

    @Test
    void verletSolverVenusTest() {
        checkVelocityAfterOneDay(2);
    }

    @Test
    void verletSolverEarthTest() {
        checkVelocityAfterOneDay(3);
    }

    @Test
    void verletSolverMoonTest() {
        checkVelocityAfterOneDay(4);
    }

    @Test
    void verletSolverMarsTest() {
        checkVelocityAfterOneDay(5);
    }

    @Test
    void verletSolverJupiterTest() {
        checkVelocityAfterOneDay(6);
    }

    @Test
    void verletSolverSaturnTest() {
        checkVelocityAfterOneDay(7);
    }

    @Test
    void verletSolverTitanTest() {
        checkVelocityAfterOneDay(8);
    }

    @Test
    void verletSolverNeptuneTest() {
        checkVelocityAfterOneDay(9);
    }

    @Test
    void verletSolverUranusTest() {
        checkVelocityAfterOneDay(10);
    }

    private void checkVelocityAfterOneDay(int i) {
        Vector3dInterface expectedVelocity = velocitiesAfterOneDay.get(i);
        Vector3dInterface actualVelocity = verletStates[(int) Math.ceil(finalTime / stepSize / 365)].getSolarSystem().get(i).getVelocity();
        double difference = expectedVelocity.dist(actualVelocity);
        System.out.println(difference / 1000);
        if (difference > biggestDifference) {
            biggestDifference = difference;
            count++;
            System.out.println("Biggest Difference: " + biggestDifference + " count: " + count);
        }
        assertEquals(difference, 0, ACCURACY);
    }

    public static void setVelocitiesAfterOneDay() {
        /* Sun */
        velocitiesAfterOneDay.add(new Vector3d(-1.420389076442240E+01, -4.973297284492950E+00, 3.994885937524490E-01));
        /* Mercury */
        velocitiesAfterOneDay.add(new Vector3d(3.884392491078140E+04, 5.360531636947680E+03, -3.125786933514490E+03));
        /* Venus */
        velocitiesAfterOneDay.add(new Vector3d(-1.639322670514930E+04, -3.120560006320120E+04, 5.174597592320920E+02));
        /* Earth */
        velocitiesAfterOneDay.add(new Vector3d(5.928403046522200E+03, -2.920157953906050E+04, 6.971881696493650E-01));
        /* Moon */
        velocitiesAfterOneDay.add(new Vector3d(4.986967717659300E+03, -2.960728368550990E+04, 8.524558801543940E+01));
        /* Mars */
        velocitiesAfterOneDay.add(new Vector3d(2.485225234741240E+04, -1.583798007887200E+03, -6.427590391460160E+02));
        /* Jupiter */
        velocitiesAfterOneDay.add(new Vector3d(1.255229369195120E+04, 3.641275804603610E+03, -2.958613480465510E+02));
        /* Saturn */
        velocitiesAfterOneDay.add(new Vector3d(8.218745880546910E+03, 4.057190729265820E+03, -3.978397469581910E+02));
        /* Titan */
        velocitiesAfterOneDay.add(new Vector3d(2.710334471640690E+03, 4.237986377019880E+03, 5.732009053110420E+01));
        /* Neptune */
        velocitiesAfterOneDay.add(new Vector3d(1.068382327996360E+03, 5.354770070321040E+03, -1.350884937503410E+02));
        /* Uranus */
        velocitiesAfterOneDay.add(new Vector3d(-4.060543671154650E+03, 5.186736931888330E+03, 7.208167162787670E+01));
    }

}