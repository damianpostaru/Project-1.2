package titan.solver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import titan.interfaces.Vector3dInterface;
import titan.space.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlanetPositionsVerletTest {

    private static final List<Vector3d> positionsAfterOneDay = new ArrayList<>();
    private static final List<Vector3d> positionsAfterOneYear = new ArrayList<>();
    private static final Solver solver = new Solver();
    private static final int finalTime = 31536000;
    private static final int stepSize = 240;
    private final double ACCURACY = 49623050;
    private static final Vector3d initialPosition = new Vector3d(0.1, -6371e3, 0.1);
    private static final Vector3d initialVelocity = new Vector3d(0, 0, 0);
    private static final State initialState = new State(initialPosition, initialVelocity);
    private static State[] verletStates;
    private static double biggestDifference = 0;
    private static int count = 0;

    @BeforeAll
    static void setUp() {
        verletStates = (State[]) solver.solve(new Function(), initialState, finalTime, stepSize);
        setPositionsAfterOneDay();
        setPositionsAfterOneYear();
    }

    @Test
    void verletSolverSun() {
        checkPositionAfterOneDay(0);
        checkPositionAfterOneYear(0);
    }

    @Test
    void verletSolverMercury() {
        checkPositionAfterOneDay(1);
        checkPositionAfterOneYear(1);
    }

    @Test
    void verletSolverVenus() {
        checkPositionAfterOneDay(2);
        checkPositionAfterOneYear(2);
    }

    @Test
    void verletSolverEarth() {
        checkPositionAfterOneDay(3);
        checkPositionAfterOneYear(3);
    }

    @Test
    void verletSolverMoon() {
        checkPositionAfterOneDay(4);
        checkPositionAfterOneYear(4);
    }

    @Test
    void verletSolverMars() {
        checkPositionAfterOneDay(5);
        checkPositionAfterOneYear(5);
    }

    @Test
    void verletSolverJupiter() {
        checkPositionAfterOneDay(6);
        checkPositionAfterOneYear(6);
    }

    @Test
    void verletSolverSaturn() {
        checkPositionAfterOneDay(7);
        checkPositionAfterOneYear(7);
    }

    @Test
    void verletSolverTitan() {
        checkPositionAfterOneDay(8);
        checkPositionAfterOneYear(8);
    }

    @Test
    void verletSolverNeptune() {
        checkPositionAfterOneDay(9);
        checkPositionAfterOneYear(9);
    }

    @Test
    void verletSolverUranus() {
        checkPositionAfterOneDay(10);
        checkPositionAfterOneYear(10);
    }

    private void checkPositionAfterOneDay(int i) {
        Vector3dInterface expectedPosition = positionsAfterOneDay.get(i);
        Vector3dInterface actualPosition = verletStates[360].getSolarSystem().get(i).getPosition();
        printPosition(expectedPosition.getX(), actualPosition.getX(), "One Day Expected X: ", "One Day Actual X: ");
        printPosition(expectedPosition.getY(), actualPosition.getY(), "One Day Expected Y: ", "One Day Actual Y: ");
        printPosition(expectedPosition.getZ(), actualPosition.getZ(), "One Day Expected Z: ", "One Day Actual Z: ");
        assertEquals(expectedPosition.getX(), actualPosition.getX(), ACCURACY);
        assertEquals(expectedPosition.getY(), actualPosition.getY(), ACCURACY);
        assertEquals(expectedPosition.getZ(), actualPosition.getZ(), ACCURACY);
    }

    private void checkPositionAfterOneYear(int i) {
        Vector3dInterface expectedPosition = positionsAfterOneYear.get(i);
        Vector3dInterface actualPosition = verletStates[131400].getSolarSystem().get(i).getPosition();
        printPosition(expectedPosition.getX(), actualPosition.getX(), "One Year Expected X: ", "One Year Actual X: ");
        printPosition(expectedPosition.getY(), actualPosition.getY(), "One Year Expected Y: ", "One Year Actual Y: ");
        printPosition(expectedPosition.getZ(), actualPosition.getZ(), "One Year Expected Z: ", "One Year Actual Z: ");
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

    public static void setPositionsAfterOneYear() {
        /* Sun */
        positionsAfterOneYear.add(new Vector3d(-1.082901546284580E+09, 8.147721163745780E+08, 1.863744464428420E+07));
        /* Mercury */
        positionsAfterOneYear.add(new Vector3d(3.950842791655450E+10, - 4.720264660931200E+10, - 7.628642664193490E+09));
        /* Venus */
        positionsAfterOneYear.add(new Vector3d(1.037791016188340E+11, 2.825331531868220E+10, -5.655906754344890E+09));
        /* Earth */
        positionsAfterOneYear.add(new Vector3d(-1.477129564888790E+11, -2.821064238692060E+10, 2.033107664331600E+07));
        /* Moon */
        positionsAfterOneYear.add(new Vector3d(-1.479155194643600E+11, -2.851137900054520E+10, 2.937109212716480E+07));
        /* Mars */
        positionsAfterOneYear.add(new Vector3d(-8.471846525675170E+10, 2.274355520083190E+11, 6.819319353400900E+09));
        /* Jupiter */
        positionsAfterOneYear.add(new Vector3d(5.299051386136680E+11, -5.398930284706270E+11, -9.615587266077510E+09));
        /* Saturn */
        positionsAfterOneYear.add(new Vector3d(8.778981939996120E+11, -1.204478262290760E+12, -1.400829719307180E+10));
        /* Titan */
        positionsAfterOneYear.add(new Vector3d(8.789384100968740E+11, -1.204002291074610E+12, -1.435729928774680E+10));
        /* Neptune */
        positionsAfterOneYear.add(new Vector3d(2.261199091016790E+12, 1.903465617218640E+12, -2.222474353840990E+10));
        /* Uranus */
        positionsAfterOneYear.add(new Vector3d(4.413142564759280E+12, -7.398714862901390E+11, -8.646907932385070E+10));
    }

    public static void setPositionsAfterOneDay() {
        /* Sun */
        positionsAfterOneDay.add(new Vector3d(-6.820120542072291E+08, 1.079531639242061E+09, 6.612314219571243E+06));
        /* Mercury */
        positionsAfterOneDay.add(new Vector3d(3.366437797005420E+09, -6.765788029127140E+10, -5.981613930315590E+09));
        /* Venus */
        positionsAfterOneDay.add(new Vector3d(-9.580765049120940E+10, 5.082756749251280E+10, 6.178628634731360E+09));
        /* Earth */
        positionsAfterOneDay.add(new Vector3d(-1.467017349489420E+11, -3.113778902611650E+10, 8.350045664343980E+06));
        /* Moon */
        positionsAfterOneDay.add(new Vector3d(-1.468279160935010E+11, -3.077869284539200E+10, 1.810196579355930E+07));
        /* Mars */
        positionsAfterOneDay.add(new Vector3d(-3.401072563872990E+10, -2.169101963086090E+11, -3.743376828999530E+09));
        /* Jupiter */
        positionsAfterOneDay.add(new Vector3d(1.792150768229270E+11, -7.547979970571840E+11, -8.788450263412590E+08));
        /* Saturn */
        positionsAfterOneDay.add(new Vector3d(6.335748551935350E+11, -1.357822481323310E+12, -1.612884301640510E+09));
        /* Titan */
        positionsAfterOneDay.add(new Vector3d(6.335303511347730E+11, -1.356727039565490E+12, -2.173112534852080E+09));
        /* Neptune */
        positionsAfterOneDay.add(new Vector3d(4.382785256683060E+12, -9.088874998513250E+11, -8.228892955386560E+10));
        /* Uranus */
        positionsAfterOneDay.add(new Vector3d(2.394844929367040E+12, 1.744899234369480E+12, -2.454507287331430E+10));
    }

}