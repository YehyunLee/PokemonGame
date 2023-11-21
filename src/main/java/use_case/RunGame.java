package use_case;

import entity.PlayerorAiPokemons;

public class RunGame {
    public RunGame(PlayerorAiPokemons player, PlayerorAiPokemons aiPlayer) {
        System.out.println(player.getName());
        System.out.println(aiPlayer.getName());
    }
}
