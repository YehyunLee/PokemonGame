package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealTest {

    private Heal heal = new Heal("Pikachu's Heal Move", 10, 20);

    @Test
    void getLightHeal() {
        assertEquals(10, heal.getLightHeal());
    }

    @Test
    void getHeavyheal() {
        assertEquals(20, heal.getHeavyheal());
    }
}