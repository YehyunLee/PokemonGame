package use_case;

import entity.PlayerorAiPokemons;
import entity.Pokemon;

public class ViewInitilizeGameState {

    public ViewInitilizeGameState(PokemonFactoryFromData factory, String[] allPokemonNames) {
        // Constructor
        CreateAllPokemons allPokemons = new CreateAllPokemons(factory, allPokemonNames);
        Pokemon[] allPokemonsObjects = allPokemons.CreatePokemons();  // call method

        // Constructor
        CreatePlayers createPlayers = new CreatePlayers(allPokemonNames, allPokemonsObjects);
        PlayerorAiPokemons[] players = createPlayers.returnPlayers();  // call method

        PlayerorAiPokemons player = players[0];
        PlayerorAiPokemons aiPlayer = players[1];
        new RunGame(player, aiPlayer);
    }

}