package entity;

/**
 * The `Pokemon` class represents a Pokemon entity with attributes such as name,
 * HP, attack, and defense, and front and back sprite images.
 */
public class Pokemon {
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private String frontImg;

    private String backImg;

    /**
     * Gets the name of the Pokemon.
     *
     * @return The name of the Pokemon.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Pokemon.
     *
     * @param name The name of the Pokemon.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the HP (Hit Points) of the Pokemon.
     *
     * @return The HP of the Pokemon.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets the HP (Hit Points) of the Pokemon.
     *
     * @param hp The HP of the Pokemon.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Gets the attack power of the Pokemon.
     *
     * @return The attack power of the Pokemon.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Sets the attack power of the Pokemon.
     *
     * @param attack The attack power of the Pokemon.
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Gets the defense power of the Pokemon.
     *
     * @return The defense power of the Pokemon.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Sets the defense power of the Pokemon.
     *
     * @param defense The defense power of the Pokemon.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }


    public void printAllStats() {
        // Print the data for the Pokemon Object
        System.out.println("Pokemon Data:");
        System.out.println("Name: " + this.getName());
        System.out.println("HP: " + this.getHp());
        System.out.println("Attack: " + this.getAttack());
        System.out.println("Defense: " + this.getDefense());
    }
}