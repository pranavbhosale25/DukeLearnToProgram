import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author Pranav Bhosale
 * @version 14th May 2020
 */
public class TestCaesarCipher {
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
        //FileResource fr = new FileResource();
        //String tester = fr.asString();
        String tester = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        //encrypt
        String encryptedMsg = cc.encrypt(tester);
        System.out.println(encryptedMsg);
        
        //here tester is the encrypted message
        int key = breakCaesarCipher(tester);
        CaesarCipher cc2 = new CaesarCipher(key);
        //decrypted message
        String decryptedMsg = cc2.decrypt(tester);
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
