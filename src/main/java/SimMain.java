public class SimMain
{
    public static void main(String[] args)
    {
        ProbeSimulator probeSimulator = new ProbeSimulator();
        Vector3d[] trajectory = (Vector3d[]) probeSimulator.trajectory(new Vector3d(0.1, 6371e3, 0.1), new Vector3d(0, 20000, 0), 31556926 , 86400);
        for (Vector3d position : trajectory) {
//            System.out.println(position);
        }
    }
}
