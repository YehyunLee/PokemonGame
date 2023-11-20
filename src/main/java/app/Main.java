package app;

import data_access.PokemonApiCallDataAccessObject;
import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesDataAcessObject;
import data_access.PokemonListFromSpritesInterface;
import use_case.PokemonFactoryFromData;
import use_case.InitializeGameState;

public class Main {
    public static void main(String[] args) {

        // [Getting ready for api, get data, and store]
        PokemonApiCallInterface apiDataAccess = new PokemonApiCallDataAccessObject();
        PokemonListFromSpritesInterface spritesDataAccess = new PokemonListFromSpritesDataAcessObject();

        // [Parse data]
        // Create an instance of PokemonFactoryFromData and inject the data access object
        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccess, spritesDataAccess);
        String[] allPokemonNames = factory.spriteParser.getAllPokemonNamesNoDuplicate(apiDataAccess);

        // [Run game]
        InitializeGameState gameState = new InitializeGameState(factory, allPokemonNames);
    }
}
