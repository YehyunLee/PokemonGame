package use_case;

import entity.PlayerPokemons;
import entity.Pokemon;

import java.util.Scanner;

public class CreatePlayers {
    PlayerPokemons player;
    PlayerPokemons aiPlayer;
    public CreatePlayers(String[] allPokemonsObjectsNames, Pokemon[] allPokemonsObjects) {

        for (int i = 0; i < allPokemonsObjects.length; i++) {
            System.out.println(allPokemonsObjects[i].getNumber() + ": " + allPokemonsObjects[i].getName());
        }
        System.out.println("Total number of Pokemons: " + allPokemonsObjects.length);
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
            for (int j = 0; j < allPokemonsObjects.length; j++) {
                if (pokemonNumberString.equals(String.valueOf(allPokemonsObjects[j].getNumber()))) {
                    chosenPokemon[i] = allPokemonsObjects[j];
                }
            }
        }
        System.out.println("You have chosen: ");
        for (int i = 0; i < 6; i++) {
            System.out.println(chosenPokemon[i].getName());
        }

        System.out.println("First Pokemon to play as Index: ");
        int firstPokemonIndex = userInput.nextInt();
        PlayerPokemons player = new PlayerPokemons(chosenPokemon, "Player", firstPokemonIndex);
        // Randomly choose 6 from allPokemonsObjects
        Pokemon[] aiPokemon = new Pokemon[6];
        for (int i = 0; i < 6; i++) {
            aiPokemon[i] = allPokemonsObjects[(int) (Math.random() * allPokemonsObjects.length)];
        }
        // Random index for first Pokemon
        PlayerPokemons aiPlayer = new PlayerPokemons(aiPokemon, "AI Player", (int) (Math.random() * 6));

        this.player = player;
        this.aiPlayer = aiPlayer;
    }
    public PlayerPokemons[] returnPlayers() {
        return new PlayerPokemons[] {player, aiPlayer};
    }
}
