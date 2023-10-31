package use_case;

import entity.Pokemon;
import org.json.JSONArray;
import java.util.Map;

public interface PokemonUseCaseInterface {
    Pokemon createPokemon(Map<String, Object> extractedData);
}

