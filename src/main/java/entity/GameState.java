package entity;

public class GameState {
    public PlayerorAiPokemons player;
    public PlayerorAiPokemons aiPlayer;
    public Pokemon ActivePlayerPokemon;
    public Pokemon ActiveAiPokemon;
    public String PlayerWin = "";


    public void initializeGameState(PlayerorAiPokemons player, PlayerorAiPokemons aiPlayer) {
        this.player = player;
        this.aiPlayer = aiPlayer;

        ActivePlayerPokemon = player.getActivePokemon();
        ActiveAiPokemon = aiPlayer.getActivePokemon();
    }


}
