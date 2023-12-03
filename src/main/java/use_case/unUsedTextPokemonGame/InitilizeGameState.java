package use_case.unUsedTextPokemonGame;

import entity.PlayerorAiPokemons;
import entity.Pokemon;
import use_case.CreateAllPokemons;
import use_case.CreatePlayers;
import use_case.PokemonFactoryFromData;
import view.BattleViewInterface;
import use_case.RunGameOutput;

public class InitilizeGameState {
    private static BattleViewInterface battleView;
    private RunGameOutput gameOutput;

    /**
     * This method initializes the game state. This is only used for the text-based game.
     *
     * @param factory         The PokemonFactoryFromData instance.
     * @param allPokemonNames An array of all Pokemon names.
     */
    public InitilizeGameState(PokemonFactoryFromData factory, String[] allPokemonNames) {
        // Constructor logic to initialize game state
        CreateAllPokemons allPokemons = new CreateAllPokemons(factory, allPokemonNames);
        Pokemon[] allPokemonObjects = allPokemons.CreatePokemons();  // call method

        // Constructor logic to create players
        CreatePlayers createPlayers = new CreatePlayers(allPokemonNames, allPokemonObjects);
        PlayerorAiPokemons[] players = createPlayers.returnPlayers();  // call method

        PlayerorAiPokemons player = players[0];
        PlayerorAiPokemons aiPlayer = players[1];

        // BattleViewInterface battleView = new BattleView();
        // RunGame game = new RunGame(player, aiPlayer, battleView);
    }
}
