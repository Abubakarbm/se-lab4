package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void constructor_SetsInitialCount() {
        TorpedoStore store = new TorpedoStore(7);
        assertEquals(7, store.getTorpedoCount());
    }

    @Test
    void fire_Success_DecreasesCount() {
        TorpedoStore store = new TorpedoStore(5, 0.0);   // never fails
        assertTrue(store.fire(1));
        assertEquals(4, store.getTorpedoCount());
    }

    @Test
    void fire_MultipleTorpedoes_DecreasesCountByAmount() {
        TorpedoStore store = new TorpedoStore(5, 0.0);
        assertTrue(store.fire(3));
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_Failure_ReturnsFalseAndKeepsCount() {
        TorpedoStore store = new TorpedoStore(5, 1.0);   // always fails
        assertFalse(store.fire(1));
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_AllRemaining_StoreBecomesEmpty() {
        TorpedoStore store = new TorpedoStore(2, 0.0);
        assertTrue(store.fire(2));
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_Zero_Throws() {
        TorpedoStore store = new TorpedoStore(5, 0.0);
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_MoreThanAvailable_Throws() {
        TorpedoStore store = new TorpedoStore(1, 0.0);
        assertThrows(IllegalArgumentException.class, () -> store.fire(2));
    }

    @Test
    void fire_FromEmptyStore_Throws() {
        TorpedoStore store = new TorpedoStore(0, 0.0);
        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }

    @Test
    void isEmpty_TrueWhenNoTorpedoes() {
        assertTrue(new TorpedoStore(0).isEmpty());
    }

    @Test
    void isEmpty_FalseWhenHasTorpedoes() {
        assertFalse(new TorpedoStore(3).isEmpty());
    }
}
