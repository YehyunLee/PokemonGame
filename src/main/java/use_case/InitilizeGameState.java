package use_case;

import entity.PlayerorAiPokemons;
import entity.Pokemon;

public class InitilizeGameState {
    public String turn = "Player";

    public PlayerorAiPokemons player;
    public PlayerorAiPokemons aiPlayer;

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

    public InitilizeGameState(PokemonFactoryFromData factory, String[] allPokemonNames) {
        // Constructor
        CreateAllPokemons allPokemons = new CreateAllPokemons(factory, allPokemonNames);
        Pokemon[] allPokemonsObjects = allPokemons.CreatePokemons();  // call method

        // Constructor
        CreatePlayers createPlayers = new CreatePlayers(allPokemonNames, allPokemonsObjects);
        PlayerorAiPokemons[] players = createPlayers.returnPlayers();  // call method

        PlayerorAiPokemons player = players[0];
        PlayerorAiPokemons aiPlayer = players[1];

        RunGame playGame = new RunGame(player, aiPlayer);
    }

}