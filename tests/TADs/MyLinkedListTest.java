package TADs;

import TADs.LinkedList.MyLinkedList;
import TADs.LinkedList.MyList;
import TADexceptions.ListaVaciaExcepcion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    @Test
    @DisplayName("Test del metodo Add aplicado a una lista vacia")
    public void testAddToEmptyList() {
        MyList<String> myList1 = new MyList<>(null);
        myList1.add("Hello");
        assertEquals("Hello", myList1.getHead().getValue());
        // Pruebo que incrementa length
        assertEquals(1, myList1.getLength());

    }

    @Test
    @DisplayName("Test del metodo Add aplicado a una lista no vacia")
    public void testAddToNonEmptyList() {
        MyList<String> myList1 = new MyList<>(null);
        myList1.add("Hello");
        myList1.add("World");
        assertEquals("World", myList1.getHead().getValue());
        // veamos que incrementa length
        assertEquals(2, myList1.getLength());

    }

    @Test
    @DisplayName("Test del metodo Remove para listas vacias")
    void testRemoveFromEmptyList() {
        MyList<String> empty = new MyList<>(null);
        assertThrows(ListaVaciaExcepcion.class, () -> empty.remove(1));
    }

    @Test
    @DisplayName("Test del metodo Remove para listas no vacias")
    void testRemoveFromNonEmptyList() throws ListaVaciaExcepcion {
        MyList<String> myList = new MyList<>(null);
        myList.add("Hola");
        myList.add("Mundo");
        myList.add("Lucia");
        myList.add("Lucas");

        // Remove head
        myList.remove(0);
        assertEquals("Lucia", myList.getHead().getValue());

        // Remove "Mundo"
        myList.remove(1);
        assertEquals("Hola", myList.getHead().getNext().getValue());

        // Exception Index out of range
        assertThrows(IndexOutOfBoundsException.class, () -> myList.remove(4));
    }

    @Test
    @DisplayName("Test del metodo Get aplicado a una lista vacia")
    void testGetFromEmptyList() {
        MyList<String> empty = new MyList<>(null);
        assertThrows(ListaVaciaExcepcion.class, () -> empty.remove(1));
    }


    @Test
    @DisplayName("Test del metodo Get aplicado a lista no vacia")
    void testGetFromNonEmptyList() throws ListaVaciaExcepcion {
        MyList<String> myList = new MyList<>(null);
        myList.add("Hola");
        myList.add("Mundo");
        myList.add("Lucia");
        myList.add("Lucas");

        // Get head
        assertEquals("Lucas", myList.get(0).getValue());

        // Get para algo que no es la head
        assertEquals("Mundo", myList.get(2).getValue());

        // Exception
        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(6));
    }

    @Test
    @DisplayName("Test del metodo Find")
    void find() {
        Node<Integer> node0 = new Node<Integer>(1);
        MyLinkedList<Integer> myList0 = new MyList<>(node0);
        myList0.add(4);
        myList0.add(2);
        myList0.add(5);
        myList0.add(3);

        assertEquals(5, myList0.find(5).getValue());
        assertEquals(node0, myList0.find(1));
        assertNull(myList0.find(10));
    }


    @Test
    @DisplayName("Test del metodo AddLast aplicado a una lista vacia")
    void addLastToEmptyList() {

        MyList<Integer> myList = new MyList<>(null);
        myList.addLast(1);
        assertEquals(1, myList.getHead().getValue());
    }

    @Test
    @DisplayName("Test del metodo AddLast aplicado a una lista no vacia")
    void addLastToEmptyNonList() {

        MyList<Integer> myList = new MyList<>(null);
        myList.addLast(1);
        myList.addLast(2);
        assertEquals(2, myList.getHead().getNext().getValue());
    }
}