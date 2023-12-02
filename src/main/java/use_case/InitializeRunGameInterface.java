package use_case;

import entity.PlayerorAiPokemons;
import entity.Pokemon;
import view.BattleView;
import view.BattleViewInterface;
import javax.swing.*;
import java.util.List;

public interface InitializeRunGameInterface {
    /**
     * This method initializes the game state. This create player and AI object and use movesFactory to create moves.
     * Then it runs the game.
     * */
    static void initializeRunGame(Pokemon[] allPokemons, List<Pokemon> selectedPokemonList, JFrame frame)

    {
        // convert listPokemon to Pokemon[]
        Pokemon[] selectedPokemonListArray = new Pokemon[6];
        for (int i = 0; i < 6; i++) {
            selectedPokemonListArray[i] = selectedPokemonList.get(i);
        }

        // Randomly choose 6 from allPokemonsObjects
        Pokemon[] aiPokemon = new Pokemon[6];
        for (int i = 0; i < 6; i++) {
            Pokemon saveRandomPoke = allPokemons[(int) (Math.random() * allPokemons.length)];
            if (!selectedPokemonList.contains(saveRandomPoke)) {
                aiPokemon[i] = saveRandomPoke;
            } else {
                i--;
            }
        }

        PlayerorAiPokemons player = new PlayerorAiPokemons(selectedPokemonListArray, "Player", 0);
        // Random index for first Pokemon
        PlayerorAiPokemons aiPlayer = new PlayerorAiPokemons(aiPokemon, "AI Player", (int) (Math.random() * 6));

        List<String> playerMoves = MovesFactory.createMoves(List.of(player.pokemons));
        List<String> aiMoves = MovesFactory.createMoves(List.of(aiPlayer.pokemons));

        for (int i = 0; i < playerMoves.size(); i++) {
            player.pokemons[i].setMoves(playerMoves.get(i));
            aiPlayer.pokemons[i].setMoves(aiMoves.get(i));
        }

        BattleViewInterface battleView = new BattleView(frame);
        RunGame game = new RunGame(player, aiPlayer, battleView);

    }
}
