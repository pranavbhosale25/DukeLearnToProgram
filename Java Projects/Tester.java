
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
    
    public void testCountUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int IPCount = la.countUniqueIPs();
        System.out.println(IPCount);
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> ipList = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println(ipList.size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int count = la.countUniqueIPsInRange(200,299);
        System.out.println(count);
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, Integer> ipList = la.countVisitsPerIP();
        System.out.println(ipList);
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> ipList = la.countVisitsPerIP();        
        int count = la.mostNumberVisitsByIP(ipList);
        System.out.println(count);
    }
    
    public void testiPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> sol = la.iPsForDays();
        System.out.println(sol);
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> sol = la.iPsForDays();
        String bestDay = la.dayWithMostIPVisits(sol);
        System.out.println(bestDay);
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> hm = la.iPsForDays();
        ArrayList<String> ans = la.iPsWithMostVisitsOnDay(hm,"Sep 30");
        System.out.println(ans);
    }
    
    public void testiPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> ipList = la.countVisitsPerIP();        
        ArrayList<String> al = la.iPsMostVisits(ipList);
        System.out.println(al);
    }
}
