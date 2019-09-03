
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (01/02/2019)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;
import org.apache.commons.csv.*;

public class Part1
{
    public void testFindhottestInday()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //CSVRecord largestT = FindhottestInday(parser);
       // System.out.println("hottest temperature is "+ largestT.get("TemperatureF")+"at "+largestT.get("TimeEST"));
       hottestInmanydays();
    }
    
    public CSVRecord findlargestsofar(CSVRecord largestsofar,CSVRecord currentrow)
    {
        
        if(largestsofar == null)
            {
                largestsofar = currentrow;
            }
            else
            {
                double largestTemp = Double.parseDouble(largestsofar.get("TemperatureF"));
                double currTemp = Double.parseDouble(currentrow.get("TemperatureF"));
                if(currTemp > largestTemp)
                {
                    largestsofar = currentrow;
                }
            }
        return largestsofar;
    
    }
    public CSVRecord FindhottestInday(CSVParser parser)
    {
        CSVRecord largestsofar = null;
        for(CSVRecord currow:parser)
        {
            largestsofar = findlargestsofar(largestsofar,currow);
        }
        return largestsofar;
    }
    
    public void hottestInmanydays()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestsofar = null;
        for (File f : dr.selectedFiles()) 
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentrow =FindhottestInday(fr.getCSVParser());
            largestsofar = findlargestsofar(largestsofar,currentrow);
        }
        System.out.println("hottes temperature is "+ largestsofar.get("TemperatureF") +"at "+ largestsofar.get("DateUTC"));
    
    
    }
    
    public void testcoldestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestT = coldestHourInFile(parser);
        System.out.print("coldest temperature is "+ coldestT.get("TemperatureF"));
        System.out.println(" at "+ coldestT.get("DateUTC"));
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldestsofar = null;
        double coldest = 0;
        for(CSVRecord currentrow:parser)
        {
            coldestsofar = findcoldestsofar(coldestsofar,currentrow);
        
        }
        return coldestsofar;
        
    }
    
    public CSVRecord findcoldestsofar(CSVRecord coldestsofar,CSVRecord currentrow)
    {
        if(coldestsofar == null)
             {
                 coldestsofar = currentrow;
             }
             else
            {
                double curTemp = Double.parseDouble(currentrow.get("TemperatureF"));
               // System.out.println("current Temp is "+curTemp +" at " + currow.get("TimeEST"));
                double coldestTemp = Double.parseDouble(coldestsofar.get("TemperatureF"));
                
                if(coldestTemp > curTemp && curTemp != -9999)
                {
                   // System.out.println("******current Temp in if ********is "+curTemp +" at " + currow.get("TimeEST"));
                    // *****here is why I can't find coldest*****coldestTemp is local variable, it don't keep the value after if statement.
                    //coldestTemp = curTemp;
                    // coldestsofar is global variable, it will keep the changing value after if statemente.
                    coldestsofar = currentrow;
                   // System.out.println("******coldest Temp is changed to currentemp ******** "+coldestTemp);
                }
                
            }
            return coldestsofar;
        
    
    }
    // this method is to find a file having coldedst temperature in many files and read through that file get the coldest temperation
    public String fileWithColdestTemperature()
    {
       
         DirectoryResource dr = new DirectoryResource();
         // global variable!! if not wont get correct coldestsofar!!!
         CSVRecord coldestsofar = null;
         // global variable!!
         File temp = null;
         for (File f : dr.selectedFiles())
         {
             FileResource fr = new FileResource(f);
             CSVParser parser = fr.getCSVParser();
             
             //CSVRecord currentrow = coldestHourInFile(parser);
             for(CSVRecord currentrow:parser)
             {
                 if(coldestsofar == null)
                 {
                     coldestsofar = currentrow;
                 }
                 else
                 {
                     double curTemp = Double.parseDouble(currentrow.get("TemperatureF"));
                     // System.out.println("current Temp is "+curTemp +" at " + currow.get("TimeEST"));
                     double coldestTemp = Double.parseDouble(coldestsofar.get("TemperatureF"));
                
                     if(coldestTemp > curTemp && curTemp != -9999)
                     {
                   
                        coldestsofar = currentrow;
                        temp = f;
                       
                }
                
            }
            
                 
             }
             //System.out.println("******coldest Temp is ******** "+coldestsofar.get("TemperatureF"));
             //System.out.println("Coldest day was in file " + temp.getName());
             //coldestsofar = findcoldestsofar(coldestsofar,currentrow);
             //temp = f;   
            
         }
        // System.out.println("******coldest Temp Finally is ******** "+coldestsofar.get("TemperatureF"));
          System.out.println("Coldest day was in file " + temp.getName());
          
         //these code using coldesthourinfile to read the file from the file name you get above and get the lowest temp
          FileResource findfile1 = new FileResource(temp);
          CSVParser newparser1 = findfile1.getCSVParser();
          CSVRecord coldestinthatday = coldestHourInFile(newparser1);
         System.out.println("Coldest temperature on that day was "+coldestinthatday.get("TemperatureF"));
          
          //csvparser needs to be redefined everytime using it.
          FileResource findfile = new FileResource(temp);
          CSVParser newparser = findfile.getCSVParser();
          System.out.println("All the temperature on the coldest day were ");
          for(CSVRecord currow : newparser)
          {
            System.out.print(currow.get("DateUTC")+": ");
            System.out.println(currow.get("TemperatureF"));
          }
         return temp.getName();
    }
    
    public void testfileWithColdestTemperature()
    {
        String filename = fileWithColdestTemperature();
        
 
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord lowesthumiditysofar = null;
        for(CSVRecord currentrow:parser)
        {
           lowesthumiditysofar = findlowesthumidity(lowesthumiditysofar,currentrow);
        }
        return lowesthumiditysofar;
    }
    
    public CSVRecord findlowesthumidity(CSVRecord lowesthumiditysofar,CSVRecord currentrow)
    {
      if(lowesthumiditysofar == null)
            {
                lowesthumiditysofar = currentrow;
            }
            else
            {
                if(currentrow.get("Humidity").contains("N/A"))
                {
                    System.out.println("Find N/A");
                }
                else
                {
                    double curhumidity =  Double.parseDouble(currentrow.get("Humidity"));
                    double lowesthumidity =  Double.parseDouble(lowesthumiditysofar.get("Humidity"));
                    if(curhumidity < lowesthumidity)
                    {
                        lowesthumiditysofar = currentrow;
                    }
                }
            } 
            return lowesthumiditysofar;
    }
 
    public void testlowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord humidity = lowestHumidityInFile(parser);
        System.out.print("Lowest Humidity was " + humidity.get("Humidity"));
        System.out.println(" at "+ humidity.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles()
    {
    
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowesthumiditysofar = null;
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            
            CSVRecord currentrow = lowestHumidityInFile(parser);
            //if(lowesthumiso)
            lowesthumiditysofar = findlowesthumidity(lowesthumiditysofar,currentrow);
            
        }
        return lowesthumiditysofar;
    }
    
    public void testLowestHumidityInManyFiles()
    {
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.print("Lowest Humidity was " + lowestHumidity.get("Humidity"));
        System.out.println(" at " + lowestHumidity.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser)
    {
        int count = 0;
        double sumTemp = 0.0;
        double averageTemp = 0.0;
        for(CSVRecord currentrow:parser)
        {
            double curTemp = Double.parseDouble(currentrow.get("TemperatureF"));
            if(curTemp != -9999)
            {
                sumTemp = sumTemp + curTemp;
                count++;
            }  
            averageTemp = sumTemp/count;
        }
        return averageTemp;
    }
    
    public void TestaverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.print("Average temperature in file is "+average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double averageTemp = 0.0;
        double sumTemp = 0.0;
        int count = 0;
        for(CSVRecord currentrow:parser)
        {
             if(currentrow.get("Humidity").contains("N/A"))
                {
                    System.out.println("Find N/A");
                }
                else
                {
                    int curhumidity =  Integer.parseInt(currentrow.get("Humidity"));
                    
                    if(curhumidity >= value)
                    {
                       double curTemp = Double.parseDouble(currentrow.get("TemperatureF"));
                        if(curTemp != -9999)
                        {
                            sumTemp = sumTemp + curTemp;
                            count++;
                        }  
                        averageTemp = sumTemp/count;
                       
                    }
                   
                }
        }
        return averageTemp;
    }
    
    public void testaverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser,80);
        if(averageTemp == 0.0)
        {
           System.out.println("No temperature with that humidity"); 
        }
        else
        {
            System.out.println("Average Temp when high Humidity is " + averageTemp);
        }
    }
}


