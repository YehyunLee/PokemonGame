package presenter;

import data_access.PokemonApiCallDataAccessObject;
import entity.Pokemon;
import use_case.PokemonUseCase;


public class Main {
    public static void main(String[] args) {
        PokemonUseCase pokemonUseCase = new PokemonUseCase();
        PokemonApiCallDataAccessObject dataAccessObject = new PokemonApiCallDataAccessObject(pokemonUseCase);

        // Fetch and process Pokemon data
        Pokemon pokemon = dataAccessObject.fetchPokemonData("pikachu");

        // Now you have a Pokemon object to work with
        System.out.println("Pokemon Data:");
        System.out.println("Name: " + pokemon.getName());
        System.out.println("HP: " + pokemon.getHp());
        System.out.println("Attack: " + pokemon.getAttack());
        System.out.println("Defense: " + pokemon.getDefense());
    }
}
