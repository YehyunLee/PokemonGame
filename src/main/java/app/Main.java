package app;

import data_access.PokemonApiCallDataAccessObject;
import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesDataAcessObject;
import data_access.PokemonListFromSpritesInterface;
import entity.PlayerPokemons;
import use_case.PokemonFactoryFromData;
import entity.Pokemon;
import java.util.Scanner;
import use_case.GameState;

public class Main {
    public static void main(String[] args) {

        // [Getting ready for api, get data, and store]
        PokemonApiCallInterface apiDataAccess = new PokemonApiCallDataAccessObject();
        PokemonListFromSpritesInterface spritesDataAccess = new PokemonListFromSpritesDataAcessObject();

        // [Parse data]
        // Create an instance of PokemonFactoryFromData and inject the data access object
        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccess, spritesDataAccess);
        // This gets Pokemon names that exists in sprites, supported by API, and no duplicates. It handles all errors.
        String[] allPokemonNames = factory.spriteParser.getAllPokemonNamesNoDuplicate(apiDataAccess);

        // [Run game]
        GameState gameState = new GameState(factory, allPokemonNames);
    }
}
