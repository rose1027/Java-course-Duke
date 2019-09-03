
/**
 * Write a description of ColonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class ColonCount {
    HashMap<String, Integer> myMap;
    public ColonCount()
    {
        myMap = new HashMap<String,Integer>();
    }
    
    private void buildColon(int start,String dna)
    {
        myMap.clear();
        int uniquenum = 0;
        String colon = "";
        for(int i=start;i<dna.length();i+=3)
        {
            int startIndex = i;
            //System.out.println("startIndex="+startIndex);
            int endIndex = i+3;
           //System.out.println("endIndex= "+endIndex);
            if(startIndex<dna.length() && endIndex-1<dna.length())
           { 
               {
                colon = dna.substring(startIndex,endIndex);
                //System.out.println("colon is "+colon);
               }
               if(!myMap.containsKey(colon))
               {
                myMap.put(colon,1);
                uniquenum+=1;
                //System.out.println("uniquenum= "+ uniquenum);
               }
               else
               {
                //System.out.println("repeat colon****");
                myMap.put(colon,myMap.get(colon)+1);
               }
            }
         }
         System.out.println("Reading frame starting with "+start+" results in "+uniquenum+" unique codons");
         // iterate whole hashmap using 
         /*Set<Map.Entry<String,Integer>> hashSet=myMap.entrySet();
         for(Map.Entry<String,Integer> entry:hashSet ) {

            System.out.println("Key="+entry.getKey()+", Value="+entry.getValue());
        }*/
        
    }
    
    private String getMostCommonCodon()
    {
        int max=0;
        String maxcolon = "";
        
        for(String colon:myMap.keySet())
        {
            int value = myMap.get(colon);
            //find max count number.
            if(max<value)
            {
                max = value;
            }
        }
        //System.out.println("max count is "+ max);
        //find colon that has max count number.
        for(String colon:myMap.keySet())
        {
            if(myMap.get(colon)==max)
            {
                //System.out.println("key="+colon);
                return colon;
            }
        }
        
        return "";
        
        
    }
    
    private void printCondonCounts(int start,int end)
    {
        
        for(String colon:myMap.keySet())
        {
            int count = myMap.get(colon);
            if(count>=start && count<=end)
            {
               System.out.println(colon+"\t"+count); 
            }
        }
    }
    
    public void tester()
    {
        
        String dna = "CGTTCAAGTTCAA";
        dna.toUpperCase();
        buildColon(0,dna);
        String s = getMostCommonCodon();
        System.out.println("and most common codon is "+s+" with count "+myMap.get(s));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCondonCounts(1,5);
        
        buildColon(1,dna);
        String s1 = getMostCommonCodon();
        System.out.println("and most common codon is "+s1+" with count "+myMap.get(s1));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCondonCounts(1,5);
        
        buildColon(2,dna);
        String s2 = getMostCommonCodon();
        System.out.println("and most common codon is "+s2+" with count "+myMap.get(s2));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCondonCounts(1,5);
    }
    
    public void testerFile()
    {
        FileResource fr = new FileResource();
        for(String s:fr.words())
        {
           s = s.trim();
           s.toUpperCase();
           buildColon(0,s);
           String common = getMostCommonCodon();
           System.out.println("and most common codon is "+common+" with count "+myMap.get(common));
           System.out.println("Counts of codons between 1 and 5 inclusive are:");
           printCondonCounts(1,7);
           
           buildColon(1,s);
           String common1 = getMostCommonCodon();
           System.out.println("and most common codon is "+common1+" with count "+myMap.get(common1));
           System.out.println("Counts of codons between 1 and 5 inclusive are:");
           printCondonCounts(1,5);
           
            buildColon(2,s);
            String common2 = getMostCommonCodon();
            System.out.println("and most common codon is "+common2+" with count "+myMap.get(common2));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCondonCounts(1,5);
           
           
        }
        
    }
}
