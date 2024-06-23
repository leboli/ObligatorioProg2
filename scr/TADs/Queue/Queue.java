package TADs.Queue;

import TADs.Node;
import TADexceptions.EmptyQueueException;
import TADexceptions.InvalidDataException;

public class Queue<T> implements MyQueue<T> {
    // Atributos
    private Node<T> head;
    private int length = 0;


    // Constructor
    public Queue(Node<T> head) {
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

    // --------------------------------------- QUEUE ----------------------------------------
    @Override
    public void enqueue(T element) throws InvalidDataException {
        if (element == null) { throw new InvalidDataException(); }

        if (head == null) { setHead(new Node<T>(element)); }
        else {
            Node<T> miNodo = new Node<>(element);
            miNodo.setNext(miNodo);
            this.setHead(miNodo);
        }
        length++;
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (head == null) { throw new EmptyQueueException(); }

        if (head.getNext() == null){
            T myHead = head.getValue();
            this.setHead(null);
            return  myHead;
        } else {

            Node<T> auxNode = this.head;
            for (int i = 0; i < length - 1; i++) {
                auxNode = auxNode.getNext();
            }
            T result = auxNode.getValue();
            auxNode.setNext(null);
            return result;
        }
    }

    @Override
    public boolean isEmpty() {
        if (length == 0) { return true; }
        return false;
    }
}