import java.util.*;
import edu.duke.*;
import java.io.*;
/**
 * Converts Hack assembly language(.asm) to machine level language (.hack)
 * 
 * Done as a part of nand2tetris course project (nand2tetris.org)
 * 
 * @author Pranav Bhosale
 * @version 1.0
 * 
 * PROCEDURE:
 * Fill the symbol table with prerequisite symbols
 * Remove all lines that are completely comments
 * Remove blank (whitespaced) lines
 * Remove midline comments
 * In the first pass - add labels to the symbol table
 * After this eliminate those labels from your file
 * In the second pass - add variables to the symbol table
 * Replace variables and labels with the values fetched from symbol table
 * In the final pass - check if A-type instruction or C-type
 * Process each instruction with a separate function depending on its type
 * Write this 16-bit opcode to the final hack file
 * Delete all intermediate files.
 * 
 * SCOPE FOR IMPROVEMENT:
 * Split into separate classes
 * Eliminate redundant passes
 * No need to create new files for every pass
 * Alternative: Array of strings, manipulated in each pass
 */


public class Assembler {
    private HashMap<String,Integer> symbolTable;
    private File assemblyFile;
    private String parent;
    
    public Assembler(){
        //FileResource fr = new FileResource("/Users/Apple/nand2tetris/projects/06/add/Add.asm");
        //rawCode = fr.asString();
        assemblyFile = new File("/Users/Apple/nand2tetris/projects/06/pong/Pong.asm");
        parent = assemblyFile.getAbsoluteFile().getParent();
        //System.out.println(parent);.
        symbolTable = fillSymbolTable();
        //File f = new File("shs");
    }
    
    public File removeLineComments(File f){
        File file = new File(parent+"/noComm.asm");
        try{
            // if file doesnt exist, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        try {
                Scanner myReader = new Scanner(f);
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    //process the line
                    boolean valid = checkValidLine(line);
                    //add to file if the line is valid 
                    if(valid){
                       bw.write(line);
                       bw.write('\n');
                    }
                }
                myReader.close();
                bw.close();
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch(IOException e){
            e.printStackTrace();
        }
        return file;
    }
    
    public boolean checkValidLine(String line){
        //entire line is a comment
        if(line.indexOf("//") == 0){
            //this line is invalid
            return false;
        }
        return true;
    }
    
