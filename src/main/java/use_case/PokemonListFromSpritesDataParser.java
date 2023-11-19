package use_case;

import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesInterface;

import javax.sound.midi.SysexMessage;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code PokemonListFromSpritesDataParser} class contains methods for processing Pokemon sprite data.
 */
public class PokemonListFromSpritesDataParser {
    private String[] frontSprites;
    private String[] backSprites;

    private final PokemonListFromSpritesInterface spritesDataAccess;

    /**
     * Constructs a new {@code PokemonListFromSpritesDataParser} with the provided sprite data access interface.
     *
     * @param spritesDataAccess The interface to access Pokemon sprite data.
     */
    public PokemonListFromSpritesDataParser(PokemonListFromSpritesInterface spritesDataAccess) {
        this.spritesDataAccess = spritesDataAccess;
    }


    /**
     * Get front and back all sprite lists and save locally and return it as well.
     * This is only ran once to make program efficient.
     */
    public void SaveAllSprites() {
        // Get front and back all sprite lists and save locally
        this.frontSprites = spritesDataAccess.getRawListOfPokemonSprites("");
        this.backSprites = spritesDataAccess.getRawListOfPokemonSprites("back");
        System.out.println("All sprites saved locally.");
    }

    /**
     * Extract Pokemon number from sprite path
     * @param spritePath String in format of C:\Users\USER\...\showdown\1.gif
     * @return return String of number(ID) of Pokemon
     */
    private String extractPokemonNumber(String spritePath) {
        String[] parts = spritePath.split("\\\\");
        String lastPart = parts[parts.length - 1];
        String pokemonNumber = lastPart.split("\\.")[0];
        return pokemonNumber;
    }

    /**
     * Get user dir, access front/back sprites folder, and fetch all Pokemon numbers
     * Then call containsMatchingSprite to get intersection of front/back sprites
     * (This is to avoid missing sprites)
     * Lastly, remove the pokemon names that includes string, not number (ID) to avoid
     * API call error. (API only support full pokemon name or just number id)
     * @return return number(ID) of all Pokemons
     **/
    public String[] getCombinedListOfPokemonNumbers() {
        List<String> combinedSprites = new ArrayList<>();
        for (String frontSprite : frontSprites) {
            if (containsMatchingSprite(backSprites, frontSprite)) {
                String pokemonNumber = extractPokemonNumber(frontSprite);
                // do not add if string has chr not number. everything must be number.
                if (pokemonNumber.matches("[0-9]+"))
                {
                    // due to api lack of support, id must be between 1-1010; inclusive
                    if (Integer.parseInt(pokemonNumber) > 0 && Integer.parseInt(pokemonNumber) < 1011) {
                        combinedSprites.add(pokemonNumber);
                    }
                }
                else {
                    System.out.println("Pokemon number(ID) is not number: " + pokemonNumber);
                }
            }
        }
        System.out.println("Names of all Pokemon IDs: " + combinedSprites);
        combinedSprites.toArray(new String[0]);
        return combinedSprites.toArray(new String[0]);
    }

    /**
     * Get user dir, access front/back sprites folder, and fetch all Pokemon numbers without duplicates
     * Then convert all numbers to Pokemon names using APIs
     * @return return names of all unique Pokemons
     */
    public String[] getAllPokemonNamesNoDuplicate(PokemonApiCallInterface apiDataAccess) {
        String[] allPokemonNumbers = getCombinedListOfPokemonNumbers();
        // [DEVELOPMENT] Limit number of Pokemons
//         allPokemonNumbers = Arrays.copyOfRange(allPokemonNumbers, 0, 200);
        List<String> allPokemonNames = new ArrayList<>();
        for (String pokemonNumber : allPokemonNumbers) {
            // get Pokemon name from API
            PokemonApiCallParser parser = new PokemonApiCallParser(apiDataAccess);
            Map<String, Object> apiDataList = parser.fetchPokemonData(pokemonNumber);
            for (Map.Entry<String, Object> entry : apiDataList.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                switch (key) {
                    case "name":
                        String pokemonName = (String) value;
                        allPokemonNames.add(pokemonName);
                        break;
                }
            }
        }
        // remove duplicate
        allPokemonNames = new ArrayList<>(new HashSet<>(allPokemonNames));
        return allPokemonNames.toArray(new String[0]);
    }

    /**
     * Fetches Pokemon sprite data for a given Pokemon number.
     *
     * @param pokemonNum The number of the Pokemon for which sprite data is fetched.
     * @return A map containing sprite types ("frontSprite" and "backSprite") and their file paths.
     */
    public Map<String, String> fetchPokemonSpritesData(Integer pokemonNum) {
        // Get the current working directory
        String currentDirectory = System.getProperty("user.dir");

        // Initialize a map to store sprite file paths
        Map<String, String> spriteMap = new HashMap<>();

        // Find matching front sprites
        findAndAddMatchingSprites(spriteMap, frontSprites, backSprites, pokemonNum, "frontSprite");

        // Find matching back sprites
        findAndAddMatchingSprites(spriteMap, backSprites, frontSprites, pokemonNum, "backSprite");

        // Default sprite file path
        String defaultSpritePath = "src\\main\\java\\data_access\\sprites\\pokemon\\showdown\\back\\0.png";

        // Set sprite file paths to full file paths if not found
        if (!spriteMap.containsKey("frontSprite")) {
            spriteMap.put("frontSprite", currentDirectory + File.separator + defaultSpritePath);
        }

        if (!spriteMap.containsKey("backSprite")) {
            spriteMap.put("backSprite", currentDirectory + File.separator + defaultSpritePath);
        }

        // Return the sprite map
        return spriteMap;
    }

    /**
     * Searches for and adds matching sprites to the sprite map.
     *
     * @param spriteMap     The map to store sprite file paths.
     * @param sourceSprites The source sprite list to search from.
     * @param targetSprites The target sprite list to compare with.
     * @param pokemonNum    The number of the Pokemon for which sprites are searched.
     * @param spriteType    The type of sprite ("frontSprite" or "backSprite").
     */
    void findAndAddMatchingSprites(Map<String, String> spriteMap, String[] sourceSprites,
                                   String[] targetSprites, Integer pokemonNum, String spriteType) {
        for (String sourceSprite : sourceSprites) {
            Integer fileNum = extractNumberFromPath(sourceSprite);
            if (containsMatchingSprite(targetSprites, sourceSprite) && fileNum.equals(pokemonNum)) {
                spriteMap.put(spriteType, sourceSprite);
            }
        }
    }

    /**
     * Checks if a sprite is present in the target sprite list.
     *
     * @param sprites      The sprite list to search.
     * @param targetSprite The target sprite to check for.
     * @return True if the target sprite is found in the list, otherwise false.
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
     * Extracts the name of a sprite from its file path.
     *
     * @param spritePath The file path of the sprite.
     * @return The name of the sprite.
     */
    private String extractSpriteName(String spritePath) {
        return spritePath.substring(spritePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * Extracts the numeric part of a file path representing a sprite.
     *
     * @param filePath The file path of the sprite.
     * @return The numeric part of the file path.
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
