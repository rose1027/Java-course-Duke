
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;


public class Part3 
{
    
    public void processGenes(StorageResource sr)
    {   
        int count = 0;
        int totalcountgene=0;
        int countcgratio = 0;
        int longest = 0;
        for(String s:sr.data())
        {
            System.out.println("current genes is "+ s);
            totalcountgene++;
            if(s.length() > 60)
            {
                count++;
                //System.out.println("strings > 60 are " + s);
                
            }
            
            float cgratio = cgRatio(s);
           // System.out.println("cgratio in strings "+cgratio);
   
           if(cgratio > 0.35)
            {
                System.out.println("strings of cgratio>0.35 is " + s);
                countcgratio++;
            }
            
            if(s.length()>longest)
            {
                longest = s.length();
            }
        }
     
        System.out.println("numbers of (strings >60) is " + count);
        System.out.println("number of total gene is  "+ totalcountgene);
        System.out.println("(cgratiocounts > 0.35) =" + countcgratio);
        System.out.println("number of longest string is "+ longest);
        
    }
    
    public float cgRatio(String dna)
    {
        int totallength = dna.length();
        int count = 0;
        int startcIndex = dna.indexOf("C");
        int startgIndex = dna.indexOf("G");
        //System.out.println("startcindex= "+startcIndex);
        //System.out.println("startgindex= "+startgIndex);
        float cgratio = 0;
        while(true)
        {
            if(startcIndex == -1 && startgIndex == -1)
            {
                break;
            }
            
           // System.out.println("currentcount= "+count);
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
           // System.out.println("startcindex after updating "+startcIndex);
            //System.out.println("startgindex after updating "+startgIndex);
           
        }
       // System.out.println("total count= "+count);
  
        cgratio = (float)count/totallength;
        
        return cgratio;
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
    
    
    public int findStopCodon(String dna, int startindex, String stopCodon)
    {
        int currstopIndex = dna.indexOf(stopCodon,startindex+3);
        /*int diff = currstopIndex-startindex;
        if(diff % 3 == 0)
        {   //System.out.println("Find stopindex!");
            return currstopIndex;
        }
        else
        {   //System.out.println("can't find stopCodon");
            return -1;
        }*/
        while(currstopIndex != -1)
        {
            int diff = currstopIndex-startindex;
            if(diff % 3 == 0)
            {   System.out.println("Find stopindex!");
                return currstopIndex;
            }
            else currstopIndex = dna.indexOf(stopCodon,currstopIndex+1);
        
        }
        
        
        return -1;
        
    
    }
    
    public String findGene(String dna, int start)
    {
      String newgene = null;
      int startIndex = dna.indexOf("ATG",start);
      //System.out.println("startindex in findGene() = "+ startIndex);
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
    
    
    public StorageResource getAllGenes(String dna)
    {
        StorageResource genelist = new StorageResource();
        int startIndex = 0;
        while(true)
        {
            String currgene = findGene(dna, startIndex);
            
            if(currgene.isEmpty())
            {
                break;
            }
            
            System.out.println("current gene is " + currgene);
            genelist.add(currgene);
            //iterate the list of genelist and print out the data in the list.
            //curgene is a variable that used to iterate the genelist
            /*for(String curgene:genelist.data())
            {
                System.out.println("all genes are "+ curgene);
            }*/
            startIndex = dna.indexOf(currgene,startIndex)+currgene.length();
            //System.out.println("startIndex= " + startIndex);
            
            
        }
        
         
        return genelist;
        
    }
    
    public void testProcessGenes()
    {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        String newdna = dna.toUpperCase();
       // printAllGenes(newdna);
        StorageResource genes =getAllGenes(newdna);
        //System.out.println("all genes are "+ genes.data());
       // StorageResource genelist = new StorageResource();
       // genelist.add(dna);
        /*String dna1 = "ATGAATTCATAA";//LENGTH = 12,CR<0.35
        String dna2 = "ATGTAA";//LENGTH<9,CR<0.35
        String dna3 = "ATGCGAGCT";//CR>0.35
        String dna4 = "ATGCGATAA";//CR<0.35
        String dna5 = "ATGTTATAGTAAAAT";//LENGTH = 15,CR<0.35
        genelist.add(dna1);
         genelist.add(dna2);
          genelist.add(dna3);
           genelist.add(dna4);
            genelist.add(dna5);*/
            float ratio = 0;
            //ratio = cgRatio(dna1);
           // System.out.println("ratio of dna1"+cgRatio(dna1));
           // System.out.println("ratio of dna2 is "+cgRatio(dna2));
             //System.out.println("ratio of dna3 is "+cgRatio(dna5));
            /*for(String curgene:genelist.data())
            {
                System.out.println("current genes is "+ curgene);
                ratio = cgRatio(curgene);
                 System.out.println("strings of cgratio=  " + ratio);  
                if (ratio > 0.35)
                {
                  System.out.println("strings of cgratio>0.35 are " + ratio);  
                }
                
            }*/
            //System.out.println("all genes are "+ genelist.data());
            processGenes(genes);
            System.out.println("totalcount CTG= " + countCTG(newdna));
        
            
        
    }

}
