package entity;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Pokemon class represents a Pokemon entity with various attributes such as name, health, attack, defense,
 * moves, sprites, number, and more. It provides methods to access and modify these attributes.
 */
public class Pokemon {

    // Instance attributes
    String name = "";
    Integer health = 0;
    Integer totalHealth = 0;
    Integer attack = 0;
    Integer defense = 0;
    String moves = Arrays.toString(new Moves[0]);
    String frontSprite = "";
    String backSprite = "";
    Integer number = 0;
    Boolean isAlive = true;
    Integer speed = 0;

    /**
     * Returns the name of the Pokemon.
     *
     * @return The name of the Pokemon.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current health of the Pokemon.
     *
     * @return The current health of the Pokemon.
     */
    public Integer getHealth() {
        return health;
    }

    /**
     * Returns the total health of the Pokemon.
     *
     * @return The total health of the Pokemon.
     */
    public Integer getTotalHealth() {
        return totalHealth;
    }

    /**
     * Returns the attack stat of the Pokemon.
     *
     * @return The attack stat of the Pokemon.
     */
    public Integer getAttack() {
        return attack;
    }

    /**
     * Returns the defense stat of the Pokemon.
     *
     * @return The defense stat of the Pokemon.
     */
    public Integer getDefense() {
        return defense;
    }

    /**
     * Returns the speed stat of the Pokemon.
     *
     * @return The speed stat of the Pokemon.
     */
    public Integer getSpeed() {
        return speed;
    }

    /**
     * Returns a map of moves available to the Pokemon, categorized by move type.
     *
     * @return A map containing move types and their respective moves with power values.
     */
    public Map<String, Map<String, Double>> getMoves() {

        Map<String, Map<String, Double>> moveMap = new HashMap<>();

        // Define a pattern to match move sections (e.g., Attack[...], Heal[...])
        Pattern sectionPattern = Pattern.compile("(\\w+)\\[(.*?)\\]");

        // Define a pattern to match individual moves (e.g., Light Attack: 6.0)
        Pattern movePattern = Pattern.compile("(\\w+ \\w+): (\\d+\\.\\d+)");

        Matcher sectionMatcher = sectionPattern.matcher(moves);

        while (sectionMatcher.find()) {
            String moveType = sectionMatcher.group(1);
            String movesData = sectionMatcher.group(2);

            Map<String, Double> moves = new HashMap<>();

            Matcher moveMatcher = movePattern.matcher(movesData);

            while (moveMatcher.find()) {
                String moveName = moveMatcher.group(1);
                double movePower = Double.parseDouble(moveMatcher.group(2));
                moves.put(moveName, movePower);
            }

            moveMap.put(moveType, moves);
        }

        return moveMap;
    }

    /**
     * Returns the front sprite image file path of the Pokemon.
     *
     * @return The file path of the front sprite image.
     */
    public String getFrontSprite() {
        return frontSprite;
    }

    /**
     * Returns the back sprite image file path of the Pokemon.
     *
     * @return The file path of the back sprite image.
     */
    public String getBackSprite() {
        return backSprite;
    }

    /**
     * Returns the Pokedex number of the Pokemon.
     *
     * @return The Pokedex number of the Pokemon.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Returns the status of the Pokemon's life, indicating whether it is alive or not.
     *
     * @return True if the Pokemon is alive, false otherwise.
     */
    public Boolean getIsAlive() {
        return isAlive;
    }

    // Methods to set Instance Attributes

    /**
     * Sets the name of the Pokemon.
     *
     * @param name The name to be set for the Pokemon.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the current health of the Pokemon.
     *
     * @param health The health value to be set for the Pokemon.
     */
    public void setHealth(Integer health) {
        this.health = health;
    }

    /**
     * Sets the total health of the Pokemon.
     *
     * @param totalHealth The total health value to be set for the Pokemon.
     */
    public void setTotalHealth(Integer totalHealth) {
        this.totalHealth = totalHealth;
    }

    /**
     * Sets the attack stat of the Pokemon.
     *
     * @param attack The attack stat value to be set for the Pokemon.
     */
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    /**
     * Sets the defense stat of the Pokemon.
     *
     * @param defense The defense stat value to be set for the Pokemon.
     */
    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    /**
     * Sets the available moves for the Pokemon.
     *
     * @param moves A string representation of moves to be set for the Pokemon.
     */
    public void setMoves(String moves) {
        this.moves = moves;
    }

    /**
     * Sets the file path of the front sprite image for the Pokemon.
     *
     * @param frontSprite The file path of the front sprite image.
     */
    public void setFrontSprite(String frontSprite) {
        this.frontSprite = frontSprite;
    }

    /**
     * Sets the file path of the back sprite image for the Pokemon.
     *
     * @param backSprite The file path of the back sprite image.
     */
    public void setBackSprite(String backSprite) {
        this.backSprite = backSprite;
    }

    /**
     * Sets the Pokedex number of the Pokemon.
     *
     * @param number The Pokedex number to be set for the Pokemon.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Reduces the health of the Pokemon by the specified damage amount.
     *
     * @param dammage The amount of damage to be applied to the Pokemon.
     */
    public void takeDammage (Double dammage) {
        health -= (int) Math.floor(dammage);
    }

    /**
     * Increases the health of the Pokemon by the specified healing amount.
     *
     * @param healingAmmount The amount of healing to be applied to the Pokemon.
     */
    public void doHealing (double healingAmmount) {
        health += (int) Math.floor(healingAmmount);
    }

    /**
     * Increases the health of the Pokemon by the specified healing amount.
     *
     * @param healingAmmount The amount of healing to be applied to the Pokemon.
     */
    public void doTotalHealing (double healingAmmount) {
        health += (int) Math.floor(healingAmmount);
    }

    /**
     * Increases the defense of the Pokemon by the specified defense buff.
     *
     * @param defenceBuff The amount by which to increase the Pokemon's defense.
     */
    public void increaseDefence(double defenceBuff) {
        defense += (int) Math.floor(defenceBuff);
    }

    /**
     * Marks the Pokemon as deceased.
     */
    public void die() {
        this.isAlive = false;
    }


}