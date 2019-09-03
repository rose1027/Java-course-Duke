
    
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;

public class TestCaesarCipherTwo 
{
    
 private int[] countLetters(String input)
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
           System.out.println("countIndex="+countIndex+" newletter= "+ newletter+" count= "+count[countIndex]);
       
        }
        return count;
    }
    
     private int maxIndex(int[] counts)
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
    
    public String halfOfString(String message,int start)
    {
        StringBuilder encrypted = new StringBuilder(message);
        StringBuilder halfencrypted= new StringBuilder();
        if(start == 0)
        {
            for(int i=0;i<encrypted.length();i+=2)
            {
                char ch = encrypted.charAt(i);
                //System.out.println("ch= "+ch);
                //put character into empty stringbuilder one by one.
                halfencrypted.append(ch);
                //System.out.println(half);
            }  
        }
        if(start == 1)
        {
            for(int j=1;j<encrypted.length();j+=2)
            {
                char ch = encrypted.charAt(j);
                //System.out.println("ch= "+ch);
                //put character into empty stringbuilder one by one.
                halfencrypted.append(ch);
                //System.out.println(newodd);
            }   
        }
        System.out.println("half string is "+halfencrypted.toString());
        return halfencrypted.toString();       
    }
    
    public int getKey1(String s)
    {
     int[] freqs = countLetters(s);  
     int maxIndex = maxIndex(freqs);
      int dkey = maxIndex - 4;
        if(maxIndex<4){
            dkey = 26-(4-maxIndex);
            
        }
        
        return dkey;
     
    }
    public int getKey2(String s)
    {
     int[] freqs = countLetters(s);  
     int maxIndex = maxIndex(freqs);
      int dkey = maxIndex - 4;
        if(maxIndex<4){
            dkey = 26-(4-maxIndex);
            
        }
        
        return dkey;
     
    }
    
    public String decryptTwoKeys(String encrypted)
    {
       String FirstHalf = halfOfString(encrypted,0);
       String SecondHalf =halfOfString(encrypted,1);
       int k1 = getKey1(FirstHalf);
       int k2 = getKey2(SecondHalf);
       System.out.println("k1= "+k1+" k2= "+k2);
       //k1 and k2 are encrypted keys
       CaesarCipherTwo cc = new CaesarCipherTwo(26-k1,26-k2);
        //System.out.println("breakencrypted message is "+cc.encrypt(input));
       return cc.encryptTwoKeys(encrypted);
        
    } 
    
    public void testsingleDecrypt()
    {
        String  message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        //CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
        CaesarCipherTwo cc = new CaesarCipherTwo(21,8);
        String encrypted = cc.encryptTwoKeys(message);
      System.out.println("encrypted message is "+ encrypted);;
        //String encrypted = "Top ncmy qkff vi vguv vbg ycpx";
        //String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        //String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
       // String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        //String decrypted = cc.decrypt(encrypted);
        //System.out.println("decrypted message is "+decrypted);
       // String decryptedTwoKeys = decryptTwoKeys(encrypted);
      //System.out.println("decrypted message is "+decryptedTwoKeys);
    }
    public void simpleTest()
    {
      FileResource fr = new FileResource();
      String message = fr.asString();
      System.out.println("original message is "+message);
      CaesarCipherTwo cc = new CaesarCipherTwo(21,8);
      String encrypted = cc.encryptTwoKeys(message);
      System.out.println("encrypted message is "+ encrypted);
      //String decrypted = cc.decrypt(encrypted);
      //System.out.println("decrypted message is "+decrypted);
      //String decryptedTwoKeys = decryptTwoKeys(message);
      //System.out.println("decrypted message is "+decryptedTwoKeys);
    }
}



