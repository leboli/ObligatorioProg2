
package TADs.Hash;

import TADexceptions.EmptyHashtableException;
import TADexceptions.EntityDoesntExist;
import other.FuncionesNumericas;

import java.util.ArrayList;

import static other.FuncionesNumericas.*;

public class ClosedHash<K,V> implements HashTable<K,V> {
    // ---------------------------------- ATRIBUTOS -----------------------------------------
    private int capacity;
    private int load = 0;
    private ArrayList<CeldaHash<K,V>> hash;


    // --------------------------------- CONSTRUCTOR ----------------------------------------
    public ClosedHash(int initialCapacity) {

        if (!esPrimo(initialCapacity)) { // Si no es primo busco al proximo
            initialCapacity = encontrarPrimoCercanoMayor(initialCapacity);
        }
        this.capacity = initialCapacity;
        this.hash = new ArrayList<>(initialCapacity);
        for (int i = 0; i < capacity; i++) {
            hash.add(null);
        }
    }


    // ------------------------------ SETTERS & GETTERS -------------------------------------
    public int getCapacity() {
        return capacity;
    }
    private void setCapacity(int capacity) { this.capacity = capacity; }

    public int getLoadFactor() {
        return load;
    }
    private void incrementLoad() {
        load++;
    }
    private void decrementLoad() {
        load--;
    }

    private void clearLoad() {
        load = 0;
    }

    public ArrayList<CeldaHash<K,V>> getHash() {
        return hash;
    }
    private void setHash(ArrayList<CeldaHash<K, V>> hash) {
        this.hash = hash;
    }

    // ----------------------------------- METODOS ------------------------------------------
    @Override
    public void put(K key, V value) {
        if (((double) load /capacity) > 0.75) {
            int newCapacity = FuncionesNumericas.encontrarPrimoCercanoMayor(capacity);
            ArrayList<CeldaHash<K,V>> newHash = new ArrayList<>(newCapacity);
            for (int i = 0; i < newCapacity; i++) {
                newHash.add(null);
            }

            this.reSize(newHash, newCapacity);
        }
        int index = key.hashCode()%capacity;
        index = Math.abs(index);
        this.insert(key, value, index);
    }


    private void insert(K key, V value, int index) {
        // me fijo si el valor es nulo o el valor esta borrado
        if (hash.get(index) == null || !(hash.get(index).isState())) {
            hash.set(index, new CeldaHash<>(key, value));
        } else {
            try {
                this.insert(key, value, index + 1);
            } catch (IndexOutOfBoundsException e) {
                index = 0;
                this.insert(key, value, index);
            }
        }
        this.incrementLoad();
    }

    private void reSize(ArrayList<CeldaHash<K,V>> newHash, int newCapacity) {
        ArrayList<CeldaHash<K,V>> oldHash = hash;
        this.clearLoad();
        for (int i = 0; i < capacity; i++) {
            CeldaHash<K,V> miCelda = oldHash.get(i);
            this.setHash(newHash);
            if (miCelda != null && miCelda.isState()) {
                insert(miCelda.getKey(), miCelda.getValue(), Math.abs(miCelda.getKey().hashCode()%newCapacity));
            }
        }
        this.setCapacity(newCapacity);
    }

    @Override
    public boolean contains(K key) {

        int index = key.hashCode()%capacity;
        index = Math.abs(index);
        CeldaHash<K,V> auxCell = new CeldaHash<>(key, null);

        if (hash.get(index) == null) {
            return false;
        }
        for (int i = index; i < capacity ; i++) {
            if ((hash.get(i) != null) && (hash.get(i).equals(auxCell))) {
                return true;
            }
        }
        for (int i = 0; i < index; i++) {
            if ((hash.get(i) != null) && (hash.get(i).equals(auxCell))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(K key) throws EmptyHashtableException {
        if (load == 0) { throw new EmptyHashtableException(); }

        int index = key.hashCode()%capacity;
        CeldaHash<K,V> auxCell = new CeldaHash<>(key, null);
        for (int i = 0; i < capacity; i++) {
            CeldaHash<K,V> myCell = hash.get(index);
            if (myCell.equals(auxCell)) {
                myCell.setState(false);
                hash.set(index, myCell);
                decrementLoad();
                break;
            }
            index++;
        }
    }

    @Override
    public V get(K key) throws EntityDoesntExist, EmptyHashtableException {
        if (load == 0) { throw new EmptyHashtableException(); }
        if (!(this.contains(key))){
            throw new EntityDoesntExist();
        }

        int index = key.hashCode()%capacity;
        index = Math.abs(index);
        CeldaHash<K,V> auxCell = new CeldaHash<>(key, null);
        for (int i = index; i < capacity; i++) {
            CeldaHash<K,V> myCell = hash.get(i);
            if (myCell.equals(auxCell)) {
                return myCell.getValue();
            }
        }
        for (int i = 0; i < index; i++) {
            CeldaHash<K,V> myCell = hash.get(i);
            if (myCell.equals(auxCell)) {
                return myCell.getValue();
            }
        }
        return null;
    }


}

