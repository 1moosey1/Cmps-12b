/* 
 Binary tree, the bulk of the program.
 Used to index the words feed through the file supplied in xref.java
 */

import static java.lang.System.*;

class Tree {

    //Nested node class
    private class Node {

        String key;
        Queue<Integer> values;
        Node left;
        Node right;

        Node(String key, Integer linenum) {

            this.key = key;
            values = new Queue<Integer>();
            values.insert(linenum);
        }
    }
    private Node root;

    /*
     recursive method that calls itself until reaching the end of its branch
     prints out the depth of the node and the key. Used to help debug insert()
     */
    private void debugHelper(Node tree, int depth) {
        if (tree != null) {
            Node currentNode = tree;
            int level = depth;
            String indent = "";
            for (int i = 0; i < depth; i++) {
                indent = indent + "  ";
            }
            debugHelper(currentNode.left, level + 1);
            System.out.println(indent + level + " " + currentNode.key);
            debugHelper(currentNode.right, level + 1);
        }
    }

    /*
     Main purpose of program. Prints every word in lexiographic order along 
     with which lines they were found in. Recursive like debugHelper()
     also calls another metehod that handles the traversal of the queue
     */
    private void outputHelper(Node tree) {
        if (tree != null) {
            Node currentNode = tree;
            outputHelper(currentNode.left);

            System.out.print(currentNode.key + " : ");
            printNumbers(tree.values);

            outputHelper(currentNode.right);
        }
    }

    //Method that takes Queue object and iterates to tranverse the keys
    private void printNumbers(Queue<Integer> values) {
        Queue<Integer>.Itor iterator = (Queue<Integer>.Itor) values.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.print("\n");
    }

    //Insert method, no balance.
    public void insert(String key, Integer linenum) {

        if (root == null) {

            root = new Node(key, linenum);
            return;
        }

        Node currentNode = root;
        while (true) {

            int value = currentNode.key.compareTo(key);
            if (value > 0) {

                if (currentNode.left != null) {
                    currentNode = currentNode.left;
                } else {

                    currentNode.left = new Node(key, linenum);
                    break;
                }
            } else if (value < 0) {

                if (currentNode.right != null) {
                    currentNode = currentNode.right;
                } else {

                    currentNode.right = new Node(key, linenum);
                    break;
                }
            } else {
                currentNode.values.insert(linenum);
                break;
            }
        }
    }

    // Shows debug output of tree by calling helper
    public void debug() {
        debugHelper(root, 0);
    }

    // Shows sorted words with lines where each word appears (using helper)
    public void output() {
        outputHelper(root);
    }

}
