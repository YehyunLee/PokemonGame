package use_case;
import entity.PlayerorAiPokemons;
import entity.Pokemon;
import view.BattleView;
import view.BattleViewInterface;
import use_case.RunGameOutput;

public class InitilizeGameState {

    private static BattleViewInterface battleView;
    private RunGameOutput gameOutput;

    /**
     * This method initializes the game state. This is only used for text-based game only.
     * @param factory
     * @param allPokemonNames
     */
    public InitilizeGameState(PokemonFactoryFromData factory, String[] allPokemonNames) {


        // Constructor logic to initialize game state
        CreateAllPokemons allPokemons = new CreateAllPokemons(factory, allPokemonNames);
        Pokemon[] allPokemonsObjects = allPokemons.CreatePokemons();  // call method

        // Constructor logic to create players
        CreatePlayers createPlayers = new CreatePlayers(allPokemonNames, allPokemonsObjects);
        PlayerorAiPokemons[] players = createPlayers.returnPlayers();  // call method

        PlayerorAiPokemons player = players[0];
        PlayerorAiPokemons aiPlayer = players[1];

//        BattleViewInterface battleView = new BattleView();
//        RunGame game = new RunGame(player, aiPlayer, battleView);
    }

}