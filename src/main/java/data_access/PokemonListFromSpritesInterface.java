package data_access;

/**
 * The {@code PokemonListFromSpritesInterface} defines an interface for accessing Pokemon sprite data.
 */
public interface PokemonListFromSpritesInterface {

    /**
     * Retrieves a list of raw Pokemon sprite file paths from the specified subfolder.
     *
     * @param subfolder The subfolder where Pokemon sprites are located.
     * @return An array of raw Pokemon sprite file paths. For example, list of strings in format of:
     * C:\Users\USER\IdeaProjects\PokemonGame\src\main\java\data_access\sprites\pokemon\showdown\1.gif
     */
    String[] getRawListOfPokemonSprites(String subfolder);
}
