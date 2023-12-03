package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    // TODO: Add tests for Pokemon getMoves and printAllStats.
    private Pokemon pikachu;

    @BeforeEach
    void testPokemon() {
        pikachu = new Pokemon();
        pikachu.setName("Pikachu");
        pikachu.setHealth(100);
        pikachu.setTotalHealth(100);
        pikachu.setAttack(85);
        pikachu.setDefense(75);
        pikachu.setMoves("Thunderbolt");
        pikachu.setFrontSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png");
        pikachu.setBackSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png");
        pikachu.setNumber(25);

    }
    @Test
    void getName() {
        assertEquals("Pikachu", pikachu.getName());
    }

    @Test
    void getHealth() {
        assertEquals(100, pikachu.getHealth());
    }

    @Test
    void getTotalHealth() {
        assertEquals(100, pikachu.getTotalHealth());
    }

    @Test
    void getAttack() {
        assertEquals(85, pikachu.getAttack());
    }

    @Test
    void getDefense() {
        assertEquals(75, pikachu.getDefense());
    }

    @Test
    void testGetMoves() {
        Pokemon pokemon = new Pokemon();
        pokemon.setMoves("Attack[Light Attack: 10.0, Heavy Attack: 20.0];Defense[Light Defense: 5.0]");
        assertEquals(2, pokemon.getMoves().size());
        assertTrue(pokemon.getMoves().containsKey("Attack"));
        assertTrue(pokemon.getMoves().containsKey("Defense"));
        assertEquals(2, pokemon.getMoves().get("Attack").size());
        assertEquals(1, pokemon.getMoves().get("Defense").size());
    }
    @Test
    void testDoTotalHealing() {
        Pokemon pokemon = new Pokemon();
        pokemon.setHealth(50);
        pokemon.doTotalHealing(25.3);
        assertEquals(75, pokemon.getHealth());
    }
    @Test
    void getMoves() {
    }

    @Test
    void getFrontSprite() {
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png", pikachu.getFrontSprite());
    }

    @Test
    void getBackSprite() {
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png", pikachu.getBackSprite());
    }

    @Test
    void getNumber() {
        assertEquals(25, pikachu.getNumber());
    }

    @Test
    void getIsAlive() {
        assertEquals(true, pikachu.getIsAlive());
    }

    @Test
    void takeDammage() {
        pikachu.takeDammage(10.0);
        assertEquals(90, pikachu.getHealth());

    }

    @Test
    void doHealing() {
        pikachu.doHealing(10.0);
        assertEquals(110, pikachu.getHealth());
    }

    @Test
    void increaseDefence() {
        pikachu.increaseDefence(10.0);
        assertEquals(85, pikachu.getDefense());
    }

    @Test
    void die() {
        pikachu.die();
        assertEquals(false, pikachu.getIsAlive());
    }

    @Test
    void printAllStats() {

    }
}