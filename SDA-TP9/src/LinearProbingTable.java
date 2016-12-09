/**
 * @author Maël Cattin
 */

public class LinearProbingTable extends ProbingHashTable {
    @Override
    protected final int findPos(Hashable x) {
        return findPosMiss(x)[0];
    }


    /*
     * @param x element to search for
     * @return array with position as first element and miss number as second element
     */
    protected final int[] findPosMiss(Hashable x){
        int currentPos = x.hash(array.length);
        int miss = 0;
        while(array[currentPos] != null && !array[currentPos].element.equals(x)) {
            miss++;
            currentPos++;
            currentPos %= array.length;
        }
        return new int[]{currentPos, miss};
    }
}
