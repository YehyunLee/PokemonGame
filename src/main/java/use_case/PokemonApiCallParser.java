package use_case;

import data_access.PokemonApiCallInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code PokemonApiCallParser} class provides methods for fetching and parsing Pokemon data from an external API.
 */
public class PokemonApiCallParser {

    private final PokemonApiCallInterface apiDataAccess;

    /**
     * Constructs a new instance of {@code PokemonApiCallParser}.
     *
     * @param apiDataAccess The data access interface for fetching Pokemon data.
     */
    public PokemonApiCallParser(PokemonApiCallInterface apiDataAccess) {
        this.apiDataAccess = apiDataAccess;
    }

    /**
     * Fetches Pokemon data for the specified Pokemon name.
     *
     * @param pokemonName The name of the Pokemon to fetch.
     * @return A map containing Pokemon data, or an empty map if not found.
     */
    public Map<String, Object> fetchPokemonData(String pokemonName) {
        JSONArray data = apiDataAccess.fetchRawPokemonData(pokemonName);
        return parsePokemonData(String.valueOf(data));
    }

    /**
     * Parses JSON data representing Pokemon information and extracts relevant data into a map.
     *
     * @param jsonData The JSON data to parse.
     * @return A map containing parsed Pokemon data.
     */
    public Map<String, Object> parsePokemonData(String jsonData) {
        Map<String, Object> dataMap = new HashMap<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            if (!jsonArray.isEmpty()) {
                JSONObject pokemonData = jsonArray.getJSONObject(0);

                dataMap.put("name", pokemonData.optString("name"));
                dataMap.put("number", pokemonData.optInt("number"));

                JSONObject baseStats = pokemonData.optJSONObject("baseStats");
                if (baseStats != null) {
                    dataMap.put("hp", baseStats.optInt("hp"));
                    dataMap.put("attack", baseStats.optInt("attack"));
                    dataMap.put("defense", baseStats.optInt("defense"));
                    dataMap.put("speed", baseStats.optInt("speed"));
                }
            }
        } catch (JSONException e) {
            // Handle JSON parsing error
            System.err.println("Error parsing JSON data: " + e.getMessage());
        }

        return dataMap; // Return the dataMap
    }
}
