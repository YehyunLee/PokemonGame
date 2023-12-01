package use_case;

import entity.PlayerorAiPokemons;
import entity.Pokemon;

import java.util.List;

public class CreatePlayersTest {

    /**
     * This method creates the players for the test game.
     * @param factory
     * @return
     */
    public static PlayerorAiPokemons MakeTestPlayer(PokemonFactoryFromData factory){

        Pokemon p0 = factory.createPokemonFromData("Pikachu");
        Pokemon p1 = factory.createPokemonFromData("Ditto");
        Pokemon p2 = factory.createPokemonFromData("Charizard");
        Pokemon p3 = factory.createPokemonFromData("Blastoise");
        Pokemon p4 = factory.createPokemonFromData("Venusaur");
        Pokemon p5 = factory.createPokemonFromData("Ho-Oh");

        Pokemon[] pokemons = {p0, p1, p2, p3, p4, p5};

        PlayerorAiPokemons player = new PlayerorAiPokemons(pokemons, "Player", 0);
        List<String> playerMoves = MovesFactory.createMoves(List.of(player.pokemons));


        for (int i = 0; i < playerMoves.size(); i++) {
            player.pokemons[i].setMoves(playerMoves.get(i));;
        }

        return player;
    }

    /**
     * This method creates the AI players for the test game.
     * @param factory
     * @return
     */
    public static PlayerorAiPokemons MakeTestPlayerAi(PokemonFactoryFromData factory){


        Pokemon p0 = factory.createPokemonFromData("Alakazam");
        Pokemon p1 = factory.createPokemonFromData("Machamp");
        Pokemon p2 = factory.createPokemonFromData("Rapidash");
        Pokemon p3 = factory.createPokemonFromData("Grimer");
        Pokemon p4 = factory.createPokemonFromData("Kangaskhan");
        Pokemon p5 = factory.createPokemonFromData("Pinsir");

        Pokemon[] pokemons = {p0, p1, p2, p3, p4, p5};

        PlayerorAiPokemons playerAi = new PlayerorAiPokemons(pokemons, "Player", 0);
        List<String> playerMoves = MovesFactory.createMoves(List.of(playerAi.pokemons));


        for (int i = 0; i < playerMoves.size(); i++) {
            playerAi.pokemons[i].setMoves(playerMoves.get(i));;
        }

        return playerAi;
    }

}


