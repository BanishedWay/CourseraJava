package coursera.xujinqi.cousera1.week1;

import java.io.File;



import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.Point;
import edu.duke.Shape;
/**
 * @author Ðí ¾¢ä¿
 * @date 2022/1/20 10:58
 */
public class PerimeterAssignmentRunner {
    public double getPerimeter(Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        // Put code here
        int length = 0;
        Iterable<Point> points = s.getPoints();
        for (Point p : points) {
            length++;
        }
        return length;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        return this.getPerimeter(s) / this.getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double maxLength = 0.0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            maxLength = Math.max(maxLength, currDist);
            prevPt = currPt;
        }
        return maxLength;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double maxX = 0.0;

        for (Point p : s.getPoints()) {
            maxX = Math.max(maxX, p.getX());
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here

        DirectoryResource directoryResource = new DirectoryResource();
        double maxPerimeter = 0.0;

        for (File f : directoryResource.selectedFiles()) {
            Shape s = new Shape(new FileResource(f));
            double perimeter = getPerimeter(s);
            maxPerimeter = Math.max(maxPerimeter, perimeter);
        }

        return maxPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;
        double maxPerimeter = 0.0;

        DirectoryResource directoryResource = new DirectoryResource();
        for (File f : directoryResource.selectedFiles()) {
            Shape s = new Shape(new FileResource(f));
            double perimeter = getPerimeter(s);

            if (maxPerimeter < perimeter) {
                maxPerimeter = perimeter;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource("datatest1.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("number points = " + getNumPoints(s));
        System.out.println("average length = " + getAverageLength(s));
        System.out.println("largest side = " + getLargestSide(s));
        System.out.println("largest x = " + getLargestX(s));
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println("max perimeter = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("max perimeter file name is " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to
    // test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        // pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
        // pr.printFileNames();
    }
}
