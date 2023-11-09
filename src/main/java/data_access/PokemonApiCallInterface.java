package data_access;

import org.json.JSONArray;

/**
 * The {@code PokemonDataAccess} interface defines a contract for classes that fetch Pokemon data
 * from an external source and return it as a JSONArray.
 */
public interface PokemonApiCallInterface {

    /**
     * Fetches Pokemon data based on the provided Pokemon name.
     *
     * @param pokemonName The name of the Pokemon to fetch.
     * @return A JSONArray containing the response data from the external source.
     */
    JSONArray fetchRawPokemonData(String pokemonName);
}
