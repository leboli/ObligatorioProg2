package TADs.Hash;

import TADexceptions.EmptyHashtableException;
import TADexceptions.EntityDoesntExist;

public interface HashTable<K, V> {
    public void put(K key, V value);
    public boolean contains(K key);
    public void remove(K clave) throws EmptyHashtableException;

    public V get(K key) throws EntityDoesntExist, EmptyHashtableException;
}

