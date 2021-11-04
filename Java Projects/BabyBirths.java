import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Final Project for Course-2 of the Java Specialisation by Duke University
 * 
 * @author Pranav Bhosale
 * @version 12th May 2020
 */
public class BabyBirths {
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int boyNames = 0;
        int girlNames = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            totalBirths += Integer.parseInt(rec.get(2));
            String gender = rec.get(1);
            if(gender.equals("M")){
                boyNames++;
            }
            else{
                girlNames++;
            }
        }
        System.out.println("The total number of births is: " + totalBirths);
        System.out.println("The total number boy names is: " + boyNames);
        System.out.println("The total number girl names is: " + girlNames);
    }
    
    public void testTotalBirths(){
        ///Users/Apple/Java Projects/baby-names/us_babynames/us_babynames_by_year
        FileResource fr = new FileResource("/Users/Apple/Java Projects/baby-names/us_babynames/us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        //get the file
        int rank = -1;
        int tempRank = 0;
        FileResource fr = new FileResource("/Users/Apple/Java Projects/baby-names/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        //traverse it
        for(CSVRecord rec : fr.getCSVParser(false)){
            String currGender = rec.get(1);
            String currName = rec.get(0);
            if(currGender.equals(gender)){
                tempRank++;
                if(currName.equals(name)){
                    rank = tempRank;
                }
            }
        }
        return rank;
    }
    
    public void testGetRank(){
        int rank = getRank(1971,"Frank","M");
        System.out.println("Rank is: " + rank);
    }
    
    public String getName(int year, int rank, String gender){
        String name = "NO NAME";
        FileResource fr = new FileResource("/Users/Apple/Java Projects/baby-names/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        int currRank = 0;

        for(CSVRecord rec : fr.getCSVParser(false)){
            String currGender = rec.get(1);
            String currName = rec.get(0);
            if(currGender.equals(gender)){
                currRank++;
                if(currRank == rank){
                    name = currName;
                }
            }
        }
        return name;
    }
    
    public void testGetName(){
        String name = getName(1982,450,"M");
        System.out.println("Name is: " + name);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year,name,gender);
        String ansName = getName(newYear,rank,gender);
        
        System.out.println(name + " born in " + year + " would be "
                           + ansName + " born in " + newYear);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen",1974,2014,"M");
    }
    
    public int yearOfHighestRank(String name, String gender){
        int maxRankYear = -1;
        int maxRank = -1;
        for(int year = 1880 ; year <= 2014 ; year++){
            int currRank = getRank(year,name,gender);
            if((currRank < maxRank && currRank!=-1) || (maxRank == -1 && currRank!=-1)){
                maxRank = currRank;
                maxRankYear = year;
            }
        }
        return maxRankYear;
    }
    
    public void testYearOfHighestRank(){
        String testName = "Mich";
        String testGender = "M";
        int maxRankYear = yearOfHighestRank(testName,testGender);
        System.out.println("For " + testName +" the year of highest rank is: " + maxRankYear);
    }
    
    public double getAverageRank(String name,String gender){
        int totalRank = 0;
        int yearCount = 0;
        for(int year = 1880 ; year <= 2014 ; year++){
            int currRank = getRank(year,name,gender);
            yearCount++;
            totalRank += currRank;
        }
        double avgRank = ((double)(totalRank))/((double)(yearCount));
        return avgRank;
    }
    
    public void testGetAverageRank(){
        String testName = "Robert";
        String testGender = "M";
        double avgRank = getAverageRank(testName,testGender);
        System.out.println("For " + testName +" the average rank is: " + avgRank);
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        FileResource fr = new FileResource("/Users/Apple/Java Projects/baby-names/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        int babyCount = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            String currName = rec.get(0);
            String currGender = rec.get(1);
            int currBaby = Integer.parseInt(rec.get(2));
            if(currGender.equals(gender) && !(currName.equals(name))){
                babyCount += currBaby;
            }
            if(currGender.equals(gender) && (currName.equals(name))){
                break;
            }
        }
        return babyCount;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        String testName = "Drew";
        int testYear = 1990;
        String testGender = "M";
        int babyCount = getTotalBirthsRankedHigher(testYear,testName,testGender);
        System.out.println(babyCount + " number of babies were ranked above " + testName
                           + " in the year " + testYear);
    }
}
