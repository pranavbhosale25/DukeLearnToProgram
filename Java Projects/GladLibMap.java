import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCats;
    private Random myRandom;
    private int countReplaced;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedCats = new ArrayList<String>();
        countReplaced = 0;
        //myMap = new HashMap<String, ArrayList<String>>();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
        countReplaced = 0;
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal",
                           "adjective", "name", "color",
                           "timeframe", "verb", "fruit"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        //add to used categories if new
        if(usedCats.indexOf(label) == -1){
            usedCats.add(label);
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        //check if sub is used
        int index = usedWords.indexOf(sub);
        while(index!=-1){
            //until you get a new word recalculate it
            sub = getSubstitute(w.substring(first+1,last));
            index = usedWords.indexOf(sub);
        }
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        usedWords.clear();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Total words replaced:" + usedWords.size());
        System.out.println("Total words in map:" + totalWordsInMap());
        System.out.println("Total words considered:" + totalWordsConsidered());
    }
    
    public int totalWordsInMap(){
        int count = 0;
        for(String word : myMap.keySet()){
            ArrayList temp = myMap.get(word);
            count += temp.size();
        }
        return count;
    }

    public int totalWordsConsidered(){
        ArrayList<String> temp;
        int total = 0;
        for(int i = 0 ; i < usedCats.size() ; i++){
            String cat = usedCats.get(i);
            temp = myMap.get(cat);
            total += temp.size();
        }
        return total;
    }
}
