import java.util.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author Pranav Bhosale
 * @version 15th May 2020
 */
public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> freq;
    
    public CharactersInPlay(){
        characterNames = new ArrayList<String>();
        freq = new ArrayList<Integer>();
    }
    
    public void update(String name){
        int index = characterNames.indexOf(name);
        if(index != -1){
            int value = freq.get(index);
            freq.set(index,value+1);
        }
        else{
            characterNames.add(name);
            freq.add(1);
        }
    }
    
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for(String s: fr.lines()){
            if(s.indexOf(".") != -1){
                String name = s.substring(0,s.indexOf("."));
                update(name);
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        System.out.println("Character list & dialogue count");
        charactersWithNumParts(9,16);
    }
    
    public void charactersWithNumParts(int n1,int n2){
        for(int i = 0 ; i < characterNames.size() ; i++){
            int count = freq.get(i);
            if(count >= n1 && count < n2){
                System.out.println(characterNames.get(i) + "\t" + freq.get(i));
            }
        }
    }
}
