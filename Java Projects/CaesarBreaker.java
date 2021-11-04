import edu.duke.*;
/**
 * Write a description of BreakCipher here.
 * 
 * @author Pranav Bhosale
 * @version 14th May 2020
 */
public class CaesarBreaker {
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
    
    public String decrypt(String encryptedMsg){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encryptedMsg);
        int maxIndex = maxIndex(freqs);
        int dkey = maxIndex - 4;
        if(maxIndex < 4){
            dkey = 26 - (4-maxIndex);
        }
        
        return cc.encrypt(encryptedMsg,26-dkey);
    }
    
    public void testDecrypt(){
        String input = "At noon be in the conference eeeeee eeeee eeeee room with your hat on for a surprise party. YELL LOUD!";
        //encrypt the message - can just input encrypted string from elsewhere
        CaesarCipher cc = new CaesarCipher();
        int key = 12;
        String encryptedMsg = cc.encrypt(input,key);
        System.out.println(encryptedMsg);
        //decrypted message
        String decryptedMsg = decrypt(encryptedMsg);
        System.out.println(decryptedMsg);
    }
    
    public String halfOfString(String message, int start){
        String result = "";
        for(int i = start ; i < message.length() ; i+=2){
            result += message.charAt(i);
        }
        return result;
    }
    
    public void testHalfOfString(){
        System.out.println(halfOfString("Qbkm Zgis", 0));
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }
    
    public int getKey(String s){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(s);
        int maxIndex = maxIndex(freqs);
        int dkey = maxIndex - 4;
        if(maxIndex < 4){
            dkey = 26 - (4-maxIndex);
        }
        return dkey;
    }
    
    public String decryptTwoKeys(String encryptedMsg){
        //get half strings
        String s1 = halfOfString(encryptedMsg,0);
        String s2 = halfOfString(encryptedMsg,1);
        //calc their keys
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        //key1=14;key2=24;
        System.out.println("Keys are " + key1 + " and " + key2);
        //decryption
        CaesarCipher cc = new CaesarCipher();
        String decryptedMsg = cc.encryptTwoKeys(encryptedMsg,26-key1,26-key2);
        //System.out.println(decryptedMsg);
        return decryptedMsg;
    }
    
    public void testDecryptTwoKeys(){
        String input = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        //int key1 = 14;
        //int key2 = 24;
        //encrypt the message
        CaesarCipher cc = new CaesarCipher();
        //String encryptedMsg = cc.encryptTwoKeys(input,key1,key2);
        //System.out.println(encryptedMsg);
        //decryption
        String tester = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        FileResource fr = new FileResource();
        String tester2 = fr.asString();
        
        String decryptedMsg = decryptTwoKeys(tester2);
        System.out.println(decryptedMsg);
    }
}
