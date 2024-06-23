package TADs;

import TADs.Hash.ClosedHash;
import TADexceptions.EmptyHashtableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosedHashTest {
    private ClosedHash<Integer, String> voidHash;
    private ClosedHash<Integer, String> emptyHash;
    private ClosedHash<Integer, String> myHash;
    private ClosedHash<Integer, String> fullHash;

    @BeforeEach
    void setUp() {
        voidHash = new ClosedHash<>(0);

        emptyHash = new ClosedHash<>(11);

        myHash = new ClosedHash<>(3);
        myHash.put(27, "Lucia");
        assertEquals("Lucia", myHash.getHash().getFirst().getValue());

        fullHash = new ClosedHash<>(3);
        fullHash.put(0, "Lucia");
        fullHash.put(1, "Lucas");
        fullHash.put(2, "Kenya");
    }

    @Test
    void getCapacity() {
        // Como 0 no es numero primo, me va a buscar el siguiente primo al contruir el Hash
        assertEquals(2, voidHash.getCapacity());
        assertEquals(11, emptyHash.getCapacity());
    }

    @Test
    void getLoadFactor() {
        assertEquals(0, voidHash.getLoadFactor());
        assertEquals(0, emptyHash.getLoadFactor());
    }


    @Test
    void getHash() {
        assertNotNull(voidHash.getHash());
        assertNotNull(emptyHash.getHash());
    }


    @Test
    @DisplayName("Testeo del put en un hash vacio")
    void testPutOnNonEmptyHashVacio() {
        emptyHash.put(9, "Lucas");
        assertEquals("Lucas", emptyHash.getHash().get(9).getValue());
    }

    @Test
    @DisplayName("Testeo del put con colisones")
    void testPutOnNonEmptyHashColision() {
        myHash.put(9, "Lucas");
        assertEquals("Lucas", myHash.getHash().get(1).getValue());
    }

    @Test
    @DisplayName("Testeo del put sin colisones")
    void testPutOnNonEmptyHashNOColision() {
        myHash.put(2, "Lucas");
        assertEquals("Lucas", myHash.getHash().get(2).getValue());
    }

    @Test
    @DisplayName("Test de put con resize")
    void testPutWithResize() {
        fullHash.put(3, "Rocky");
        assertEquals("Rocky", fullHash.getHash().get(3).getValue());
        assertEquals(5, fullHash.getCapacity());
        assertEquals(4, fullHash.getLoadFactor());
    }

    @Test
    @DisplayName("Test del metodo contains")
    void testContains() {
        assertTrue(fullHash.contains(0));
        assertFalse(fullHash.contains(6));
    }

    @Test
    @DisplayName("Test de remove aplicado a una lista vacia")
    void testRemoveFromEmptyHashTable() throws EmptyHashtableException {
        assertThrows(EmptyHashtableException.class, () -> emptyHash.remove(0));
    }

    @Test
    @DisplayName("Test de remove aplicado a una lista no vacia")
    void testRemoveFromNonEmptyHashTable() throws EmptyHashtableException {
        fullHash.remove(0);
        System.out.println(fullHash.getHash());
        assertFalse(fullHash.contains(0));
    }
}