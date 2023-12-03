package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


class PlayerorAiPokemonsTest {

    @Test
    void isSwapPokemonAlive() {
        Pokemon alivePokemon = new Pokemon();
        alivePokemon.isAlive = true;

        Pokemon deadPokemon = new Pokemon();
        deadPokemon.isAlive = false;

        PlayerorAiPokemons player = new PlayerorAiPokemons(
                new Pokemon[]{alivePokemon, deadPokemon},
                "Player",
                0
        );

        assertTrue(player.isSwapPokemonAlive(0));  // Alive Pokemon
        assertFalse(player.isSwapPokemonAlive(1)); // Dead Pokemon
    }

    @Test
    void swapActivePokemon() {
        Pokemon pokemon1 = new Pokemon();
        Pokemon pokemon2 = new Pokemon();

        PlayerorAiPokemons player = new PlayerorAiPokemons(
                new Pokemon[]{pokemon1, pokemon2},
                "Player",
                0
        );

        player.SwapActivePokemon(1);
        assertEquals(1, player.getCurrentPokemonIndex());
    }

    @Test
    void getActivePokemon() {
        Pokemon pokemon1 = new Pokemon();
        pokemon1.setName("Bulbasaur");

        PlayerorAiPokemons player = new PlayerorAiPokemons(
                new Pokemon[]{pokemon1},
                "Player",
                0
        );

        assertEquals(pokemon1, player.getActivePokemon());
    }

    @Test
    void isAllDead() {
        Pokemon deadPokemon = new Pokemon();
        deadPokemon.isAlive = false;

        PlayerorAiPokemons player = new PlayerorAiPokemons(
                new Pokemon[]{deadPokemon, deadPokemon, deadPokemon, deadPokemon, deadPokemon, deadPokemon},
                "Player",
                0
        );

        assertTrue(player.isAllDead());
    }

    @Test
    void getCurrentPokemonIndex() {
        PlayerorAiPokemons player = new PlayerorAiPokemons(
                new Pokemon[]{},
                "Player",
                0
        );

        assertEquals(0, player.getCurrentPokemonIndex());
    }

    @Test
    void getType() {
        PlayerorAiPokemons player = new PlayerorAiPokemons(
                new Pokemon[]{/* Pokemon objects */},
                "Player",
                0
        );

        assertEquals("Player", player.getType());
    }

    @Test
    void getAlivePokemonIndices() {
        Pokemon alivePokemon1 = new Pokemon();
        Pokemon alivePokemon2 = new Pokemon();
        alivePokemon1.isAlive = true;
        alivePokemon2.isAlive = true;

        Pokemon deadPokemon = new Pokemon();
        deadPokemon.isAlive = false;

        PlayerorAiPokemons player = new PlayerorAiPokemons(
                new Pokemon[]{alivePokemon1, deadPokemon, alivePokemon2},
                "Player",
                0
        );

        List<Integer> aliveIndices = player.getAlivePokemonIndices();

        assertEquals(2, aliveIndices.size());
        assertTrue(aliveIndices.contains(0)); // Index of alivePokemon1
        assertTrue(aliveIndices.contains(2)); // Index of alivePokemon2
        assertFalse(aliveIndices.contains(1)); // Index of deadPokemon
    }
}
