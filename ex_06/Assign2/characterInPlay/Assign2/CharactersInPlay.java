
/**
 * Write a description of CharactersInPlay here.
 * This class is to find all the characters in a play and record how many times they spoke in a play.
 * @author (your name)
 * @version (01/19/19)
 */
import java.util.ArrayList;
import edu.duke.*;
import java.io.*;
import java.lang.*;

public class CharactersInPlay
{
    private ArrayList<String> CharacterName;
    private ArrayList<Integer> NameFreqs;
    public CharactersInPlay()
    {
        CharacterName = new ArrayList<String>();
        NameFreqs = new ArrayList<Integer>();
    }

    public void update(String person)
    {
      int index = CharacterName.indexOf(person);
      // the name is not in the CharacterName list then put it in also update nameFreqs list to 1;
      if(index == -1)
      {
          CharacterName.add(person);
          NameFreqs.add(1);
      }
      // the name is already in CharacterName list.Get the value from the index of the name and update value to value+1.
      else
      {
          int value = NameFreqs.get(index);
          NameFreqs.set(index,value+1);
      }
    }

    public void findAllCharacters()
    {
       CharacterName.clear();
       NameFreqs.clear();
       FileResource fr = new FileResource();
       String name="";
       for(String line:fr.lines())
       {
          int endIndex = line.indexOf('.');
          //System.out.println("endIndex "+endIndex);
          if(endIndex!=-1)
          {
               name = line.substring(0,endIndex);
               //System.out.println("name is "+ name);
           }
           else{continue;}
          if(name.contains(","))
          {
           continue;
          }
          else
          {
           update(name);
          }

       }
    }

    public void tester()
    {
        findAllCharacters();
        int num1 = 10;
        int num2 = 15;
        for(int i=0;i<CharacterName.size();i++)
        {
            if(NameFreqs.get(i)>60)
            {
                System.out.println(CharacterName.get(i)+"\t"+NameFreqs.get(i));
            }

         }
           charactersWithNumParts(num1,num2);
    }
//This method should print out the names of all those characters that have exactly number speaking parts,
    public void charactersWithNumParts(int num1,int num2)
    {
        for(int i=0;i<CharacterName.size();i++)
        {
            if(NameFreqs.get(i)>= num1 && NameFreqs.get(i) <= num2)
            {


                System.out.println(CharacterName.get(i));
            }
        }
    }

}
