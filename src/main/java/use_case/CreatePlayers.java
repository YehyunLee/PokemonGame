package use_case;

import entity.PlayerorAiPokemons;
import entity.Pokemon;

import java.util.List;
import java.util.Scanner;

/**
 * The CreatePlayers class is responsible for creating player and aiPlayer objects for the game.
 * Player represents the human player, while aiPlayer represents the AI opponent.
 */
public class CreatePlayers {
    PlayerorAiPokemons player;
    PlayerorAiPokemons aiPlayer;

    /**
     * Constructs CreatePlayers instance and creates player and aiPlayer objects for the game.
     *
     * @param allPokemonsObjectsNames An array of Pokemon names to choose from.
     * @param allPokemonsObjects      An array of Pokemon objects.
     */
    public CreatePlayers(String[] allPokemonsObjectsNames, Pokemon[] allPokemonsObjects) {
        // Display available Pokemon choices
        for (int i = 0; i < allPokemonsObjects.length; i++) {
            System.out.println(allPokemonsObjects[i].getNumber() + ": " + allPokemonsObjects[i].getName());
        }
        System.out.println("Total number of Pokemons: " + allPokemonsObjects.length);
        System.out.println("Choose 6 pokemons to battle!");
        System.out.println("Enter the number of the Pokemon you want to choose: ");

        // Get user input and create an array to store the chosen Pokemon
        Pokemon[] chosenPokemon = new Pokemon[6];
        Scanner userInput = new Scanner(System.in);
        String pokemonNumberString;
        for (int i = 0; i < 6; i++) {
            System.out.println("Pokemon " + (i + 1) + ": ");
            pokemonNumberString = userInput.nextLine();

            // Find the Pokemon with the specified number
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
        PlayerorAiPokemons player = new PlayerorAiPokemons(chosenPokemon, "Player", firstPokemonIndex);

        // Randomly choose 6 Pokemon for the AI opponent
        Pokemon[] aiPokemon = new Pokemon[6];
        for (int i = 0; i < 6; i++) {
            aiPokemon[i] = allPokemonsObjects[(int) (Math.random() * allPokemonsObjects.length)];
        }

        // Duplicate Pokemon for the AI opponent
        Pokemon[] duplicate;
        duplicate = aiPokemon.clone();
        aiPokemon = duplicate;

        // Randomly select an index for the AI opponent's first Pokemon
        PlayerorAiPokemons aiPlayer = new PlayerorAiPokemons(aiPokemon, "AI Player", (int) (Math.random() * 6));

        // Create moves for both players
        List<String> playerMoves = MovesFactory.createMoves(List.of(player.pokemons));
        List<String> aiMoves = MovesFactory.createMoves(List.of(aiPlayer.pokemons));

        // Set moves for both players' Pokemon
        for (int i = 0; i < playerMoves.size(); i++) {
            player.pokemons[i].setMoves(playerMoves.get(i));
            aiPlayer.pokemons[i].setMoves(aiMoves.get(i));
        }

        this.player = player;
        this.aiPlayer = aiPlayer;
    }

    /**
     * Returns the player and aiPlayer objects.
     *
     * @return An array containing player and aiPlayer objects.
     */
    public PlayerorAiPokemons[] returnPlayers() {
        return new PlayerorAiPokemons[]{player, aiPlayer};
    }
}
