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
        // Constructor
        CreateAllPokemons allPokemons = new CreateAllPokemons(factory, allPokemonNames);
        Pokemon[] allPokemonsObjects = allPokemons.CreatePokemons();  // call method

        // Constructor
        CreatePlayers createPlayers = new CreatePlayers(allPokemonNames, allPokemonsObjects);
        PlayerPokemons[] players = createPlayers.returnPlayers();  // call method

        PlayerPokemons player = players[0];
        PlayerPokemons aiPlayer = players[1];

        RunGame playGame = new RunGame(player, aiPlayer);
    }

}