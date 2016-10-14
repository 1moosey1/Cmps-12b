// edfile.java
// Simple line-oriented text editor that uses a doubly-link list (dllist.java)
// to keep track of lines in the file being edited.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

class edfile{

    public static void main (String[] args) {
        
        System.out.println("Welcome to the most amazing line editor!");
       
        boolean want_echo = false;
        dllist lines = new dllist();
       
        int fileArg = 0;
        if(args.length > 1) {
            if(args[0].equals("-e")) {
                
                want_echo = true;
                fileArg = 1;
            }
        }
        
        try {
            
            readFile(args[fileArg], lines, dllist.position.LAST);
        } catch (Exception ex) {
            
           System.out.println("Error with loading file or reading options");
           System.out.println("Editor will start with an empty list");
        }
         
        //Once text is stored in a list, looks for input
        Scanner stdin = new Scanner (in);
        for (;;) {
          
            if (! stdin.hasNextLine()) 
                break;
            
            String inputline = stdin.nextLine();
            
            //options
            if (want_echo) 
                out.printf ("%s%n", inputline);
            
            if (inputline.matches ("^\\s*$")) 
                continue;
            
            char command = inputline.charAt(0);
            
            //commands
            switch (command) {
             
                case '#': break; //comment line, ignore
                    
                case '$': //sets the current of dllist to the end and prints
                    currToLast(lines); 
                    break;
                    
                case '*':  //prints whole list, curr is now LAST
                    disAllSetLast(lines); 
                    break;
                    
                case '.':  //prints current line
                    disCurr(lines); 
                    break;
                    
                case '0':  //sets current to FIRST and prints current line
                    currToFirst(lines); 
                    break;
                    
                case '<': //sets current to previous line and prints
                    currToPrev(lines); 
                    break;
                    
                case '>':  //sets current to following line and prints
                    currToFollowing(lines); 
                    break;
                    
                case 'a':  //inserts text to a new line after current
                    insertAfterCommand(lines, inputline); 
                    break;
                    
                case 'd':  //deletes current line and moves pos to the following
                    deleteCommand(lines); 
                    break;
                    
                case 'i': //inserts text before current line
                    insertBeforeCommand(lines, inputline); 
                    break;
                    
                case 'r': //reads and attaches text from a file to after current line
                    readFileCommand(lines, inputline); 
                    break;
                    
                case 'w': //writes current list to a file
                    writeFileCommand(lines, inputline);
                    break;
                    
                default : //ooga booga
                    invalidCommand();
                    break;
            }
      }
      
      System.out.println("Program will now close. Have a nice day!");
   }
    
    private static int readFile(String fileName, dllist lines, dllist.position pos) 
        throws IOException {
        
        int numInserted = 0;
        BufferedReader bufferedReader;
               
        bufferedReader = new BufferedReader(new FileReader(fileName));
                    
        String text;
        while((text = bufferedReader.readLine()) != null) {
       
            lines.insert(text, pos);
            numInserted++;
        }
        bufferedReader.close();
        
        return numInserted;
    }
    
    private static void invalidCommand() {
        
        System.out.println("Invalid Command");
    }
    
    private static void currToLast(dllist lines){ //for $ command
        lines.setPosition(dllist.position.LAST);
        System.out.println(lines.getItem());
    }
    
    private static void disAllSetLast(dllist lines){ //for * command
        lines.setPosition(dllist.position.FIRST);
        for( int i = 0; i< lines.getSize(); ++i){
            System.out.println(lines.getItem());
            lines.setPosition(dllist.position.FOLLOWING);
        }
    }
    
    private static void disCurr(dllist lines){ //for . command
        System.out.println(lines.getItem());
    }
    
    private static void currToFirst(dllist lines){ //for 0 command
        lines.setPosition(dllist.position.FIRST);
        System.out.println(lines.getItem());
    }
    
    private static void currToPrev(dllist lines){ //for  < command
        lines.setPosition(dllist.position.PREVIOUS);
        System.out.println(lines.getItem());
    }
    
    private static void currToFollowing(dllist lines){ // for > command
        lines.setPosition(dllist.position.FOLLOWING);
        System.out.println(lines.getItem());
    }
    
    private static void insertAfterCommand(dllist lines, String text) {// for a text command
        
        text = text.substring(1);
        lines.insert(text, dllist.position.FOLLOWING);
        System.out.println(lines.getItem());
    }
    
    private static void deleteCommand(dllist lines) {// for d command
        lines.delete();
    }
    
    private static void insertBeforeCommand(dllist lines, String text) {// for i text command
        
        text = text.substring(1);
        lines.insert(text, dllist.position.PREVIOUS);
        System.out.println(lines.getItem());
    }
    
    private static void readFileCommand(dllist lines, String fileName) { // for r filename command
        
        int numInserted;
        
        fileName = fileName.substring(1);      
        try {
            
            numInserted = readFile(fileName, lines, dllist.position.FOLLOWING);
            System.out.println(numInserted);
        }
        catch(IOException ex) {
            System.out.println("Operation Failed: File not found");
        }
    }
    
    private static void writeFileCommand(dllist list, String fileName) { //w filename
        
        int numWritten = 0;
        BufferedWriter bufferedWriter;
        
        fileName = fileName.substring(1);
        try {
            
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            
            list.setPosition(dllist.position.FIRST);
            for(int i = 0; i < list.getSize(); ++i) {
                
                bufferedWriter.write(list.getItem());
                bufferedWriter.newLine();
                list.setPosition(dllist.position.FOLLOWING);
                numWritten++;
            }
            
            bufferedWriter.close();
            System.out.println(numWritten);
        } catch (IOException ex) {
            
            System.out.println("Operation Failed: Error with file name");
        }
    }
}