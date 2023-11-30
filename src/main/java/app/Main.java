package app;

import data_access.PokemonApiCallDataAccessObject;
import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesDataAcessObject;
import data_access.PokemonListFromSpritesInterface;
import use_case.PokemonFactoryFromData;
import view.Winner;
import view.Loser;
import use_case.InitilizeGameState;
import use_case.InitializeTestGameState;
import view.BattleView;
import view.BattleViewInterface;
import view.MenuView;
import view.GameView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Initialize the API and sprite data access objects
        PokemonApiCallInterface apiDataAccess = new PokemonApiCallDataAccessObject();
        PokemonListFromSpritesInterface spritesDataAccess = new PokemonListFromSpritesDataAcessObject();

        // Create an instance of PokemonFactoryFromData and inject the data access objects
        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccess, spritesDataAccess);
        String[] allPokemonNames = factory.spriteParser.getAllPokemonNamesNoDuplicate(apiDataAccess);




        // Create the game state
        InitilizeGameState gameState = new InitilizeGameState(factory, allPokemonNames);
        //InitializeTestGameState gameState = new InitializeTestGameState(factory, allPokemonNames);

        // Create an instance of MenuView and display the Main Menu
//        MenuView menuView = new MenuView();
//        MenuView.displayMainView();


//        SwingUtilities.invokeLater(() -> {
//            BattleView newBattle = new BattleView();
//            newBattle.appendToConsoleWithTypingAnimation("Prepare for Battle!");
//            newBattle.appendToConsoleWithTypingAnimation("Choose your move wisely.");
//            newBattle.appendToConsoleWithTypingAnimation("The enemy is strong.");
//            newBattle.updatePlayerHealth(50);
//            newBattle.updateFrontGif("src/main/java/data_access/sprites/pokemon/showdown/back/487.gif");
//            newBattle.updateBackGif("src/main/java/data_access/sprites/pokemon/showdown/6.gif");
//        });

//        BattleView battle = new BattleView();
//        battle.displayBattleView();
    }
}