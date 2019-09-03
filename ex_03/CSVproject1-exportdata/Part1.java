
/**
 * Write a description of Part1 here.
 * 
 * @author (Tingting Hu) 
 * @version (12/31/2018)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;
import org.apache.commons.csv.*;

public class Part1 {
    
    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //String country = countryInfo(parser,"Nauru");
        //System.out.println(country);
        //listExportersTwoProducts(parser,"cotton","flowers");
        //int totalcount = numberOfExporters(parser, "cocoa");
        //System.out.println("totalcount= "+totalcount);
        bigExporters(parser,"$999,999,999,999");
       //long a = convertStringtoNumber("$400,000,000");
        
    }
    // this method is get the country information you input in.
    public String countryInfo(CSVParser parser,String country)
    {
        String d = null;
        for(CSVRecord record:parser)
        {
            String eachcountry = record.get("Country");
            
            if(eachcountry.contains(country))
            {
               /* System.out.print(record.get("Country")+": ");
                System.out.print(record.get("Exports")+": ");
                System.out.println(record.get("Value (dollars)"));
                */
                
                String diffcountry = record.get("Country")+": ";
                String exports = record.get("Exports")+": ";
                String value = record.get("Value (dollars)");
                 d = diffcountry + exports + value;
                 //System.out.println("d = "+ d);
                 // "return" in the for loop means it gets one value and break the loop and return the value immediately.
                 return d;
               
                 
            }
        }
               return "Not FOUND";             
    }
    // this method get the country's names which include exportItem1 and exportItemw
    public void listExportersTwoProducts(CSVParser parser,String exportItem1, String exportItem2)
    {
         
       for(CSVRecord record:parser)
       {
           String item1 = record.get("Exports");
           //String item2 = record.get("")
           if((item1.contains(exportItem1))&&(item1.contains(exportItem2)))
           {
               
               System.out.println(record.get("Country")); 
            }
       } 
    
    }
    //return number of countries export exportItem
    public int numberOfExporters(CSVParser parser,String exportItem)
    {
        int count = 0;
        for(CSVRecord record:parser)
        {
            String item = record.get("Exports");
            if(item.contains(exportItem))
            {
                count++;
            }   
        }
        return count;
    }
    
    public long convertStringtoNumber(String s)
    {
            String news = "";
            //find the index of $ in the string
            int startIndex = s.indexOf("$");
            // find the index of first comma in the string
            int posIndex = s.indexOf(",");
            news = s.substring(startIndex+1,posIndex);
            //count records how many commas in the string.
            int count = 0;
            long newamountvalue=0;
            while(true)
            {
                count++;
                //Index after a comma
                startIndex = posIndex+1;
                //find the index of comma
                posIndex = s.indexOf(",",startIndex);
                if(posIndex == -1)
                {
                break;                        
                }
                
                //string without $, commas and miss last 3 digits.
                news = news + s.substring(startIndex,posIndex);
                //System.out.println("new string is "+ news);                
            }
            //string without $,commas and Include last 3 digits
            news = news + s.substring(startIndex,s.length());
            if(news != "")
            {
                  try
                  {
                          newamountvalue = Long.parseLong(news);
                         
                  }
                  catch(NumberFormatException e)
                  {
                         System.out.println("This is not a number");
                  }
            } 
           return newamountvalue;      
    }
        
    
    public void bigExporters(CSVParser parser,String amount)
    {
        long newamount = convertStringtoNumber(amount);
        //System.out.println("amount= "+ newamount);
        for(CSVRecord record:parser)
        {
            String s = record.get("Value (dollars)");
            long newdata = convertStringtoNumber(s);
            //System.out.println("data= "+ newdata);
            if(newdata > newamount)
            {
                System.out.print(record.get("Country")+" ");   
                System.out.println(record.get("Value (dollars)"));    
            }
        }
    
    }

}
