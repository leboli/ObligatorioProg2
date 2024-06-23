package TADs.LinkedList;

import TADs.Node;
import TADexceptions.ListaVaciaExcepcion;

public class MyList<T> implements MyLinkedList<T> {
    // Atributos
    private Node<T> head;
    private int length = 0;


    // Constructor
    public MyList(Node<T> head) {
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


    // -------------------------------------------- LINKEDLIST -------------------------------------------------
    @Override
    public void add(T value) {
        if (head == null) {
            this.head = new Node<>(value);
        } else {
            Node<T> newNode = new Node<>(value);
            newNode.setNext(head);
            this.head = newNode;
        }
        length++;
    }

    @Override
    public void remove(int position) throws ListaVaciaExcepcion, IndexOutOfBoundsException {
        if (length == 0) {
            throw new ListaVaciaExcepcion();
        }
        if (length < position || position < 0) {
            throw new IndexOutOfBoundsException();
        }
        // Si lo que remuevo es la head
        if (position == 0) {
            this.setHead(head.getNext());
            length--;
        } else {
            Node<T> myNewNode = head;
            for (int i = 1; i < position - 1; i++) {
                myNewNode = myNewNode.getNext();
            }
            myNewNode.setNext(myNewNode.getNext().getNext());
            length--;
        }
    }


    // todo preguntar si puedo usar otras excepciones, es que sino me coincide el nombre.
    @Override
    public Node<T> get(int position) throws ListaVaciaExcepcion, IndexOutOfBoundsException {
        if (length == 0) {
            throw new ListaVaciaExcepcion();
        }
        if (length < position || position < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> myNewNode = head;
        for (int i = 0; i < position; i++) {
            myNewNode = myNewNode.getNext();
        }
        return myNewNode;
    }

    @Override
    public Node<T> find(T value) {
        Node<T> temp = this.head;
        while (temp != null && !temp.getValue().equals(value)) {
            temp = temp.getNext();
        }
        if (temp == null) {
            return null;
        } else {
            return temp;
        }
    }

    @Override
    public void addLast(T value) {
        if (head == null) {
            this.setHead(new Node<>(value));
        } else {
            Node<T> myNode = head;
            for (int i = 0; i < length; i++) {
                if (myNode.getNext() == null) {
                    myNode.setNext(new Node<>(value));
                }
                myNode = myNode.getNext();
            }
        }
    }

}