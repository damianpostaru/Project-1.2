package titan.solver;

import org.junit.jupiter.api.Test;
import titan.interfaces.Vector3dInterface;
import titan.space.Planet;
import titan.space.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanetPositionsTest {

    private final List<Planet> positionsAfterOneDay = new ArrayList<>();
    private final Solver solver = new Solver();
    private final int finalTime = 31536000;
    private final int stepSize = 240;
    private final double ACCURACY = 49623050;
    private final Vector3d initialPosition = new Vector3d(0.1, -6371e3, 0.1);
    private final Vector3d initialVelocity = new Vector3d(0, 0, 0);
    private final State initialState = new State(initialPosition, initialVelocity);
    private final State[] verletStates = (State[]) solver.solve(new Function(), initialState, finalTime, stepSize);
    private static double biggestDifference = 0;
    private static int count = 0;


    public void setPositionsAfterOneDay() {
        /* Sun */
        positionsAfterOneDay.add(new Planet(new Vector3d(-6.820120542072291E+08, 1.079531639242061E+09, 6.612314219571243E+06), new Vector3d(-1.420389076442244E01, -4.973297284492958E00, 3.994885937524495E-01)));
        /* Mercury */
        positionsAfterOneDay.add(new Planet(new Vector3d(3.366437797005420E+09, -6.765788029127140E+10, -5.981613930315590E+09), new Vector3d(0, 0, 0)));
        /* Venus */
        positionsAfterOneDay.add(new Planet(new Vector3d(-9.580765049120940E+10, 5.082756749251280E+10, 6.178628634731360E+09), new Vector3d(0, 0, 0)));
        /* Earth */
        positionsAfterOneDay.add(new Planet(new Vector3d(-1.467017349489420E+11, -3.113778902611650E+10, 8.350045664343980E+06), new Vector3d(0, 0, 0)));
        /* Moon */
        positionsAfterOneDay.add(new Planet(new Vector3d(-1.468279160935010E+11, -3.077869284539200E+10, 1.810196579355930E+07), new Vector3d(0, 0, 0)));
        /* Mars */
        positionsAfterOneDay.add(new Planet(new Vector3d(-3.401072563872990E+10, -2.169101963086090E+11, -3.743376828999530E+09), new Vector3d(0, 0, 0)));
        /* Jupiter */
        positionsAfterOneDay.add(new Planet(new Vector3d(1.792150768229270E+11, -7.547979970571840E+11, -8.788450263412590E+08), new Vector3d(0, 0, 0)));
        /* Saturn */
        positionsAfterOneDay.add(new Planet(new Vector3d(6.335748551935350E+11, -1.357822481323310E+12, -1.612884301640510E+09), new Vector3d(0, 0, 0)));
        /* Titan */
        positionsAfterOneDay.add(new Planet(new Vector3d(6.335303511347730E+11, -1.356727039565490E+12, -2.173112534852080E+09), new Vector3d(0, 0, 0)));
        /* Neptune */
        positionsAfterOneDay.add(new Planet(new Vector3d(4.382785256683060E+12, -9.088874998513250E+11, -8.228892955386560E+10), new Vector3d(0, 0, 0)));
        /* Uranus */
        positionsAfterOneDay.add(new Planet(new Vector3d(2.394844929367040E+12, 1.744899234369480E+12, -2.454507287331430E+10), new Vector3d(0, 0, 0)));
    }

    @Test
    void verletSolver() {
        setPositionsAfterOneDay();
    }

    @Test
    void verletSolverSun() {
        checkPositionAfterOneDay(0);
    }

    @Test
    void verletSolverMercury() {
        checkPositionAfterOneDay(1);
    }
    @Test
    void verletSolverVenus() {
        checkPositionAfterOneDay(2);
    }
    @Test
    void verletSolverEarth() {
        checkPositionAfterOneDay(3);
    }
    @Test
    void verletSolverMoon() {
        checkPositionAfterOneDay(4);
    }
    @Test
    void verletSolverMars() {
        checkPositionAfterOneDay(5);
    }
    @Test
    void verletSolverJupiter() {
        checkPositionAfterOneDay(6);
    }
    @Test
    void verletSolverSaturn() {
        checkPositionAfterOneDay(7);
    }
    @Test
    void verletSolverTitan() {
        checkPositionAfterOneDay(8);
    }
    @Test
    void verletSolverNeptune() {
        checkPositionAfterOneDay(9);
    }
    @Test
    void verletSolverUranus() {
        checkPositionAfterOneDay(10);
    }

    private void checkPositionAfterOneDay(int i) {
        setPositionsAfterOneDay();
        Vector3dInterface expectedPosition = positionsAfterOneDay.get(i).getPosition();
        Vector3dInterface actualPosition = verletStates[360].getSolarSystem().get(i).getPosition();
        printPosition(expectedPosition.getX(), actualPosition.getX(), "Expected X: ", "Actual X: ");
        printPosition(expectedPosition.getY(), actualPosition.getY(), "Expected Y: ", "Actual Y: ");
        printPosition(expectedPosition.getZ(), actualPosition.getZ(), "Expected Z: ", "Actual Z: ");
        assertEquals(expectedPosition.getX(), actualPosition.getX(), ACCURACY);
        assertEquals(expectedPosition.getY(), actualPosition.getY(), ACCURACY);
        assertEquals(expectedPosition.getZ(), actualPosition.getZ(), ACCURACY);
    }

    private void printPosition(double z, double z2, String s, String s2) {
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


    @Test
    void rungeKuttaSolver() {
    }
}