package use_case;

import entity.PlayerPokemons;
import entity.Pokemon;

public class GameState {
    public String turn = "Player";

    public PlayerPokemons player;
    public PlayerPokemons aiPlayer;

    public String getGameState() {
        return turn;
    }

    public String changeTurn() {
        if (turn.equals("Player")) {
            turn = "AI Player";
        } else {
            turn = "Player";
        }
        return turn;
    }

    public GameState(PokemonFactoryFromData factory, String[] allPokemonNames) {
        CreateAllPokemons allPokemons = new CreateAllPokemons(factory, allPokemonNames);
        Pokemon[] allPokemonsObjects = allPokemons.CreatePokemons();

        CreatePlayers createPlayers = new CreatePlayers(allPokemonNames, allPokemonsObjects);
//        PlayerPokemons[] players = createPlayers.returnPlayers();

        PlayerPokemons player = new createPlayers.returnPlayer();
        PlayerPokemons aiPlayer = new createPlayers.returnAI();

//        System.out.println(player.getName());
//        System.out.println(aiPlayer.getName());
        RunGame playGame = new RunGame(player, aiPlayer);
    }

}