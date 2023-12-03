package app;

import view.MenuView;
import data_access.PokemonApiCallDataAccessObject;
import data_access.PokemonListFromSpritesDataAcessObject;

/**
 * The main class, this runs the application.
 */
public class Main {
    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create an instance of PokemonApiCallDataAccessObject to access Pokemon data from an API.
        PokemonApiCallDataAccessObject apiDataAccess = new PokemonApiCallDataAccessObject();

        // Create an instance of PokemonListFromSpritesDataAcessObject to access Pokemon sprites data.
        PokemonListFromSpritesDataAcessObject spritesDataAccess = new PokemonListFromSpritesDataAcessObject();

        // Create a MenuView, passing the data access objects as parameters.
        MenuView menuView = new MenuView(apiDataAccess, spritesDataAccess);

        // Display the main view of the menu.
        menuView.displayMainView();
    }
}
