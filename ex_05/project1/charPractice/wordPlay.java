
/**
 * Write a description of wordPlay here.
 * 
 * @author (Tingting Hu) 
 * @version (01/09/2019)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;

public class wordPlay 
{
    //return true if ch is a vowel or return false if ch is not a vowel
    public boolean isVowel(char ch)
    {
        String vowel = "aeiou";
        String vowelupper = vowel.toUpperCase();
        for(int i=0;i<vowel.length();i++)
        {
            char lowerletter = vowel.charAt(i);
            char upperletter = vowelupper.charAt(i);
            if(lowerletter == ch ||upperletter == ch)
            {
                return true;
            }
        }
        return false;
    }
    
    public void testisVowel()
    {
        char ch = 'i';
        System.out.println("i is vowel is "+isVowel(ch));
        char ch2 = 'I';
        System.out.println("I is vowel is "+isVowel(ch2));
    }
    //return the String with all the vowels (uppercase or lowercase) replaced by ch
    public String replaceVowels(String phrase, char ch)
    {
        StringBuilder newphrase = new StringBuilder(phrase);
        for(int i=0;i<newphrase.length();i++)
        {
            char word = newphrase.charAt(i);
            if(isVowel(word))
            {
                word = ch;
            }
            //insert new ch into the position where the vowel was.
            newphrase.setCharAt(i,word);
        }
        //stringbuilder transfer to string
        return newphrase.toString();
    }
    
    public void testreplaceVowels()
    {
        String phrase = replaceVowels("Hello World", '*');
        System.out.println(phrase);
    }
    //return a string when it has ch in even index replaced as "*" and when it has ch in odd index replaced as "+"
    public String emphasize(String phrase, char ch)
    {
        String lowerphrase = phrase.toLowerCase();
        StringBuilder newphrase = new StringBuilder(lowerphrase);
        for(int i=0;i<newphrase.length();i+=2)
        {
            char oddword = newphrase.charAt(i);
            if(oddword == ch)
            {
              oddword = '*';  
            }
            newphrase.setCharAt(i,oddword);
            
        }
        for(int j =1; j<newphrase.length();j+=2)
        {
                char evenword = newphrase.charAt(j);
                if(evenword == ch)
                {
                    evenword = '+';  
                }
                newphrase.setCharAt(j,evenword);
        }
        
        return newphrase.toString();
    }
    
    public void testemphasize()
    {
        String phrase = emphasize("dna ctgaaactga",'a');
        System.out.println(phrase);
        String phrase2 = emphasize("Mary Bella Abracadabra",'a');
        System.out.println(phrase2);
        
    }
}

