package use_case;

public interface RunGameOutput {

    public void useAttackOnOpponent(String move);

    public void useHealOnSelf(String move);

    public void useDefense(String move);

    public void playRandomMove();

    public void SwitchPlayerPokemon(String move);
}
