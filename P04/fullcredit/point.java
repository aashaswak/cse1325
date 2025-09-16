public class point
{
    private final double x;
    private final double y;

    public point()
    {
        this(0.0,0.0);
    }

    public point(double x,double y)
    {
        this.x=x;
        this.y=y;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    @override
    public boolean equals(Object O )
    {
      if (o == this) return true;
      if (o == null || o.getClass() != getClass()) return false;
      Point p = (Point) o;
      retun (p.x,x)==0 && (p.y,y)==0;
      
    }
    @Override
    public int hashCode() {
        return Object.hash(x, y);
    }

    @Override
    public String toString() {
    return "(" + x + ", " + y + ")";
}

}