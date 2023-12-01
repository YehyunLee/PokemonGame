package use_case;

public interface RunGameOutput {

    /**
     * Used for light, true, heavy attack.
     * @param move
     */
    public void useAttackOnOpponent(String move);

    /**
     * Used for light, heavy heal.
     * @param move
     * */
    public void useHealOnSelf(String move);

    /**
     * Used for light, heavy defense.
     * @param move
     */
    public void useDefense(String move);

    /**
     * Used for playing random move.
     */
    public void playRandomMove();

    /**
     * Used for switching player pokemon.
     * @param move
     */
    public void SwitchPlayerPokemon(String move);

    /**
     * Get the winner of the game.
     */
    String getWinnerOfGame();
}
