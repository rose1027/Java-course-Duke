
/**
 * Write a description of Part1 here.
 * 
 * @author (Tingting Hu) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.lang.*;

public class Part1 {
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
        //System.out.println("current string in findGene():  "+ dna.substring(startIndex,min_dis+3));
        return dna.substring(startIndex,min_dis+3); 
      /*int mindis = Math.min(stopintga,stopintaa);
      System.out.println("dis btw tag and taa =" + mindis);
      if(stopintag == -1 && mindis > 0) 
      {
        newgene = dna.substring(startIndex,mindis+3); 
        return newgene;
      }
      if(stopintag == -1 && (mindis < 0))
       {
          if(stopintaa>0)
          {
              newgene = dna.substring(startIndex,stopintaa+3);
            return newgene;
           }
           else if(stopintga > 0) 
           {
             newgene = dna.substring(startIndex,stopintga+3);
            return newgene;  
            }
            else
                return "";
        }
      
      if(stopintag > 0 && mindis > 0)
      {
           int temp = Math.min(stopintag,mindis);
           newgene = dna.substring(startIndex,temp+3);
            return newgene;
      }
      if (stopintag> 0 && mindis < 0)
      {
        if(stopintaa>0)
        {
            int temp = Math.min(stopintag,stopintaa);
            newgene = dna.substring(startIndex,temp+3);
            return newgene;
            
        }
        else if(stopintga > 0)
        {
            int temp = Math.min(stopintag,stopintga);
                newgene = dna.substring(startIndex,temp+3);
            return newgene;
            
        }
        else
        {
            newgene = dna.substring(startIndex,stopintag+3);
             return newgene;
        }
      }
      return newgene;*/
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
    
    public void testPrint()
    {
        String dna1 = "AATGCTAACTAGCTGACTAAT";
        System.out.println("original string: "+ dna1);
        printAllGenes(dna1);
        
    
    }
    
    public void testFindGene()
    {
        String dna1 = "TAATAG";
        // System.out.println("original string: "+ dna1);
        //System.out.println("no frontco: "+ findGene(dna1));
        String dna2 = "TTAATGTTGTAT";
           // System.out.println("original string: "+ dna2);
        // System.out.println("no stopco: "+ findGene(dna2));
        String dna3 = "TTAATGAATTAG";
            //System.out.println("original string: "+ dna3);
         //System.out.println("1 stopco: "+ findGene(dna3));
        String dna4 = "ATGATTTGATGA";
         //System.out.println("original string: "+ dna4);
        //System.out.println("2 stopco: "+ findGene(dna4));
        String dna5 = "ATATGGTATATAG";
           // System.out.println("original string"+ dna5);
         //System.out.println("no gene"+ findGene(dna5));
        String dna6 ="ATGAAGTAGGATTGAGAATAA";
            System.out.println("original string: "+ dna6);
         //System.out.println("3 stopco: "+ findGene(dna6,0));
        
        
    
    
    }
    public void testFindStopCodon()
    {
        String dna1 = "ATATGTTATAGTAA";
        //int index1 = findStopCodon(dna1, 2, "TAA");
        //System.out.println("stopIndex = " + index1);
        String dna2 = "ATGTTATGAGTATTAA";
        int index2 = findStopCodon(dna2, 0, "TAA");
        System.out.println("stopIndex = " + index2);
        
    }
    


}
