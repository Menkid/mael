/**
 * Created by Maël Cattin on 05.12.2016.
 */

public class QuadraticProbingTable extends ProbingHashTable {
    protected final int findPos(Hashable x) {
        int collisionNum = 0;
        int currentPos = x.hash(array.length);
        while(array[currentPos] != null && !array[currentPos].element.equals(x)) {
            currentPos += 2 * ++collisionNum - 1;
            if (currentPos >= array.length)
                currentPos -= array.length;
        }
        return currentPos;
    }
}