package data_access;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code PokemonListFromSpritesDataAccessObject} class is responsible for managing a list of Pokemon sprites.
 */
public class PokemonListFromSpritesDataAcessObject implements PokemonListFromSpritesInterface {

    /**
     * Retrieves a list of raw Pokemon sprite file paths from the specified subfolder.
     *
     * @param subfolder The subfolder where Pokemon sprites are located.
     * @return An array of raw Pokemon sprite file paths.
     */
    public String[] getRawListOfPokemonSprites(String subfolder) {
        // Construct the folder path for Pokemon sprites
        String folderPath = constructSpritesFolderPath(subfolder);

        // Initialize a list to store Pokemon sprite file paths
        List<String> pokemonSprites = new ArrayList<>();

        try {
            // Walk through the folder and collect sprite paths ending with ".gif"
            List<Path> spritePaths = getSpritePaths(folderPath);
            for (Path spritePath : spritePaths) {
                pokemonSprites.add(spritePath.toString());
            }
        } catch (IOException e) {
            // Proper error handling should be implemented here (e.g., throw an exception or log the error).
            e.printStackTrace();
        }

        // Convert the list to an array and return it
        return pokemonSprites.toArray(new String[0]);
    }

    private String constructSpritesFolderPath(String subfolder) {
        return "src" + File.separator + "main" + File.separator + "java"
                + File.separator + "data_access" + File.separator + "sprites" + File.separator + "pokemon" +
                File.separator + "showdown" + File.separator + subfolder;
    }

    private List<Path> getSpritePaths(String folderPath) throws IOException {
        return Files.walk(Path.of(folderPath), 1, FileVisitOption.FOLLOW_LINKS)
                .filter(path -> path.toString().endsWith(".gif"))
                .collect(Collectors.toList());
    }
}
