package use_case;

import entity.Pokemon;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CreateAllPokemonsTest {

    @Mock
    private PokemonFactoryFromData mockFactory;

    private CreateAllPokemonsInterface createAllPokemons;

    private String[] allPokemonNames = {"Pikachu", "Bulbasaur", "Charmander"};

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createAllPokemons = new CreateAllPokemons(mockFactory, allPokemonNames);
    }

    @Test
    public void testCreatePokemons() {
        // Mock the behavior of createPokemonFromData in the factory
        when(mockFactory.createPokemonFromData("Pikachu")).thenReturn(new Pokemon());
        when(mockFactory.createPokemonFromData("Bulbasaur")).thenReturn(new Pokemon());
        when(mockFactory.createPokemonFromData("Charmander")).thenReturn(new Pokemon());

        // Call the method under test
        Pokemon[] result = createAllPokemons.CreatePokemons();

        // Verify that the factory method was called for each Pokemon name
        verify(mockFactory, times(allPokemonNames.length)).createPokemonFromData(anyString());

        // Verify that the result array has the expected length
        assertEquals(allPokemonNames.length, result.length);

        // Verify that each Pokemon in the result array has been created by the factory
        for (Pokemon pokemon : result) {
            assertNotNull(pokemon);
        }
    }
}
