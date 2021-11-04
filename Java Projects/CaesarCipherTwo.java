import edu.duke.*;
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author Pranav Bhosale
 * @version 14th May 2020
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String lowerAlpha;
    private String shiftedAlphabet1;
    private String shiftedAlphabetLower1;
    private String shiftedAlphabet2;
    private String shiftedAlphabetLower2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2){
        mainKey1 = key1;
        mainKey2 = key2;
        
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowerAlpha = alphabet.toLowerCase();
        //for key1
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabetLower1 = shiftedAlphabet1.toLowerCase();
        //for key2
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        shiftedAlphabetLower2 = shiftedAlphabet2.toLowerCase();
    }
    
    public String encryptTwoKeys(String input){
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i = 0 ; i < encrypted.length() ; i++){
            
            if(i%2 == 0){
                char currChar = encrypted.charAt(i);
                if(Character.isUpperCase(currChar)){
                    int index = alphabet.indexOf(currChar);
                    if(index != -1){
                            char newChar = shiftedAlphabet1.charAt(index);
                            encrypted.setCharAt(i,newChar);
                        }
                    }
                else{
                    int index = lowerAlpha.indexOf(currChar);
                    if(index != -1){
                        char newChar = shiftedAlphabetLower1.charAt(index);
                        encrypted.setCharAt(i,newChar);
                    }
                }
            }
            else{
                char currChar = encrypted.charAt(i);
                if(Character.isUpperCase(currChar)){
                    int index = alphabet.indexOf(currChar);
                    if(index != -1){
                            char newChar = shiftedAlphabet2.charAt(index);
                            encrypted.setCharAt(i,newChar);
                        }
                    }
                else{
                    int index = lowerAlpha.indexOf(currChar);
                    if(index != -1){
                        char newChar = shiftedAlphabetLower2.charAt(index);
                        encrypted.setCharAt(i,newChar);
                    }
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decryptTwoKeys(String input){
        //CaesarCipherTwo cc2 = new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        //CaesarCipherTwo cc2 = new CaesarCipherTwo(14,24);
        String result = encryptTwoKeys(input);
        return result;
    }
}
