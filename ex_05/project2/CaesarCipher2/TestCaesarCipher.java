
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (11/13/19)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;


public class TestCaesarCipher 
{
    //the method is to count the each letter in the string. The key is comparing input with alphabet and get the index in the alphabet as the array's index!!!
    public int[] countLetters(String input)
    {
        //char startletter = '\0';
        StringBuilder countString = new StringBuilder(input);
        String alphabet = "abcdefghijklmnopqrstuvwxyz"; 
        int[] count = new int[26];
        for(int i=0;i<countString.length();i++)
        {
            char newletter = countString.charAt(i);
            char lownewletter = Character.toLowerCase(newletter);
            int countIndex = alphabet.indexOf(lownewletter);
            if(countIndex!=-1)
            {
                count[countIndex]=count[countIndex]+1;
            }
            else
            {
                continue;
            }
           //System.out.println("countIndex="+countIndex+" newletter= "+ newletter+" count= "+count[countIndex]);
       
        }
        
        
        return count;
    }
    
    public int maxIndex(int[] counts)
    {
      int maxindex = 0;
      for(int i=0;i<counts.length;i++)
      {
          if(counts[i]>counts[maxindex])
          {
              maxindex = i;
          }
          
      }  
       return maxindex;
    }
    
    void simpleTests()
    {
      FileResource fr = new FileResource();
      String message = fr.asString();
      System.out.println("original message: "+message);
      //int[] count = new int[256];
     // count = countLetters(message);
      CaesarCipher cc = new CaesarCipher(15);
      String encrypted = cc.encrypt(message);
      System.out.println("encrypted message is "+ encrypted);
     //System.out.println("decrypted message is " +cc.decrypt(encrypted));
      //String breakencrypted = breakCaesarCipher(encrypted);
     // System.out.println("breakencrypted message is "+breakencrypted);
      //String decrypted = cc.decrypt(breakencrypted);
      //System.out.println("decrypted message is "+decrypted);
      
    }
    
    public String breakCaesarCipher(String input)
    {
        int[] freqs = countLetters(input);
        //print out all numbers in array
        /*for(int i=0;i<freqs.length;i++)
        {
            System.out.println("frequency array is "+freqs[i]);
        }*/
        int maxIndex = maxIndex(freqs);
        System.out.println("maxIndex= "+maxIndex);
        //int deIndex = maxIndex - 4;
        //8 is depending on the position where the most frenqucy letter of original messages shows in alphabet
        int deIndex = maxIndex-8;
        if(maxIndex<8)
        {
            deIndex = 26-(8-maxIndex);
        }
        
        CaesarCipher cc = new CaesarCipher(26-deIndex);
        //System.out.println("breakencrypted message is "+cc.encrypt(input));
        return cc.encrypt(input);
        
    }
}
