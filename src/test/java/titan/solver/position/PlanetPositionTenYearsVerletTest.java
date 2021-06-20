package titan.solver.position;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import titan.interfaces.Vector3dInterface;
import titan.solver.Function;
import titan.solver.Solver;
import titan.solver.State;
import titan.space.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlanetPositionTenYearsVerletTest {

    private static final List<Vector3d> positionsAfterTenYears = new ArrayList<>();
    private static final Solver solver = new Solver();
    private static final double finalTime = 315569260;
    private static final double stepSize = 500;
    private final double ACCURACY = 0;
    private static final Vector3d initialPosition = new Vector3d(-6371e3, 0.1, 0.1);
    private static final Vector3d initialVelocity = new Vector3d(0, 0, 0);
    private static final State initialState = new State(initialPosition, initialVelocity);
    private static State[] states;
    private static double biggestDifference = 0;
    private static int count = 0;

    @BeforeAll
    static void setUp() {
        states = (State[]) solver.solve(new Function(), initialState, finalTime, stepSize);
        setPositionsAfterTenYears();
    }

    @Test
    void verletSolverSun() {
        checkPositionAfterTenYears(0);
    }

    @Test
    void verletSolverMercury() {
        checkPositionAfterTenYears(1);
    }

    @Test
    void verletSolverVenus() {
        checkPositionAfterTenYears(2);
    }

    @Test
    void verletSolverEarth() {
        checkPositionAfterTenYears(3);
    }

    @Test
    void verletSolverMoon() {
        checkPositionAfterTenYears(4);
    }

    @Test
    void verletSolverMars() {
        checkPositionAfterTenYears(5);
    }

    @Test
    void verletSolverJupiter() {
        checkPositionAfterTenYears(6);
    }

    @Test
    void verletSolverSaturn() {
        checkPositionAfterTenYears(7);
    }

    @Test
    void verletSolverTitan() {
        checkPositionAfterTenYears(8);
    }

    @Test
    void verletSolverNeptune() {
        checkPositionAfterTenYears(9);
    }

    @Test
    void verletSolverUranus() {
        checkPositionAfterTenYears(10);
    }

    private void checkPositionAfterTenYears(int i) {
        Vector3dInterface expectedPosition = positionsAfterTenYears.get(i);
        Vector3dInterface actualPosition = states[(int) Math.ceil(finalTime / stepSize)].getPlanetPosition(i);
        double difference = expectedPosition.dist(actualPosition);
        System.out.println(difference / 1000);
        if (difference > biggestDifference) {
            biggestDifference = difference;
            count++;
            System.out.println("Biggest Difference: " + (biggestDifference / 1000) + " count: " + count);
        }
        assertEquals(difference, 0, ACCURACY);
    }

    public static void setPositionsAfterTenYears() {
        /* Sun */
        positionsAfterTenYears.add(new Vector3d(3.574267265275620E+07, 9.611099154679780E+07,
                6.484829402991250E+06));
        /* Mercury */
        positionsAfterTenYears.add(new Vector3d(-1.997086674600300E+10, 4.305749685586780E+10,
                5.352439487118570E+09));
        /* Venus */
        positionsAfterTenYears.add(new Vector3d(-5.044004132764930E+10, -9.589498137456320E+10,
                1.598916083961540E+09));
        /* Earth */
        positionsAfterTenYears.add(new Vector3d(-1.467398048986680E+11, -2.814485005982040E+10,
                8.991430910643190E+06));
        /* Moon */
        positionsAfterTenYears.add(new Vector3d(-1.463542174647440E+11, -2.822024658665510E+10,
                4.362384367004780E+07));
        /* Mars */
        positionsAfterTenYears.add(new Vector3d(1.796468293282010E+11, 1.183279983322950E+11,
                -1.918865582684830E+09));
        /* Jupiter */
        positionsAfterTenYears.add(new Vector3d(-5.296327958548410E+11, -6.106292073417650E+11,
                1.439561541913100E+10));
        /* Saturn */
        positionsAfterTenYears.add(new Vector3d(7.583125886053430E+11, 1.130777264156530E+12,
                -4.985835628130300E+10));
        /* Titan */
        positionsAfterTenYears.add(new Vector3d(7.585087860461300E+11, 1.131840574172500E+12,
                -5.042554318636520E+10));
        /* Neptune */
        positionsAfterTenYears.add(new Vector3d(4.391503004778200E+12, 8.050926878743470E+11,
                -1.177863441099580E+11));
        /* Uranus */
        positionsAfterTenYears.add(new Vector3d(6.256217620717880E+11, 2.803900955935270E+12,
                2.306942349562640E+09));
    }

}