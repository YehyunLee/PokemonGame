package data_access;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokemonListFromSpritesDataAcessObjectTest {

    private static final String TEST_FOLDER_NAME = "test_sprites_folder";

    private Path testFolderPath;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary directory for testing
        testFolderPath = Files.createTempDirectory(TEST_FOLDER_NAME);

        // Create some fake .gif files in the temporary directory
        Files.write(testFolderPath.resolve("fake1.gif"), "fake content".getBytes(), StandardOpenOption.CREATE);
        Files.write(testFolderPath.resolve("fake2.gif"), "fake content".getBytes(), StandardOpenOption.CREATE);
        Files.write(testFolderPath.resolve("not_a_gif.txt"), "fake content".getBytes(), StandardOpenOption.CREATE);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the temporary directory after each test
        Files.walk(testFolderPath)
                .sorted((path1, path2) -> -path1.compareTo(path2))
                .forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Test
    void testGetSpritePaths() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        // Create an instance of PokemonListFromSpritesDataAcessObject
        PokemonListFromSpritesDataAcessObject dao = new PokemonListFromSpritesDataAcessObject();

        // Specify the folder path for testing
        String testFolderPathString = testFolderPath.toString();

        // Use reflection to access the private method
        Method method = PokemonListFromSpritesDataAcessObject.class.getDeclaredMethod("getSpritePaths", String.class);
        method.setAccessible(true);

        // Invoke the private method
        List<Path> result = (List<Path>) method.invoke(dao, testFolderPathString);

        // Assertions for the result
        assertAll(
                () -> assertNotNull(result),
                () -> assertTrue(!result.isEmpty()),
                () -> assertTrue(result.stream().allMatch(path -> path.toString().endsWith(".gif")))
        );
    }

    @Test
    void testConstructSpritesFolderPath() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Create an instance of PokemonListFromSpritesDataAcessObject
        PokemonListFromSpritesDataAcessObject dao = new PokemonListFromSpritesDataAcessObject();

        // Specify the subfolder for testing
        String testSubfolder = "test_sprites";

        // Use reflection to access the private method
        Method method = PokemonListFromSpritesDataAcessObject.class.getDeclaredMethod("constructSpritesFolderPath", String.class);
        method.setAccessible(true);

        // Invoke the private method
        String result = (String) method.invoke(dao, testSubfolder);

        // Specify the expected result based on your constructSpritesFolderPath implementation
        String expectedPath = "src" + File.separator + "main" + File.separator + "java"
                + File.separator + "data_access" + File.separator + "sprites" + File.separator + "pokemon" +
                File.separator + "showdown" + File.separator + testSubfolder;

        // Assertions for the result
        assertEquals(expectedPath, result);
    }
}
