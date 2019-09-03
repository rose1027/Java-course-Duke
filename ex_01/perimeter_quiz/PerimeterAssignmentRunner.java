import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner 
{
    public double getPerimeter (Shape s)
    {
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

    public int getNumPoints (Shape s) 
    {
        // Put code herehat is the number of points in Shape s. Hint: You will need to iterate over all the points in the Shape S and count them.
        int count = 0;
        Point prevPt = s.getLastPoint();
        System.out.println("prev = " + prevPt);
        
        for (Point currPt: s.getPoints())
        {
         System.out.println("curr = " + currPt);   
            if(currPt.getX() != prevPt.getX())
            {
                
                count++;
            }
   
            else
                if(currPt.getX() != prevPt.getY())
                {
                   count++;
                }
            else
            break;
            
        }
   
        return count;
        
    }

    public double getAverageLength(Shape s)
    {
        // Put code here
        double average;
        double totalperim = getPerimeter(s);
        int totalsides = getNumPoints(s);
        average = totalperim / totalsides;
        return average;
    }

    public double getLargestSide(Shape s)
    {
        // Put code here
        double largeside = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point currPt: s.getPoints())
        {
          double currDist = prevPt.distance(currPt); 
          System.out.println("currDist = "+ currDist);
          if(currDist > largeside)
          {
              largeside = currDist;
            }
          
        }
        return largeside;
    }

    public double getLargestX(Shape s)
    {
        // Put code here
        double largex = 0.0;
        for(Point currPt: s.getPoints())
        {
          if(currPt.getX() > largex)
          {
            largex = currPt.getX();
          }
        }
        return largex;
    }

    public double getLargestPerimeterMultipleFiles() 
    {
        // Put code here\
        double largestperimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) 
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currperimeter = getPerimeter(s);
            System.out.println("currentPerimeter = " + currperimeter);
            if(currperimeter > largestperimeter)
            {
                largestperimeter = currperimeter;
            }
            
            
            
        }
        return largestperimeter;
    }

    public String getFileWithLargestPerimeter() 
    {
        // Put code here
        double largestperimeter = 0.0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) 
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currperimeter = getPerimeter(s);
            System.out.println("currentPerimeter = " + currperimeter);
            if(currperimeter > largestperimeter)
            {
                largestperimeter = currperimeter;
                temp = f;
            }
            
            
            
        }
        //File temp = null; // replace this code
        return temp.getName();
    }

    public void testPerimeter () 
    {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int pointcount = getNumPoints(s);
        System.out.println("count=" + pointcount);
        double averageLength = getAverageLength(s);
        System.out.println("AverageLength=" + averageLength);
        double LargeSide = getLargestSide(s);
        System.out.println("Largeside = " + LargeSide);
        double LargeX = getLargestX(s);
        System.out.println("LargestX= " + LargeX);
        
        
        
    }
    
    public void testPerimeterMultipleFiles()
    {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        
        double largestfiles = getLargestPerimeterMultipleFiles();
        System.out.println("largestPerimrMultiFiles=" + largestfiles);
        
    }

    public void testFileWithLargestPerimeter() 
    {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        String filename = getFileWithLargestPerimeter();
        System.out.println("FilewithLargestPerimeter=" + filename);
        
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
