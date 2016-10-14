// dllist.java
// A SIMPLE (DOUBLY) LINKED LIST!!!!!!!!!!!!

public class dllist {

    public enum position {FIRST, PREVIOUS, FOLLOWING, LAST};
    
    //nested node class
    private class node {
        
        String item;
        node prev;
        node next;
    }
    
    //class variables
    private node first, current, last;
    private int currentPosition, size;
    
    //constructor
    public dllist() { 
        
        first = new node();
        last = new node();
        first.next = last;
        last.prev = first;
    }

    //moves the current node using enum
    public void setPosition (position pos) { 
        
        switch(pos) {
            
            case FIRST:
                
                current = first.next;               
                currentPosition = 0;
                break;
            
            case PREVIOUS:
                
                if(currentPosition != 0) {
                   
                    current = current.prev;
                    currentPosition--;
                }
                break;
                
            case FOLLOWING:
                
                if(currentPosition != (size - 1)) {
                    
                    current = current.next;
                    currentPosition++;
                }
                break;
                
            case LAST:
                
                current = last.prev;   
                currentPosition = size - 1;
        }
    }

    //checks to see if the list is empty
    public boolean isEmpty () { 
       return size == 0;
    }
    
    //returns size of list
    public int getSize() { 
        return size;
    }

    //returns the string stored in the current node
    public String getItem () { 
        
        if(isEmpty())
            throw new java.util.NoSuchElementException();
        
        return current.item;
    }

    //returns the current position of the node
    public int getPosition () { 
        
        if(isEmpty())
            throw new java.util.NoSuchElementException();
        
        return currentPosition;
    }

    //deletes the current node
    public void delete () { 
        
        if(isEmpty())
            throw new java.util.NoSuchElementException();
        
        node previous = current.prev;
        node next = current.next;
        
        previous.next = next;
        next.prev = previous;
        
        if(currentPosition == size - 1) {
            
            current = previous;
            currentPosition--;
        }
        else 
            current = next;
        
        size--;
    }

    //inserts a new node to pos
    public void insert (String item, position pos) { 
        
        if(current == first || current == last)
            if (pos == position.PREVIOUS || pos == position.FOLLOWING)
                throw new IllegalArgumentException();
        
        size++;
        node newNode = new node();
        newNode.item = item;
        
        switch(pos) {
            
            case FIRST:
                
                newNode.prev = first;
                newNode.next = first.next;
                
                first.next.prev = newNode;
                first.next = newNode;
                currentPosition = 0;
                break;
                
            case PREVIOUS:
                
                newNode.prev = current.prev;
                newNode.next = current;
                
                current.prev.next = newNode;
                current.prev = newNode;
                break;
                
            case FOLLOWING:
                
                newNode.prev = current;
                newNode.next = current.next;
                
                current.next.prev = newNode;
                current.next = newNode;
                currentPosition++;
                break;
                
            case LAST:
                
                newNode.prev = last.prev;
                newNode.next = last;
                
                last.prev.next = newNode;
                last.prev = newNode;
                currentPosition = size - 1;
        }
        
        current = newNode;
    }
}