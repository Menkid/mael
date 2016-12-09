/**
 * @author Maël Cattin
 */

public class HashableImpl implements Hashable {
    private int key;

    public HashableImpl(int key){
        this.key = key;
    }

    public int getKey(){
        return key;
    }

    @Override
    public int hash(int tableSize) {
        return key % tableSize; // simple modulo
    }
}
