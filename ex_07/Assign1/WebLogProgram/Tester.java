
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void tesUniqueIp()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
        int uniqueIp = la.countUniqueIPs();
        System.out.println("There are "+uniqueIp +" unique IP.");
    }
    
    public void testprintAllHigherThanNum()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAll();
        System.out.println("\n");
        la.printAllHigherThanNum(400);
    }
    public void testuniqueIPVisitsOnDay()
    {
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog2_log");
      la.printAll();
      ArrayList<String> uniqueIpOnDay = new ArrayList<String>();
      uniqueIpOnDay = la.uniqueIPVisitsOnDay("Sep 27");
      System.out.println("uniqueIp on that day are"+uniqueIpOnDay);
      System.out.println("uniqueIp size is "+uniqueIpOnDay.size());
    }
    
    public void testcountUniqueIPsInRange()
    {
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog2_log");
      la.printAll();
      int count1 = la.countUniqueIPsInRange(400,499);
      System.out.println("count1 = "+count1);
      int count2 = la.countUniqueIPsInRange(300,399);
      System.out.println("count2 = "+count2);
    }
    
    public void testhashmap()
    {
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog2_log");
      //la.printAll();
      HashMap<String,Integer> IpList = la.uniqueIplist();
      //System.out.println("hashmap is "+IpList);
      int max = la.mostNumberVisitsByIP(IpList);
      //System.out.println("max number by Ip is "+max);
      ArrayList<String> IpMost = la.iPsMostVisits(IpList);
      IpMost = la.iPsMostVisits(IpList);
      //System.out.println("Ip of most visits are"+IpMost);
      HashMap<String,ArrayList<String>> ipForDays = la.iPsForDays();
      /*for(String key:ipForDays.keySet()){
      System.out.println(key+ipForDays.get(key));
    }*/
   /* Set<Map.Entry<String,ArrayList<String>>> hashSet=ipForDays.entrySet();
         for(Map.Entry<String,ArrayList<String>> entry:hashSet ) {

            System.out.println("Key="+entry.getKey()+", Value="+entry.getValue());
        }*/
        String date = la.dayWithMostIPVisits(ipForDays);
        //System.out.println("daywithMostIp is "+date);
        ArrayList<String> ipwithdays = la.iPsWithMostVisitsOnDay(ipForDays,"Sep 30");
        System.out.println("ips With most visit on day is "+ipwithdays);
      
    }
    
    public void test(){}
}
