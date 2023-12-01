package use_case;
import entity.PlayerorAiPokemons;
import entity.Pokemon;
import view.BattleView;
import view.BattleViewInterface;
import use_case.RunGameOutput;

public class InitializeTestGameState {
    private static BattleViewInterface battleView;
    private RunGameOutput gameOutput;
    public InitializeTestGameState(PokemonFactoryFromData factory, String[] allPokemonNames) {
        PlayerorAiPokemons player = CreatePlayersTest.MakeTestPlayer(factory);
        PlayerorAiPokemons playerAi = CreatePlayersTest.MakeTestPlayerAi(factory);
//        BattleViewInterface battleView = new BattleView();
//        RunGame game = new RunGame(player, playerAi, battleView);

    }

}