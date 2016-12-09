/**
 * Created by Maël Cattin on 05.12.2016.
 */
public interface HashTable {
    void makeEmpty();

    boolean isEmpty();

    void insert(Hashable x);

    void remove(Hashable x) throws ItemNotFound;

    Hashable find(Hashable x) throws ItemNotFound;
}
