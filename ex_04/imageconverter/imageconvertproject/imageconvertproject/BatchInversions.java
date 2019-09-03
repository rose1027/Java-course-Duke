
/**
 * Write a description of BatchInversions here.
 * 
 * @author (Tingting Hu) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;

public class BatchInversions {
    public ImageResource makeInversion(ImageResource image)
    {
        ImageResource outImage = new ImageResource(image.getWidth(),image.getHeight());
        for(Pixel p:outImage.pixels())
        {
            Pixel Inpixel = image.getPixel(p.getX(),p.getY());
            int red = Inpixel.getRed();
            int green = Inpixel.getGreen();
            int blue = Inpixel.getBlue();
            int newred = 255 - red;
            int newgreen = 255 - green;
            int newblue = 255 - blue;
            p.setRed(newred);
            p.setGreen(newgreen);
            p.setBlue(newblue);
            
        }
        outImage.draw();
        return outImage;
        
    }
    
    public void testmakeInversion()
    {
        ImageResource origimage = new ImageResource();
        ImageResource inversion = makeInversion(origimage);
    }
    
    public void selectAndConvert()
    {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) 
        {
            ImageResource ir = new ImageResource(f);
            ImageResource inversion = makeInversion(ir);
            String fname = ir.getFileName();
            String newfname = "inverted-"+fname;
            inversion.setFileName(newfname);
            inversion.save();
        }
    }

}
