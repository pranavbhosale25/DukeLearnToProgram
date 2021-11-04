import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of coldDays here.
 * 
 * @author Pranav Bhosale
 * @version 7th May 2020
 */
public class coldDays{
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord currRow : parser){
            if(coldestSoFar == null){
                coldestSoFar = currRow;
            }
            else{
                double temp = Double.parseDouble(currRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(temp < smallestTemp && temp != -9999){
                    coldestSoFar = currRow;
                }  
            }
        }
        return coldestSoFar;
    }

    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord mini = null;
        File miniFile = null;
        for(File f: dr.selectedFiles()){
            FileResource currFr = new FileResource(f);
            CSVRecord currTemp = coldestHourInFile(currFr.getCSVParser());
            if(mini == null && miniFile == null){
                mini = currTemp;
                miniFile = f;
            }
            else{
                double temp = Double.parseDouble(currTemp.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(mini.get("TemperatureF"));
                if(temp < smallestTemp){
                    miniFile = f;
                    mini = currTemp;
                }
            }
        }
  
        return miniFile.getAbsolutePath();
    }
    
    public void testFileWithColdestTemperature(){
        String ans = fileWithColdestTemperature();
        //print file name
        System.out.println("The file with coldest day is: " + ans);
        
        FileResource fr = new FileResource(ans);
        CSVParser parser = fr.getCSVParser();
        
        //print lowest temp
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest Temperature is: " + coldest.get("TemperatureF"));
        
        //print all temps for that day
        CSVParser parser2 = fr.getCSVParser();
        for(CSVRecord record : parser2){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            System.out.print(record.get("DateUTC"));
            System.out.print("  ::  ");
            System.out.println(temp);
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowest = null; 
        for(CSVRecord currRow : parser){
            if(lowest == null){
                lowest = currRow;
            }
            else{
                if(!(currRow.get("Humidity")).equals("N/A") && !(lowest.get("Humidity")).equals("N/A")){
                    double hum = Double.parseDouble(currRow.get("Humidity"));
                    double smallestHum = Double.parseDouble(lowest.get("Humidity"));
                    if(hum < smallestHum){
                        lowest = currRow;
                    }
                }  
            }
        }
        return lowest;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity is " + (csv.get("Humidity")) + " on " + (csv.get("DateUTC")));
    }
    
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestRec = null;
        //comment in if lowest file name is also needed :)
        //File lowestFile = null;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRec = lowestHumidityInFile(parser);
            if(lowestRec == null){
                lowestRec = currRec;
                //lowestFile = f;
            }
            else{
                if(!(currRec.get("Humidity")).equals("N/A") && !(lowestRec.get("Humidity")).equals("N/A")){
                    double hum = Double.parseDouble(currRec.get("Humidity"));
                    double smallestHum = Double.parseDouble(lowestRec.get("Humidity"));
                    if(hum < smallestHum){
                        lowestRec = currRec;
                        //lowestFile = f;
                    }
                }
            }
        }
        return lowestRec;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord ans = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity is " + (ans.get("Humidity")) + " on " + (ans.get("DateUTC")));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        int rowCount = 0;
        double totalTemp = 0;
        double currTemp = 0;
        for(CSVRecord record : parser){
            currTemp = Double.parseDouble(record.get("TemperatureF"));
            totalTemp += currTemp;
            rowCount++;
        }
        double avg = totalTemp/rowCount;
        return avg;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature is " + average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        int rowCount = 0;
        double totalTemp = 0;
        double currTemp = 0;
        double currHum = 0;
        for(CSVRecord record : parser){
            currTemp = Double.parseDouble(record.get("TemperatureF"));
            currHum = Double.parseDouble(record.get("Humidity"));
            if(currHum > value){
                totalTemp += currTemp;
                rowCount++;
            }
        }
        if((int)(totalTemp) == 0) return 0;
        double avg = totalTemp/rowCount;
        return avg;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if((int)(average) == 0){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average temperature is " + average);
        }
    }
}
