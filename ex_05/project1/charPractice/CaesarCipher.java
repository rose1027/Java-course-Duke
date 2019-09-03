
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (01/10/2019)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;

public class CaesarCipher 
{
    //
    public String encrypt(String input, int key)
    {
        String Alphbat = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //StringBuilder output = new StringBuilder();
        String loweralphbat = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder encrypted = new StringBuilder(input);
        StringBuilder shiftedalphbat = new StringBuilder(Alphbat.substring(key)+Alphbat.substring(0,key));
        StringBuilder shiftedlowealph = new StringBuilder(loweralphbat.substring(key)+loweralphbat.substring(0,key));
        //System.out.println("k2 of encrypted is "+shiftedalphbat.toString());
        for(int i=0;i<encrypted.length();i++)
        {
            char eachch = encrypted.charAt(i);
            //find the index of each character of original string in alphbat
            int chIndex = Alphbat.indexOf(Character.toString(eachch));
            int lowerchIndex = loweralphbat.indexOf(Character.toString(eachch));
            //find corresponding character according to the index was found above and rewrite the original string
            if(chIndex != -1){
                char findch =shiftedalphbat.charAt(chIndex);
            
                encrypted.setCharAt(i,findch);
            } 
            if(lowerchIndex != -1)
            {
                char findch =shiftedlowealph.charAt(lowerchIndex);
            
                encrypted.setCharAt(i,findch); 
            }
        }
        return encrypted.toString();
    }
    
    public void testencrypt()
    {
        String input = encrypt("FIRST LEGION ATTACK EAST FLANK!", 23);
        System.out.println("encrypted code is "+ input);
        
    }
    
    public void testCaesar()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 15;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n"+encrypted);
        
    }
    //encrpt the string according two keys
    public String encryptTwoKeys(String input,int key1,int key2)
    {
        String Alphbat = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String loweralphbat = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder encrypted = new StringBuilder(input);
        StringBuilder shiftedalphbat = new StringBuilder(Alphbat.substring(key1)+Alphbat.substring(0,key1));
        StringBuilder shiftedlowealph = new StringBuilder(loweralphbat.substring(key1)+ loweralphbat.substring(0,key1));
        StringBuilder shiftedalphbatWithk2 = new StringBuilder(Alphbat.substring(key2)+Alphbat.substring(0,key2));
        StringBuilder shiftedlowealphWithk2 = new StringBuilder(loweralphbat.substring(key2)+ loweralphbat.substring(0,key2));
        //System.out.println("k2 of encrypted is "+shiftedalphbatWithk2.toString());
        //index is even number using k1
        for(int i=0;i<encrypted.length();i+=2)
        {
           
            char eachoddch = encrypted.charAt(i);
            int chIndex = Alphbat.indexOf(Character.toString(eachoddch));
            int lowerchIndex = loweralphbat.indexOf(Character.toString(eachoddch));
            if(chIndex != -1){
                char findch =shiftedalphbat.charAt(chIndex);
            
                encrypted.setCharAt(i,findch);
            } 
            if(lowerchIndex != -1)
            {
                char findch =shiftedlowealph.charAt(lowerchIndex);
            
                encrypted.setCharAt(i,findch); 
            }
            
            
        }
        //index is odd number using key2
        for(int j=1;j<encrypted.length();j+=2)
            {
              char evench = encrypted.charAt(j);
             // System.out.println("j= "+j+" odd num is "+ evench);
              int upperIndex = Alphbat.indexOf(Character.toString(evench));
              //System.out.println("upperIndex="+upperIndex);
              int lowerIndex = loweralphbat.indexOf(Character.toString(evench));
              //System.out.println("lowerIndex="+lowerIndex);
              if(upperIndex != -1)
              {
                char findword = shiftedalphbatWithk2.charAt(upperIndex);
            
                encrypted.setCharAt(j,findword);
              } 
              if(lowerIndex != -1)
              {
                char findword = shiftedlowealphWithk2.charAt(lowerIndex);
            
                encrypted.setCharAt(j,findword); 
               // System.out.println("encrypted string"+encrypted.toString());
              }
              
            }
       
        return encrypted.toString();
    }
    
    public void testencryptedtwokeys()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key1 = 8;
        int key2 = 21;
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("key1 is " + key1 + " key2 is "+key2+"\n"+encrypted);
    }
}
