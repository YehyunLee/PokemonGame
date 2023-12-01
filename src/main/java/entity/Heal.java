package entity;

/**
 * The Heal class represents a healing move in the game, extending the Moves class.
 * It includes information about the light and heavy healing values.
 */
public class Heal extends Moves {

    /** The amount of light healing provided by the move. */
    float lightHeal;

    /** The amount of heavy healing provided by the move. */
    float heavyHeal;

    /**
     * Constructs a Heal object with the specified name, light healing, and heavy healing values.
     *
     * @param name The name of the healing move.
     * @param lightHeal The amount of light healing provided by the move.
     * @param heavyHeal The amount of heavy healing provided by the move.
     */
    public Heal(String name, float lightHeal, float heavyHeal) {
        super(name);
        this.lightHeal = lightHeal;
        this.heavyHeal = heavyHeal;
    }

    /**
     * Gets the amount of light healing provided by the move.
     *
     * @return The amount of light healing.
     */
    public float getLightHeal() {
        return lightHeal;
    }

    /**
     * Gets the amount of heavy healing provided by the move.
     *
     * @return The amount of heavy healing.
     */
    public float getHeavyheal() {
        return heavyHeal;
    }
}
