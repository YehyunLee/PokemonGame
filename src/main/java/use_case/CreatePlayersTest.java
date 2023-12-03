package use_case;

import entity.PlayerorAiPokemons;
import entity.Pokemon;

import java.util.List;

/**
 * The CreatePlayersTest class is responsible for creating players for the test game.
 */
public class CreatePlayersTest {

    /**
     * Creates a test player for the test game.
     *
     * @param factory The PokemonFactoryFromData used to create Pokemon objects.
     * @return A PlayerorAiPokemons instance representing the test player.
     */
    public static PlayerorAiPokemons makeTestPlayer(PokemonFactoryFromData factory) {

        Pokemon p0 = factory.createPokemonFromData("Darkrai");
        Pokemon p1 = factory.createPokemonFromData("Reshiram");
        Pokemon p2 = factory.createPokemonFromData("Groudon");
        Pokemon p3 = factory.createPokemonFromData("Ho-Oh");
        Pokemon p4 = factory.createPokemonFromData("Yveltal");
        Pokemon p5 = factory.createPokemonFromData("Dialga");

        Pokemon[] pokemons = {p0, p1, p2, p3, p4, p5};

        PlayerorAiPokemons player = new PlayerorAiPokemons(pokemons, "Player", 0);
        List<String> playerMoves = MovesFactory.createMoves(List.of(player.pokemons));

        // Set moves for the player's Pokemon
        for (int i = 0; i < playerMoves.size(); i++) {
            player.pokemons[i].setMoves(playerMoves.get(i));
        }

        return player;
    }

    /**
     * Creates a test AI player for the test game.
     *
     * @param factory The PokemonFactoryFromData used to create Pokemon objects.
     * @return A PlayerorAiPokemons instance representing the test AI player.
     */
    public static PlayerorAiPokemons makeTestPlayerAi(PokemonFactoryFromData factory) {

        Pokemon p0 = factory.createPokemonFromData("Cresselia");
        Pokemon p1 = factory.createPokemonFromData("Zekrom");
        Pokemon p2 = factory.createPokemonFromData("Kyogre");
        Pokemon p3 = factory.createPokemonFromData("Lugia");
        Pokemon p4 = factory.createPokemonFromData("Xerneas");
        Pokemon p5 = factory.createPokemonFromData("Palkia");

        Pokemon[] pokemons = {p0, p1, p2, p3, p4, p5};

        PlayerorAiPokemons playerAi = new PlayerorAiPokemons(pokemons, "Player", 0);
        List<String> playerMoves = MovesFactory.createMoves(List.of(playerAi.pokemons));

        // Set moves for the AI player's Pokemon
        for (int i = 0; i < playerMoves.size(); i++) {
            playerAi.pokemons[i].setMoves(playerMoves.get(i));
        }

        return playerAi;
    }
}
