import java.util.*;
import edu.duke.*;
import java.io.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author Pranav Bhosale
 * @version 17th May 2020
 */
public class WordsInFiles {
    private HashMap<String, ArrayList> wordsToFile;
    
    public WordsInFiles(){
        wordsToFile = new HashMap<String, ArrayList>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            if(!wordsToFile.containsKey(word)){
                ArrayList<String> al = new ArrayList<String>();
                al.add(f.getName());
                wordsToFile.put(word,al);
            }
            else{
                ArrayList<String> temp = wordsToFile.get(word);
                int index = temp.indexOf(f.getName());
                if(index == -1){
                    temp.add(f.getName());
                    wordsToFile.put(word,temp);
                }
            }
        }
    }
    
    public void buildWordFileMap(){
        wordsToFile.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int maxNum=0;
        for(String word : wordsToFile.keySet()){
            ArrayList currList = wordsToFile.get(word);
            if(currList.size() > maxNum){
                maxNum = currList.size();
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordList = new ArrayList<String>();
        for(String word : wordsToFile.keySet()){
            ArrayList currList = wordsToFile.get(word);
            if(currList.size() == number){
                wordList.add(word);
            }
        }
        return wordList;
    }
    
    public void printFilesIn(String word){
        ArrayList<String> fileList = wordsToFile.get(word);
        for(int i = 0 ; i < fileList.size() ; i++){
            System.out.print(fileList.get(i) + " ");
        }
    }
    
    public void tester(){
        //WordsInFiles wif = new WordsInFiles();
        buildWordFileMap();
        System.out.println("The maximum number of files any word is in: "
                            + maxNumber());
        //ArrayList<String> mostCommonWords = wordsInNumFiles(maxNumber());
        
        ArrayList<String> mostCommonWords = wordsInNumFiles(4);        
        //System.out.println("# words occuring 4 times: " + mostCommonWords.size());
        //System.out.println("# most common words" + mostCommonWords.size());
        
        printFilesIn("tree");
        //ArrayList<String> temp = wordsInNumFiles(2);
        //System.out.println(temp.size());

        //print entire map
        /*for(String key : wordsToFile.keySet()){
            System.out.print(key + ":\t");
            printFilesIn(key);
            System.out.println();
        }*/
    }
}
