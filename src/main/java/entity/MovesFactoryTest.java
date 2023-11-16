package entity;

import entity.*;
import use_case.MovesFactory;

import java.util.ArrayList;
import java.util.List;

public class MovesFactoryTest {

    public static void main(String[] args) {
        // Create a list of Pokemon for testing
        List<Pokemon> pokemonList = createTestPokemonList();

        // Use MovesFactory to generate moves for each Pokemon
        List<String> movesList = MovesFactory.createMoves(pokemonList);

        // Print or inspect the generated moves
        for (String moveDetails : movesList) {
            System.out.println(moveDetails);
        }
    }

    private static List<Pokemon> createTestPokemonList() {
        // Create and return a list of test Pokemon with different stats
        List<Pokemon> testPokemonList = new ArrayList<>();

        // Add test Pokemon with various stats
        Pokemon pikachu = new Pokemon();
        pikachu.setName("Pikachu");
        pikachu.setAttack(50);
        pikachu.setDefense(30);
        pikachu.setHealth(80);

        Pokemon bulbasaur = new Pokemon();
        bulbasaur.setName("Bulbasaur");
        bulbasaur.setAttack(40);
        bulbasaur.setDefense(35);
        bulbasaur.setHealth(70);

        Pokemon charmander = new Pokemon();
        charmander.setName("Charmander");
        charmander.setAttack(55);
        charmander.setDefense(25);
        charmander.setHealth(75);

        // Add more test Pokemon as needed...

        testPokemonList.add(pikachu);
        testPokemonList.add(bulbasaur);
        testPokemonList.add(charmander);

        return testPokemonList;
    }
}

