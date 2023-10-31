package use_case;

import entity.Pokemon;
import org.json.JSONArray;
import java.util.Map;

public interface PokemonUseCaseInterface {

    /**
     * Extracts specific fields from a JSON array and returns them as a map.
     *
     * @param jsonArray The JSON array to extract data from.
     * @return A map containing the extracted fields and their values.
     */
    Map<String, Object> extractFields(JSONArray jsonArray);

    /**
     * Creates a Pokemon object from the extracted fields.
     *
     * @param extractedData A map containing the extracted fields and their values.
     * @return A Pokemon object with the extracted data.
     */
    Pokemon createPokemon(Map<String, Object> extractedData);

}
