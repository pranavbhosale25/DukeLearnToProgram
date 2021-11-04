import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice ; i < message.length() ; i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        //create sliced strings & pass them to getKey of cc 
        //add returned keys to jey array
        for(int i = 0 ; i < klength ; i++){
            String slice = sliceString(encrypted,i,klength);
            key[i] = cc.getKey(slice);
            //System.out.println(key[i]); //check if keys are right
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String,HashSet<String>> langs = new HashMap<String,HashSet<String>>();
        DirectoryResource dict = new DirectoryResource();
        for(File f : dict.selectedFiles()){
            FileResource currLang = new FileResource(f);
            HashSet<String> currDict = readDictionary(currLang);
            langs.put(f.getName(),currDict);
        }
        String decryptedMsg = breakForAllLangs(encrypted,langs);
        System.out.println(decryptedMsg);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for(String line : fr.lines()){
            hs.add(line.toLowerCase());
        }
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dict){
        int count = 0;
        for(String word : message.split("\\W+")){
            if(dict.contains(word.toLowerCase())){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dict){
        int maxWordCount = 0;
        String finalDec = "";
        int[] finalKeys = {};
        for(int i = 1 ; i <= 100 ; i++){
            int[] currKeys = tryKeyLength(encrypted,i,mostCommonCharIn(dict));
            VigenereCipher vc = new VigenereCipher(currKeys);
            String decryptedMsg = vc.decrypt(encrypted);
            int wordCount = countWords(decryptedMsg,dict);
            if(wordCount > maxWordCount){
                maxWordCount = wordCount;
                finalDec = decryptedMsg;
                finalKeys = currKeys;
            }
        }
        //System.out.println("Keysize:" + finalKeys.length);
        //System.out.println("Maximum legit words:" + maxWordCount);
        return finalDec;
    }
    
    public char mostCommonCharIn(HashSet<String> dict){
        HashMap<Character,Integer> freq = new HashMap<Character,Integer>();
        for(String word : dict){
            word = word.toLowerCase();
            for(char ch : word.toCharArray()){
                if(freq.containsKey(ch)){
                    freq.put(ch,freq.get(ch)+1);
                }
                else{
                    freq.put(ch,1);
                }
            }
        }
        //hashmap with frequencies is constructed
        char mostCommon = '\0';
        int maxCount = 0;
        for(char ch: freq.keySet()){
            if(freq.get(ch) > maxCount){
                maxCount = freq.get(ch);
                mostCommon = ch;
            }
        }
        return mostCommon;
    }
    
    public String breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> langs){
        int bestCount = 0;
        String bestDec = "";
        String targetLang = "";
        for(String language : langs.keySet()){
            String currDec = breakForLanguage(encrypted,langs.get(language));
            int currCount = countWords(currDec,langs.get(language));
            if(currCount > bestCount){
                bestCount = currCount;
                bestDec = currDec;
                targetLang = language;
            }
        }
        //final results
        //System.out.println(bestDec);
        //System.out.println("Words matched: " + bestCount);
        System.out.println("Message is in: " + targetLang);
        return bestDec;
    }
    
    public void testSliceString(){
        System.out.println(sliceString("abcdefghijklm", 4, 5));
        System.out.println(sliceString("abcdefghijklm", 2, 3));
    }
    
    public void testTryKeyLength(){
        FileResource fr = new FileResource();
        String file = fr.asString();
        int[] keys = tryKeyLength(file,4,'e');
    }
}
