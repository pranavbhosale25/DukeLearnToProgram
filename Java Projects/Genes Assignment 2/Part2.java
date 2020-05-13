
/**
 * Write a description of Part2 here.
 * 
 * @author Pranav Bhosale
 * @version 6th May 2020
 */
public class Part2 {
    public int howMany(String a, String b){
        int startIndex = 0;
        int count = 0;
        while(true){
            startIndex = b.indexOf(a,startIndex);
            if(startIndex == -1){
                break;
            }
            count++;
            startIndex = b.indexOf(a,startIndex) + a.length();
        }
        return count;
    }
    
    public void testHowMany(){
        int res = howMany("aa","baaaaa");
        System.out.println(res);
        res = howMany("zoo","forest");
        System.out.println(res);
        
    }
}
