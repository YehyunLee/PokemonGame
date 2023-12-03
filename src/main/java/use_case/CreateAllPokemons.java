package use_case;

import entity.Pokemon;

public class CreateAllPokemons implements CreateAllPokemonsInterface {
    private PokemonFactoryFromData factory;
    private String[] allPokemonNames;

    /**
     * This method saves all the Pokemon objects from the data and saves an array of Pokemon objects as a local variable.
     *
     * @param factory
     * @param allPokemonNames
     */
    public CreateAllPokemons(PokemonFactoryFromData factory, String[] allPokemonNames) {
        this.factory = factory;
        this.allPokemonNames = allPokemonNames;
    }

    /**
     * This method creates all the Pokemon objects from the data and saves an array of Pokemon objects.
     *
     * @return allPokemon
     */
    @Override
    public Pokemon[] CreatePokemons() {
        // For each Pokemon name, create a Pokemon object, save Pokemon and print its stats
        Pokemon[] allPokemon = new Pokemon[allPokemonNames.length];
        for (int i = 0; i < allPokemonNames.length; i++) {
            allPokemon[i] = factory.createPokemonFromData(allPokemonNames[i]);
        }
        return allPokemon;
    }
}
