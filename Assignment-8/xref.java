/*
 Main java class that runs the indexing.
 It reads file and pumps it into the tree and queue and then calls the printing
 methods from Tree.java
 */

import java.io.*;
import java.util.Scanner;
import static java.lang.System.*;

class xref {
    
    //method that takes a file and feeds it to the tree.
    static void processFile(String filename, boolean debug) throws IOException {
        Scanner scan = new Scanner(new File(filename));
        Tree tree = new Tree();
        for (int linenr = 1; scan.hasNextLine(); ++linenr) {
            for (String word : scan.nextLine().split("\\W+")) {
                //out.printf ("%s: %d: %s%n", filename, linenr, word); //removed for polish
                tree.insert(word, linenr);
            }
        }
        scan.close();
        if (debug) {
            tree.debug();
        } else {
            tree.output();
        }
    }
    
    //main method of the program
    public static void main(String[] args) {

        String filename = args[0];
        try {
            if (args[0].equals("-d")) {
                processFile(args[1], true);
            } else {
                processFile(filename, false);
            }
        } catch (IOException error) {
            auxlib.warn(error.getMessage());
        }
        auxlib.exit();
    }

}
