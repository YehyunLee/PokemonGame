package entity;

public class PlayerorAiPokemons {
    private String type = "";
    public Pokemon[] pokemons = new Pokemon[0];
    private int firstPokemon = 0;
    private int currentPokemonIndex = 0;

    public PlayerorAiPokemons(Pokemon[] pokemons, String type, int firstPokemon) {
        this.type = type;
        this.pokemons = pokemons;
        this.firstPokemon = firstPokemon;
        this.currentPokemonIndex = firstPokemon;
    }

    public Boolean isSwapPokemonAlive(int index) {
        if (pokemons[index].getIsAlive()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void SwapActivePokemon(int index) {
        currentPokemonIndex = index;
    };

    public Pokemon getActivePokemon() {
        return pokemons[currentPokemonIndex];
    };

    public Boolean isAllDead() {
        Integer counter = 0;
        for (Pokemon p : pokemons)  {
            if (!p.isAlive) {
                counter += 1;
            }
        }
        System.out.println(counter);
        return (counter == 6);
    }

    public int getCurrentPokemonIndex() {
        return currentPokemonIndex;
    }
}