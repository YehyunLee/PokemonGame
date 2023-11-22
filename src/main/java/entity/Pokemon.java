package entity;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pokemon {
    String name = "";
    Integer health = 0;
    Integer attack = 0;
    Integer defense = 0;
    String moves = Arrays.toString(new Moves[0]);
    String frontSprite = "";
    String backSprite = "";

    Integer number = 0;

    Boolean isAlive = true;

    Integer speed = 0;


    // All methods to get Instance Attributes
    public String getName() {
        return name;
    }

    public Integer getSpeed() {return speed;}
    public Integer getHealth() {
        return health;
    }
    public Integer getAttack() {
        return attack;
    }
    public Integer getDefense() {
        return defense;
    }
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
    public String getFrontSprite() {
        return frontSprite;
    }
    public String getBackSprite() {
        return backSprite;
    }
    public Integer getNumber() {
        return number;
    }

    public Boolean getIsAlive() {return isAlive; }


    // All methods to set Instance Attributes
    public void setName(String name) {
        this.name = name;
    }
    public void setHealth(Integer health) {
        this.health = health;
    }
    public void setAttack(Integer attack) {
        this.attack = attack;
    }
    public void setDefense(Integer defense) {
        this.defense = defense;
    }
    public void setSpeed(Integer speed) {this.speed = speed; }
    public void setMoves(String moves) {
        this.moves = moves;
    }
    public void setFrontSprite(String frontSprite) {
        this.frontSprite = frontSprite;
    }
    public void setBackSprite(String backSprite) {
        this.backSprite = backSprite;
    }
    public void setNumber(int number) {
        this.number = number;
    }




    public void takeDammage (Double dammage) {
        health -= (int) Math.floor(dammage);
    }

    public void doHealing (double healingAmmount) {
        health += (int) Math.floor(healingAmmount);
    }

    public void increaseDefence(double defenceBuff) {
        defense += (int) Math.floor(defenceBuff);
    }

    public void die() {
        this.isAlive = false;
    }


    // Print the data for the Pokemon Object
    public void printAllStats() {
        System.out.println("Pokemon Data:");
//        System.out.println("Name: " + this.getName());
//        System.out.println("HP: " + this.getHealth());
//        System.out.println("Attack: " + this.getAttack());
//        System.out.println("Defense: " + this.getDefense());
//        System.out.println("Number: " + this.getNumber());
//        System.out.println("FrontSprite: " + this.getFrontSprite());
//        System.out.println("BackSprite: " + this.getBackSprite());
        System.out.println("IsAlive: " + this.getIsAlive());
    }




}