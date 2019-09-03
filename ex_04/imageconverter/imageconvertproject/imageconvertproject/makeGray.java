
/**
 * Write a description of makeGray here.
 * 
 * @author (Tingting Hu) 
 * @version (01/08/2019)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;

public class makeGray 
{
    public ImageResource makeGray(ImageResource inImage)
    {
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel p:outImage.pixels())
        {
            Pixel pixel =inImage.getPixel(p.getX(),p.getY());
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            int average = (red+green+blue)/3;
            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
        outImage.draw();
        return outImage;
    }
    
    public void testmakeGray()
    {    
        ImageResource origimage = new ImageResource();
        ImageResource gray = makeGray(origimage);
    }
    
    public void testmakeGraywithManyFiles()
    {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) 
        {
            ImageResource ir = new ImageResource(f);
            ImageResource gray = makeGray(ir);
        }

    }
    
    public void dosaver()
    {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) 
        {
            ImageResource ir = new ImageResource(f);
            ImageResource gray = makeGray(ir);
            String fname = ir.getFileName();
            String newfname = "gray-"+fname;
            gray.setFileName(newfname);
            gray.save();
            
        }
    }
    
}
