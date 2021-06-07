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

public class PlanetVelocityOneYearVerletTest {

    private static final List<Vector3d> velocitiesAfterOneYear = new ArrayList<>();
    private static final Solver solver = new Solver();
    private static final double finalTime = 31536000;
    private static final double stepSize = 500;
    private final double ACCURACY = 65000;
    private static final Vector3d initialPosition = new Vector3d(-6371e3, 0.1, 0.1);
    private static final Vector3d initialVelocity = new Vector3d(0, 0, 0);
    private static final State initialState = new State(initialPosition, initialVelocity);
    private static State[] verletStates;
    private static double biggestDifference = 0;
    private static int count = 0;

    @BeforeAll
    static void setUp() {
        verletStates = (State[]) solver.solve(new Function(), initialState, finalTime, stepSize);
        setVelocitiesAfterOneYear();
    }

    @Test
    void verletSolverSunTest() {
        checkVelocityAfterOneYear(0);
    }

    @Test
    void verletSolverMercuryTest() {
        checkVelocityAfterOneYear(1);
    }

    @Test
    void verletSolverVenusTest() {
        checkVelocityAfterOneYear(2);
    }

    @Test
    void verletSolverEarthTest() {
        checkVelocityAfterOneYear(3);
    }

    @Test
    void verletSolverMoonTest() {
        checkVelocityAfterOneYear(4);
    }

    @Test
    void verletSolverMarsTest() {
        checkVelocityAfterOneYear(5);
    }

    @Test
    void verletSolverJupiterTest() {
        checkVelocityAfterOneYear(6);
    }

    @Test
    void verletSolverSaturnTest() {
        checkVelocityAfterOneYear(7);
    }

    @Test
    void verletSolverTitanTest() {
        checkVelocityAfterOneYear(8);
    }

    @Test
    void verletSolverNeptuneTest() {
        checkVelocityAfterOneYear(9);
    }

    @Test
    void verletSolverUranusTest() {
        checkVelocityAfterOneYear(10);
    }

    private void checkVelocityAfterOneYear(int i) {
        Vector3dInterface expectedVelocity = velocitiesAfterOneYear.get(i);
        Vector3dInterface actualVelocity = verletStates[(int) Math.ceil(finalTime / stepSize)].getSolarSystem().get(i).getVelocity();
        double difference = expectedVelocity.dist(actualVelocity);
        System.out.println(difference / 1000);
        if (difference > biggestDifference) {
            biggestDifference = difference;
            count++;
            System.out.println("Biggest Difference: " + biggestDifference + " count: " + count);
        }
        assertEquals(difference, 0, ACCURACY);
    }

    public static void setVelocitiesAfterOneYear() {
        /* Sun */
        velocitiesAfterOneYear.add(new Vector3d(-1.066974517312190E+01, -1.143612548437740E+01, 3.462019547224800E-01));
        /* Mercury */
        velocitiesAfterOneYear.add(new Vector3d(2.750473883640400E+04, 3.377701495512690E+04, 2.374213743794630E+02));
        /* Venus */
        velocitiesAfterOneYear.add(new Vector3d(-9.000039117559160E+03, 3.371052705031940E+04, 9.818723126766740E+02));
        /* Earth */
        velocitiesAfterOneYear.add(new Vector3d(5.278155150099900E+03, -2.933726235139810E+04, 2.829493882106950E+00));
        /* Moon */
        velocitiesAfterOneYear.add(new Vector3d(6.154257720388410E+03, -2.996687959729850E+04, -8.851792752802540E+01));
        /* Mars */
        velocitiesAfterOneYear.add(new Vector3d(-2.182576629101100E+04, -6.342607930949870E+03, 4.028075281420640E+02));
        /* Jupiter */
        velocitiesAfterOneYear.add(new Vector3d(9.164631943293960E+03, 9.769805820601120E+03, -2.455256050305460E+02));
        /* Saturn */
        velocitiesAfterOneYear.add(new Vector3d(7.267453937241430E+03, 5.665808328648830E+03, -3.874413441311550E+02));
        /* Titan */
        velocitiesAfterOneYear.add(new Vector3d(4.600295683062070E+03, 1.023830877803220E+04, -2.479030926015200E+03));
        /* Neptune */
        velocitiesAfterOneYear.add(new Vector3d(8.633265089089730E+02, 5.393369612798610E+03, -1.308834502370690E+02));
        /* Uranus */
        velocitiesAfterOneYear.add(new Vector3d(-4.435842138445760E+03, 4.892621228775580E+03, 7.573025407170590E+01));
    }

}
