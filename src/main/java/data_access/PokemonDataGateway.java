package data_access;

import entity.Pokemon;

public interface PokemonDataGateway {
    /**
     * Fetches Pokemon data for a given Pokemon name.
     *
     * @param pokemonName The name of the Pokemon to fetch.
     * @return A Pokemon object containing the fetched data.
     * @author Tyseer Toufiq
     */
    Pokemon fetchPokemonData(String pokemonName);
}
