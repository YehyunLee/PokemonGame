package use_case;

import data_access.PokemonApiCallDataAccessObject;
import entity.Pokemon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The PokemonUseCase class provides use cases related to Pokemon data manipulation.
 * It includes methods for extracting Pokemon data from JSON, creating Pokemon objects,
 * and extracting specific fields from JSON arrays.
 *
 * @author Tyseer Toufiq
 */
public class PokemonUseCase implements PokemonUseCaseInterface {

    /**
     * Extracts specific fields from a JSON array and returns them as a map.
     *
     * @param jsonArray The JSON array to extract data from.
     * @return A map containing the extracted fields and their values.
     */
    public Map<String, Object> extractFields(JSONArray jsonArray) {
        // Define the keys to retrieve from the JSON data (We can Change this!)
        List<String> keysToRetrieve = Arrays.asList("name", "baseStats");

        // Extract and store specific fields
        Map<String, Object> extractedFields = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            for (String key : keysToRetrieve) {
                if (jsonObject.has(key)) {
                    Object value = jsonObject.get(key);

                    // Check if the value is a JSON object represented as a string
                    if (value instanceof String && ((String) value).startsWith("{")) {
                        try {
                            JSONObject nestedJSON = new JSONObject((String) value);
                            extractedFields.put(key, nestedJSON);
                        } catch (JSONException e) {
                            // Handle any JSON parsing exceptions if needed
                            e.printStackTrace();
                        }
                    } else {
                        extractedFields.put(key, value);
                    }
                }
            }
        }

        return extractedFields;
    }

    /**
     * Creates a Pokemon object from the extracted fields.
     *
     * @param extractedFields A map containing the extracted fields and their values.
     * @return A Pokemon object with the extracted data.
     */
    public Pokemon createPokemon(Map<String, Object> extractedFields) {
        Pokemon pokemon = new Pokemon();

        // Set name from extracted fields
        if (extractedFields.containsKey("name")) {
            String fullName = (String) extractedFields.get("name");

            // Split the name by whitespace into words
            String[] nameWords = fullName.split("\\s+");

            // Check if there are multiple words in the name
            if (nameWords.length > 1) {
                // Save only the last word as the name
                pokemon.setName(nameWords[nameWords.length - 1]);
            } else {
                // Save the entire name if there's only one word
                pokemon.setName(fullName);
            }
        }

        // Extract baseStats as a JSON object
        if (extractedFields.containsKey("baseStats")) {
            JSONObject baseStatsJSON = (JSONObject) extractedFields.get("baseStats");
            if (baseStatsJSON != null) {
                pokemon.setHp(baseStatsJSON.optInt("hp"));
                pokemon.setAttack(baseStatsJSON.optInt("attack"));
                pokemon.setDefense(baseStatsJSON.optInt("defense"));
                // We can Add more here if we want
            }
        }

        return pokemon;
    }

    /**
     * Creates a new Pokemon object by fetching and processing data for the given Pokemon name.
     *
     * @param pokemonName The name of the Pokemon to fetch and create.
     * @return A Pokemon object with the data of the specified Pokemon.
     */
    public Pokemon createPokemonByName(String pokemonName) {
        PokemonApiCallDataAccessObject dataAccessObject = new PokemonApiCallDataAccessObject(this);
        return dataAccessObject.fetchPokemonData(pokemonName);
    }

}
