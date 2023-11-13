package use_case;

import entity.Pokemon;

public class CreateAllPokemons {
    private PokemonFactoryFromData factory;
    private String[] allPokemonNames;

    public CreateAllPokemons(PokemonFactoryFromData factory, String[] allPokemonNames) {
        this.factory = factory;
        this.allPokemonNames = allPokemonNames;
    }

    public Pokemon[] CreatePokemons() {
        // For each Pokemon name, create a Pokemon object, save Pokemon and print its stats
        Pokemon[] allPokemon = new Pokemon[allPokemonNames.length];
        for (int i = 0; i < allPokemonNames.length; i++) {
            allPokemon[i] = factory.createPokemonFromData(allPokemonNames[i]);
            allPokemon[i].printAllStats();
        }
        return allPokemon;
    }
}
