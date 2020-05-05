import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
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

    public int getNumPoints (Shape s) {
        int num = 0 ;
        for(Point curr : s.getPoints()){
            num++;
        }
        return num;
    }

    public double getAverageLength(Shape s) {
        double length = getPerimeter(s);
        int num = getNumPoints(s);
        double avg = length/num;
        return avg;
    }
    

    public double getLargestSide(Shape s) {
        double maxlen = 0;
        Point prevPt = s.getLastPoint();
        for(Point cp : s.getPoints()){
            double currLen = prevPt.distance(cp);
            if(currLen > maxlen){
                maxlen = currLen;
            }
            prevPt = cp;
        }
        return maxlen;
    }

    public double getLargestX(Shape s) {
        double x = -100000;
        for(Point cp : s.getPoints()){
            double currX = cp.getX();
            if(currX > x){
                x = currX;
            }
        }
        return x;
    }
    

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double maxPeri = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPeri = getPerimeter(s);
            if(currPeri > maxPeri){
                maxPeri = currPeri;
            }
        }
        return maxPeri;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double maxPeri = 0;
        DirectoryResource dr = new DirectoryResource();
        File temp = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPeri = getPerimeter(s);
            if(currPeri > maxPeri){
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int num = getNumPoints(s);
        double avg = getAverageLength(s);
        double maxlen = getLargestSide(s);
        double maxX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points = " + num);
        System.out.println("The length of the largest side is: " + maxlen);
        System.out.println("Average side length is: " + avg);
        System.out.println("The largest x co-ordinate is: " + maxX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double maxPeri = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is = " + maxPeri);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String bigFile = getFileWithLargestPerimeter();
        System.out.println("The file with largest perimeter is: " + bigFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
