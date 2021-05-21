package titan.solver;

import org.junit.jupiter.api.Test;
import titan.interfaces.Vector3dInterface;
import titan.space.Planet;
import titan.space.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlanetVelocitiesVerletTest {

    private final List<Vector3d> velocitiesAfterOneDay = new ArrayList<>();

    public void setVelocitiesAfterOneDay() {
        /* Sun */
        velocitiesAfterOneDay.add(new Vector3d(-1.420389076442244E01, -4.973297284492958E00,
                3.994885937524495E-01));
        /* Mercury */

    }

    @Test
    public void verletTest() {
        //sun

    }

}
