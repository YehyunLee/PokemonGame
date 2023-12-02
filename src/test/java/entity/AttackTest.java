package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {
    private Attack attack = new Attack("Pikachu's Attack Move", 10, 20, 30);
    @Test
    void getLightAttack() {
        assertEquals((float)10, attack.getLightAttack());
    }

    @Test
    void getTrueAttack() {
        assertEquals((float)20, attack.getTrueAttack());
    }

    @Test
    void getHeavyAttack() {
        assertEquals((float)30, attack.getHeavyAttack());
    }
}