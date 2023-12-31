package use_case.unUsedTextPokemonGame;

import entity.PlayerorAiPokemons;
import entity.Pokemon;
import use_case.CreatePlayersTest;
import use_case.PokemonFactoryFromData;
import view.BattleView;
import view.BattleViewInterface;
import use_case.RunGameOutput;

public class InitializeTestGameState {
    private static BattleViewInterface battleView;
    private RunGameOutput gameOutput;

    /**
     * This method initializes the game state for testing purposes.
     *
     * @param factory         The PokemonFactoryFromData instance.
     * @param allPokemonNames An array of all Pokemon names.
     */
    public InitializeTestGameState(PokemonFactoryFromData factory, String[] allPokemonNames) {
        PlayerorAiPokemons player = CreatePlayersTest.makeTestPlayer(factory);
        PlayerorAiPokemons playerAi = CreatePlayersTest.makeTestPlayerAi(factory);
        // BattleViewInterface battleView = new BattleView();
        // RunGame game = new RunGame(player, playerAi, battleView);
    }
}
