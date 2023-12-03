package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import entity.Pokemon;
import use_case.MovesFactory;

import java.util.ArrayList;
import java.util.List;

public class MovesFactoryTest {

    private List<Pokemon> pokemonList;

    @BeforeEach
    void setUp() {
        // Initialize a list of test Pokemon for each test case
        pokemonList = createTestPokemonList();
    }

    @Test
    void testCreateMoves() {
        // Use MovesFactory to generate moves for the test Pokemon
        List<String> movesList = MovesFactory.createMoves(pokemonList);

        // Perform assertions to validate the generated moves
        assertNotNull(movesList);
        assertFalse(movesList.isEmpty());
    }

    /**
     * Creates a list of test Pokemon with various stats.
     *
     * @return A list of test Pokemon.
     */
    private List<Pokemon> createTestPokemonList() {
        // Create and return a list of test Pokemon with different stats
        List<Pokemon> testPokemonList = new ArrayList<>();

        // Add test Pokemon with various stats
        Pokemon pikachu = new Pokemon();
        pikachu.setName("Pikachu");
        pikachu.setAttack(50);
        pikachu.setDefense(30);
        pikachu.setHealth(80);

        Pokemon bulbasaur = new Pokemon();
        bulbasaur.setName("Bulbasaur");
        bulbasaur.setAttack(40);
        bulbasaur.setDefense(35);
        bulbasaur.setHealth(70);

        Pokemon charmander = new Pokemon();
        charmander.setName("Charmander");
        charmander.setAttack(55);
        charmander.setDefense(25);
        charmander.setHealth(75);

        // Add more test Pokemon as needed...

        testPokemonList.add(pikachu);
        testPokemonList.add(bulbasaur);
        testPokemonList.add(charmander);

        return testPokemonList;
    }
}
