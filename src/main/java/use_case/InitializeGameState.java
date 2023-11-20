package use_case;

import entity.PlayerorAiPokemons;
import entity.Pokemon;


public class InitializeGameState {
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

    public InitializeGameState(PokemonFactoryFromData factory, String[] allPokemonNames) {
        CreateAllPokemons allPokemons = new CreateAllPokemons(factory, allPokemonNames);
        Pokemon[] allPokemonsObjects = allPokemons.CreatePokemons();

        CreatePlayers createPlayers = new CreatePlayers(allPokemonNames, allPokemonsObjects);
        PlayerorAiPokemons[] players = createPlayers.returnPlayers();

        PlayerorAiPokemons player = players[0];
        PlayerorAiPokemons aiPlayer = players[1];


        RunGame playGame = new RunGame(player, aiPlayer);
    }

}