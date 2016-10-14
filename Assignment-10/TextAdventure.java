// TextAdventure.java
// This class contains the main method and handles reading the adventure
// file as well as all command line inputs

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class TextAdventure {
    
    private static TreeMap<String, Room> rooms;
    private static Stack<Room> history;
    private static Room startRoom, currentRoom;

    public static void main(String[] args) {
        
        rooms = new TreeMap<>();
        history = new Stack<>();
        
        if(args.length > 0){
            
            File file = new File(args[0]);
            readAdventureFile(file);
        } else{
                
            System.out.println("Invalid argument count: " + args.length);
            System.out.println("Usage: cyoa adventurefile");
            System.exit(1);
        }
        
        Scanner scanner = new Scanner(System.in);
        char command;
        
        // Game Loop
        do {
            
            System.out.println(currentRoom.getDescription());
            command = 'a';
            
            for(Room.Option option : currentRoom.getOptions()) {
                
                System.out.println(command + " " + option.getText());
                command++;
            }
            System.out.println();
            
            if(!currentRoom.hasOptions())
                return;
            
            command = scanner.nextLine().charAt(0);
            if(command >= 'a' && command <= 'l') {
                processOption(command);
            }
            else {
                
                switch(command) {
                    
                    case 'r':
                        
                        rCMD();
                        break;
                        
                    case 'y':
                        
                        yCMD();
                        break;
                        
                    case 'z':
                        
                        zCMD();
                        break;
                        
                    case 'q':
                        break;
                        
                    default:
                        
                        System.out.println("Uh-oh unrecognized command");
                }
            }
            
        } while(command != 'q');
    }
    
    private static void processOption(char command) {
        
        int index = command - 'a';
        String tag = currentRoom.getOptionDestination(index);
        
        if(tag == null) {
            
            System.out.println("That option is not available!");
        } 
        else if(!rooms.containsKey(tag)) {
            
            System.out.println("Error: Link to room that was not created");
            System.out.println("Aborting!");
            System.exit(1);
        }
        else {
            
            history.push(currentRoom);
            currentRoom = rooms.get(tag);
        }
    }
    
    private static void rCMD() {
        
        history.clear();
        currentRoom = startRoom;
    }
    
    private static void yCMD() {
        
        for(Room room : rooms.values()) {
            
            System.out.print(room.getKey() + ": ");
            for(Room.Option option : room.getOptions())
                System.out.print(option.getTag() + " ");
            
            System.out.println();
        }
        
        System.out.println();
    }
    
    private static void zCMD() {
        
        if(history.isEmpty())
            System.out.println("There is nothing to undo");
        else
            currentRoom = history.pop();
    }
    
    public static void readAdventureFile(File adventureFile) {
        
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(adventureFile));
            
            char command;
            String line, content;
            Room recentRoom = null;
            Room.Option recentOption = null;
            
            while((line = reader.readLine()) != null) {
                
                if(line.isEmpty())
                    continue;
                
                command = line.charAt(0);
                content = line.substring(2);
                
                switch (line.charAt(0)) {
                    
                    case 'r' : // add a new blank room
                        
                        recentRoom = new Room(content);
                        if(startRoom == null)
                            currentRoom = startRoom = recentRoom;
                        
                        rooms.put(content, recentRoom);
                        break;
                        
                    case 'd' : // add a line of description to most recently added room
                        
                        recentRoom.addDescription(content);
                        break;
                        
                    case 'o' : // add a new option and grab next line (t command)
                        
                        recentOption = new Room.Option(content);
                        recentRoom.addOption(recentOption);
                        break;
                        
                    case 't': // update destination tag of most recently added room
                        
                        recentOption.setTag(content);
                        break;
                        
                    default: 
                        throw new Exception("Invalid commands detected!");
		}
            } 
	} catch (Exception e){
            
            System.out.println("Error reading specified file");
            System.out.println("Please correct any mistakes in the adventure file");
            System.exit(1);
	}
    }
}
