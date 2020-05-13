
/**
 * Write a description of Part3 here.
 * 
 * @author Pranav Bhosale 
 * @version 6th May 2020
 */
public class Part3 {
    public boolean twoOccurrences(String a, String b){
        int startIndex = -1;
        //look for first appearance
        startIndex = b.indexOf(a);
        
        if(startIndex != -1){
            startIndex = b.indexOf(a,startIndex+a.length()); 
        }
        else{
            return false;
        }
        
        if(startIndex == -1){
            return false;
        }
        
        return true;
    }
    
    public void testing(){
        String a = "atg";
        String b = "ctgtatgta";
        
        boolean result = twoOccurrences(a,b);
        
        if(result){System.out.println("Found");}
        else{System.out.println("Not found");}
        
        
    }
    
    public void testLastPart(){
        String a = "zoo";
        String b = "forest";
        
        System.out.println(lastPart(a,b));
    }
    
    public String lastPart(String a, String b){
        int startIndex;
        startIndex = b.indexOf(a);
        if(startIndex == -1){
            return b;
        }
        else{
            String ans = b.substring(startIndex+a.length(),b.length());
            return ans;
        }

    }
}
