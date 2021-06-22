package titan.solver.position;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import titan.interfaces.Vector3dInterface;
import titan.solver.Function;
import titan.solver.Solver;
import titan.solver.State;
import titan.space.SolarSystem;
import titan.space.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlanetPositionFiveYearsVerletTest {

    private static final List<Vector3d> positionsAfterFiveYears = new ArrayList<>();
    private static final Solver solver = new Solver();
    private static final double finalTime = 157784630;
    private static final double stepSize = 500;
    private final double ACCURACY = 3306417730.0;
    private static final Vector3d initialPosition = new Vector3d(-6371e3, 0.1, 0.1);
    private static final Vector3d initialVelocity = new Vector3d(0, 0, 0);
    private static final State initialState = new State(initialPosition, initialVelocity);
    private static State[] states;
    private static double biggestDifference = 0;
    private static int count = 0;

    @BeforeAll
    static void setUp() {
        states = (State[]) solver.solve(new Function(), initialState, finalTime, stepSize);
        SolarSystem.reset(initialPosition,initialVelocity);
        setPositionsAfterFiveYears();
    }

    @Test
    void verletSolverSun() {
        checkPositionAfterFiveYears(0);
    }

    @Test
    void verletSolverMercury() {
        checkPositionAfterFiveYears(1);
    }

    @Test
    void verletSolverVenus() {
        checkPositionAfterFiveYears(2);
    }

    @Test
    void verletSolverEarth() {
        checkPositionAfterFiveYears(3);
    }

    @Test
    void verletSolverMoon() {
        checkPositionAfterFiveYears(4);
    }

    @Test
    void verletSolverMars() {
        checkPositionAfterFiveYears(5);
    }

    @Test
    void verletSolverJupiter() {
        checkPositionAfterFiveYears(6);
    }

    @Test
    void verletSolverSaturn() {
        checkPositionAfterFiveYears(7);
    }

    @Test
    void verletSolverTitan() {
        checkPositionAfterFiveYears(8);
    }

    @Test
    void verletSolverNeptune() {
        checkPositionAfterFiveYears(9);
    }

    @Test
    void verletSolverUranus() {
        checkPositionAfterFiveYears(10);
    }

    private void checkPositionAfterFiveYears(int i) {
        Vector3dInterface expectedPosition = positionsAfterFiveYears.get(i);
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

    public static void setPositionsAfterFiveYears() {
        /* Sun */
        positionsAfterFiveYears.add(new Vector3d(-7.596727134361320E+08, -7.76482680346203E+08, 2.520871232682850E+07));
        /* Mercury */
        positionsAfterFiveYears.add(new Vector3d(-5.671193615988730E+10, -3.227251209672350E+10,
                2.583296735726040E+09));
        /* Venus */
        positionsAfterFiveYears.add(new Vector3d(-1.039326720916520E+11, -3.187005740943380E+10,
                5.551106734133110E+09));
        /* Earth */
        positionsAfterFiveYears.add(new Vector3d(-1.474114613044810E+11, -2.972578730668050E+10, 2.745063093019830E+07));
        /* Moon */
        positionsAfterFiveYears.add(new Vector3d(-1.471660130896690E+11, -2.946233624390670E+10,
                5.289107585630000E+07));
        /* Mars */
        positionsAfterFiveYears.add(new Vector3d(-2.145952052444850E+11, 1.266512572943520E+11,
                7.939414221385670E+09));
        /* Jupiter */
        positionsAfterFiveYears.add(new Vector3d(5.543705768794880E+10, 7.620296282600120E+11,
                -4.400748237416440E+09));
        /* Saturn */
        positionsAfterFiveYears.add(new Vector3d(1.422232980544840E+12, -1.907185316405950E+11,
                -5.331046601369830E+10));
        /* Titan */
        positionsAfterFiveYears.add(new Vector3d(1.421787828379910E+12, -1.917156133252350E+11,
                -5.275191841378620E+10));
        /* Neptune */
        positionsAfterFiveYears.add(new Vector3d(4.469540357827110E+12, -5.309854693989040E+10,
                -1.019116933726740E+11));
        /* Uranus */
        positionsAfterFiveYears.add(new Vector3d(1.615977854235190E+12, 2.434176772693910E+12,
                -1.189481936502610E+10));
    }

}