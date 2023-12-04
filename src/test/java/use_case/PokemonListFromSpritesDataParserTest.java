package use_case;

import data_access.PokemonListFromSpritesInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

class PokemonListFromSpritesDataParserTest {

    @Mock
    private PokemonListFromSpritesInterface spritesDataAccess;

    private PokemonListFromSpritesDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        parser = new PokemonListFromSpritesDataParser(spritesDataAccess);
    }

    @Test
    void testExtractPokemonNumber() {
        // Call the private method extractPokemonNumber using reflection
        String result = invokePrivateMethod(parser, "extractPokemonNumber", "C:\\path\\to\\sprite\\1.gif");

        // Assert the result
        assertEquals("1", result);
    }

    @Test
    void testGetCombinedListOfPokemonNumbers() {
        // Mock data for frontSprites and backSprites
        String[] frontSprites = {"C:\\path\\to\\sprite\\1.gif", "C:\\path\\to\\sprite\\2.gif"};
        String[] backSprites = {"C:\\path\\to\\sprite\\1.gif", "C:\\path\\to\\sprite\\3.gif"};

        // Stub the behavior of spritesDataAccess
        when(spritesDataAccess.getRawListOfPokemonSprites("")).thenReturn(frontSprites);
        when(spritesDataAccess.getRawListOfPokemonSprites("back")).thenReturn(backSprites);

        // Call SaveAllSprites method to initialize frontSprites and backSprites
        parser.SaveAllSprites();

        // Call the method to test
        String[] result = parser.getCombinedListOfPokemonNumbers();

        // Assert the result
        assertArrayEquals(new String[]{"1"}, result);
    }


    // Helper method to invoke private methods using reflection
    private static String invokePrivateMethod(Object object, String methodName, String arg) {
        try {
            java.lang.reflect.Method method = object.getClass().getDeclaredMethod(methodName, String.class);
            method.setAccessible(true);
            return (String) method.invoke(object, arg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
