/*
Basic generic que to be used with Tree.java and xref.java
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

class Queue <Item> implements Iterable <Item> {
   
   //nested node class
   private class Node {
      Item item;
      Node next;
   }
   private Node head = null;
   private Node tail = null;

   //checks to see if queue is empty. returns true if so.
   public boolean isempty() {
      
      return head == null;
   }

   // insert method taken and modified from http://algs4.cs.princeton.edu/13stacks/LinkedQueue.java.html
   public void insert(Item newitem) {
      
      Node oldtail = tail;
      tail = new Node();
      tail.item = newitem;
      tail.next = null;
      if(isempty()){
         head = tail;
      } else {
         oldtail.next = tail;
      }
   }

   /*iterator function that has nested Itor class (used to print line numbers in
     Tree.java
   */
   public Iterator <Item> iterator() {
      return new Itor ();
   }

   class Itor implements Iterator <Item> {
      Node current = head;
      public boolean hasNext() {
         return current != null;
      }
      public Item next() {
         if (! hasNext ()) throw new NoSuchElementException();
         Item result = current.item;
         current = current.next;
         return result;
      }
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
