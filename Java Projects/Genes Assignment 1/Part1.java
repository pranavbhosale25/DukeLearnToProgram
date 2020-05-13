
import edu.duke.*;
import java.io.*;

/**
 * Write a description of Part1 here.
 * 
 * @author Pranav Bhosale 
 * @version 6th May 2020
 */

public class Part1 {
    public String findSimpleGene(String dna){
        String gene = "";
        String startCodon = "ATG";
        String stopCodon = "TAA";
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1){
            return gene;
        }
        
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        if(stopIndex == -1){
            return gene;
        }
        
        if((stopIndex - startIndex)%3 != 0){
            return gene;
        }
        
        gene = dna.substring(startIndex,stopIndex+3);
        return gene;
    }
    
    public void testSimpleGene(){
        //valid but not a multiple of 3
        String dna1 = "AGCTAATGGCGATACTAAGCT";
        //no atg
        String dna2 = "AGCTAGCGATACTAAGCT";
        //no taa
        String dna3 = "AGCTATGGCGATACTAGAGCT";
        //no to both
        String dna4 = "AGCTAGCGATACTAGAGCT";
        //valid
        String dna5 = "AGTCGTAATGGCATCGACGTACTAAGCTGACT";
        
        System.out.println("The dna string is: " + dna1);
        System.out.println("Gene (if found) is: " + findSimpleGene(dna1));
    
        System.out.println("The dna string is: " + dna2);
        System.out.println("Gene (if found) is: " + findSimpleGene(dna2));
        
        System.out.println("The dna string is: " + dna3);
        System.out.println("Gene (if found) is: " +  findSimpleGene(dna3));
        
        System.out.println("The dna string is: " + dna4);
        System.out.println("Gene (if found) is: " +  findSimpleGene(dna4));

        System.out.println("The dna string is: " + dna5);
        System.out.println("Gene (if found) is: " +  findSimpleGene(dna5));
        
    }
}
