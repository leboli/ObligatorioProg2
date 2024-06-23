
package TADs.BinaryTree;
import TADexceptions.EmptyTreeException;

import java.util.List;

public interface BinaryTree<T extends Comparable<T>> {

    void add(T oElement);

    void remove(T oElement) throws EmptyTreeException;

    boolean contains(T oElement);

    T find(T oElement);

    List<T> preOrder();

    List<T> posOrder();

    List<T> inOrder();

}

