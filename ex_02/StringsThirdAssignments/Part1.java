
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;

public class Part1 
{
    public int findStopCodon(String dna, int startindex, String stopCodon)
    {
        int currstopIndex = dna.indexOf(stopCodon,startindex+3);
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
      System.out.println("startindex in findGene() = "+ startIndex);
      if(startIndex == -1)
      {
          return "";
      }
      
      int stopintaa = findStopCodon(dna, startIndex, "TAA");
      int stopintag = findStopCodon(dna, startIndex, "TAG");
      int stopintga = findStopCodon(dna, startIndex, "TGA");
      
      System.out.println("stoptag =" + stopintag);
      System.out.println("stoptaa =" + stopintaa);
      System.out.println("stoptga =" + stopintga);
      int min_dis = 0;
      if(stopintag == -1 || (stopintga !=-1 && stopintga < stopintag))
      {
          min_dis = stopintga;
          
       }
      else 
        {
            min_dis = stopintag;
            
        }
      
      if(min_dis == -1 || (stopintaa !=-1 && stopintaa < min_dis))
      {
          min_dis = stopintaa;
      }
      
      if(min_dis == -1)
      {
        return "";
      }
      
       // System.out.println("current string in findGene():  "+ dna.substring(startIndex,min_dis+3));
        return dna.substring(startIndex,min_dis + 3); 
    }
    
    
    public void printAllGenes(String dna)
    {
        int startIndex = 0;
   
        while(true)
        {
            String currgene = findGene(dna, startIndex);
            System.out.println("startIndex in print()= "+startIndex);
            System.out.println("dna1=" + currgene);
            if(currgene.isEmpty())
            {
                break;
            }
            
            System.out.println("current gene is " + currgene);
            System.out.println("length of current gene is " + currgene.length());
            startIndex = dna.indexOf(currgene,startIndex)+currgene.length();
            //System.out.println("startIndex= " + startIndex);
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
            System.out.println("startIndex= " + startIndex);
            
            
        }
        
         /*for(String curgene:genelist.data())
        {
            System.out.println("all genes are "+ curgene);
        }*/
        return genelist;
        
    }
    
    public void testgetallGenes()
    {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        String newdna = dna.toUpperCase();
        //String dna = "AATGCTAACTAGCTGACTAATATGAATTAAATATGGATTGA";
        StorageResource genes =getAllGenes(newdna);
        System.out.println("all genes are "+ genes.data());
        
       
    }
    
    
    public void testPrint()
    {
        //String dna1 = "AATGCTAACTAGCTGACTAAT";
        //System.out.println("original string: "+ dna1);
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        String newdna = dna.toUpperCase();
        System.out.println("dna= "+dna.toUpperCase());
        System.out.println("dna length= "+dna.length());
        printAllGenes(newdna);
        //printAllGenes(dna1);
        
    
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
