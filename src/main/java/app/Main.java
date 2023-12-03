package app;

import view.MenuView;
import data_access.PokemonApiCallDataAccessObject;
import data_access.PokemonListFromSpritesDataAcessObject;

/**
 * The Main class serves as the entry point for the application.
 * It initializes the data access objects and displays the main menu view.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize data access objects
        PokemonApiCallDataAccessObject apiDataAccess = new PokemonApiCallDataAccessObject();
        PokemonListFromSpritesDataAcessObject spritesDataAccess = new PokemonListFromSpritesDataAcessObject();

        // Create the main menu view and display it
        MenuView menuView = new MenuView(apiDataAccess, spritesDataAccess);
        MenuView.displayMainView();
    }
}
