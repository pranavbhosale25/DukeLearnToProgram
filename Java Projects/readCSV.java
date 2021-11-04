import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class readCSV {
    public String countryInfo(CSVParser parser, String country){
        String ans = "";
        for(CSVRecord record : parser){
            String curr = record.get("Country");
            if(curr.equals(country)){
                //do this
                ans += record.get("Country");
                ans += " : ";
                ans += record.get("Exports");
                ans += " : ";
                ans += record.get("Value (dollars)");
                return ans;
            }
        }
        return "NOT FOUND";
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String res = countryInfo(parser,"Nauru");
        System.out.println(res);
    }
    
    public void listExportersTwoProducts(CSVParser parser, String item1, String item2){
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(item1) && export.contains(item2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public void testListExportersTwoProducts(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        listExportersTwoProducts(parser,"cotton", "flowers");
    }
    
    public int numberOfExporters(CSVParser parser, String item1){
        int count = 0;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(item1)){
                count++;
            }
        }
        return count;
    }
    
    public void testNumberOfExporters(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        int res = numberOfExporters(parser,"cocoa");
        System.out.println(res);
    }
    
    public void bigExporters(CSVParser parser, String amount){
        int len = amount.length();
        for(CSVRecord record : parser){
            int currLen = (record.get("Value (dollars)")).length();
            if(currLen > len){
                System.out.print(record.get("Country"));
                System.out.print(" : ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }
    
    public void testBigExporters(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        bigExporters(parser,"$999,999,999,999");
    }
}
