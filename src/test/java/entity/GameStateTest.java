package entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private PlayerorAiPokemons player;
    private PlayerorAiPokemons aiPlayer;

    @BeforeEach
    void setUp() {
        // Set up player and AI Pokemon teams for testing
        Pokemon playerPokemon1 = new Pokemon();
        Pokemon playerPokemon2 = new Pokemon();
        playerPokemon1.setName("Charmander");
        playerPokemon2.setName("Bulbasaur");

        Pokemon aiPokemon1 = new Pokemon();
        Pokemon aiPokemon2 = new Pokemon();
        aiPokemon1.setName("Squirtle");
        aiPokemon2.setName("Pikachu");

        player = new PlayerorAiPokemons(new Pokemon[]{playerPokemon1, playerPokemon2}, "Player", 0);
        aiPlayer = new PlayerorAiPokemons(new Pokemon[]{aiPokemon1, aiPokemon2}, "AI", 0);
    }

    @Test
    void initializeGameState() {
        GameState gameState = new GameState();

        gameState.initializeGameState(player, aiPlayer);

        // Check if the player and AI Pokemon teams are correctly set
        assertEquals(player, gameState.player);
        assertEquals(aiPlayer, gameState.aiPlayer);

        // Check if the active Pokemon of the player and AI are correctly set
        assertEquals(player.getActivePokemon(), gameState.ActivePlayerPokemon);
        assertEquals(aiPlayer.getActivePokemon(), gameState.ActiveAiPokemon);
    }
}
