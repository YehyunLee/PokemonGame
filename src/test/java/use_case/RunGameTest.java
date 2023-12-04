package use_case;

import entity.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.BattleViewInterface;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RunGameTest {

    @Mock
    private BattleViewInterface mockBattleView;

    private RunGame runGame;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a sample player and AI with their Pokemon teams
        PlayerorAiPokemons player = createSamplePlayer();
        PlayerorAiPokemons aiPlayer = createSampleAI();

        // Create RunGame instance for testing
        runGame = new RunGame(player, aiPlayer, mockBattleView);
    }

    @Test
    public void testSwitchRandomPokemon() {
        // Mock the behavior when calling SwitchRandomPokemon
        runGame.SwitchRandomPokemon();

        // Verify that the expected methods are called
        verify(mockBattleView, times(9)).printToConsole(anyString());
        verify(mockBattleView, times(2)).updateBackGif(anyString());
        verify(mockBattleView).updateEnemyHealth(anyInt());
        verify(mockBattleView).updatePlayerHealth(anyInt());
        verify(mockBattleView).displayBattleView();
    }

    @Test
    public void testSwitchPlayerPokemon() {
        // Mock the behavior when calling SwitchPlayerPokemon
        runGame.SwitchPlayerPokemon("1");

        // Verify that the expected methods are called
        verify(mockBattleView, times(15)).printToConsole(anyString());
        verify(mockBattleView, times(2)).updateFrontGif(anyString());
        verify(mockBattleView, times(2)).updatePlayerHealth(anyInt());
        verify(mockBattleView, times(2)).updateEnemyHealth(anyInt());
    }

    @Test
    public void testGetWinnerOfGame() {
        // Mock the behavior when calling getWinnerOfGame
        String winner = runGame.getWinnerOfGame();

        // Verify that the expected methods are called
        verify(mockBattleView, times(4)).printToConsole(anyString());

        // Perform assertions on the result
        assertEquals("", winner);
    }


    private PlayerorAiPokemons createSamplePlayer() {
        // Create a sample player with Pokemon team
        Pokemon[] playerPokemons = new Pokemon[]{
                createSamplePokemon("Charizard"),
                createSamplePokemon("Blastoise"),
                createSamplePokemon("Venusaur")
        };
        return new PlayerorAiPokemons(playerPokemons, "Player", 0);
    }

    private PlayerorAiPokemons createSampleAI() {
        // Create a sample AI with Pokemon team
        Pokemon[] aiPokemons = new Pokemon[]{
                createSamplePokemon("Pikachu"),
                createSamplePokemon("Jolteon"),
                createSamplePokemon("Raichu")
        };
        return new PlayerorAiPokemons(aiPokemons, "AIPlayer", 0);
    }

    private Pokemon createSamplePokemon(String name) {
        // Create a sample Pokemon
        Pokemon pokemon = new Pokemon();
        pokemon.setName(name);
        pokemon.setHealth(100);
        pokemon.setTotalHealth(100);
        pokemon.setAttack(50);
        pokemon.setDefense(30);
        return pokemon;
    }
}
