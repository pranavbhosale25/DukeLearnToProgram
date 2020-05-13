import edu.duke.*;
import java.io.*;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public double cgRatio(String dna){
        double len = dna.length();
        int count = 0;
        for(int i = 0 ; i < dna.length() ; i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                count++;
            }
        }
        double cgRatio = count/len;
        return cgRatio;
    
    }
    
    public void testcgRatio(){
        double ans = cgRatio("ATGCCATAG");
        System.out.println(ans);
    }
    
    public int countCTG(String dna){
        int startIndex = 0;
        int count = 0;
        while(true){
            startIndex = dna.indexOf("CTG",startIndex);
            if(startIndex == -1){
                break;
            }
            count++;
            startIndex += 3;
        }
        return count;
    }
    
    public void testCountCTG(){
        int res = countCTG("xxxaCTGCTGshdsCTGskaCTG");
        System.out.println("CTG count in the given dna is: " + res);
    }
}   
