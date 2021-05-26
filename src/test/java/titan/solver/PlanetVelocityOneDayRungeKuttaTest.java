package titan.solver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import titan.interfaces.Vector3dInterface;
import titan.space.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanetVelocityOneDayRungeKuttaTest {

    private static final List<Vector3d> velocitiesAfterOneDay = new ArrayList<>();
    private static final Solver solver = new Solver();
    private static final int finalTime = 31536000;
    private static final int stepSize = 240;
    private final double ACCURACY = 1;
    private static final Vector3d initialPosition = new Vector3d(-6371e3, 0.1, 0.1);
    private static final Vector3d initialVelocity = new Vector3d(0, 0, 0);
    private static final State initialState = new State(initialPosition, initialVelocity);
    private static State[] rkStates;
    private static double biggestDifference = 0;
    private static int count = 0;

    @BeforeAll
    static void setUp() {
        rkStates = (State[]) solver.rungeKuttaSolve(new Function(), initialState, finalTime, stepSize);
        setVelocitiesAfterOneDay();
    }

    @Test
    void rkSolverSunTest() {
        checkVelocityAfterOneDay(0);
    }

    @Test
    void rkSolverMercuryTest() {
        checkVelocityAfterOneDay(1);
    }

    @Test
    void rkSolverVenusTest() {
        checkVelocityAfterOneDay(2);
    }

    @Test
    void rkSolverEarthTest() {
        checkVelocityAfterOneDay(3);
    }

    @Test
    void rkSolverMoonTest() {
        checkVelocityAfterOneDay(4);
    }

    @Test
    void rkSolverMarsTest() {
        checkVelocityAfterOneDay(5);
    }

    @Test
    void rkSolverJupiterTest() {
        checkVelocityAfterOneDay(6);
    }

    @Test
    void rkSolverSaturnTest() {
        checkVelocityAfterOneDay(7);
    }

    @Test
    void rkSolverTitanTest() {
        checkVelocityAfterOneDay(8);
    }

    @Test
    void rkSolverNeptuneTest() {
        checkVelocityAfterOneDay(9);
    }

    @Test
    void rkSolverUranusTest() {
        checkVelocityAfterOneDay(10);
    }

    private void checkVelocityAfterOneDay(int i) {
        Vector3dInterface expectedVelocity = velocitiesAfterOneDay.get(i);
        Vector3dInterface actualVelocity =
                rkStates[(int) Math.ceil(finalTime/stepSize/365)].getSolarSystem().get(i).getVelocity();
        double difference = expectedVelocity.dist(actualVelocity);
        System.out.println(difference);
        if (difference > biggestDifference) {
            biggestDifference = difference;
            count++;
            System.out.println("Biggest Difference: " + biggestDifference + " count: " + count);
        }
        assertEquals(difference, 0, ACCURACY);
    }

    public static void setVelocitiesAfterOneDay() {
        /* Sun */
        velocitiesAfterOneDay.add(new Vector3d(-1.420389076442240E+01, -4.973297284492950E+00,
                3.994885937524490E-01));
        /* Mercury */
        velocitiesAfterOneDay.add(new Vector3d(3.884392491078140E+04, 5.360531636947680E+03,
                -3.125786933514490E+03));
        /* Venus */
        velocitiesAfterOneDay.add(new Vector3d(-1.639322670514930E+04, -3.120560006320120E+04,
                5.174597592320920E+02));
        /* Earth */
        velocitiesAfterOneDay.add(new Vector3d(5.928403046522200E+03, -2.920157953906050E+04,
                6.971881696493650E-01));
        /* Moon */
        velocitiesAfterOneDay.add(new Vector3d(4.986967717659300E+03, -2.960728368550990E+04,
                8.524558801543940E+01));
        /* Mars */
        velocitiesAfterOneDay.add(new Vector3d(2.485225234741240E+04, -1.583798007887200E+03,
                -6.427590391460160E+02));
        /* Jupiter */
        velocitiesAfterOneDay.add(new Vector3d(1.255229369195120E+04, 3.641275804603610E+03,
                -2.958613480465510E+02));
        /* Saturn */
        velocitiesAfterOneDay.add(new Vector3d(8.218745880546910E+03, 4.057190729265820E+03,
                -3.978397469581910E+02));
        /* Titan */
        velocitiesAfterOneDay.add(new Vector3d(2.710334471640690E+03, 4.237986377019880E+03,
                5.732009053110420E+01));
        /* Neptune */
        velocitiesAfterOneDay.add(new Vector3d(1.068382327996360E+03, 5.354770070321040E+03,
                -1.350884937503410E+02));
        /* Uranus */
        velocitiesAfterOneDay.add(new Vector3d(-4.060543671154650E+03, 5.186736931888330E+03,
                7.208167162787670E+01));
    }
}
