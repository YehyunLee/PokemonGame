package use_case;

public interface RunGameOutput {

    /**
     * Used for light, true, heavy attack.
     * @param move
     */
    void useAttackOnOpponent(String move);

    /**
     * Used for light, heavy heal.
     * @param move
     * */
    void useHealOnSelf(String move);

    /**
     * Used for light, heavy defense.
     * @param move
     */
    void useDefenseOnSelf(String move);

    /**
     * Used for playing random move.
     */
    void playRandomMove();

    /**
     * Used for switching player pokemon.
     * @param move
     */
    void SwitchPlayerPokemon(String move);

    /**
     * Get the winner of the game.
     */
    String getWinnerOfGame();

}
