package TADs;

import java.util.Objects;

public class Node<T> {
    // Atributos
    private T value;
    private Node<T> next;


    // Constructor
    public Node(T value) {
        this.value = value;
    }


    // Setters & Getters
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }
    public void setNext(Node<T> next) {
        this.next = next;
    }


    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }


    // To String
    @Override
    public String toString() {
        return (String) value;
    }
}

