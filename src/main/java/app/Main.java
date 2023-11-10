package app;

import data_access.PokemonApiCallDataAccessObject;
import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesDataAcessObject;
import data_access.PokemonListFromSpritesInterface;
import use_case.PokemonFactoryFromData;
import entity.Pokemon;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        PokemonApiCallInterface apiDataAccess = new PokemonApiCallDataAccessObject();
        PokemonListFromSpritesInterface spritesDataAccess = new PokemonListFromSpritesDataAcessObject();

        // Create an instance of PokemonFactoryFromData and inject the data access object
        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccess, spritesDataAccess);
        String[] allPokemonNames = factory.spriteParser.getAllPokemonNamesNoDuplicate(apiDataAccess);

        // For each Pokemon name, create a Pokemon object, save Pokemon and print its stats
        Pokemon[] allPokemon = new Pokemon[allPokemonNames.length];
        for (int i = 0; i < allPokemonNames.length; i++) {
            allPokemon[i] = factory.createPokemonFromData(allPokemonNames[i]);
            allPokemon[i].printAllStats();
        }

        for (int i = 0; i < allPokemon.length; i++) {
            System.out.println(allPokemon[i].getNumber() + ": " + allPokemon[i].getName());
        }
        System.out.println("Total number of Pokemons: " + allPokemon.length);
        System.out.println("Choose 6 pokemons to battle!");
        System.out.println("Enter the number of the pokemon you want to choose: ");
        // Get user input and create a new Pokemon array to store the chosen Pokemon
        Pokemon[] chosenPokemon = new Pokemon[6];
        Scanner userInput = new Scanner(System.in);
        String pokemonNumberString;
        for (int i = 0; i < 6; i++) {
            System.out.println("Pokemon " + (i + 1) + ": ");
            pokemonNumberString = userInput.nextLine();
            // Find the pokemon with the number
            for (int j = 0; j < allPokemon.length; j++) {
                if (pokemonNumberString.equals(String.valueOf(allPokemon[j].getNumber()))) {
                    chosenPokemon[i] = allPokemon[j];
                }
            }
        }
        System.out.println("You have chosen: ");
        for (int i = 0; i < 6; i++) {
            System.out.println(chosenPokemon[i].getName());
        }
    }
}
