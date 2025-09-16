import java.util.Scanner;

public class CreatePolygon {
    private static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        Polygon poly = new Polygon();
        try {
            System.out.println("Type coordinates as x y values (press Ctrl-D when finished):");
            
            int totalVertices = 0;
            while (reader.hasNextDouble()) {
            double xVal = reader.nextDouble();
            if (!reader.hasNextDouble()) {
            throw new IllegalArgumentException("Mismatched input: missing y-value for the last x-value");
            }
            double yVal = reader.nextDouble();
            Point node = new Point(xVal, yVal);
            poly.addPoint(node);
            totalVertices++;
            System.out.printf("Vertex recorded: (%.2f, %.2f)%n", xVal, yVal);
        }

        if (totalVertices < 3) {
            throw new RuntimeException("Not enough vertices: at least three points required to form a polygon");
        }

        System.out.println(poly + " --> perimeter length = " + poly.perimeter());
    } catch (Exception problem) {
        System.out.println("INVALID INPUT: " + problem);

        }
    }
}