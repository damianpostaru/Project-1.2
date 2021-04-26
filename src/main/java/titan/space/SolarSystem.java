package titan.space;

import titan.interfaces.Vector3dInterface;

import java.util.ArrayList;

public class SolarSystem extends ArrayList<Planet> {
    Vector3d[] accelerations;
    private final double G = 6.67408e-11;
    static int count = 0;
    public SolarSystem(Vector3dInterface initialPosition, Vector3dInterface initialVelocity) {
        add(new Planet( new Vector3d(-6.806783239281648e+08, 1.080005533878725e+09, 6.564012751690170e+06), new Vector3d(-1.420511669610689e+01, -4.954714716629277e+00, 3.994237625449041e-01)));//sun
        add(new Planet(new Vector3d(6.047855986424127e+06, -6.801800047868888e+10, -5.702742359714534e+09), new Vector3d(3.892585189044652e+04, 2.978342247012996e+03, -3.327964151414740e+03)));//mercury
        add(new Planet(new Vector3d(-9.435345478592035e+10, 5.350359551033670e+10, 6.131453014410347e+09), new Vector3d(-1.726404287724406e+04, -3.073432518238123e+04, 5.741783385280979e-04)));//venus
        add(new Planet(new Vector3d(-1.471922101663588e+11, -2.860995816266412e+10, 8.278183193596080e+06), new Vector3d(5.427193405797901e+03, -2.931056622265021e+04, 6.575428158157592e-01)));//earth
        add(new Planet(new Vector3d(-1.472343904597218e+11, -2.822578361503422e+10, 1.052790970065631e+07), new Vector3d(4.433121605215677e+03, -2.948453614110320e+04, 8.896598225322805e+01)));//moon
        add(new Planet(new Vector3d(-3.615638921529161e+10, -2.167633037046744e+11, -3.687670305939779e+09), new Vector3d(2.481551975121696e+04, -1.816368005464070e+03, -6.467321619018108e+02)));//mars
        add(new Planet(new Vector3d(1.781303138592153e+11, -7.551118436250277e+11, -8.532838524802327e+08), new Vector3d(1.255852555185220e+04, 3.622680192790968e+03, -2.958620380112444e+02)));//jupiter
        add(new Planet(new Vector3d(6.328646641500651e+11, -1.358172804527507e+12, -1.578520137930810e+09), new Vector3d(8.220842186554890e+03, 4.052137378979608e+03, -3.976224719266916e+02)));//saturn
        add(new Planet(new Vector3d(6.332873118527889e+11, -1.357175556995868e+12, -2.134637041453660e+09), new Vector3d(3.056877965721629e+03, 6.125612956428791e+03, -9.523587380845593e+02)));//titan
        add(new Planet(new Vector3d(4.382692942729203e+12, -9.093501655486243e+11, -8.227728929479486e+10), new Vector3d(1.068410720964204e+03, 5.354959501569486e+03, -1.343918199987533e+02)));//uranus
        add(new Planet( new Vector3d(2.395195786685187e+12, 1.744450959214586e+12, -2.455116324031639e+10), new Vector3d(-4.059468635313243e+03, 5.187467354884825e+03, 7.182516236837899e+01)));//neptune
        add(new Shuttle((Vector3d) initialPosition.add(get(3).getPosition()), (Vector3d) initialVelocity.add(get(3).getVelocity()),5000000));
    }

    public Shuttle getShuttle() {
        return (Shuttle) get(11);
    }

    public Vector3d[] calcAcc(double t) {
        accelerations = new Vector3d[size()];
        for (int i = 0; i < accelerations.length; i++) {
            accelerations[i] = new Vector3d(0, 0, 0);
            for (int j = 0; j < size() - 1; j++) {
                if (i == j) {
                    continue;
                }
                Vector3d deltaPos = (Vector3d) get(j).getPosition().sub(get(i).getPosition());
                Vector3d distanceCubed = (Vector3d) deltaPos.mul(Math.pow(1 / deltaPos.norm(), 3));
                accelerations[i] = (Vector3d) accelerations[i].add(distanceCubed.mul(SolarSystemData.masses[j]));
            }
            accelerations[i] = (Vector3d) accelerations[i].mul(G);
        }
        accelerations[11] = (Vector3d) accelerations[11].add(((Shuttle)get(11)).calcEngineAcc(t));
        return accelerations;
    }

    @Override
    public String toString() {
        String output = "";
        for (Planet planet : this) {
            output += planet.toString();
        }
        return output;
    }
}
