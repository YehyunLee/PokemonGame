package use_case;

import data_access.PokemonDataAccess;
import entity.Pokemon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The {@code PokemonFactoryFromData} class is responsible for creating Pokemon objects
 * from JSON data fetched using a {@link PokemonDataAccess} implementation.
 */
public class PokemonFactoryFromData implements PokemonFactoryFromDataInterface {
    private final PokemonDataAccess dataAccess;

    /**
     * Constructs a {@code PokemonFactoryFromData} instance with the specified data access object.
     *
     * @param dataAccess The data access object to fetch Pokemon data.
     */
    public PokemonFactoryFromData(PokemonDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * Creates a Pokemon object from JSON data.
     *
     * @param data The JSON data representing a Pokemon.
     * @return A Pokemon object created from the JSON data, or null if parsing fails.
     */
    @Override
    public Pokemon createPokemonFromData(String data) {
        // Fetch data using the data access object
        JSONArray jsonData = dataAccess.fetchPokemonData(data);

        // Parse and create a Pokemon object from the JSON data
        Pokemon pokemon = parsePokemonData(String.valueOf(jsonData));
        return pokemon;
    }

    /**
     * Parses JSON data to create a Pokemon object.
     *
     * @param jsonData The JSON data representing a Pokemon.
     * @return A Pokemon object created from the JSON data, or null if parsing fails.
     */
    private Pokemon parsePokemonData(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            if (!jsonArray.isEmpty()) {
                JSONObject pokemonData = jsonArray.getJSONObject(0); // Assuming the data is in the first object

                Pokemon pokemon = new Pokemon();

                // Parse and set Pokemon name
                pokemon.setName(pokemonData.optString("name"));

                // Parse and set HP, attack, and defense from baseStats
                JSONObject baseStats = pokemonData.optJSONObject("baseStats");
                if (baseStats != null) {
                    pokemon.setHealth(baseStats.optInt("hp"));
                    pokemon.setAttack(baseStats.optInt("attack"));
                    pokemon.setDefense(baseStats.optInt("defense"));
                }

                return pokemon;
            }
        } catch (JSONException e) {
            // Handle JSON parsing error
            e.printStackTrace();
        }

        return null; // Return null in case of parsing failure
    }
}
