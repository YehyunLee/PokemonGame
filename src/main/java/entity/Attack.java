package entity;

/**
 * The {@code Attack} class represents a type of move inherited from the {@code Moves} class.
 * This class provides methods to access and retrieve information about the different types of attacks.
 */
public class Attack extends Moves {

    /** The power of the light attack. */
    private float lightAttack;

    /** The power of the true attack. */
    private float trueAttack;

    /** The power of the heavy attack. */
    private float heavyAttack;

    /**
     * Constructs a new {@code Attack} with the specified name, light attack power, true attack power,
     * and heavy attack power.
     *
     * @param name         the name of the attack.
     * @param lightAttack  the power of the light attack.
     * @param trueAttack   the power of the true attack.
     * @param heavyAttack  the power of the heavy attack.
     */
    public Attack(String name, float lightAttack, float trueAttack, float heavyAttack) {
        super(name);
        this.lightAttack = lightAttack;
        this.trueAttack = trueAttack;
        this.heavyAttack = heavyAttack;
    }

    /**
     * Gets the power of the light attack.
     *
     * @return the power of the light attack.
     */
    public float getLightAttack() {
        return lightAttack;
    }

    /**
     * Gets the power of the true attack.
     *
     * @return the power of the true attack.
     */
    public float getTrueAttack() {
        return trueAttack;
    }

    /**
     * Gets the power of the heavy attack.
     *
     * @return the power of the heavy attack.
     */
    public float getHeavyAttack() {
        return heavyAttack;
    }
}
