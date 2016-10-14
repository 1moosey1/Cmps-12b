// dllistTest.java
// Unit tests for dllist

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class dllistTest {
    
    /**********************
    * Custom test section *
    ***********************/
    
    @Test
    public void deleteTest() {
        
        dllist list = new dllist();
        list.insert("A", dllist.position.LAST);
        list.insert("B", dllist.position.LAST);
        list.insert("C", dllist.position.LAST);
        
        list.delete();      
        list.delete();
        assertEquals("A", list.getItem());
        list.delete();
        assertEquals(true, list.isEmpty());
    }
    
    @Test
    public void deleteFrontBackTest() {
        
        dllist list = new dllist();
        list.insert("A", dllist.position.LAST);
        list.insert("B", dllist.position.LAST);
        list.insert("C", dllist.position.LAST);
        list.insert("D", dllist.position.LAST);

        
        assertEquals("D", list.getItem());
        list.delete();
        assertEquals("C", list.getItem());
        list.setPosition(dllist.position.FIRST);
        assertEquals("A", list.getItem());
        list.delete();
        assertEquals("B", list.getItem());
    }
    
    @Test
    public void getPositionTest() {
        
        dllist list = new dllist();
        list.insert("B", dllist.position.LAST);
        list.insert("A", dllist.position.FIRST);
        list.insert("C", dllist.position.FOLLOWING);
        list.insert("D", dllist.position.PREVIOUS);
        
        assertEquals(1, list.getPosition());
        list.setPosition(dllist.position.FOLLOWING);
        assertEquals(2, list.getPosition());
        list.setPosition(dllist.position.FOLLOWING);
        assertEquals(3, list.getPosition());
        list.setPosition(dllist.position.FOLLOWING);
        assertEquals(3, list.getPosition());
        list.setPosition(dllist.position.PREVIOUS);
        list.setPosition(dllist.position.PREVIOUS);
        list.setPosition(dllist.position.PREVIOUS);
        list.setPosition(dllist.position.PREVIOUS);
        assertEquals(0, list.getPosition());
    }
    
    @Test
    public void followingPreviousTest() {
        
        dllist list = new dllist();
        list.insert("B", dllist.position.LAST);
        list.insert("A", dllist.position.FIRST);
        list.insert("C", dllist.position.FOLLOWING);
        list.insert("D", dllist.position.PREVIOUS);
        
        assertEquals("D", list.getItem());
        list.setPosition(dllist.position.FOLLOWING);
        assertEquals("C", list.getItem());
        list.setPosition(dllist.position.FOLLOWING);
        assertEquals("B", list.getItem());
        list.setPosition(dllist.position.FOLLOWING);
        assertEquals("B", list.getItem());
        list.setPosition(dllist.position.PREVIOUS);
        list.setPosition(dllist.position.PREVIOUS);
        list.setPosition(dllist.position.PREVIOUS);
        list.setPosition(dllist.position.PREVIOUS);
        assertEquals("A", list.getItem());
    }
    
    @Test
    public void insertFollowingTest() {
        
        dllist list = new dllist();
        list.insert("A", dllist.position.FIRST);
        list.insert("B", dllist.position.FIRST);
        list.insert("C", dllist.position.FIRST);
        list.insert("D", dllist.position.FOLLOWING);
        list.setPosition(dllist.position.FIRST);
        assertEquals("C", list.getItem());
    }
    
    @Test
    public void insertPreviousTest() {
        
        dllist list = new dllist();
        list.insert("A", dllist.position.LAST);
        list.insert("B", dllist.position.LAST);
        list.insert("C", dllist.position.LAST);
        list.insert("D", dllist.position.PREVIOUS);
        list.setPosition(dllist.position.LAST);
        assertEquals("C", list.getItem());
    }
    
    @Test
    public void setPositionLastTest() {
        
        dllist list = new dllist();
        list.insert("A", dllist.position.LAST);
        list.insert("B", dllist.position.LAST);
        list.insert("C", dllist.position.LAST);
        list.setPosition(dllist.position.FIRST);
        assertEquals("A", list.getItem());
    }
    
    @Test
    public void setPositionFirstTest() {
        
        dllist list = new dllist();
        list.insert("C", dllist.position.FIRST);
        list.insert("B", dllist.position.FIRST);
        list.insert("A", dllist.position.FIRST);
        list.setPosition(dllist.position.LAST);
        assertEquals("C", list.getItem());
    }
    
    @Test
    public void firstInsertTest() {
        
        dllist list = new dllist();
        list.insert("3", dllist.position.FIRST);
        assertEquals("3", list.getItem());
                
        list.insert("2", dllist.position.FIRST);
        list.insert("1", dllist.position.FIRST);
        assertEquals("1", list.getItem());
    }
    
    @Test
    public void lastInsertTest() {
        
        dllist list = new dllist();
        list.insert("Test", dllist.position.LAST);
        assertEquals("Test", list.getItem());
        
        list.insert("Test2", dllist.position.LAST);
        list.insert("Test3", dllist.position.LAST);
        assertEquals("Test3", list.getItem());
    }
    
    @Test
    public void notEmptyTest() {
        
        dllist list = new dllist();
        list.insert("Test", dllist.position.LAST);
        assertEquals(false, list.isEmpty());
    }
    
    // End custom test section

    @Test
    public void startsEmptyTest() {
        dllist lst = new dllist();
        assertEquals(true, lst.isEmpty());
    }
}
