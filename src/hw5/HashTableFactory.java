package hw5;

/**
 * Factory used for creating hash tables
 */
public final class HashTableFactory {

    public static <K, V> HashTable<K, V> create() {

        // TODO: Create a hash table based on your implementation and return it
        // i.e. return new MyHashTable<K, V>();
        return new MyHashTable<K, V>();
    }

}
