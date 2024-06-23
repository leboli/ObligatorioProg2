
package TADs.Stack;

import TADexceptions.EmptyStackException;

public interface MyStack <T> {
    void pop () throws EmptyStackException;
    T top() throws EmptyStackException;
    void push(T element); // hola
    boolean isEmpty ();

}
