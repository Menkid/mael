/**
 * Created by Maël Cattin on 05.12.2016.
 */

public abstract class ProbingHashTable implements HashTable {
    protected abstract int findPos(Hashable x);

    public ProbingHashTable(){
        allocateArray( DEFAULT_TABLE_SIZE );
        makeEmpty();
    }

    private static final int DEFAULT_TABLE_SIZE = 11;
    protected HashEntry[] array;
    private int currentSize; // # occupied cells

    private final void allocateArray ( int arraySize ) {
        array = new HashEntry[arraySize];
    }

    public final boolean isEmpty(){
        return (currentSize == 0);
    }

    private final void assertFound(int currentPos, String message) throws ItemNotFound {
        if ( array[ currentPos ] == null || !array[ currentPos ].isActive )
            throw new ItemNotFound ( message );
    }

    public final void makeEmpty(){
        currentSize = 0;
        for(int i = 0; i < array.length; i++ )
            array[ i ] = null;
    }
    private static final int nextPrime(int n ) {
        if(n % 2 == 0)
            n++;
        for( ; !isPrime( n ); n += 2 );
        return n;
    }
    private
    static
    final
    boolean
    isPrime
            (
                    int
                            n ) {
        for
                (
                int
                i = 3; i * i <= n; i += 2 )
            if
                    (n % i == 0)
                return
                        false
                        ;
        return
                true
                ;
    }

    public final Hashable find(Hashable x) throws ItemNotFound {
        int currentPos = findPos(x);
        assertFound(currentPos, "PHT find");
        return array[currentPos].element;
    }

    public final void remove(Hashable x) throws ItemNotFound {
        int currentPos = findPos( x );
        assertFound( currentPos, "PHT remove" );
        array[currentPos].isActive = false;
        currentSize--;
        return;
    }

    public final void insert(Hashable x){
        int currentPos = findPos(x);
        array[currentPos] = new HashEntry(x, true);
        if (++currentSize < array.length / 2)
            return;
        HashEntry[] oldArray = array;
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;
        for(int i = 0; i < oldArray.length; i++)
            if (oldArray[i] != null && oldArray[i].isActive)
                insert(oldArray[i].element);
    }
}