public class Polygon {
    public static final int MAX_SIDES=12;

    private int numSides=0;
    private Point[] points=new Point[MAX_SIDES];
    public void addPoint(Point p)
    {
        int counter=0;

        for(int i=0;i<numSides;i++)
        {
            if(points[i].equals(p))
            {
                counter++;
            }
        }
        if(counter>0) throw new IllegalArgumentException("Duplicate point:"+p);
        if (numSides >= MAX_SIDES) {
            throw new RuntimeException("Polygon is full");
        }
        points[numSides++] = p;
    
    }

    private static double lineLength(Point a,Point b)
    {
        double x=a.getX()-b.getX();
        double y=a.getY()-b.getY();
        return Math.sqrt(x*x + y*y);
    }

    public double perimeter()
    {
        if(numSides<3)
        {
            throw new RuntimeException("Polygons required 3+ sides!");
        }
        else
        {
        double sum=0;
        for (int i = 0; i < numSides - 1; i++) 
        {
            sum += lineLength(points[i], points[i + 1]);
        }
    
        sum += lineLength(points[numSides - 1], points[0]);
    
        return sum;
        }
    }
    @Override
    public String toString(){
            String s = "Polygon[";
            for (int counter = 0; counter < numSides; counter++)
            {
                if (counter != 0){
                    s = s + ", " + points[counter];
                    
                }
                else {
                    s = s + points[counter];
                }
            }
            return s + "] has the perimeter of " + perimeter();
    }
}

