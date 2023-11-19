package use_case;

import entity.PlayerPokemons;

public class RunGame {
    public RunGame(PlayerPokemons player, PlayerPokemons aiPlayer) {
        System.out.println(player.getName());
        System.out.println(aiPlayer.getName());
    }
}
