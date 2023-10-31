package use_case;

import entity.Pokemon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonUseCase {

    public class PokemonDataExtractor {
        public static Map<String, Object> extractFields(JSONArray jsonArray) {
            // Define the keys to retrieve from the JSON data
            List<String> keysToRetrieve = Arrays.asList("name", "baseStats");

            // Extract and store specific fields
            Map<String, Object> extractedFields = new HashMap<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                for (String key : keysToRetrieve) {
                    if (jsonObject.has(key)) {
                        Object value = jsonObject.get(key);

                        if (value instanceof String && ((String) value).startsWith("{")) {
                            try {
                                JSONObject nestedJSON = new JSONObject((String) value);
                                extractedFields.put(key, nestedJSON);
                            } catch (JSONException e) {
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
    }


    public static Pokemon createPokemon(Map<String, Object> extractedFields) {
        Pokemon pokemon = new Pokemon();

        // Set name from extracted fields
        if (extractedFields.containsKey("name")) {
            pokemon.setName((String) extractedFields.get("name"));
        }

        // Extract baseStats as a JSON object
        if (extractedFields.containsKey("baseStats")) {
            JSONObject baseStatsJSON = (JSONObject) extractedFields.get("baseStats");
            if (baseStatsJSON != null) {
                pokemon.setHp(baseStatsJSON.optInt("hp"));
                pokemon.setAttack(baseStatsJSON.optInt("attack"));
                pokemon.setDefense(baseStatsJSON.optInt("defense"));
            }
        }

        return pokemon;
    }


    public static Map<String, Object> extractFields(JSONArray jsonArray, List<String> keys) {
        // Initialize a map to store the extracted fields
        Map<String, Object> result = new HashMap<>();

        // Iterate through the JSON array
        for (int i = 0; i < jsonArray.length(); i++) {
            // Get the JSON object at the current index
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            // Iterate through the list of keys
            for (String key : keys) {
                // Check if the JSON object contains the specified key
                if (jsonObject.has(key)) {
                    // Extract the value associated with the key
                    Object value = jsonObject.get(key);

                    // Check if the value is a JSON object represented as a string
                    if (value instanceof String && ((String) value).startsWith("{")) {
                        try {
                            // Convert the string representation to a JSONObject
                            JSONObject nestedJSON = new JSONObject((String) value);
                            result.put(key, nestedJSON);
                        } catch (JSONException e) {
                            // Handle any JSON parsing exceptions if needed
                            e.printStackTrace();
                        }
                    } else {
                        // Add the value to the result map
                        result.put(key, value);
                    }
                }
            }
        }

        // Return the map containing the extracted fields and values
        return result;
    }

}