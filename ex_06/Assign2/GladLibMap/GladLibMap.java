
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (01/27/19)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> wordList;
    private int totalunique;
    private int consideredNum;
    private String findLabel;
    private ArrayList<String> wordConsidered;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<>();
        //need intialize hashmap before initializefromsource because the source need to put in hashmap.
        //if not,will get NullPointException Error!!
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordList = new ArrayList<String>();
        wordConsidered = new ArrayList<String>();
        totalunique = 0;
        consideredNum = 0;
        findLabel = "";
        
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        wordList = new ArrayList<String>();
        wordConsidered = new ArrayList<String>();
       
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for(String s:labels)
        {
          ArrayList<String> list = readIt(source+"/"+s+".txt");
          myMap.put(s,list);
        }
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
      
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        findLabel = w.substring(first+1,last);
        String sub = getSubstitute(w.substring(first+1,last));
        consideredNum = totalWordsConsidered(findLabel);
        //System.out.println("FindLabel in processWord is "+findLabel);
        //System.out.println("total words considered is "+consideredNum);
        while(!unique(sub))
        {
        
           sub = getSubstitute(w.substring(first+1,last));
        }        
        return prefix+sub+suffix; 
    }
    //this method is to return true if the word is not in wordList.
    private boolean unique(String sub)
    {
        //wordList.clear();
        int index = wordList.indexOf(sub);
        if(index == -1)
        {
            wordList.add(sub);
            totalunique +=1;    
        }
        else
        {  
            return false;   
        }
        return true;       
    }
    
    private int totalWordsInMap()
    {
        int totalWords = 0;
        for(String label:myMap.keySet())
        {
           ArrayList<String> listInMap = myMap.get(label);
           //get size of everylist in map
           totalWords+=listInMap.size();   
        } 
        return totalWords;
    }
    
    private int totalWordsConsidered(String sub)
    {
           int index = wordConsidered.indexOf(sub);
           //System.out.println("index="+index);
           if(index == -1)
           {
               wordConsidered.add(sub);
               for(String label:myMap.keySet())
               {
                   //System.out.println("get in map****");
                   if(label.equals(sub))
                   {
                      // System.out.println("label is equal****");
                       ArrayList<String> listInMap = myMap.get(label);
                       consideredNum += listInMap.size();  
                    }
                
               }
           }
           /*for(int i=0;i<wordConsidered.size();i++)
           {System.out.println("label list is "+wordConsidered);
            }*/
       return consideredNum; 
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory()
    {
        System.out.println("\n");
        wordList.clear();
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
       /* for(int i=0;i<wordList.size();i++)
        {
            System.out.println(wordList.get(i));
        }*/
        System.out.println("\n");
        System.out.println("total unique number is "+totalunique);
        //System.out.println("\n");
        int totalwords = totalWordsInMap();
        System.out.println("total words In map is "+totalwords);
        //String findlabel = "";
        //int consideredwords = totalWordsConsidered();
        System.out.println("total words considered is "+consideredNum); 
    }
    

}
