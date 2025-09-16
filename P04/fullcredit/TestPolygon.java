import java.util.Scanner
public class TestPolygon {
    private static Scanner in = new Scanner(System.in);
    public static void main(String [] args)
    {
        Polygon vec1=new Polygon();
        Point x1=new Point();
        Point y1=new Point(3,0);
        Point z1=new Point(3,4);
        vec1.addPoint(x1);
        vec1.addPoint(y1);
        vec1.addPoint(z1);
        if(vec1.perimeter()!=12)
        {
            System.err.println("FAIL: Expected perimeter=12, but got"+vec1.perimeter);
        }


        Polygon vec2=new Polygon();
        Point x2=new Point();
        Point y2=new Point(3,0);
        Point z2=new Point();
        try
        {
        vec2.addPoint(x2);
        vec2.addPoint(y2);
        vec2.addPoint(z2);
        System.err.println("FAIL:Should be IllegalArguementException");
        }
        
}
}
