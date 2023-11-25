package app;

import data_access.PokemonApiCallDataAccessObject;
import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesDataAcessObject;
import data_access.PokemonListFromSpritesInterface;
import entity.Pokemon;
import use_case.PokemonFactoryFromData;
import use_case.InitilizeGameState;
import use_case.InitializeTestGameState;
import view.MenuView;

public class Main {
    public static void main(String[] args) {
        // Initialize the API and sprite data access objects
//        PokemonApiCallInterface apiDataAccess = new PokemonApiCallDataAccessObject();
//        PokemonListFromSpritesInterface spritesDataAccess = new PokemonListFromSpritesDataAcessObject();
//
//        // Create an instance of PokemonFactoryFromData and inject the data access objects
//        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccess, spritesDataAccess);
//        String[] allPokemonNames = factory.spriteParser.getAllPokemonNamesNoDuplicate(apiDataAccess);
//
//        // Create the game state
//        InitilizeGameState gameState = new InitilizeGameState(factory, allPokemonNames);

        // Create an instance of MenuView and display the Main Menu
        MenuView menuView = new MenuView();
        MenuView.displayMainView();
    }
}
