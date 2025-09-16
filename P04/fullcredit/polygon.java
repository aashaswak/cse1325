public class Polygon {
    public static final int MAX_SLIDES=12;

    private int numSides=0;
    private Point[] points=new Point[MAX_SLIDES];
    int counter=0;
    public void addPoint(Point p)
    {
        for(int i=0;i<numSides;i++)
        {
            if(points[i].equals(p))
            {
                counter++;
            }
        }
        if(counter>0) throw new IllegalArgumentException("Duplicate point:"+p);
        if (numSides >= MAX_SLIDES) {
            throw new RuntimeException("Polygon is full");
        }
        points[numSides++] = p;
    
    }

    private static double lineLength(Point a,Point b)
    {
        int x=a.getX()-b.getY();
        int y=a.getY()-b.getX();
        return Math.sqrt(a*a+b*b);
    }

    public double perimeter()
    {
        if(numSides<3)
        {
            throw new RuntimeException("Polygons required 3+ sides!");
        }
    }
}
