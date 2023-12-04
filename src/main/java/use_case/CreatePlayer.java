package use_case;
import entity.PlayerorAiPokemons;
import entity.Pokemon;

public class CreatePlayer {
    public CreatePlayer() {
    }

    public static PlayerorAiPokemons CreatePlayerObject(Pokemon[] pokemons, String name, int index) {
        return new PlayerorAiPokemons(pokemons, name, index);
    }
}
