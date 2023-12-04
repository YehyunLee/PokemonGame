package use_case;

import entity.PlayerorAiPokemons;
import entity.Pokemon;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreatePlayersTest {

    private CreatePlayers createPlayers;
    private Pokemon[] allPokemonsObjects;

    @Before
    public void setUp() {
        // Create mock Pokemon objects for testing
        allPokemonsObjects = new Pokemon[]{
                createMockPokemon(1, "Bulbasaur"),
                createMockPokemon(2, "Charmander"),
                createMockPokemon(3, "Squirtle"),
                createMockPokemon(4, "Pikachu"),
                createMockPokemon(5, "Jigglypuff"),
                createMockPokemon(6, "Meowth")
        };
    }

    @Test
    public void testCreatePlayers() {
        // Mock user input for Pokemon selection
        String userInput = "1\n2\n3\n4\n5\n6\n1\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        createPlayers = new CreatePlayers(new String[]{}, allPokemonsObjects);

        // Retrieve player and aiPlayer objects
        PlayerorAiPokemons[] players = createPlayers.returnPlayers();
        PlayerorAiPokemons player = players[0];
        PlayerorAiPokemons aiPlayer = players[1];

        // Verify that the player and aiPlayer objects are not null
        assertNotNull(player);
        assertNotNull(aiPlayer);

        // Verify that the chosen Pokemon for the player are correct
        assertEquals("Bulbasaur", player.pokemons[0].getName());
        assertEquals("Charmander", player.pokemons[1].getName());
        assertEquals("Squirtle", player.pokemons[2].getName());
        assertEquals("Pikachu", player.pokemons[3].getName());
        assertEquals("Jigglypuff", player.pokemons[4].getName());
        assertEquals("Meowth", player.pokemons[5].getName());

        // Verify that the AI opponent has 6 Pokemon
        assertEquals(6, aiPlayer.pokemons.length);
    }

    private Pokemon createMockPokemon(int number, String name) {
        Pokemon mockPokemon = new Pokemon();
        mockPokemon.setNumber(number);
        mockPokemon.setName(name);
        return mockPokemon;
    }
}
