
package TADs.Hash;

import java.util.Objects;

public class CeldaHash<K,V> {
    // ---------------------------------- ATRIBUTOS -----------------------------------------
    private K key;
    private V value;
    private boolean state; // false si fue eliminado, true en caso contrario


    // --------------------------------- CONSTRUCTOR ----------------------------------------
    public CeldaHash(K key, V value) {
        this.key = key;
        this.value = value;
        this.state = true;
    }


    // ------------------------------ SETTERS & GETTERS -------------------------------------
    public K getKey() { return key; }
    public void setKey(K key) { this.key = key; }

    public V getValue() { return value; }
    public void setValue(V value) { this.value = value; }

    public boolean isState() { return state; }
    public void setState(boolean state) { this.state = state; }


    // ----------------------------------- METODOS ------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CeldaHash<?, ?> hashCell = (CeldaHash<?, ?>) o;
        return Objects.equals(key, hashCell.key) && state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "HashCell{" +
                "key=" + key +
                ", value=" + value +
                ", state=" + state +
                '}';
    }
}

