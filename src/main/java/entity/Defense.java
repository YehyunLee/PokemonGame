package entity;

/**
 * The {@code Defense} class represents a defensive move in the game, extending the {@link Moves} class.
 * It includes attributes for light defense and heavy defense of the defensive move.

 */
public class Defense extends Moves {

    float lightDefense;

    float heavyDefense;

    /**
     * Constructs a new {@code Defense} object with the specified name, light defense, and heavy defense values.
     *
     * @param name The name of the defensive move.
     * @param lightDefense The light defense value for the defensive move.
     * @param heavyDefense The heavy defense value for the defensive move.
     */
    public Defense(String name, float lightDefense, float heavyDefense) {
        super(name);
        this.lightDefense = lightDefense;
        this.heavyDefense = heavyDefense;
    }

    /**
     * Retrieves the light defense value of the defensive move.
     *
     * @return The light defense value.
     */
    public float getLightDefense() {
        return lightDefense;
    }

    /**
     * Retrieves the heavy defense value of the defensive move.
     *
     * @return The heavy defense value.
     */
    public float getHeavyDefense() {
        return heavyDefense;
    }
}

