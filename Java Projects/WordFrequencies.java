import java.util.*;
import edu.duke.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author Pranav Bhosale
 * @version 15th May 2020
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        for(int i = 0 ; i < myWords.size() ; i++){
            System.out.println(myWords.get(i) +"\t"+ myFreqs.get(i));
        }
        int index = findIndexOfMax();
        System.out.println("Most frequent word: " + myWords.get(index));
        System.out.println("Frequency: " +myFreqs.get(index));
        System.out.println("Number of unique words: " + myWords.size());
    }
    
    public int findIndexOfMax(){
        int max = 0;
        int maxFreq = 0;
        for(int i = 0 ; i < myFreqs.size() ; i++){
            if(myFreqs.get(i) > maxFreq){
                maxFreq = myFreqs.get(i);
                max = i;
            }
        }
        return max;
    }
}