    public File removeWhiteSpaces(File f){
        Scanner file;
        PrintWriter writer;
        try {
            
            file = new Scanner(f);
            writer = new PrintWriter(parent+ "/noWhite.asm");
            
            while (file.hasNext()) {
                String line = file.nextLine();
                if (!line.isEmpty()) {
                    writer.write(line);
                    writer.write("\n");
                }
            }
            file.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File retFile = new File(parent+ "/noWhite.asm");
        return retFile;
    }
    
    public File removeInlineComments(File f){
        //read file line by line and make a function call 
        //that returns valid substrings of the line
        //File file = new File("/Users/Apple/nand2tetris/projects/06/add/noMidLines.asm");
        File file = new File(parent+"/noMidLines.asm");
        try{
            // if file doesnt exist, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        try {
                Scanner myReader = new Scanner(f);
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    //process the line
                    String cleanLine = midLineHelper(line);
                    //add clean line to file
                    bw.write(cleanLine);
                    bw.write('\n');
                }
                myReader.close();
                bw.close();
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch(IOException e){
            e.printStackTrace();
        }
        return file;
    }
    
    public String midLineHelper(String line){
        int lim = line.indexOf("//");
        if(lim != -1){
            String clean = line.substring(0,lim);
            return clean;
        }
        else{
            return line;
        }
    }
    
    
    public void firstPass(File cleanFile){
        //adds all labels to the symbol table
        int lineNumber = 0;
        try {
                Scanner myReader = new Scanner(cleanFile);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    //process the line
                    if(line.indexOf("(") != -1 && line.indexOf(")") != -1 ){
                        int start = line.indexOf("(");
                        int end = line.indexOf(")");
                        symbolTable.put(line.substring(start+1,end),lineNumber);
                        lineNumber--;
                    }
                    lineNumber++;
                }
                myReader.close();
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        //System.out.println(symbolTable);
    }
    
    public void secondPass(File cleanFile){
        int varIndex = 16;
        try {
                Scanner myReader = new Scanner(cleanFile);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    //process the line
                    if(line.indexOf("@") != -1){
                        int start = line.indexOf("@");
                        if(!(symbolTable.containsKey(line.substring(start+1)))
                        && notANumber(line)){
                            symbolTable.put(line.substring(start+1),varIndex);
                            varIndex++;
                        }
                    }
                }
                myReader.close();
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        //System.out.println(symbolTable);
    }
    
    public boolean notANumber(String line){
        int start = line.indexOf("@")+1;
        
        if(line.charAt(start) == '0' ||
        line.charAt(start) == '1' ||
        line.charAt(start) == '2' ||
        line.charAt(start) == '3' ||
        line.charAt(start) == '4' ||
        line.charAt(start) == '5' ||
        line.charAt(start) == '6' ||
        line.charAt(start) == '7' ||
        line.charAt(start) == '8' ||
        line.charAt(start) == '9')
        {return false;}
        return true;
    }
    
    public File removeLabels(File f){
        File file = new File(parent+"/noLabels.asm");
        try{
            // if file doesnt exist, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        try {
                Scanner myReader = new Scanner(f);
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    //process the line
                    //if () present dont write line
                    //if @ present write a map replaced value
                    if(line.indexOf("@") != -1 && notANumber(line)){
                        //@ is present, get its replacement
                        String replacement = "@"+symbolTable.get(line.substring(line.indexOf('@')+1));
                        bw.write(replacement);
                        bw.write('\n');
                    }
                    else if(line.indexOf("(") != -1 && line.indexOf(")") != -1 ){
                        //() are present do nothing
                        
                    }
                    else{
                        bw.write(line);
                        bw.write('\n');
                    }
                }
                myReader.close();
                bw.close();
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch(IOException e){
            e.printStackTrace();
        }
        return file;
    }
    
    public File converter(File f){
        File file = new File(parent+"/res.hack");
        try{
            // if file doesnt exist, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        try {
                Scanner myReader = new Scanner(f);
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    //process the line
                    int start = line.indexOf("@");
                    if(start != -1){
                        //A type instruction spotted
                        //call function to process A instructions
                        //operate bitwise on the passed string - Integer.parseInt(s)
                        //should return a 16-bit binary string
                        String opCode = processAInstr(line.substring(start+1));
                        bw.write(opCode);
                        bw.write('\n');
                    }
                    else{
                        //C-type instruction found
                        //call parser to process C instructions 
                        //should return an array of strings with d,c,j parts in it
                        //further convert each of these to binary
                        //add them all together and write this to file
                        String opCode = processCInstr(line);
                        bw.write(opCode);
                        bw.write('\n');
                    }
                }
                myReader.close();
                bw.close();
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch(IOException e){
            e.printStackTrace();
        }
        return file;
    
    }
    
    public String processAInstr(String s){
        //returns 16-bit binary instruction
        int num = Integer.parseInt(s);
        String binary = "";
        while(num>0){
            String bit = Integer.toString(num&1);
            binary = bit + binary;
            num = num >> 1;
        }
        int len = binary.length();
        //make it 16 bits long
        while(16-len>0){
            binary = "0"+binary;
            len++;
        }
        return binary;
    }
    
    public String processCInstr(String s){
        String dest = "";
        String comp = "";
        String jump = "";
        boolean destExists = false, jumpExists = false;
        if(s.indexOf('=') != -1){
            //dest exists
            dest = s.substring(0,s.indexOf('='));
            dest = dest.trim(); //to remove space when instr is D = comp ; jump
            destExists = true;
        }
        else{
            dest = null;
        }
        if(s.indexOf(';') != -1){
            //jump exists
            jump = s.substring(s.indexOf(';')+1);
            jump = jump.trim(); //to remove space when instr is D = comp ; jump
            jumpExists = true;
        }
        else{
            jump = null;
        }
        if(destExists && jumpExists){
            comp = s.substring(s.indexOf('=')+1,s.indexOf(';'));
            comp = comp.trim();
        }
        else if(!destExists && jumpExists){
            comp = s.substring(0,s.indexOf(';'));
            comp = comp.trim();
        }
        else if(destExists && !jumpExists){
            comp = s.substring((s.indexOf('=')+1));
            comp = comp.trim();
        }
        //all fields are filled
        String opCode = "";
        String cc="",dd="",jj="",a="";
        if(comp.equals("0")){
            cc = "101010";
            a = "0";
        }
        else if(comp.equals("1")){
            cc = "111111";
            a = "0";
        }
        else if(comp.equals("-1")){
            cc = "111010";
            a = "0";
        }
        else if(comp.equals("D")){
            cc = "001100";
            a = "0";
        }
        else if(comp.equals("A")){
            cc = "110000";
            a = "0";
        }
        else if(comp.equals("!D")){
            cc = "001101";
            a = "0";
        }
        else if(comp.equals("!A")){
            cc = "110001";
            a = "0";
        }
        else if(comp.equals("-D")){
            cc = "001111";
            a = "0";
        }
        else if(comp.equals("-A")){
            cc = "110011";
            a = "0";
        }
        else if(comp.equals("D+1")){
            cc = "011111";
            a = "0";
        }
        else if(comp.equals("A+1")){
            cc = "110111";
            a = "0";
        }
        else if(comp.equals("D-1")){
            cc = "001110";
            a = "0";
        }
        else if(comp.equals("A-1")){
            cc = "110010";
            a = "0";
        }
        else if(comp.equals("D+A")){
            cc = "000010";
            a = "0";
        }
        else if(comp.equals("D-A")){
            cc = "010011";
            a = "0";
        }
        else if(comp.equals("A-D")){
            cc = "000111";
            a = "0";
        }
        else if(comp.equals("D&A")){
            cc = "000000";
            a = "0";
        }
        else if(comp.equals("D|A")){
            cc = "010101";
            a = "0";
        }
        else if(comp.equals("M")){
            cc = "110000";
            a = "1";
        }
        else if(comp.equals("!M")){
            cc = "110001";
            a = "1";
        }
        else if(comp.equals("-M")){
            cc = "110011";
            a = "1";
        }
        else if(comp.equals("M+1")){
            cc = "110111";
            a = "1";
        }
        else if(comp.equals("M-1")){
            cc = "110010";
            a = "1";
        }
        else if(comp.equals("D+M")){
            cc = "000010";
            a = "1";
        }
        else if(comp.equals("D-M")){
            cc = "010011";
            a = "1";
        }
        else if(comp.equals("M-D")){
            cc = "000111";
            a = "1";
        }
        else if(comp.equals("D&M")){
            cc = "000000";
            a = "1";
        }
        else if(comp.equals("D|M")){
            cc = "010101";
            a = "1";
        }
        
        //fill dd
        if(!destExists){
            dd = "000";
        }
        else if(dest.equals("M")){
            dd = "001";
        }
        else if(dest.equals("D")){
            dd = "010";
        }
        else if(dest.equals("MD")){
            dd = "011";
        }
        else if(dest.equals("A")){
            dd = "100";
        }
        else if(dest.equals("AM")){
            dd = "101";
        }
        else if(dest.equals("AD")){
            dd = "110";
        }
        else if(dest.equals("AMD")){
            dd = "111";
        }
        
        //fill jump
        if(!jumpExists){
            jj = "000";
        }
        else if(jump.equals("JGT")){
            jj = "001";
        }
        else if(jump.equals("JEQ")){
            jj = "010";
        }
        else if(jump.equals("JGE")){
            jj = "011";
        }
        else if(jump.equals("JLT")){
            jj = "100";
        }
        else if(jump.equals("JNE")){
            jj = "101";
        }
        else if(jump.equals("JLE")){
            jj = "110";
        }
        else if(jump.equals("JMP")){
            jj = "111";
        }
        
        opCode = "111" + a + cc + dd + jj;
        return opCode;
    }

    public HashMap<String,Integer> fillSymbolTable(){
        HashMap<String,Integer> symbolTable = new HashMap<String,Integer>();
        for(int i = 0 ; i < 16 ; i++){
            symbolTable.put("R"+i,i);
        }
        symbolTable.put("SP",0);
        symbolTable.put("LCL",1);
        symbolTable.put("ARG",2);
        symbolTable.put("THIS",3);
        symbolTable.put("THAT",4);
        symbolTable.put("SCREEN",16384);
        symbolTable.put("KBD",24576);
        return symbolTable;
    }
    
    public void mainProcedure(){
        File noComm = removeLineComments(assemblyFile);
        File noWhite = removeWhiteSpaces(noComm);
        File noInLine = removeInlineComments(noWhite);
        firstPass(noInLine);
        secondPass(noInLine);
        File noLabels = removeLabels(noInLine);
        File hackFile = converter(noLabels);
        //remove the unnecessary files
        noComm.delete();
        noWhite.delete();
        noInLine.delete();
        noLabels.delete();
        //System.out.println(symbolTable);
        //FileResource fr = new FileResource(noInLine);
        //System.out.println(fr.asString());
    }
    
    public void tester(){
        //System.out.println(rawCode);
    }
}
