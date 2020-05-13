import edu.duke.*;
import java.io.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon,startIndex+3);
        while(currIndex != -1){
            int diff = startIndex - currIndex;
            if(diff%3 == 0){
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon,startIndex)+3;
        }
        return -1;
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        
        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }
        
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        
        if(minIndex == -1){
            return "";
        }
        
        return dna.substring(startIndex,minIndex+3);
    }
    
    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource genes = new StorageResource();
        while(true){
            String gene = findGene(dna,startIndex);
            //if gene not found
            if(gene.isEmpty()){
                break;
            }
            //else continue finding more genes
            genes.add(gene);
            //move to next startIndex
            startIndex = dna.indexOf("ATG",startIndex) + gene.length();
        }
        return genes;
    }
    public void testGetAllGenes(){
        //test the code by calling getAllGenes
        StorageResource ans = getAllGenes("xaxATGxxxyyyzzzTAAhsATGxxxyyyTAGbasATGxxxyyyTGA");
        for(String gene: ans.data()){
            System.out.println(gene);
        }
    }
}
