import java.util.ArrayList;

public class SolarSystem extends ArrayList<Planet>
{
    Vector3d[] acc;
    private final double G = 6.67408e-11;

    public SolarSystem() {
        add(new Planet(1.9885e30,696.342e6,new Vector3d(-6.806783239281648e+08,1.080005533878725e+09,6.564012751690170e+06),new Vector3d(-1.420511669610689e+01, -4.954714716629277e+00, 3.994237625449041e-01),"Sun"));//sun
        add(new Planet(3.302e23,2.440e6,new Vector3d(6.047855986424127e+06, -6.801800047868888e+10,-5.702742359714534e+09),new Vector3d(3.892585189044652e+04, 2.978342247012996e+03, -3.327964151414740e+03),"Mercury"));//mercury
        add(new Planet(4.8685e24,6.052e6,new Vector3d(-9.435345478592035e+10,  5.350359551033670e+10,6.131453014410347e+09),new Vector3d(-1.726404287724406e+04, -3.073432518238123e+04, 5.741783385280979e-04),"Venus"));//venus
        add(new Planet(5.97219e24,6.371e6,new Vector3d(-1.471922101663588e+11,  -2.860995816266412e+10,   8.278183193596080e+06),new Vector3d(5.427193405797901e+03, -2.931056622265021e+04, 6.575428158157592e-01),"Earth"));//earth
        add(new Planet(7.349e22,1.738e6,new Vector3d(-1.472343904597218e+11,  -2.822578361503422e+10,   1.052790970065631e+07),new Vector3d(4.433121605215677e+03, -2.948453614110320e+04, 8.896598225322805e+01),"Moon"));//moon
        add(new Planet(6.4171e23,3.390e6,new Vector3d(-3.615638921529161e+10,  -2.167633037046744e+11,  -3.687670305939779e+09),new Vector3d(2.481551975121696e+04, -1.816368005464070e+03, -6.467321619018108e+02),"Mars"));//mars
        add(new Planet(1.89813e27,69.91e6,new Vector3d(1.781303138592153e+11,  -7.551118436250277e+11,  -8.532838524802327e+08),new Vector3d( 1.255852555185220e+04,  3.622680192790968e+03, -2.958620380112444e+02),"Jupiter"));//jupiter
        add(new Planet(5.6834e26,58.2e6,new Vector3d(6.328646641500651e+11,  -1.358172804527507e+12,  -1.578520137930810e+09),new Vector3d(8.220842186554890e+03, 4.052137378979608e+03, -3.976224719266916e+02),"Saturn"));//saturn
        add(new Planet(1.34553e23,2.5755e6,new Vector3d(6.332873118527889e+11,  -1.357175556995868e+12,  -2.134637041453660e+09),new Vector3d(3.056877965721629e+03, 6.125612956428791e+03, -9.523587380845593e+02),"Titan"));//titan
        add(new Planet(8.6813e25,25.4e6,new Vector3d(2.395195786685187e+12,   1.744450959214586e+12,  -2.455116324031639e+10),new Vector3d(-4.059468635313243e+03, 5.187467354884825e+03,  7.182516236837899e+01),"Uranus"));//neptune
        add(new Planet(1.02413e26,24.6e6,new Vector3d(4.382692942729203e+12,  -9.093501655486243e+11,  -8.227728929479486e+10),new Vector3d( 1.068410720964204e+03, 5.354959501569486e+03, -1.343918199987533e+02),"Neptune"));//uranus

    }

    public Vector3d[] calcAcc()
    {
        acc = new Vector3d[size()];
        for (int i = 0; i < acc.length; i++)
        {
            acc[i] = new Vector3d(0,0,0);
            for (int j = 0; j < size(); j++)
            {
                if(i == j){continue;}
                Vector3d deltaPos = (Vector3d) get(i).getPos().sub(get(j).getPos());
                Vector3d yes = (Vector3d)deltaPos.mul(Math.pow(1/deltaPos.norm(),3));
                acc[i] = (Vector3d)acc[i].add(yes.mul(get(j).getMass()));
            }


            acc[i] = (Vector3d) acc[i].mul(G);
        }
        return acc;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < size(); i++) {
            output += get(i).toString();
        }
        return output;
    }



    //    shuttle = new Shuttle(150000, 10,(Vector3d) p0.add(planets[3].getPos()),(Vector3d)v0.add(planets[3].getVel()),"Shuttle" );//the shuttle with location relative to SSB
}
