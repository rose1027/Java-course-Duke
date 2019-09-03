
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;

public class Part2 
{
    
    public float cgRatio(String dna)
    {
        int totallength = dna.length();
        int count = 0;
        int startcIndex = dna.indexOf("C");
        int startgIndex = dna.indexOf("G");
        System.out.println("startcindex= "+startcIndex);
        System.out.println("startgindex= "+startgIndex);
        float cgratio = 0;
        while(true)
        {
            if(startcIndex == -1 && startgIndex == -1)
            {
                break;
            }
           // System.out.println("startcindex= "+startcIndex);
            //System.out.println("startgindex= "+startgIndex);
            System.out.println("currentcount= "+count);
            if(startcIndex != -1 && startgIndex != -1)
            {
               count = count + 2; 
               startcIndex = dna.indexOf("C",startcIndex+1);
               startgIndex = dna.indexOf("G",startgIndex+1);
            }
            if((startcIndex == -1 && startgIndex !=-1)||(startcIndex !=-1 && startgIndex== -1))
            {
                 count++;
                 if (startcIndex!=-1)
                 {
                     startcIndex = dna.indexOf("C",startcIndex+1);
                 }
                 if (startgIndex!=-1)
                 {
                     startgIndex = dna.indexOf("G",startgIndex+1);
                 }
            }
            
            //startcIndex = dna.indexOf("C",startcIndex+1);
           // startgIndex = dna.indexOf("G",startgIndex+1);
            System.out.println("startcindex after updating "+startcIndex);
            System.out.println("startgindex after updating "+startgIndex);
           
        }
        System.out.println("total count= "+count);
  
        cgratio = (float)count/totallength;
        
        return cgratio;
    }
    
    public void testcgRatio()
    {
        String dna1 = "ATGTAA";
        float ratio = cgRatio(dna1);
        System.out.println("cgratio= "+ ratio);
    }

    public int countCTG(String dna)
    {
        int count = 0;
        int startIndex = dna.indexOf("CTG");
        System.out.println("startIndex is " + startIndex);
        if(startIndex == -1)
        {
            return 0;
        }
        while(true)
        {
            if(startIndex == -1)
            {
                break;
            }
            count++;
            System.out.println("count= " + count);
            startIndex = dna.indexOf("CTG",startIndex+3);
            System.out.println("startIndex updating " + startIndex);
            
            
        }
        return count;
    }
    
    public void testcountCTG()
    {
        String dna1 = "ATGCTGATTTACTGTAACTGA";
       // System.out.println("totalcount DNA1= " + countCTG(dna1));
        String dna2 = "ATGAATTAG";
        //System.out.println("totalcount DNA2= " + countCTG(dna2));
        String dna3 = "ATGAACTGA";
        System.out.println("totalcount DNA3= " + countCTG(dna3));
        
        
    }
}
