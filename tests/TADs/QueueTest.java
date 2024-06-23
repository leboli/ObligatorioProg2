package TADs;

import TADs.Queue.Queue;
import TADs.Stack.Stack;
import TADexceptions.EmptyQueueException;
import TADexceptions.EmptyStackException;
import TADexceptions.InvalidDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    @DisplayName("Test del método Enqueue aplicado a una lista vacia.")
    void testEnqueueOnEmptyList() throws InvalidDataException {

        Queue<Integer> newQueue = new Queue<>(null);

        newQueue.enqueue(1);

        assertEquals(1, newQueue.getHead().getValue());
    }

    @Test
    @DisplayName("Test del método Enqueue aplicado a una lista no vacía.")
    void testEnqueueOnNonEmptyList() throws InvalidDataException {

        Queue<Integer> newQueue = new Queue<>(new Node<>(1));

        newQueue.enqueue(2);

        assertEquals(2, newQueue.getHead().getValue());
    }

    @Test
    @DisplayName("Test del método Dequeue aplicado a una lista vacia")
    void testDequeueOnEmptyList() {
        Queue<Integer> miQueue = new Queue<>(null);
        assertThrows(EmptyQueueException.class, () -> miQueue.dequeue());
    }

    @Test
    @DisplayName("Test del método Dequeue aplicaado a una lista no vacía.")
    void testDequeueOnNonEmptyList() throws InvalidDataException, EmptyQueueException {

        Queue<Integer> newQueue = new Queue<>(new Node<>(1));

        newQueue.enqueue(2);

        newQueue.dequeue();

        assertEquals(null, newQueue.getHead().getNext());

        newQueue.dequeue();

        assertEquals(null, newQueue.getHead());
    }

    @Test
    void isEmpty() {
        Queue<Integer> emptyQueue = new Queue<>(null);
        Queue<Integer> newQueue = new Queue<>(new Node<>(1));

        assertTrue(emptyQueue.isEmpty());
        assertFalse(newQueue.isEmpty());
    }

}