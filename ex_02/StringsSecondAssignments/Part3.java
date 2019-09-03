
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.lang.*;

public class Part3 
{
   
    public int findStopCodon(String dna, int startindex, String stopCodon)
    {
        int currstopIndex = dna.indexOf(stopCodon,startindex+3);
        int diff = currstopIndex-startindex;
        if(diff % 3 == 0)
        {   //System.out.println("Find stopindex!");
            return currstopIndex;
        }
        else
        {   //System.out.println("can't find stopCodon");
            return -1;
        }
        
    
    }
    
    public String findGene(String dna, int start)
    {
      String newgene = null;
      int startIndex = dna.indexOf("ATG",start);
      System.out.println("startindex in findGene() = "+ startIndex);
      if(startIndex == -1)
      {
          return "";
      }
      
      int stopintaa = findStopCodon(dna, startIndex, "TAA");
      int stopintag = findStopCodon(dna, startIndex, "TAG");
      int stopintga = findStopCodon(dna, startIndex, "TGA");
      int min_dis = 0;
      //System.out.println("stoptag =" + stopintag);
      if(stopintag == -1 || (stopintga > 0 && stopintga < stopintag))
      {
          min_dis = stopintga;
          
       }
      else 
        {
            min_dis = stopintag;
            
        }
      
      if(min_dis == -1 || (stopintaa > 0 && stopintaa < min_dis))
      {
          min_dis = stopintaa;
      }
      
      if(min_dis == -1)
      {
        return "";
      }
      else
       // System.out.println("current string in findGene():  "+ dna.substring(startIndex,min_dis+3));
        return dna.substring(startIndex,min_dis+3); 
      
    }
    
    
    public void printAllGenes(String dna)
    {
        int startIndex = 0;
        while(true)
        {
            String currgene = findGene(dna, startIndex);
            if(currgene.isEmpty())
            {
                break;
            }
            
            System.out.println("current gene is " + currgene);
            startIndex = dna.indexOf(currgene,startIndex)+currgene.length();
            System.out.println("startIndex= " + startIndex);
        }
    }
    
    public int countGenes(String dna)
    {
        int count = 0;
        int startIndex = 0;
        while(true)
        {
            String currgene = findGene(dna,startIndex);
            System.out.println("current gene is "+currgene);
            
            System.out.println("count=" + count);
            if(currgene.isEmpty())
            {
                break;
            }
            
            //System.out.println("current gene is " + currgene);
            //startIndex position is different of Part2,it can't use sensearch,because currgene and the next gene don't have common.
            startIndex = dna.indexOf(currgene,startIndex)+currgene.length();
            System.out.println("startIndex= " + startIndex);
            count++;
            //int sensearch = startIndex + currgene.length();
            //System.out.println("sencondsearch= " + sensearch);
            //startIndex = dna.indexOf(currgene,sensearch);
            //System.out.println("startIndex= "+ startIndex);
        }
        return count;
        
    }
    
    public void testCountGenes()
    {
        String dna1 = "ATGTAAGATGCCCTAGT";
        //int count1 = countGenes(dna1);
        //System.out.println("totalcount= "+count1);
        String dna2 = "ATGTAA";
        //int count2 = countGenes(dna2);
        //System.out.println("totalcount= "+count2);
        String dna3 = "aaaaaa";
        int count3 = countGenes(dna3);
        System.out.println("totalcount= "+count3);
    }
    
}
    

