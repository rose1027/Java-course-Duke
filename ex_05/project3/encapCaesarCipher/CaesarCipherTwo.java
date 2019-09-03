
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;

public class CaesarCipherTwo {
    private String Alphabat;
    private String loweralphbat;
    private StringBuilder shiftedalphbat;
    private StringBuilder shiftedlowealph;
    private StringBuilder shiftedalphbatWithk2;
    private StringBuilder shiftedlowealphWithk2;
    private int deIndex;
    private int maIndex;
    public CaesarCipherTwo(int key1,int key2)
    {
        Alphabat = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        loweralphbat = "abcdefghijklmnopqrstuvwxyz";
        shiftedalphbat = new StringBuilder(Alphabat.substring(key1)+Alphabat.substring(0,key1));
        shiftedlowealph = new StringBuilder(loweralphbat.substring(key1)+ loweralphbat.substring(0,key1));
        shiftedalphbatWithk2 = new StringBuilder(Alphabat.substring(key2)+Alphabat.substring(0,key2));
        shiftedlowealphWithk2 = new StringBuilder(loweralphbat.substring(key2)+ loweralphbat.substring(0,key2));
        deIndex = key1;
        maIndex = key2;
    }
    
    public String encryptTwoKeys(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);
        //index is even number using k1
        for(int i=0;i<encrypted.length();i+=2)
        {
           
            char eachoddch = encrypted.charAt(i);
            int chIndex = Alphabat.indexOf(Character.toString(eachoddch));
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
              int upperIndex = Alphabat.indexOf(Character.toString(evench));
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
    
    public String decrypt(String encrypted)
    {
        //int key =18;
      //CaesarCipher cc = new CaesarCipher(26-mainKey);
      CaesarCipherTwo cc = new CaesarCipherTwo(26-deIndex,26-maIndex);
      String message = cc.encryptTwoKeys(encrypted);
      return message;
      //return cc.encrypt(input); 
    }
    
}



