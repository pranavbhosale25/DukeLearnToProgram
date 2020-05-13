
/**
 * Write a description of Part3 here.
 * 
 * @author Pranav Bhosale
 * @version 6th May 2020
 */
public class Part3 {
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String gene = findGene(dna,startIndex);
            //if gene not found
            if(gene.isEmpty()){
                break;
            }
            //else continue finding more genes 
            //print the gene
            System.out.println(gene);
            //move to next startIndex
            startIndex = dna.indexOf("ATG",startIndex) + gene.length();
        }
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        
        int minIndex = Math.min(taaIndex,tagIndex);
        minIndex = Math.min(minIndex,tgaIndex);
        
        if(minIndex == dna.length()){
            return "";
        }
        
        return dna.substring(startIndex,minIndex+3);
    }
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon,startIndex);
        //if stopIndex isn't appropriate
        if(stopIndex == -1){
            return dna.length();
        }
        if((startIndex - stopIndex)%3 != 0){
            return dna.length();
        }
        return stopIndex;
    }
    
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while(true){
            String gene = findGene(dna,startIndex);
            //if gene not found
            if(gene.isEmpty()){
                break;
            }
            //else continue finding more genes 
            count++;
            //move to next startIndex
            startIndex = dna.indexOf("ATG",startIndex) + gene.length();
        }
        return count;
    }
    
    public void testCountGenes(){
        System.out.println("Number of genes found: " + countGenes("ATGTAAGATGCCCTAGT"));
    }
}
