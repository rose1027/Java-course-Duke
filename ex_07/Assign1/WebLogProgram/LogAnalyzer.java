
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
          FileResource fr = new FileResource(filename);
          WebLogParser parser = new WebLogParser();
            for(String line : fr.lines())
            {
                
                LogEntry record = parser.parseEntry(line);
                int index = records.indexOf(record);
                if(index == -1)
                {
                    records.add(record);
                }
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
      public int countUniqueIPs()
      {
          ArrayList<String> uniqueIp = new ArrayList<String>();
          for(LogEntry record:records)
          {
              String currIp = record.getIpAddress();
              int index = uniqueIp.indexOf(currIp);
              if(index == -1)
              {
                  uniqueIp.add(currIp);
              }
          }
          return uniqueIp.size();
      }
     
     public void printAllHigherThanNum(int num)
     {
         int statuscode = 0;
         for(LogEntry record:records)
         {
             statuscode = record.getStatusCode();
             if(statuscode > num)
             {
                 System.out.println(record);
             }
         }
     }
     
     public HashMap uniqueIplist()
     {
         HashMap<String,Integer> uniqueIp = new HashMap<String,Integer>();
         for(LogEntry record:records)
         {
            String currIp = record.getIpAddress();
            if(!uniqueIp.containsKey(currIp))
            {
               uniqueIp.put(currIp,1); 
            } 
            else
            {
                uniqueIp.put(currIp,uniqueIp.get(currIp)+1);
            }
         }
         return uniqueIp;
    }
    
    public int mostNumberVisitsByIP(HashMap<String,Integer> uniqueIp)
    {
        int max = 0;
        for(String key:uniqueIp.keySet())
        {
            int curNum = uniqueIp.get(key);
            if(max<curNum)
            {
                max = curNum;
            }
        }
        return max;
    }
    
    public ArrayList iPsMostVisits(HashMap<String,Integer> uniqueIp)
    {
        ArrayList<String> MostVisitsIp = new ArrayList<String>();
        int max = mostNumberVisitsByIP(uniqueIp); 
        for(String key:uniqueIp.keySet())
        {
            int numIp = uniqueIp.get(key);
            if(numIp == max)
            {
                MostVisitsIp.add(key);
            }
        }
        return MostVisitsIp;
    }
    /*This method returns a HashMap<String, ArrayList<String>> that uses records and maps
days from web logs to an ArrayList of IP addresses that occurred on that day*/
    public HashMap<String, ArrayList<String>> iPsForDays()
    {
        HashMap<String,ArrayList<String>> ipforDays = new HashMap<>();
        
         for(LogEntry record:records)
        {
            String currIp = record.getIpAddress();
            String curTime = record.getAccessTime().toString();
            String curDate = curTime.substring(4,10);
            //System.out.println("currentdate = "+curDate);
            if(!ipforDays.containsKey(curDate))
            {//if the map is new,everytime add new key in the map need to create new list match that key!!!
              ArrayList<String> uniqIp = new ArrayList<>();
              uniqIp.add(currIp);
             // ipforDays.put(curDate,uniqueIPVisitsOnDay(curDate));
             //System.out.println(uniqIp);
             ipforDays.put(curDate,uniqIp);
            }
            else
            {
               ArrayList<String> curIplist = ipforDays.get(curDate);
               curIplist.add(currIp);
               ipforDays.put(curDate,curIplist);
               
            }
        }
        return ipforDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> ipForDays)
    {
       int maxIpnum = 0;
       String date = "";
       for(String key:ipForDays.keySet())
       {
           if(maxIpnum < ipForDays.get(key).size())
           {
               maxIpnum = ipForDays.get(key).size();
               date = key;
           }
        } 
        return date;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> ipForDays,String date)
    {
        ArrayList<String> Ips = new ArrayList<>();
        HashMap<String,Integer> IpwithCount = new HashMap<>();
        for(String keydate:ipForDays.keySet())
        {
            if(keydate.equals(date))
            {
                Ips = ipForDays.get(keydate);
            }
        }
        for(int i=0;i<Ips.size();i++)
        {
            String ip = Ips.get(i);
            if(!IpwithCount.containsKey(ip))
            {
                IpwithCount.put(ip,1);
            }
            else
            {
                IpwithCount.put(ip,IpwithCount.get(ip)+1);
            }
        }
        ArrayList<String> IpwithVisit = iPsMostVisits(IpwithCount);
        return IpwithVisit;
    }
    
     public ArrayList<String> uniqueIPVisitsOnDay(String someday)
     {
        ArrayList<String> uniqueIp = new ArrayList<String>();
        for(LogEntry record:records)
        {
            String currIp = record.getIpAddress();
            String curDate = record.getAccessTime().toString();
            //System.out.println("currDate= "+curDate);
            if(curDate.contains(someday))
            {
                //System.out.println("find the same day****");
                if(!uniqueIp.contains(currIp))
                {
                    uniqueIp.add(currIp);
                }
            }
        } 
        return uniqueIp;
     }
     
     public int countUniqueIPsInRange(int num1,int num2)
     {
       ArrayList<String> uniqueIp = new ArrayList<String>();
       for(LogEntry record:records)
       {  
          int statuscode = record.getStatusCode();
          String currIp = record.getIpAddress();
          if(statuscode >= num1 && statuscode <= num2)
          {
            if(!uniqueIp.contains(currIp))
                {
                    uniqueIp.add(currIp);
                }  
          }
       }
       return uniqueIp.size();
    }
    
    
}
