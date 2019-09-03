
/**
 * Write a description of Part1 here.
 * 
 * @author (Tingting) 
 * @version (01/05/2019)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;
import org.apache.commons.csv.*;

public class Part1 
{
    public void printbabynameInfo()
    {   //pick up file from random files
        FileResource fr = new FileResource();
        CSVParser parser= fr.getCSVParser(false);
        for(CSVRecord rec:parser)
        {
            System.out.print("Name: " + rec.get(0));
            System.out.print(" Gender: " + rec.get(1));
            System.out.println(" Num: " + rec.get(2)); 
        }
    }
    
    public void totalBirths(FileResource fr)
    {
       CSVParser parser= fr.getCSVParser(false);
       int totalnum = 0;
       int totalgirlnum = 0;
       int countgirlname = 0;
       int countboyname = 0;
       
       for(CSVRecord rec:parser)
       {
           int currnum = Integer.parseInt(rec.get(2));
           totalnum = totalnum + currnum;
           String curgender = rec.get(1);
           if(curgender.equals("F"))
           {
               totalgirlnum += currnum;
               countgirlname++;
               
           }
           if(curgender.equals("M"))
           {
               countboyname++;
           }
           
       }
       //System.out.println("totalnum = " + totalnum);
       //System.out.println("totalgirlnum = " + totalgirlnum);
       System.out.println("num of girls name = " + countgirlname);
       System.out.println("num of boys name = " + countboyname);
       int totalboynum = totalnum - totalgirlnum;
       //System.out.println("totalboynum = " + totalboynum);
       int totalcountname = countgirlname + countgirlname;
       System.out.println("num of total name = " + totalcountname);
    }
    
    public void testotalBirths()
    {
       // FileResource fr = new FileResource("us_babynames_small/testing/yob2012short.csv");
       FileResource fr = new FileResource();
        totalBirths(fr);
    }
    // find the rank depending on baby's name and gender.
    public int getRank(int year, String name, String gender)
    {
      int countrank = 0;
      int rank = 0;
      FileResource fr = new FileResource();
      CSVParser parser = fr.getCSVParser(false);
      for(CSVRecord rec:parser)
      {
          String Babyname = rec.get(0);
          String Babygender = rec.get(1);
          //int Totalnum = Integer.parseInt(rec.get(2));
           System.out.print(" Gender: " + Babygender);
          if(Babygender.equals(gender))
          {
              System.out.println("***find the gender***");
              //System.out.print(" Name: " + Babyname);
              //this is the way to compare two strings are not the same!!!
              if(!Babyname.equals(name))
              {
                  System.out.println("***not the same name***");
                countrank++;  
              }
              else
              {
                  rank = countrank + 1;
                  System.out.println("rank is "+ rank);  
                  return rank;
              }
              
          }
          
      }
     // not find the rank of this babyname
        return -1;    
    }
    
    public int getRankWithoutYear(CSVParser parser,String name, String gender)
    {
      int countrank = 0;
      int rank = 0;
      //FileResource fr = new FileResource();
      //CSVParser parser = fr.getCSVParser(false);
      for(CSVRecord rec:parser)
      {
          String Babyname = rec.get(0);
          String Babygender = rec.get(1);
          //int Totalnum = Integer.parseInt(rec.get(2));
          //System.out.print(" Gender: " + Babygender);
          if(Babygender.equals(gender))
          {
              //System.out.println("***find the gender***");
              //System.out.print(" Name: " + Babyname);
              //this is the way to compare two strings are not the same!!!
              if(!Babyname.equals(name))
              {
                 // System.out.println("***not the same name***");
                countrank++;  
              }
              else
              {
                  rank = countrank + 1;
                  System.out.println("rank is "+ rank);  
                  return rank;
              }
              
          }
          
      }
     // not find the rank of this babyname
        return -1;    
        
    }
    
    public void testgetRank()
    {
        int rank = getRank(1971,"Frank","M");
        System.out.println("rank = "+rank);
        
    }
    //This method get baby name through the year,rank and gender
    public String getName(int year,int rank,String gender)
    {
      int count = 1;// to set count =1 is to match number with rank.
      FileResource fr = new FileResource();
      CSVParser parser = fr.getCSVParser(false);
      String babyName = "";
      for(CSVRecord rec:parser)
      {
         String babyGender = rec.get(1);
          if(babyGender.equals(gender))
          {
            
            if(count<rank){
             //System.out.println("count= "+count);
             babyName = rec.get(0);
             //System.out.println("name = "+babyName);
             count++;
             continue;
            }
            if(count == rank)
            {
             babyName = rec.get(0);
             System.out.println(" find name = "+babyName); 
             return babyName;
            }
            
           }
           
      }
      //System.out.println("name = "+babyName);
     // return babyName;
     return "NO NAME";
    }
    
    public void testgetName()
    {
        String babyname = getName(1982,450,"M");
       System.out.println("babyname = "+babyname); 
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
      int rank = getRank(year,name, gender);
      String babyname = getName(newYear,rank,gender);
      System.out.println(name+" born in "+year+" would be " + babyname+ " if she was born in "+newYear); 
    }
    
    public void testwhatIsNameInYear()
    {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name,String gender)
    {
        int highestrank = -1;
        int year = -1;
        int temp = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            String filename = f.getName();
            year = getYear(filename);
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int currank = getRankWithoutYear(parser,name,gender);
            if(currank != -1 && highestrank == -1)
                {
                    highestrank = currank;
                    temp = year;
                }
                else
                {
                    if(highestrank == -1 && currank == -1)
                    {
                        continue;
                    }
                    if(currank < highestrank && currank != -1)
                    {
                        highestrank = currank;
                        temp = year;
                        //System.out.println("highestrank when curr< highestrank is "+highestrank);
                    }
                    
                }
 
        }
        System.out.println("Highest rank is "+ highestrank);
    return temp;
   }
   
   public int getYear(String filename)
   {
            System.out.println("open the file: " + filename);
            int startIndex = filename.indexOf("b")+1;
            int endIndex = filename.indexOf(".");
            String Syear = filename.substring(startIndex,endIndex);
            int year = Integer.parseInt(Syear);
            System.out.println("year is "+ year);
            return year;
       
    }
   
   public void testyearOfHighestRank()
   {
       int year = yearOfHighestRank("Mich","M");
       System.out.print("highestrank in the year is "+year);
   }
   
   public double getAverageRank(String name,String gender)
   {
       double totalrank = 0.0;
       int count = 0;
       double averagerank = 0.0;
      // double temprank = 0;
       DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            count++;
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            double temprank = getRankWithoutYear(parser,name,gender);
     
            if(temprank == -1)
            {
                temprank = 0.0;
                System.out.println("temprank is changed to "+temprank);
            }
            
            totalrank = totalrank + temprank;
            System.out.println("totalrank= "+totalrank);
            System.out.println("count= "+count);
            
        }
        if(totalrank == 0)
        {
            return -1.0;
        }
        averagerank = totalrank / count;
        return averagerank;
       
   }
   
   public void testgetAverageRank()
   {
       double average = getAverageRank("Robert","M");
       System.out.println("averagerank= "+average);
   }
   //This method returns an integer, the total number of births of those names with the
//same gender and same year who are ranked higher than name.
   public int getTotalBirthsRankedHigher(int year,String name,String gender)
   {
     FileResource fr = new FileResource();
     CSVParser parser = fr.getCSVParser(false); 
     int totalBirthrank = 0;
     for(CSVRecord rec:parser)
     {
         String babyGender = rec.get(1);
         
        
         if(babyGender.equals(gender))
         {
            String babyName = rec.get(0);
            if(babyName.equals(name))
            {
                break;
            }
            int totalnum = Integer.parseInt(rec.get(2));
            System.out.println("totalnum is "+totalnum);
            totalBirthrank += totalnum;
            System.out.println("totalbirthsranked is "+totalBirthrank);
            
            
         }
         
     }
     return totalBirthrank;
   }
   
   public void testgetTotalBirthsRankedHigher()
   {
       int totalbirthrank = getTotalBirthsRankedHigher(1990,"Drew","M");
       System.out.println("totalbirthsrankedhigher is "+totalbirthrank);
    }
}
