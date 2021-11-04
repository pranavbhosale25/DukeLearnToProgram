import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author Pranav Bhosale
 * @version 14th May 2020
 */
public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start){
        String result = "";
        for(int i = start ; i < message.length() ; i+=2){
            result += message.charAt(i);
        }
        return result;
    }
    
    public int[] countLetters(String message){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0 ; i < message.length() ; i++){
            char currChar = message.charAt(i);
            currChar = Character.toLowerCase(currChar);
            int idx = alpha.indexOf(currChar);
            if(idx != -1){
                counts[idx]++;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] values){
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
    
    public void simpleTests(){
       FileResource fr = new FileResource();
       String tester = fr.asString();
       
       //here tester is the encrypted message
       String s1 = halfOfString(tester,0);
       String s2 = halfOfString(tester,1);
       
       //CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
       //String encryptedMsg = cc.decryptTwoKeys(tester);
       //System.out.println(encryptedMsg);
       
       int key1 = breakCaesarCipher(s1);
       int key2 = breakCaesarCipher(s2);
       System.out.println("Keys are " + key1 + " and " + key2);
       
       CaesarCipherTwo cc2 = new CaesarCipherTwo(26-key1,26-key2);
       //decrypted message
       String decryptedMsg = cc2.decryptTwoKeys(tester);
       System.out.println(decryptedMsg);
    }
    
    public int breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxIndex = maxIndex(freqs);
        int dkey = maxIndex - 4;
        if(maxIndex < 4){
            dkey = 26 - (4-maxIndex);
        }
        return dkey;
    }
}
