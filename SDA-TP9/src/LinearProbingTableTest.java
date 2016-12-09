/**
 * @author Maël Cattin
 */

import org.junit.*;

import static org.junit.Assert.*;

public class LinearProbingTableTest {
    private LinearProbingTable myTable;
    private static final int TABLE_SIZE = 997;

    @Before
    public void setUp() throws Exception {
        myTable = new LinearProbingTable();
    }

    @Test
    public void hashCalculation() {
        int key = (int)(Math.random() * Integer.MAX_VALUE);
        Hashable a = new HashableImpl(key);
        Hashable b = new HashableImpl(key);
        assertTrue(a.hash(TABLE_SIZE) == b.hash(TABLE_SIZE));
    }
    @Test
    public void hashCalculation0() {
        int key = 0;
        Hashable a = new HashableImpl(key);
        Hashable b = new HashableImpl(key);
        assertTrue(a.hash(TABLE_SIZE) == b.hash(TABLE_SIZE));
    }
    @Test
    public void hashCalculationMAXINT() {
        int key = Integer.MAX_VALUE;
        Hashable a = new HashableImpl(key);
        Hashable b = new HashableImpl(key);
        assertTrue(a.hash(TABLE_SIZE) == b.hash(TABLE_SIZE));
    }

    @Test
    public void isFirstEmpty(){
        assertTrue(myTable.isEmpty());
    }

    @Test
    public void removeEmpty(){
        boolean success = false;
        try {
            myTable.remove(new HashableImpl((int)(Math.random() * Integer.MAX_VALUE)));
        } catch (ItemNotFound itemNotFound) {
            success = true;
        }
        assertTrue(success);
    }

    @Test
    public void findEmpty(){
        boolean success = false;
        try {
            myTable.find(new HashableImpl((int)(Math.random() * Integer.MAX_VALUE)));
        } catch (ItemNotFound itemNotFound) {
            success = true;
        }
        assertTrue(success);
    }

    @Test
    public void insertionNotEmpty(){
        int key = (int)(Math.random() * Integer.MAX_VALUE);
        Hashable a = new HashableImpl(key);
        myTable.insert(a);
        assertFalse(myTable.isEmpty());
    }

    @Test
    public void makeEmpty(){
        int key = (int)(Math.random() * Integer.MAX_VALUE);
        Hashable a = new HashableImpl(key);
        myTable.insert(a);
        myTable.makeEmpty();
        assertTrue(myTable.isEmpty());
    }

    @Test
    public void removeOne(){
        boolean success = true;
        int key = (int)(Math.random() * Integer.MAX_VALUE);
        Hashable a = new HashableImpl(key);
        myTable.insert(a);
        try {
            myTable.remove(a);
        } catch (ItemNotFound itemNotFound) {
            success = false;
        }
        assertTrue(success);
        assertTrue(myTable.isEmpty());
    }

    @Test
    public void unity(){
        int key = (int)(Math.random() * Integer.MAX_VALUE);
        Hashable a = new HashableImpl(key);
        myTable.insert(a);
        int pos = myTable.findPos(a);
        myTable.insert(a);
        assertEquals(pos, myTable.findPos(a));
    }

    @Test
    public void collision(){
        int key = (int)(Math.random() * Integer.MAX_VALUE);
        Hashable a = new HashableImpl(key);
        Hashable b = new HashableImpl(key);
        myTable.insert(a);
        myTable.insert(b);
        assertTrue(myTable.findPos(a) != myTable.findPos(b));
    }

    @Test
    public void remove(){
        boolean success = true;
        int key = (int)(Math.random() * Integer.MAX_VALUE);
        Hashable a = new HashableImpl(key);
        Hashable b = new HashableImpl(key);
        myTable.insert(a);
        myTable.insert(b);
        try {
            myTable.remove(a);
        } catch (ItemNotFound itemNotFound) {
            itemNotFound.printStackTrace();
        }
        try {
            myTable.find(b);
        } catch (ItemNotFound itemNotFound){
            success = false;
        }
        assertTrue(success);
    }

}