package TADs.Stack;

import TADs.Node;
import TADexceptions.EmptyStackException;

public class Stack<T> implements MyStack<T> {
    // Atributos
    private Node<T> head;
    private int length = 0;


    // Constructor
    public Stack(Node<T> head) {
        this.head = head;
        if (head != null) { length++; }
    }


    // Getter y Setter
    public Node<T> getHead() {
        return this.head;
    }

    public void setHead(Node<T> head) {
        if (this.head == null) { length++; }
        this.head = head;
    }

    public int getLength() {
        return length;
    }
    // ------------------------------------------------- STACK ----------------------------------------------------
    @Override
    public void pop() throws EmptyStackException { //hola
        if (head == null) { throw new EmptyStackException(); }
        this.setHead(this.head.getNext());
        length--;
    }

    @Override
    public T top() throws EmptyStackException {
        if (head == null) { throw new EmptyStackException(); }
        return head.getValue();
    }

    @Override
    public void push(T element) {
        if (head == null) {
            this.head = new Node<>(element);
        } else {
            Node<T> newNode = new Node<>(element);
            newNode.setNext(head);
            this.head = newNode;
        }
        length++;
    }

    // todo probar en tests
    @Override
    public boolean isEmpty() {
        if (length == 0) { return true; }
        return false;
    }
}
