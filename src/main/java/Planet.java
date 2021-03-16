public class Planet
{
    private Vector3d pos;
    private Vector3d vel;
    private String name;
    private double mass;
    private double radius;

    public Planet(double m, double r,Vector3d p0,Vector3d v0, String n)
    {
        pos = p0;
        vel = v0;
        name = n;
        mass = m;
        radius = r;
    }

    public void update(Vector3dInterface acc, double dt)
    {
        vel = (Vector3d) vel.addMul(dt, acc);
        pos = (Vector3d) pos.addMul(dt, vel);
    }


    public double getMass()
    {
        return mass;
    }

    public double getRadius() { return radius; }

    public Vector3dInterface getPos()
    {
        return pos;
    }

    public void setPos(Vector3d pos) {
        this.pos = pos;
    }

    public void setVel(Vector3d vel) {
        this.vel = vel;
    }

    public Vector3dInterface getVel()
    {
        return vel;
    }

    public String toString()
    {
        return "[" + name + " pos: " + pos.toString() + " vel: " + vel.toString()+ "]"; 
    }
}
