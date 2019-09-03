import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    //public static String dataSourceDirectory;
   // public HashMap<String,HashSet<String>> dictionaries; 
    public VigenereBreaker()
    {
        
       // dataSourceDirectory = "dictionaries";
        //dictionaries = new HashMap<>();
        //initializeFromSource(dataSourceDirectory);
    }
    public String sliceString(String message, int whichSlice, int totalSlices) 
    {
            //stringbuilder can be changed, string can not be changed!
            StringBuilder slice = new StringBuilder();
       
            for(int i=whichSlice;i<message.length();i+=totalSlices)
            {
                char letter = message.charAt(i);
                //System.out.println("letter is "+letter);
                slice.append(letter);
                
            }
            //System.out.println(slice);
        
            return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) 
    {
        int[] key = new int[klength];
        ArrayList<String> slice = new ArrayList<>();
        for(int i=0;i<klength;i++)
        {
             slice.add(sliceString(encrypted,i,klength));
        }
        CaesarCracker cc = new CaesarCracker();
        for(int i=0;i<klength;i++)
        {
            key[i]=cc.getKey(slice.get(i));
        }
       
        return key;
    }

    public void breakVigenere1() 
    {
        //WRITE YOUR CODE HERE
      FileResource fr = new FileResource();
      String contents = fr.asString();
      int[] key = tryKeyLength(contents,4,'e');
      VigenereCipher vc = new VigenereCipher(key);
      String decrypted = vc.decrypt(contents);
      System.out.println("original message is "+decrypted);
    }
    
    public void testsliceString()
    {
        String original = "abcdefghijklm";
        String slice0 = sliceString(original,0,5);
        System.out.println(slice0);
        String slice1 = sliceString(original,1,5);
        System.out.println(slice1);
        String slice2 = sliceString(original,2,5);
        System.out.println(slice2);
        String slice3 = sliceString(original,3,5);
        System.out.println(slice3);
        String slice4 = sliceString(original,4,5);
        System.out.println(slice4);
    }
    
    public void testtryKeyLength()
    {
      FileResource fr = new FileResource();
      String contents = fr.asString();
      int keyLength = 5;
      int[] key = tryKeyLength(contents,keyLength,'e');
      for(int i=0;i<keyLength;i++)
      {
          System.out.println(key[i]);
      }
    }
    
    public HashSet<String> readDictionary(FileResource fr)
    {
      HashSet<String> dict = new HashSet<String>();
      for(String word:fr.words())
      {
          dict.add(word.toLowerCase());
          
      }
      return dict;
      
    }
    
    public int countWords(String message, HashSet dict)
    {
        String[] words = message.split("\\W+");
        int count = 0;
        for(String word:words)
        {
           if(dict.contains(word.toLowerCase()))
           {
               count++;
           } 
        }
       return count; 
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dict)
    {
        int maxcount = 0;
        String findecrypted = "";
        for(int keyLength=1;keyLength<100;keyLength++)
        {
            char commonchar = mostCommonCharIn(dict);
            int[] key = tryKeyLength(encrypted,keyLength,commonchar);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int curcount = countWords(decrypted,dict);
            if(curcount > maxcount)
            {
                maxcount = curcount;
                System.out.println("maxcount= "+maxcount);
                findecrypted = decrypted;
                System.out.println("keyLength= "+keyLength);
            }
        }
        
        return findecrypted;
    }
    
     public void breakVigenere2() 
    {
      FileResource fr = new FileResource();
      String contents = fr.asString();
      FileResource f = new FileResource();
      HashSet<String> dict = readDictionary(f);
      String decrypted = breakForLanguage(contents,dict);
      System.out.println("original message is "+decrypted);
    }
    
    public char mostCommonCharIn(HashSet<String> dict)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //put each alphabet and count number of each alphabter in dictionary in the hashmap.
        HashMap<Character,Integer> alph = new HashMap<Character,Integer>();
        
        for(int i=0; i<alphabet.length();i++)
        {
            char curchar = alphabet.charAt(i);
            //find how many each alphabet is in each word of dictionary
            for(String word:dict)
            {
                int curcount = 0;
                for(int j=0;j<word.length();j++)
                {
                    char charIndict = word.charAt(j);
                    
                    if(charIndict == curchar)
                    {
                        curcount +=1;
                    }
                }
                if(!alph.containsKey(curchar)){
                    alph.put(curchar,curcount);
                }
                else
                {
                    alph.put(curchar,alph.get(curchar)+curcount);
                }
            }
        }
        //System.out.println(alph);
        int max = 0;
        char commonle = '\u0000';
        for(char ch:alph.keySet())
        {
            int curcount = alph.get(ch);
            if(curcount > max)
            {
                max = curcount;
                commonle = ch;
            }
        }
        return commonle;
    }
    
    public HashSet<String> readIn(String source)
    {
      HashSet<String> dict = new HashSet<String>();
      FileResource fr = new FileResource(source);
      for(String word:fr.words())
      {
          dict.add(word.toLowerCase());
          
      }
      return dict;
      
    }
    public HashMap<String,HashSet<String>> dictMap()
    {
        String[] labels = {"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        HashMap<String,HashSet<String>> dictionaries = new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) 
        {
            FileResource fr = new FileResource(f);
            String filename = f.getName();
           // System.out.println("filename is "+filename);
            for(String label:labels)
            {
                if(label.equals(filename))
                {
                    System.out.println("find label and filename are equal***"+label);
                    HashSet<String> dict = readDictionary(fr);
                   
                    dictionaries.put(label,dict);  
                }   
            }
       // System.out.println(dictionaries);
        }
        return dictionaries;
    }
    
    public String breakForAllLanguages(String encrypted)
    {
        HashMap<String,HashSet<String>> languages = dictMap();
        String finaldecrypted = "";
        String finalanguage = "";
        int max = 0;
        for(String lan:languages.keySet())
        {
            System.out.println("current language"+lan);
           
           String curdecrypted = breakForLanguage(encrypted,languages.get(lan));
           int curcount = countWords(curdecrypted,languages.get(lan));
           if(curcount > max)
           {
               max = curcount;
               finaldecrypted = curdecrypted;
               finalanguage = lan;
           }
          // System.out.println(lan+" decrypted is"+curbreak); 
        }
        System.out.println("language is "+ finalanguage);
        return finaldecrypted;
        
    }
    
     public void breakVigenere3() 
    {
      FileResource fr = new FileResource();
      String contents = fr.asString();
      //FileResource f = new FileResource();
      //HashSet<String> dict = readDictionary(f);
      String finalde = breakForAllLanguages(contents);
     // String decrypted = breakForLanguage(contents,dict);
     System.out.println("original message is "+finalde);
    }
    
    public void testmostCommonCharIn()
    {
      FileResource f = new FileResource();
      HashSet<String> dict = readDictionary(f); 
      char ch = mostCommonCharIn(dict);
      System.out.println("common char in dict is "+ch);
    }
}
