package use_case;
import entity.Pokemon;

/**
 * The {@code PokemonFactoryFromDataInterface} interface defines a contract for classes
 * that create Pokemon objects from JSON data.
 */
public interface PokemonFactoryFromDataInterface {
    /**
     * Creates a Pokemon object from JSON data.
     *
     * @param data The JSON data representing a Pokemon.
     * @return A Pokemon object created from the JSON data, or null if parsing fails.
     */

    Pokemon createPokemonFromData(String data);
}
