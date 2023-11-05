package view;

import data_access.PokemonApiCallDataAccessObject;
import data_access.PokemonDataAccess;
import use_case.PokemonFactoryFromData;
import entity.Pokemon;

public class Main {
    public static void main(String[] args) {
        // Create an instance of PokemonDataAccess (e.g., PokemonApiCallDataAccessObject)
        PokemonDataAccess dataAccess = new PokemonApiCallDataAccessObject();

        // Create an instance of PokemonFactoryFromData and inject the data access object
        PokemonFactoryFromData factory = new PokemonFactoryFromData(dataAccess);

        Pokemon poke = factory.createPokemonFromData("Ho-Oh");
        poke.printAllStats();
    }
}
