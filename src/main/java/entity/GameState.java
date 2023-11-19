package entity;

public class GameState {
    public Boolean GameOver = false;
    public PlayerPokemons player;
    public PlayerPokemons aiPlayer;

    public Pokemon ActivePlayerPokemon;
    public Pokemon ActiveAiPokemon;


    public void initializeGameState(PlayerPokemons player, PlayerPokemons aiPlayer) {
        this.GameOver = false;
        this.player = player;
        this.aiPlayer = aiPlayer;

        ActivePlayerPokemon = player.getActivePokemon();
        ActiveAiPokemon = aiPlayer.getActivePokemon();
    }








}
