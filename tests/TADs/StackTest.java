package TADs;

import TADs.Stack.Stack;
import TADexceptions.EmptyStackException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    @DisplayName("Test del metodo Pop aplicado a un stack vacio")
    void testPopOnEmptyStack() {
        Stack<Integer> miStack = new Stack<>(null);
        assertThrows(EmptyStackException.class, () -> miStack.pop());

    }
    @Test
    @DisplayName("Test del metodo Pop aplicado a un stack no vacio")
    void testPopOnNonEmptyStack() throws EmptyStackException {
        Node<Integer> node0 = new Node<Integer>(1);
        Node<Integer> node1 = new Node<Integer>(4);
        Node<Integer> node2 = new Node<Integer>(2);

        Stack<Integer> miStack = new Stack<>(node0);
        node0.setNext(node1);
        node1.setNext(node2);

        miStack.pop();
        assertEquals(4, miStack.top());
    }

    @Test
    @DisplayName("Test del metodo Top aplicado a una lista vacia")
    void testTopOnEmptyList() {
        Stack<Integer> miStack = new Stack<>(null);
        assertThrows(EmptyStackException.class, () -> miStack.top());
    }
    @Test
    @DisplayName("Test del metodo Top aplicado a una lista no vacia")
    void testTopOnNonEmptyList() throws EmptyStackException {
        Node<Integer> node0 = new Node<Integer>(1);
        Node<Integer> node1 = new Node<Integer>(4);
        Node<Integer> node2 = new Node<Integer>(2);

        Stack<Integer> miStack = new Stack<>(node0);
        node0.setNext(node1);
        node1.setNext(node2);

        assertEquals(1, miStack.top());
    }

    @Test
    @DisplayName("Test del metodo push aplicado a una lista vacia")
    void testPushOnEmptyList() {
        Stack<Integer> miStackVacio = new Stack<>(null);
        assertEquals(0, miStackVacio.getLength());
        miStackVacio.push(10);
        assertEquals(10, miStackVacio.getHead().getValue());
        assertEquals(1, miStackVacio.getLength());
    }

    @Test
    @DisplayName("Test del metodo push aplicado a una lista no vacia")
    void testPushOnNonEmptyList() {
        Stack<Integer> miStack = new Stack<>(null);
        miStack.push(10);
        miStack.push(20);
        assertEquals(20, miStack.getHead().getValue());
        assertEquals(2, miStack.getLength());
    }

    @Test
    void isEmpty() {
        Stack<Integer> miStackVacio = new Stack<>(null);
        Stack<Integer> miStack = new Stack<>(null);
        miStack.push(10);

        assertTrue(miStackVacio.isEmpty());
        assertFalse(miStack.isEmpty());
    }

}