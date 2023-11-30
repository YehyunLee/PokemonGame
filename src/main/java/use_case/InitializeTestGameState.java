package use_case;

import entity.PlayerorAiPokemons;
import view.BattleViewInterface;


public class InitializeTestGameState {

    private final BattleViewInterface battleView;

    // Did not end up using these so we can delete - Tyseer


//    public String turn = "Player";

//    public PlayerorAiPokemons player;
//    public PlayerorAiPokemons aiPlayer;

//    public String getGameState() {
//        return turn;
//    }

//    public String changeTurn() {
//        if (turn.equals("Player")) {
//            turn = "AI Player";
//        } else {
//            turn = "Player";
//        }
//        return turn;
//    }

    public InitializeTestGameState(PokemonFactoryFromData factory, String[] allPokemonNames, BattleViewInterface battleView) {
        this.battleView = battleView;

        // This is all the code to Initilize Gamestate with all the Pokemons, DONT DELETE!
//        CreateAllPokemons allPokemons = new CreateAllPokemons(factory, allPokemonNames);
//        Pokemon[] allPokemonsObjects = allPokemons.CreatePokemons();
//
//        CreatePlayers createPlayers = new CreatePlayers(allPokemonNames, allPokemonsObjects);
//        PlayerorAiPokemons[] players = createPlayers.returnPlayers();
//
//        PlayerorAiPokemons player = players[0];
//        PlayerorAiPokemons aiPlayer = players[1];

//        RunGame playGame = new RunGame(player, aiPlayer);



        PlayerorAiPokemons player = CreatePlayersTest.MakeTestPlayer(factory);
        PlayerorAiPokemons playerAi = CreatePlayersTest.MakeTestPlayerAi(factory);
        RunGame playGame = new RunGame(player, playerAi, battleView);



    }

}