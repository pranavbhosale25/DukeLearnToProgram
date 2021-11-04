
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
         WebLogParser wlp = new WebLogParser();
         for(String line : fr.lines()){
             records.add(wlp.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!uniqueIPs.contains(ip)){
                uniqueIPs.add(ip);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status > num){
                System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> ipList = new ArrayList<String>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString();
             String md = date.substring(4,10);
             //System.out.println(md);
             if(md.equals(someday)){
                 String ip = le.getIpAddress();
                 if(!ipList.contains(ip)){
                     ipList.add(ip);
                 }
             }
         }
         return ipList;
     }
     
     public int countUniqueIPsInRange(int low,int high){
         int count = 0;
         ArrayList<String> ipList = new ArrayList<String>();
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status >= low && status <= high){
                String ip = le.getIpAddress();
                if(!ipList.contains(ip)){
                    count++;
                    ipList.add(ip);
                }
             }
         }
         return count;
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> ipList = new HashMap<String, Integer>();
         for(LogEntry le : records){
            String ip = le.getIpAddress();
            if(!ipList.containsKey(ip)){
                ipList.put(ip,1);
            }
            else{
                ipList.put(ip,ipList.get(ip)+1);
            }
         }
         return ipList;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> ipList){
         int count = 0;
         for(String ip : ipList.keySet()){
             count = Math.max(ipList.get(ip),count);
         }
         return count;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipList){
         int count = mostNumberVisitsByIP(ipList);
         ArrayList<String> sol = new ArrayList<String>();
         for(String ip : ipList.keySet()){
             if(ipList.get(ip) == count){
                sol.add(ip);
             }
         }
         return sol;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
         //iterate over records
         //fetch date
         //if present add ip to its array list
         //if absent make new entry
         HashMap<String, ArrayList<String>> dateToIP = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString();
             String md = date.substring(4,10);
             //System.out.println(md);
             String ip = le.getIpAddress();
             if(!dateToIP.containsKey(md)){
                 ArrayList<String> al = new ArrayList<String>();
                 al.add(ip);
                 dateToIP.put(md,al);
             }
             else{
                 ArrayList<String> al = dateToIP.get(md);
                 al.add(ip);
                 dateToIP.put(md,al);
             }
         }
         return dateToIP;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> hm){
         String bestDay="";
         int count = 0;
         for(String day : hm.keySet()){
             if(hm.get(day).size() > count){
                 count = hm.get(day).size();
                 bestDay = day;
             }
         }
         return bestDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(
     HashMap<String, ArrayList<String>> hm, String date){
         ArrayList<String> thatDay = hm.get(date);
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for (int index=0; index < thatDay.size(); index++) {
             String ipAddress = thatDay.get(index);
             if (!counts.containsKey(ipAddress)) {
                 counts.put(ipAddress,1);
             }
             else {
                 int currentIpCount = counts.get(ipAddress);
                 counts.put(ipAddress,currentIpCount+1);
             }
         }
         return iPsMostVisits(counts);
     }
}
