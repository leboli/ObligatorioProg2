
package TADs.Queue;

import TADexceptions.EmptyQueueException;
import TADexceptions.InvalidDataException;

public interface MyQueue <T> {
    void enqueue (T element) throws InvalidDataException;
    T dequeue () throws EmptyQueueException;
    boolean isEmpty();
}

