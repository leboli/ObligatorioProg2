
package TADs.LinkedList;

import TADs.Node;
import TADexceptions.ListaVaciaExcepcion;

public interface MyLinkedList<T> {

    void add(T value);
    void remove(int position) throws ListaVaciaExcepcion, IndexOutOfBoundsException;
    Node<T> get(int position) throws ListaVaciaExcepcion, IndexOutOfBoundsException;

    Node<T> find(T value);

    public void addLast(T value);
}


