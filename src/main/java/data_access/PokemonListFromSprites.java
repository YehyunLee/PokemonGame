package data_access;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;


public class PokemonListFromSprites {
    public String[] getRawListOfPokemonSprites(String subfolder) {
        /**
         * Get user dir, access front/back sprites folder, and fetch all pokemon names
         * This return raw data like list of:
         * C:\Users\USER\IdeaProjects\PokemonGame\src\main\java\data_access\sprites\pokemon\showdown\1.gif
         **/
        String userDirectory = System.getProperty("user.dir");
        String folderPath = userDirectory + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + "data_access" + File.separator + "sprites" + File.separator + "pokemon" +
                File.separator + "showdown" + File.separator + subfolder;
        List<String> pokemonSprites = new ArrayList<>();

        try {
            List<Path> spritePaths = Files.walk(Path.of(folderPath), 1, FileVisitOption.FOLLOW_LINKS)
                    .filter(path -> path.toString().endsWith(".gif"))
                    .collect(Collectors.toList());

            for (Path spritePath : spritePaths) {
                pokemonSprites.add(spritePath.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pokemonSprites.toArray(new String[0]);
    }



    private boolean containsMatchingSprite(String[] sprites, String targetSprite) {
        /**
         * Compare last index name and return true if match
         **/
        String targetSpriteName = targetSprite.substring(targetSprite.lastIndexOf(File.separator) + 1);

        for (String sprite : sprites) {
            String spriteName = sprite.substring(sprite.lastIndexOf(File.separator) + 1);
//            System.out.println(spriteName);
//            output: 493-fire.gif
            if (spriteName.equals(targetSpriteName)) {
                return true;
            }
        }
        return false;
    }

    public String[] getCombinedListOfPokemonSprites() {
        /**
         * Get user dir, access front/back sprites folder, and fetch all pokemon names
         * Then call containsMatchingSprite to get intersection of front/back sprites
         * This return data like list of:
         * C:\Users\USER\IdeaProjects\PokemonGame\src\main\java\data_access\sprites\pokemon\showdown\1.gif
         **/
        String[] frontSprites = getRawListOfPokemonSprites("");
        String[] backSprites = getRawListOfPokemonSprites("back");

        List<String> combinedSprites = new ArrayList<>();
        for (String frontSprite : frontSprites) {
            if (containsMatchingSprite(backSprites, frontSprite)) {
                combinedSprites.add(frontSprite);
            }
        }

        return combinedSprites.toArray(new String[0]);
    }



//    public static void main(String[] args) {
//        PokemonListFromSprites pokemonListFromSprites = new PokemonListFromSprites();
//        String[] pokemonSprites = pokemonListFromSprites.getCombinedListOfPokemonSprites();
//
//        for (String sprite : pokemonSprites) {
//            System.out.println(sprite);
//        }
//    }

}
