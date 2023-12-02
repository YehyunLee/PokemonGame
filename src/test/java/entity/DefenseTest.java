package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefenseTest {
    private Defense defense = new Defense("Pikachu's Defense Move", 10, 20);
    @Test
    void getLightDefense() {
        assertEquals((float)10, defense.getLightDefense());
    }

    @Test
    void getHeavyDefense() {
        assertEquals((float)20, defense.getHeavyDefense());
    }
}