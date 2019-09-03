
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (01/26/19)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles 
{
    // maps a word to an ArrayList of filenames
    private HashMap<String, ArrayList<String>> myMap;
    //ArrayList<String> filenames;  
    public WordsInFiles()
    {
        myMap = new HashMap<>();
        //filenames = new ArrayList<>();
    }
    /*This method should add all the words from f into the map. If a word is not in
the map, then you must create a new ArrayList of type String with this word, and have
the word map to this ArrayList. If a word is already in the map, then add the current
filename to its ArrayList, unless the filename is already in the ArrayList.*/
    private void addWordsFromFile(File f)
    {
            File currentFile = f;
            FileResource fr = new FileResource(f); 
            for(String word:fr.words())
            {
                if(!myMap.containsKey(word))
                {
                   
                    ArrayList<String> newfile = new ArrayList<>();
                    newfile.add(currentFile.getName());
                    myMap.put(word,newfile);
                }
                else
                {
                    if(!myMap.get(word).contains(currentFile.getName()))
                    {
                        //System.out.println("add new file "+f.getName());
                        //get current arraylist from map and add new file name in that arraylist
                        ArrayList<String> currArray = myMap.get(word);
                        currArray.add(currentFile.getName());
                        myMap.put(word,currArray);
                        
                    }
                }
            }
            
         
        
    }
    
    private void buildWordFileMap()
    {
      myMap.clear();
      DirectoryResource dr = new DirectoryResource();
      for(File f : dr.selectedFiles())
      {
         addWordsFromFile(f); 
      }
      /*Set<Map.Entry<String,ArrayList<String>>> hashSet=myMap.entrySet();
         for(Map.Entry<String,ArrayList<String>> entry:hashSet ) {

            System.out.println("Key="+entry.getKey()+", Value="+entry.getValue());
        }*/
    }
    //This method returns the maximum number of files any word appears in, considering all words from a group of files. 
    private int maxNumber()
    {
        int max = 0;
        
        for(String word:myMap.keySet())
        {
            
            ArrayList<String> list = myMap.get(word);
            int currentCount = wordOccurence(list);
            if(currentCount>max)
            {
                max = currentCount;
            }
        }
        return max;
        
    }
    
    private int wordOccurence(ArrayList<String> list)
    {
        int currentcount = 0;
        for(int i=0; i<list.size();i++)
        {
             //System.out.println("i= "+i+"items in list are "+list.get(i));
             currentcount+=1;
        }
        return currentcount;
    }
  //This method returns an ArrayList of words that appear in exactly number files.  
    private ArrayList wordsInNumFiles(int number)
    {
      ArrayList<String> numofWords = new ArrayList<String>();
      for(String word:myMap.keySet())
      {
          ArrayList<String> list = myMap.get(word);
          int wordcount = wordOccurence(list);
          if(number == wordcount)
          {
              numofWords.add(word);
          }
      }
      return numofWords;
    }
    
    private void printFilesIn(String word)
    {
         for(String w:myMap.keySet())
         {
             if(w.equals(word))
             {
                 System.out.println(myMap.get(w));
              }
         }        
    }
    
    public void tester()
    {
      buildWordFileMap();
      int maxnumOfile = maxNumber();
      int totalwordsIn = 0;
      System.out.println("max num of files is "+ maxnumOfile);
      ArrayList wordsList = wordsInNumFiles(4);
      //System.out.println("words shows in 5 files are "+wordsList);
      //get totalwords doesn't need to iterate the whole list!!!just use total+= list.size()
      totalwordsIn += wordsList.size();
      System.out.println("total words in 4 files are "+totalwordsIn);       
      printFilesIn("sea");      
    }
    
}
