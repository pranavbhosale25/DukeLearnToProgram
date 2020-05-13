
/**
 * Write a description of Part1 here.
 * 
 * @author Pranav Bhosale
 * @version 6th May 2020
 */
public class Part1 {
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
    
    public void testFindStopCodon(){
        int res;
        res = findStopCodon("atgxxxyyyzzztaa",0,"taa");
        System.out.println(res);
        res = findStopCodon("atgxxxyyyzztga",0,"tga");
        System.out.println(res);
        res = findStopCodon("atgxxxyyyzzzaaa",0,"taa");
        System.out.println(res);
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
    
    public void testFindGene(){
        String res = findGene("XXXYYYZZZXXXX",0);
        System.out.println(res);
        res = findGene("AATATGXXXYYYZZZTAA",0);
        System.out.println(res);
        res = findGene("AATATGXXXYYYOOOTAAOOOTAGXXXTGA",0);
        System.out.println(res);
        res = findGene("AATATGXXXYYYZZZAATA",0);
        System.out.println(res);
        res = findGene("AATATGXXXYYYZZZTAA",0);
        System.out.println(res);
    }
    
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
    
    public void test(){
        //test the code by calling printAllGenes
    }
}
