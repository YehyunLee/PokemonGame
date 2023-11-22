package entity;

public class GameState {
    public Boolean GameOver = false;
    public PlayerorAiPokemons player;
    public PlayerorAiPokemons aiPlayer;

    public Pokemon ActivePlayerPokemon;
    public Pokemon ActiveAiPokemon;


    public void initializeGameState(PlayerorAiPokemons player, PlayerorAiPokemons aiPlayer) {
        this.GameOver = false;
        this.player = player;
        this.aiPlayer = aiPlayer;

        ActivePlayerPokemon = player.getActivePokemon();
        ActiveAiPokemon = aiPlayer.getActivePokemon();
    }

}
