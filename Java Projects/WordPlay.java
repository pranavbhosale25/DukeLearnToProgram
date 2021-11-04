import edu.duke.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        char lower = Character.toLowerCase(ch);
        if(lower == 'a' || lower == 'e'||lower == 'i'||lower == 'o'||lower == 'u'){
            return true;
        }
        return false;
    }
    
    public void testIsVowel(){
        char input = 'c';
        boolean ans = isVowel(input);
        if(ans){
            System.out.println(input + " is a vowel");
        }
        else{
            System.out.println(input + " is a consonant");
        }
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for(int i = 0 ; i < sb.length() ; i++){
            char currChar = sb.charAt(i);
            if(isVowel(currChar)){
                sb.setCharAt(i,ch);
            }
        }
        return sb.toString();
    }
    
    public void testReplaceVowels(){
        String phrase = "College assignments are due tomorrow!";
        char rep = 'x';
        String ans = replaceVowels(phrase,rep);
        System.out.println(ans);
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for(int i = 0 ; i < sb.length() ; i++){
            char currChar = sb.charAt(i);
            currChar = Character.toLowerCase(currChar);
            char chLower = Character.toLowerCase(ch);
            if(currChar == chLower){
                if(i%2 == 0){
                    sb.setCharAt(i,'*');
                }
                else{
                    sb.setCharAt(i,'+');
                }
            }
        }
        return sb.toString();
    }
    
    public void testEmphasize(){
        String phrase = "dna ctgaaactga";
        char ch = 'A';
        String ans = emphasize(phrase,ch);
        System.out.println(ans);
    }
    }
