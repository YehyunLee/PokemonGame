package use_case;

import entity.PlayerPokemons;
import entity.GameState;

public class RunGame {
    public RunGame(PlayerPokemons player, PlayerPokemons aiPlayer) {

        // Initialize a new gateState to keep track of win conditions
        GameState gameSate = new GameState();
        gameSate.initializeGameState(player, aiPlayer);

        System.out.println("Prepare for Pokemon Battle!");


        // Prints Active Pokemon
        System.out.println("Player: " + gameSate.player.getActivePokemon().getName() + " I choose you!");
        System.out.println("PlayerAi: " + gameSate.aiPlayer.getActivePokemon().getName() + " I choose you!");


//        while (!gameSate.GameOver) { // While the game is not over
//
//
//
//        }




    }
}
