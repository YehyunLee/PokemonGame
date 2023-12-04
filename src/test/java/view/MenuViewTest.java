package view;

import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class MenuViewTest {

    private PokemonApiCallInterface mockApiDataAccess;
    private PokemonListFromSpritesInterface mockSpritesDataAccess;

    @BeforeEach
    void setUp() {
        // Create mock objects for dependencies
        mockApiDataAccess = mock(PokemonApiCallInterface.class);
        mockSpritesDataAccess = mock(PokemonListFromSpritesInterface.class);
    }

    @Test
    void testMenuViewCreation() {
        // Test if MenuView can be created without errors
        MenuView menuView = new MenuView(mockApiDataAccess, mockSpritesDataAccess);
        assertNotNull(menuView);
    }

}
