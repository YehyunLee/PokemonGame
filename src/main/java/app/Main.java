package app;

import data_access.PokemonApiCallApiCallInterfaceObject;
import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesDataAcessObject;
import data_access.PokemonListFromSpritesInterface;
import use_case.PokemonFactoryFromData;
import entity.Pokemon;
public class Main {
    public static void main(String[] args) {


        PokemonApiCallInterface apiDataAccess = new PokemonApiCallApiCallInterfaceObject();
        PokemonListFromSpritesInterface spritesDataAccess = new PokemonListFromSpritesDataAcessObject();

        // Create an instance of PokemonFactoryFromData and inject the data access object
        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccess, spritesDataAccess);
        Pokemon poke = factory.createPokemonFromData("Charizard");
        poke.printAllStats();

    }
}
