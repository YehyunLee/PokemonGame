package use_case;

import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesInterface;
import entity.Pokemon;

import java.util.Map;

/**
 * The {@code PokemonFactoryFromData} class is responsible for creating Pokemon objects
 * from JSON data fetched using a {@link PokemonApiCallInterface} implementation.
 */
public class PokemonFactoryFromData implements PokemonFactoryFromDataInterface {
    private final PokemonApiCallInterface apiDataAccess;
    public final PokemonListFromSpritesDataParser spriteParser; // Need to reuse this 10000 times. Want to run only once

    /**
     * Constructs a {@code PokemonFactoryFromData} instance with the specified data access objects.
     *
     * @param dataAccess        The data access object to fetch Pokemon data.
     * @param spritesDataAccess The data access object to fetch Pokemon sprites.
     */
    public PokemonFactoryFromData(PokemonApiCallInterface dataAccess, PokemonListFromSpritesInterface spritesDataAccess) {
        this.apiDataAccess = dataAccess;
        this.spriteParser = new PokemonListFromSpritesDataParser(spritesDataAccess);
        spriteParser.SaveAllSprites(); // Fetch and save all sprites' data. Ran only once to maxmise efficiency.
    }

    /**
     * Creates a Pokemon object from JSON data.
     *
     * @param name The JSON data representing a Pokemon.
     * @return A Pokemon object created from the JSON data, or null if parsing fails.
     */
    @Override
    public Pokemon createPokemonFromData(String name) {

        // Make a new Pokemon Object
        Pokemon pokemon = new Pokemon();

        // Set all the Pokemon Attributes
        setPokemonAttributes(pokemon, name);

        // Set all Pokemon back and front sprites
        setPokemonSprites(pokemon, name);

        return pokemon;
    }

    /**
     * Sets Pokemon attributes by fetching data from an API.
     *
     * @param pokemon The Pokemon object to set attributes for.
     * @param name    The name of the Pokemon.
     */
    public void setPokemonAttributes(Pokemon pokemon, String name) {

        PokemonApiCallParser parser = new PokemonApiCallParser(apiDataAccess);
        Map<String, Object> apiDataList = parser.fetchPokemonData(name);

        for (Map.Entry<String, Object> entry : apiDataList.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key) {
                case "number":
                    pokemon.setNumber((Integer) value);
                    break;
                case "name":
                    pokemon.setName((String) value);
                    break;
                case "hp":
                    pokemon.setHealth((Integer) value);
                    break;
                case "attack":
                    pokemon.setAttack((Integer) value);
                    break;
                case "defense":
                    pokemon.setDefense((Integer) value);
                    break;
                case "speed":
                    pokemon.setSpeed((Integer) value);
                default:
                    break;
            }
        }
    }

    /**
     * Sets Pokemon sprites by fetching data from a sprite source.
     *
     * @param pokemon The Pokemon object to set sprites for.
     * @param name    The name of the Pokemon.
     */
    public void setPokemonSprites(Pokemon pokemon, String name) {
        Map<String, String> spriteDataList = spriteParser.fetchPokemonSpritesData(pokemon.getNumber());

        for (Map.Entry<String, String> entry : spriteDataList.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key) {
                case "frontSprite":
                    pokemon.setFrontSprite((String) value);
                    break;
                case "backSprite":
                    pokemon.setBackSprite((String) value);
                    break;
                default:
                    break;
            }
        }
    }
}
