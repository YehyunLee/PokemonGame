package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovesTest {

    @Test
    void getName() {
        Moves move = new Moves("Thunderbolt");
        assertEquals("Thunderbolt", move.getName());
    }
}