package TADs;

import TADs.BinaryTree.SearchBinaryTree;
import TADexceptions.EmptyStackException;
import TADexceptions.EmptyTreeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchBinaryTreeTest {
    private SearchBinaryTree<Integer> myEmptySBT;
    private SearchBinaryTree<Integer> mySBT;

    @BeforeEach
    void setUp() {
        myEmptySBT = new SearchBinaryTree<>();

        mySBT = new SearchBinaryTree<>();
        mySBT.add(2);
        mySBT.add(1);
        mySBT.add(3);
        mySBT.add(5);
        mySBT.add(4);
        mySBT.add(-1);

    }

    @Test
    @DisplayName("Test del metodo Add aplicado a un arbol vacio")
    void testAddToEmptyTree() {
        myEmptySBT.add(2);
        assertEquals(2, myEmptySBT.getRoot().getValue());
    }

    @Test
    @DisplayName("Test del metodo Add aplicado a un arbol no vacio")
    void testAddToNonEmptyTree() {
        myEmptySBT.add(2);
        myEmptySBT.add(1);
        myEmptySBT.add(3);

        assertEquals(1, myEmptySBT.getRoot().getLeft().getValue());
        assertEquals(3, myEmptySBT.getRoot().getRight().getValue());
    }

    @Test
    @DisplayName("Test del metodo Contains")
    void contains() {
        myEmptySBT.add(2);
        myEmptySBT.add(1);

        assertTrue(myEmptySBT.contains(1));
        assertTrue(myEmptySBT.contains(2));
        assertFalse(myEmptySBT.contains(10));
    }

    @Test
    @DisplayName("Test del metodo remove aplicado a un arbol vacio")
    void testRemoveFromEmptyTree() {
        assertThrows(EmptyTreeException.class, () -> myEmptySBT.remove(1));
    }

    @Test
    @DisplayName("Test del metodo remove aplicado a un arbol no vacio")
    void testRemoveFromNonEmptyTree() throws EmptyTreeException {
        myEmptySBT.add(2);
        myEmptySBT.add(1);
        myEmptySBT.add(3);
        myEmptySBT.add(5);
        myEmptySBT.add(4);
        myEmptySBT.add(-1);

        myEmptySBT.remove(-1);
        assertNull(myEmptySBT.getRoot().getLeft().getLeft());
        myEmptySBT.remove(2);
        assertEquals(3, myEmptySBT.getRoot().getValue());

        myEmptySBT.add(2);
        myEmptySBT.add(-1);
        myEmptySBT.remove(5);
        assertEquals(4, myEmptySBT.getRoot().getRight().getValue());
    }

    @Test
    void find() {
        myEmptySBT.add(2);
        myEmptySBT.add(1);
        assertNotNull(myEmptySBT.find(1));
        assertNull(myEmptySBT.find(5));
    }

    @Test
    void preOrder() {
        int[] valores = {2, 1, -1, 3, 5, 4};
        int j = 0;
        for (int i : valores) {
            assertEquals(i, mySBT.preOrder().get(j));
            j++;
        }
    }

    @Test
    void posOrder() {
        int[] valores = {1, -1, 3, 5, 4, 2};
        int j = 0;
        for (int i : valores) {
            assertEquals(i, mySBT.posOrder().get(j));
            j++;
        }
    }

    @Test
    void inOrder() {
        int[] valores = {-1, 1, 2, 3, 4, 5};
        int j = 0;
        for (int i : valores) {
            assertEquals(i, mySBT.inOrder().get(j));
            j++;
        }

    }
}