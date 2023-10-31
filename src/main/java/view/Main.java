package view;

import data_access.PokemonApiCallDataAccessObject;
import entity.Pokemon;
import use_case.PokemonUseCase;
import use_case.PokemonUseCaseInterface;

public class Main {
    public static void main(String[] args) {

        // Create an instance of PokemonUseCase
        PokemonUseCase pokemonUseCase = new PokemonUseCase();

        // Call createPokemonByName to fetch and create a Pokemon
        Pokemon pokemon = pokemonUseCase.createPokemonByName("venusaur");
        pokemon.printAllStats();
    }
}
