
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (01/15/19)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;

public class WordLengths 
{

    public void countWordLengths(FileResource resource,int[] counts)
    {
        
        for(String word : resource.words())
        {
           //index = word's length
            int index = indexof(word);
           if(index!=-1)
           {
               counts[index]+=1;
               //System.out.println("wordlength= "+index+" count= "+counts[index]);
           }
           
        }
        for(int i=2;i<counts.length;i++)
           {
             System.out.println(counts[i] +"words of length "+i); 
           }
        
    }
    //return word's length
    public int indexof(String word)
    {
        int wordlength = 0;
        //int[] count = new int[256];
         for(int k=0;k<word.length();k++)
            {
                char ch = word.charAt(k);
                if(Character.isLetter(ch))
                {
                  wordlength+=1;  
                }
                else if(ch=='-')
                {
                    wordlength+=1;
                }
                else if(ch == '\'')
                {
                    wordlength+=1;
                }
                else if(ch == ' ')
                {
                    break;
                }
                //count[wordlength]+=1;
            }
            // System.out.println("word length is "+wordlength);
            return wordlength;
    }
    
    public int indexOfMax(int[] values)
    {
        int maxIndex = 0;
        for(int i=0;i<values.length;i++)
        {
            if(values[i]>values[maxIndex])
            {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public void testCountWordLengths()
    {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr,counts);
        int commonLength = indexOfMax(counts);
        System.out.println("commonLength= "+commonLength);
    }
}
