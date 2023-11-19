package entity;

public class PlayerPokemons {
    private String type = "";
    private Pokemon[] pokemons = new Pokemon[0];
    private int firstPokemon = 0;
    private int currentPokemonIndex = 0;

    public PlayerPokemons(Pokemon[] pokemons, String type, int firstPokemon) {
        this.type = type;
        this.pokemons = pokemons;
        this.firstPokemon = firstPokemon;
        this.currentPokemonIndex = firstPokemon;
    }


    public Pokemon getActivePokemon() {
        return pokemons[currentPokemonIndex];
    };

    public void printAllAlivePokemon() {
        for (Pokemon p : pokemons)  {
            if (p.isAlive) {
                p.printAllStats();
            }
        }
    }

}