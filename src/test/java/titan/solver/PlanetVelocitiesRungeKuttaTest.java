package titan.solver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import titan.interfaces.Vector3dInterface;
import titan.space.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanetVelocitiesRungeKuttaTest {

    private static final List<Vector3d> velocitiesAfterOneDay = new ArrayList<>();
    private static final List<Vector3d> velocitiesAfterOneYear = new ArrayList<>();
    private static final Solver solver = new Solver();
    private static final int finalTime = 31536000;
    private static final int stepSize = 240;
    private final double ACCURACY = 49623050;
    private static final Vector3d initialPosition = new Vector3d(0.1, -6371e3, 0.1);
    private static final Vector3d initialVelocity = new Vector3d(0, 0, 0);
    private static final State initialState = new State(initialPosition, initialVelocity);
    private static State[] rkStates;
    private static double biggestDifference = 0;
    private static int count = 0;

    @BeforeAll
    static void setUp() {
        rkStates = (State[]) solver.rungeKuttaSolve(new Function(), initialState, finalTime, stepSize);
        setVelocitiesAfterOneDay();
        setVelocitiesAfterOneYear();
    }

    @Test
    void rkSolverSunTest() {
        checkVelocityAfterOneDay(0);
        checkVelocityAfterOneYear(0);
    }

    @Test
    void rkSolverMercuryTest() {
        checkVelocityAfterOneDay(1);
        checkVelocityAfterOneYear(1);
    }

    @Test
    void rkSolverVenusTest() {
        checkVelocityAfterOneDay(2);
        checkVelocityAfterOneYear(2);
    }

    @Test
    void rkSolverEarthTest() {
        checkVelocityAfterOneDay(3);
        checkVelocityAfterOneYear(3);
    }

    @Test
    void rkSolverMoonTest() {
        checkVelocityAfterOneDay(4);
        checkVelocityAfterOneYear(4);
    }

    @Test
    void rkSolverMarsTest() {
        checkVelocityAfterOneDay(5);
        checkVelocityAfterOneYear(5);
    }

    @Test
    void rkSolverJupiterTest() {
        checkVelocityAfterOneDay(6);
        checkVelocityAfterOneYear(6);
    }

    @Test
    void rkSolverSaturnTest() {
        checkVelocityAfterOneDay(7);
        checkVelocityAfterOneYear(7);
    }

    @Test
    void rkSolverTitanTest() {
        checkVelocityAfterOneDay(8);
        checkVelocityAfterOneYear(8);
    }

    @Test
    void rkSolverNeptuneTest() {
        checkVelocityAfterOneDay(9);
        checkVelocityAfterOneYear(9);
    }

    @Test
    void rkSolverUranusTest() {
        checkVelocityAfterOneDay(10);
        checkVelocityAfterOneYear(10);
    }

    private void checkVelocityAfterOneDay(int i) {
        Vector3dInterface expectedVelocity = velocitiesAfterOneDay.get(i);
        Vector3dInterface actualVelocity = rkStates[360].getSolarSystem().get(i).getVelocity();
        printVelocity(expectedVelocity.getX(), actualVelocity.getX(), "One Day Expected " +
                "X-Velocity: ", "One Day Actual X-Velocity: ");
        printVelocity(expectedVelocity.getY(), actualVelocity.getY(), "One Day Expected " +
                "Y-Velocity: ", "One Day Actual Y-Velocity: ");
        printVelocity(expectedVelocity.getZ(), actualVelocity.getZ(), "One Day Expected " +
                "Z-Velocity: ", "One Day Actual Z-Velocity: ");
        assertEquals(expectedVelocity.getX(), actualVelocity.getX(), ACCURACY);
        assertEquals(expectedVelocity.getY(), actualVelocity.getY(), ACCURACY);
        assertEquals(expectedVelocity.getZ(), actualVelocity.getZ(), ACCURACY);
    }

    private void checkVelocityAfterOneYear(int i) {
        Vector3dInterface expectedVelocity = velocitiesAfterOneYear.get(i);
        Vector3dInterface actualVelocity =
                rkStates[131400].getSolarSystem().get(i).getVelocity();
        printVelocity(expectedVelocity.getX(), actualVelocity.getX(), "One Year Expected " +
                "X-Velocity: ", "One Year Actual X-Velocity: ");
        printVelocity(expectedVelocity.getY(), actualVelocity.getY(), "One Year Expected " +
                "Y-Velocity: ", "One Year Actual Y-Velocity: ");
        printVelocity(expectedVelocity.getZ(), actualVelocity.getZ(), "One Year Expected " +
                "Z-Velocity: ", "One Year Actual Z-Velocity: ");
        assertEquals(expectedVelocity.getX(), actualVelocity.getX(), ACCURACY);
        assertEquals(expectedVelocity.getY(), actualVelocity.getY(), ACCURACY);
        assertEquals(expectedVelocity.getZ(), actualVelocity.getZ(), ACCURACY);
    }

    private void printVelocity(double z, double z2, String s, String s2) {
        if (Math.abs(z - z2) > ACCURACY) {
            calculateBiggestDifference(z, z2);
            System.out.println(s + z);
            System.out.println(s2 + z2);
        }
    }

    private void calculateBiggestDifference(double x, double x2) {
        if (Math.abs(x - x2) > biggestDifference) {
            biggestDifference = Math.abs(x - x2);
            count++;
            System.out.println("Difference: " + biggestDifference + " count: " + count);
        }
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

    public static void setVelocitiesAfterOneYear() {
        /* Sun */
        velocitiesAfterOneYear.add(new Vector3d(-1.066974517312190E+01, -1.143612548437740E+01,
                3.462019547224800E-01));
        /* Mercury */
        velocitiesAfterOneYear.add(new Vector3d(2.750473883640400E+04, 3.377701495512690E+04,
                2.374213743794630E+02));
        /* Venus */
        velocitiesAfterOneYear.add(new Vector3d(-9.000039117559160E+03, 3.371052705031940E+04,
                9.818723126766740E+02));
        /* Earth */
        velocitiesAfterOneYear.add(new Vector3d(5.278155150099900E+03, -2.933726235139810E+04,
                2.829493882106950E+00));
        /* Moon */
        velocitiesAfterOneYear.add(new Vector3d(6.154257720388410E+03, -2.996687959729850E+04,
                -8.851792752802540E+01));
        /* Mars */
        velocitiesAfterOneYear.add(new Vector3d(-2.182576629101100E+04, -6.342607930949870E+03,
                4.028075281420640E+02));
        /* Jupiter */
        velocitiesAfterOneYear.add(new Vector3d(9.164631943293960E+03, 9.769805820601120E+03,
                -2.455256050305460E+02));
        /* Saturn */
        velocitiesAfterOneYear.add(new Vector3d(7.267453937241430E+03, 5.665808328648830E+03,
                -3.874413441311550E+02));
        /* Titan */
        velocitiesAfterOneYear.add(new Vector3d(4.600295683062070E+03, 1.023830877803220E+04,
                -2.479030926015200E+03));
        /* Neptune */
        velocitiesAfterOneYear.add(new Vector3d(8.633265089089730E+02, 5.393369612798610E+03,
                -1.308834502370690E+02));
        /* Uranus */
        velocitiesAfterOneYear.add(new Vector3d(-4.435842138445760E+03, 4.892621228775580E+03,
                7.573025407170590E+01));
    }
}
