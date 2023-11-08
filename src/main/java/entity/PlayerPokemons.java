package entity;

public class PlayerPokemons {

    Pokemon[] pokemons = new Pokemon[0];
    int FirstPokemon = 0;

    int chosenPokemon = 0;

    // Create a method for changing chosenPokemon value
    public int setChosenPokemon(int newChosenPokemon) {
        this.chosenPokemon = newChosenPokemon;
        return newChosenPokemon;
    }
}