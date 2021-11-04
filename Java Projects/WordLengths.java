import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author Pranav Bhosale
 * @version 13th May 2020
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            int len = lenCounter(word);
            if(len < counts.length){
                counts[len]++;
            }
            else{
                counts[counts.length-1]++;
            }
        }
    }
    
    public int lenCounter(String word){
        int totalLen = word.length();
        int count = 0;
        for(int i = 0 ; i < totalLen ; i++){
            char curr = word.charAt(i);
            if(!Character.isLetter(curr) && (i==0 || i==totalLen-1)){
                //count++;
                continue;
            }
            count++;
        }
        return count;
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr,counts);
        for(int i = 0 ; i < counts.length ; i++){
            System.out.println(counts[i] + " words of length " + i);
        }
        int idx = indexOfMax(counts);
        System.out.println("Most common word length is: " + idx);
    }
    
    public int indexOfMax(int[] values){
        int idx = 0;
        int maxCount = 0;
        for(int i = 0 ; i < values.length ; i++){
            if(values[i] > maxCount){
                maxCount = values[i];
                idx = i;
            }
        }
        return idx;
    }
}
