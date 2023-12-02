package use_case;
import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesInterface;
import entity.Pokemon;

public interface InitializePokemonObjectsInterface {

    /**
     * Creates Pokemon objects from JSON data.
     * @param apiDataAccess The data access object to fetch Pokemon data.
     * @param spritesDataAccess The data access object to fetch Pokemon sprites.
     * @return An array of Pokemon objects created from the JSON data.
     * */
    static Pokemon[] GetPokemonObjects(PokemonApiCallInterface apiDataAccess, PokemonListFromSpritesInterface spritesDataAccess) {
        // Create an instance of PokemonFactoryFromData and inject the data access objects
        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccess, spritesDataAccess);
        String[] allPokemonNames = factory.spriteParser.getAllPokemonNamesNoDuplicate(apiDataAccess);

        // frame.getContentPane().remove(loadingLabel);

        // Create the game state
        CreateAllPokemons allPokemons = new CreateAllPokemons(factory, allPokemonNames);
        Pokemon[] allPokemonsObjects = allPokemons.CreatePokemons();

        return allPokemonsObjects;
    }
}
