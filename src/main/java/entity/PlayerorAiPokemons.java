package entity;

public class PlayerorAiPokemons {
    private String name = "";
    private Pokemon[] pokemons = new Pokemon[0];
    private int firstPokemon = 0;
    private int currentPokemonIndex = 0;

    // Create a method for changing chosenPokemon value
    public void switchPokemon(int newChosenPokemonIndex) {
        this.currentPokemonIndex = newChosenPokemonIndex;
    }

    public PlayerorAiPokemons(Pokemon[] pokemons, String name, int firstPokemon) {
        this.name = name;
        this.pokemons = pokemons;
        this.firstPokemon = firstPokemon;
        this.currentPokemonIndex = firstPokemon;
    }

    public String getName() {
        return name;
    }
}