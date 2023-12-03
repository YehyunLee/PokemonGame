package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code PlayerorAiPokemons} class represents a player or AI entity in our Pokémon game, containing information
 * about the type, an array of Pokémon, and the current active Pokémon.
 */
public class PlayerorAiPokemons {

    /** The type of the player or AI entity. */
    public String type = "";

    /** An array containing the Pokémon owned by the player or AI entity. */
    public Pokemon[] pokemons = new Pokemon[0];

    /** The index of the first Pokémon in the array. */
    private int firstPokemon = 0;

    /** The index of the currently active Pokémon. */
    private int currentPokemonIndex = 0;

    /**
     * Constructs a new {@code PlayerorAiPokemons} object with the specified array of Pokémon, type, and the index
     * of the first Pokémon.
     *
     * @param pokemons The array of Pokémon owned by the player or AI entity.
     * @param type The type of the player or AI entity.
     * @param firstPokemon The index of the first Pokémon in the array.
     */
    public PlayerorAiPokemons(Pokemon[] pokemons, String type, int firstPokemon) {
        this.type = type;
        this.pokemons = pokemons;
        this.firstPokemon = firstPokemon;
        this.currentPokemonIndex = firstPokemon;
    }

    /**
     * Checks if the Pokémon at the specified index is alive.
     *
     * @param index The index of the Pokémon to check.
     * @return {@code true} if the Pokémon is alive, {@code false} otherwise.
     */
    public Boolean isSwapPokemonAlive(int index) {
        return pokemons[index].getIsAlive();
    }

    /**
     * Sets the currently active Pokémon to the one at the specified index.
     *
     * @param index The index of the Pokémon to set as active.
     */
    public void SwapActivePokemon(int index) {
        currentPokemonIndex = index;
    }

    /**
     * Gets the currently active Pokémon.
     *
     * @return The currently active Pokémon.
     */
    public Pokemon getActivePokemon() {
        return pokemons[currentPokemonIndex];
    }

    /**
     * Checks if all Pokémon owned by the player or AI entity are dead.
     *
     * @return {@code true} if all Pokémon are dead, {@code false} otherwise.
     */
    public Boolean isAllDead() {
        int counter = 0;
        for (Pokemon p : pokemons)  {
            if (!p.isAlive) {
                counter += 1;
            }
        }
        return (counter == 6);
    }

    /**
     * Gets the index of the currently active Pokémon.
     *
     * @return The index of the currently active Pokémon.
     */
    public int getCurrentPokemonIndex() {
        return currentPokemonIndex;
    }

    /**
     * Gets the type of the player or AI entity.
     *
     * @return The type of the player or AI entity.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets all the alive pokemons indicies
     */
    public List<Integer> getAlivePokemonIndices() {
        List<Integer> aliveIndices = new ArrayList<>();
        for (int i = 0; i < pokemons.length; i++) {
            if (pokemons[i].getIsAlive()) {
                aliveIndices.add(i);
            }
        }
        return aliveIndices;
    }

}
