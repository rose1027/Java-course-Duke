
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (01/19/19)
 */

import java.util.ArrayList;
import edu.duke.*;
import java.io.*;
import java.lang.*;

public class WordFrequencies 
{
    // myWords should store unique words from a file
    private ArrayList<String> myWords;
    //The kth position in myFreqs should represent the number of times the kth word in myWords occurs in the file.
    private ArrayList<Integer> myFreqs;
    //initialize empty arraylist!
    public WordFrequencies()
    {
       myWords = new ArrayList<String>();
       myFreqs = new ArrayList<Integer>();
    }
    
   public int findUnique()
   {
       myWords.clear();
       myFreqs.clear();
       FileResource fr = new FileResource();
       //total number of unique words.
       int numofuniq = 0;
       for(String word:fr.words())
       {
           word = word.toLowerCase();
           int index = myWords.indexOf(word);
           //word is not find in myWords list then add it to myWord and update myFreqs.
           if(index == -1)
           {
               myWords.add(word); 
               numofuniq += 1;
               myFreqs.add(1);
           }
           else
           {
               
               int value = myFreqs.get(index);
               myFreqs.set(index,value+1);
           }
       }
       return numofuniq;
   }
   
   public int findIndexOfMax()
   {
       int max = 0;
       int maxIndex = 0;
       for(int i=0;i<myFreqs.size();i++)
       {
          if( myFreqs.get(i)>max)
          {
              max = myFreqs.get(i);
              maxIndex = i;
          }
          else
          { continue;}
       }
       return maxIndex;
   }
   
   public void tester()
   {
     int unique = findUnique();
     int maxIndex = findIndexOfMax();
     System.out.println("Number of unique words: "+unique);
     for(int k=0; k<myWords.size();k++)
     {
         System.out.println(myFreqs.get(k)+ "\t"+ myWords.get(k));
     }
     System.out.println("The word that occurs most often and its count are: "+myWords.get(maxIndex)+" "+myFreqs.get(maxIndex));
   }

}
