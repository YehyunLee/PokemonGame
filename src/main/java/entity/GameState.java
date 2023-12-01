package entity;

/**
 * The GameState class represents the current state of a Pokemon battle between a player and an AI.
 */
public class GameState {

    /**
     * The player's Pokemon team.
     */
    public PlayerorAiPokemons player;

    /**
     * The AI's Pokemon team.
     */
    public PlayerorAiPokemons aiPlayer;

    /**
     * The active Pokemon of the player.
     */
    public Pokemon ActivePlayerPokemon;

    /**
     * The active Pokemon of the AI.
     */
    public Pokemon ActiveAiPokemon;

    /**
     * Indicates the winner of the battle. An empty string means no winner yet.
     */
    public String PlayerWin = "";

    /**
     * Initializes the GameState with the specified player and AI Pokemon teams.
     *
     * @param player The player's Pokemon team.
     * @param aiPlayer The AI's Pokemon team.
     */
    public void initializeGameState(PlayerorAiPokemons player, PlayerorAiPokemons aiPlayer) {
        this.player = player;
        this.aiPlayer = aiPlayer;

        ActivePlayerPokemon = player.getActivePokemon();
        ActiveAiPokemon = aiPlayer.getActivePokemon();
    }
}
