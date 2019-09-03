
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;

public class CaesarCipher 
{
    private String alphabet;
    private StringBuilder shiftedAlphabet;
    private int mainKey;
    CaesarCipher(int key)
    {
       alphabet = "abcdefghijklmnopqrstuvwxyz"; 
       shiftedAlphabet = new StringBuilder(alphabet.substring(key)+alphabet.substring(0,key));
       mainKey = key;
    }
    
    
    public String encrypt(String input)
    {
        StringBuilder inputencrypted = new StringBuilder(input);
        String Alphabet = alphabet.toUpperCase();
        for(int i=0;i<input.length();i++)
        {
            char word = inputencrypted.charAt(i);
            //System.out.println("word= "+word);
            int index = alphabet.indexOf(word);
           // System.out.println("lowerIndex= "+index);
            int upperIndex = Alphabet.indexOf(word);
           // System.out.println("upperIndex= "+upperIndex);
            if(index != -1)
            {
                char findword = shiftedAlphabet.charAt(index);
                //System.out.println("lowerword= "+findword);
                inputencrypted.setCharAt(i,findword);
            }
            if(upperIndex != -1)
            {
               char findword = Character.toUpperCase(shiftedAlphabet.charAt(upperIndex));
              // System.out.println("upperword= "+findword);
                inputencrypted.setCharAt(i,findword); 
            }
            
        }
        return inputencrypted.toString();
        
    }
    
    
    public String decrypt(String input)
    {

      CaesarCipher cc = new CaesarCipher(26-mainKey);
      
      return cc.encrypt(input);
      
      
    }
    
   
}
