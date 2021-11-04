import java.util.*;
import edu.duke.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author Pranav Bhosale
 * @version 16th May 2020
 */

public class CodonCount {
    private HashMap<String,Integer> codonMap;

    public CodonCount(){
        codonMap = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start,String dna){
        codonMap.clear();
        for(int i = start ; i < dna.length()-2 ; i+=3){
            String codon = dna.substring(i,i+3);
            boolean present = codonMap.containsKey(codon);
            //System.out.println(codon);
            if(codonMap.containsKey(codon)){
                int val = codonMap.get(codon);
                codonMap.put(codon,val+1);
            }
            else{
                codonMap.put(codon,1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        String cc = "";
        int count = 0;
        for(String codon : codonMap.keySet()){
            int currCount = codonMap.get(codon);
            if(currCount > count){
                count = currCount;
                cc = codon;
            }
        }
        return cc;
    }
    
    public void printCodonCounts(int start,int end){
        for(String codon : codonMap.keySet()){
            int currCount = codonMap.get(codon);
            if(currCount >= start && currCount < end){
                System.out.println(codon + " : " + currCount);
            }
        }
    }
    
    public void tester(){
        FileResource fileResource = new FileResource();
        String dna = fileResource.asString();
        dna = dna.toUpperCase();
        
        for (int index = 0; index <= 2; index++) {
            System.out.println("Testing with start position " + index + ":");
            buildCodonMap(index, dna);
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("Total unique codons found: " + codonMap.size());
            System.out.println("Most common codon: " + mostCommonCodon
                    + "\t" + codonMap.get(mostCommonCodon));
            printCodonCounts(4, 8);
        }
    }
}