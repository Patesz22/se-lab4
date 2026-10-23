package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    private TorpedoStore store;

    @BeforeEach
    void setUp() {
        store = new TorpedoStore(10, 0.0);
    }

    @Test
    void fire_Success() {
        // Act
        boolean result = store.fire(1);

        // Assert
        assertTrue(result);
        assertEquals(9, store.getTorpedoCount());
    }

    @Test
    void fire_Failure_AlwaysFailsWhenFailureRateIsOne() {
        // Arrange
        TorpedoStore failingStore = new TorpedoStore(10, 1.0);

        // Act
        boolean result = failingStore.fire(1);

        // Assert
        assertFalse(result);
        assertEquals(10, failingStore.getTorpedoCount());
    }

    @Test
    void fire_InvalidTorpedoCount_TooHigh_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(11);
        });
    }

    @Test
    void fire_InvalidTorpedoCount_ZeroOrNegative_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(-1);
        });
    }

    @Test
    void isEmpty_WhenTorpedosAvailable_ReturnsFalse() {
        assertFalse(store.isEmpty());
    }

    @Test
    void isEmpty_WhenNoTorpedos_ReturnsTrue() {
        TorpedoStore emptyStore = new TorpedoStore(0, 0.0);
        assertTrue(emptyStore.isEmpty());
    }

    @Test
    void getTorpedoCount_ReturnsCorrectValue() {
        assertEquals(10, store.getTorpedoCount());
        
        store.fire(3);
        assertEquals(7, store.getTorpedoCount());
    }

    @Test
    void constructor_SingleArgument_InitializesCountCorrectly() {
        TorpedoStore singleArgStore = new TorpedoStore(5);
        
        assertEquals(5, singleArgStore.getTorpedoCount());
        assertFalse(singleArgStore.isEmpty());
    }

}