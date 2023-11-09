package data_access;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code PokemonListFromSpritesDataAccessObject} class is responsible for managing a list of Pokemon sprites.
 */
public class PokemonListFromSpritesDataAcessObject implements PokemonListFromSpritesInterface {

    /**
     * Gets a raw list of Pokemon sprites from a specified subfolder.
     *
     * @param subfolder The subfolder containing Pokemon sprites (e.g., "back" for back sprites).
     * @return An array of strings representing the file paths to the Pokemon sprites.
     */
    public String[] getRawListOfPokemonSprites(String subfolder) {
        // Construct the folder path for Pokemon sprites
        String folderPath = constructSpritesFolderPath(subfolder);

        // Initialize a list to store Pokemon sprite file paths
        List<String> pokemonSprites = new ArrayList<>();

        try {
            // Walk through the folder and collect sprite paths ending with ".gif"
            List<Path> spritePaths = Files.walk(Path.of(folderPath), 1, FileVisitOption.FOLLOW_LINKS)
                    .filter(path -> path.toString().endsWith(".gif"))
                    .collect(Collectors.toList());

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

    /**
     * Checks if the provided array of sprites contains a matching sprite based on the sprite's name.
     *
     * @param sprites       An array of sprite file paths.
     * @param targetSprite The target sprite file path to compare.
     * @return True if a matching sprite is found, otherwise false.
     */
    private boolean containsMatchingSprite(String[] sprites, String targetSprite) {
        // Extract the name of the target sprite from its file path
        String targetSpriteName = extractSpriteName(targetSprite);

        for (String sprite : sprites) {
            // Extract the name of the sprite from its file path
            String spriteName = extractSpriteName(sprite);

            // Compare sprite names, return true if a match is found
            if (spriteName.equals(targetSpriteName)) {
                return true;
            }
        }

        // No matching sprite found
        return false;
    }

    /**
     * Gets a combined list of Pokemon sprites that have both front and back versions.
     *
     * @return An array of strings representing the file paths to the combined Pokemon sprites.
     */
    public String[] getCombinedListOfPokemonSprites(Integer pokemonNum) {
        // Get the raw lists of front and back Pokemon sprites
        String[] frontSprites = getRawListOfPokemonSprites("");
        String[] backSprites = getRawListOfPokemonSprites("back");

        // Initialize a list to store combined Pokemon sprite file paths
        List<String> combinedSprites = new ArrayList<>();

        // Loop through frontSprites to find matching front sprites
        for (String frontSprite : frontSprites) {
            Integer fileNum = extractNumberFromPath(frontSprite);
            if (containsMatchingSprite(backSprites, frontSprite) && fileNum.equals(pokemonNum)) {
                combinedSprites.add(frontSprite);
            }
        }

        // Loop through backSprites to find matching back sprites
        for (String backSprite : backSprites) {
            Integer fileNum = extractNumberFromPath(backSprite);
            if (containsMatchingSprite(frontSprites, backSprite) && fileNum.equals(pokemonNum)) {
                combinedSprites.add(backSprite);
            }
        }

        // Convert the list to an array and return it
        return combinedSprites.toArray(new String[0]);
    }

    /**
     * Constructs the folder path for Pokemon sprites based on the specified subfolder.
     *
     * @param subfolder The subfolder containing Pokemon sprites.
     * @return The constructed folder path.
     */
    private String constructSpritesFolderPath(String subfolder) {
        // Get the user directory
        String userDirectory = System.getProperty("user.dir");

        // Construct the full folder path
        return userDirectory + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + "data_access" + File.separator + "sprites" + File.separator + "pokemon" +
                File.separator + "showdown" + File.separator + subfolder;
    }

    /**
     * Extracts the name of a sprite from its file path.
     *
     * @param spritePath The file path of the sprite.
     * @return The extracted sprite name.
     */
    private String extractSpriteName(String spritePath) {
        return spritePath.substring(spritePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * Extracts a number from a file path using a regular expression pattern.
     *
     * @param filePath The file path containing the number.
     * @return The extracted number or 0 if not found.
     */
    private static int extractNumberFromPath(String filePath) {
        Pattern pattern = Pattern.compile("(\\d+)\\.gif$");
        Matcher matcher = pattern.matcher(filePath);

        if (matcher.find()) {
            String matchedNumber = matcher.group(1);
            return Integer.parseInt(matchedNumber);
        }

        return 0;
    }
}
