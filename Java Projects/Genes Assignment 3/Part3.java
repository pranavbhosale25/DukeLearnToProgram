import edu.duke.*;
import java.io.*;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public void processGenes(StorageResource sr){
        int count = 0;
        int count1 = 0;
        int total = 0;
        int longestLen = 0;
        for(String gene : sr.data()){
            if(gene.length() > 60){
                //System.out.println(">9: " + gene);
                count++;
            }
            double cgr = cgRatio(gene);
            if(cgr > 0.35){
                //System.out.println("CGR: " + gene);
                count1++;
            }
            total++;
            longestLen = Math.max(gene.length(),longestLen);
        }
        
        System.out.println("Genes that are longer than 60: " + count);
        System.out.println("Genes with cgRatio >0.35: " + count1);
        System.out.println("Longest gene is: " + longestLen);
        System.out.println("Total genes found: " + total);
    }
    
    
    public double cgRatio(String dna){
        double len = dna.length();
        int count = 0;
        for(int i = 0 ; i < dna.length() ; i++){
            if(dna.charAt(i) == 'c' || dna.charAt(i) == 'g'){
                count++;
            }
        }
        double cgRatio = count/len;
        return cgRatio;
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
            startIndex = dna.indexOf(gene,startIndex) + gene.length();
        }
        return genes;
    }
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon,startIndex+3);
        while(currIndex != -1){
            int diff = startIndex - currIndex;
            if(diff%3 == 0){
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon,currIndex+1);
        }
        return -1;
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("atg",where);
        if(startIndex == -1){
            return "";
        }

        int taaIndex = findStopCodon(dna,startIndex,"taa");

        int tagIndex = findStopCodon(dna,startIndex,"tag");

        int tgaIndex = findStopCodon(dna,startIndex,"tga");

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
        String sol = dna.substring(startIndex,minIndex+3);
        sol = sol.toLowerCase();
        return sol;
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        StorageResource sr = new StorageResource();
        sr = getAllGenes(dna);
        //sr = getAllGenes("ATGAAACCCTGA");
        System.out.println("Results:");
        //System.out.println(dna);
        processGenes(sr);
    }
}
