package use_case;

import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesInterface;
import entity.Pokemon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The {@code PokemonFactoryFromData} class is responsible for creating Pokemon objects
 * from JSON data fetched using a {@link PokemonApiCallInterface} implementation.
 */
public class PokemonFactoryFromData implements PokemonFactoryFromDataInterface {
    private final PokemonApiCallInterface apiDataAccess;
    private final PokemonListFromSpritesInterface spritesDataAccess;

    /**
     * Constructs a {@code PokemonFactoryFromData} instance with the specified data access objects.
     *
     * @param dataAccess       The data access object to fetch Pokemon data.
     * @param spritesDataAccess The data access object to fetch Pokemon sprites.
     */
    public PokemonFactoryFromData(PokemonApiCallInterface dataAccess, PokemonListFromSpritesInterface spritesDataAccess) {
        this.apiDataAccess = dataAccess;
        this.spritesDataAccess = spritesDataAccess;
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
        JSONArray jsonData = apiDataAccess.fetchPokemonData(data);

        // Parse and create a Pokemon object from the JSON data
        Pokemon pokemon = parsePokemonData(String.valueOf(jsonData));

        // Add the sprites to the Pokemon object. We pass through combinedSprites instead of calling it again.
        String[] sprites = spritesDataAccess.getCombinedListOfPokemonSprites(pokemon.getNumber());
        pokemon.setFrontSprite(sprites[0]);
        pokemon.setBackSprite(sprites[1]);

        return pokemon;
    }

    /**
     * Parses JSON data to create a Pokemon object with the required attributes.
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

                // Parse and set Pokemon number
                pokemon.setNumber(pokemonData.optInt("number"));

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
