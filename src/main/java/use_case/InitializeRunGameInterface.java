package use_case;

import entity.PlayerorAiPokemons;
import entity.Pokemon;
import view.BattleView;
import view.BattleViewInterface;

import javax.swing.*;
import java.util.List;

public interface InitializeRunGameInterface {
    /**
     * This method initializes the game state. This creates player and AI objects and uses MovesFactory to create moves.
     * Then it runs the game.
     */
    static void InitializeRunGame(Pokemon[] allPokemons, List<Pokemon> selectedPokemonList, JFrame frame) {
        // Convert listPokemon to Pokemon[]
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

        CreatePlayer createPlayer = new CreatePlayer();
        PlayerorAiPokemons player = createPlayer.CreatePlayerObject(selectedPokemonListArray, "Player", 0);
        // Random index for the first Pokemon
        PlayerorAiPokemons aiPlayer = createPlayer.CreatePlayerObject(aiPokemon, "AI Player", (int) (Math.random() * 6));

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
