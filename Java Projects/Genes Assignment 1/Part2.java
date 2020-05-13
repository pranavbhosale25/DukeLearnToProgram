
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String gene = "";
        //String startCodon = "ATG";
        //String stopCodon = "TAA";
        
        startCodon = startCodon.toUpperCase();
        stopCodon = stopCodon.toUpperCase();
        String dnaCopy = dna;
        dna = dna.toUpperCase();
        
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
        
        gene = dnaCopy.substring(startIndex,stopIndex+3);
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
        dna5 = dna5.toLowerCase();
        String startCodon = "ATG";
        String stopCodon = "TAA";        
        
        System.out.println("START OUTPUT STREAM");
        System.out.println("");
        
        System.out.println("The dna string is: " + dna1);
        System.out.println("Gene (if found) is: " + findSimpleGene(dna1,startCodon,stopCodon));
    
        System.out.println("The dna string is: " + dna2);
        System.out.println("Gene (if found) is: " + findSimpleGene(dna2,startCodon,stopCodon));
        
        System.out.println("The dna string is: " + dna3);
        System.out.println("Gene (if found) is: " +  findSimpleGene(dna3,startCodon,stopCodon));
        
        System.out.println("The dna string is: " + dna4);
        System.out.println("Gene (if found) is: " +  findSimpleGene(dna4,startCodon,stopCodon));

        System.out.println("The dna string is: " + dna5);
        System.out.println("Gene (if found) is: " +  findSimpleGene(dna5,startCodon,stopCodon));
        
    }
}
