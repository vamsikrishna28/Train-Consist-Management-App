import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase14TrainConsistMgmntTest {

    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    static class PassengerBogie {
        String type;
        int capacity;

        PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.type = type;
            this.capacity = capacity;
        }
    }

    @Test
    void testException_ValidCapacityCreation() throws InvalidCapacityException {
        PassengerBogie bogie = new PassengerBogie("Sleeper", 72);
        assertNotNull(bogie);
    }

    @Test
    void testException_NegativeCapacityThrowsException() {
        assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("Sleeper", -10);
        });
    }

    @Test
    void testException_ZeroCapacityThrowsException() {
        assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("AC Chair", 0);
        });
    }

    @Test
    void testException_ExceptionMessageValidation() {
        InvalidCapacityException ex = assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("First Class", -5);
        });
        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    void testException_ObjectIntegrityAfterCreation() throws InvalidCapacityException {
        PassengerBogie bogie = new PassengerBogie("AC Chair", 55);
        assertEquals("AC Chair", bogie.type);
        assertEquals(55, bogie.capacity);
    }

    @Test
    void testException_MultipleValidBogiesCreation() throws InvalidCapacityException {
        PassengerBogie b1 = new PassengerBogie("Sleeper", 72);
        PassengerBogie b2 = new PassengerBogie("AC Chair", 55);
        PassengerBogie b3 = new PassengerBogie("First Class", 40);
        assertNotNull(b1);
        assertNotNull(b2);
        assertNotNull(b3);
    }
}
