package entity;

public class Pokemon {
    String name = "";
    Integer health = 0;
    Integer attack = 0;
    Integer defense = 0;
    entity.Move[] moves = new entity.Move[0];
    String frontSprite = "";
    String backSprite = "";

    // All methods to get Instance Attributes
    public String getName() {
        return name;
    }
    public Integer getHealth() {
        return health;
    }
    public Integer getAttack() {
        return attack;
    }
    public Integer getDefense() {
        return defense;
    }
    public entity.Move[] getMoves() {
        return moves;
    }
    public String getFrontSprite() {
        return frontSprite;
    }
    public String getBackSprite() {
        return backSprite;
    }


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
    public void setMoves(entity.Move[] moves) {
        this.moves = moves;
    }
    public void setFrontSprite(String frontSprite) {
        this.frontSprite = frontSprite;
    }
    public void setBackSprite(String backSprite) {
        this.backSprite = backSprite;
    }


    // Print the data for the Pokemon Object
    public void printAllStats() {
        System.out.println("Pokemon Data:");
        System.out.println("Name: " + this.getName());
        System.out.println("HP: " + this.getHealth());
        System.out.println("Attack: " + this.getAttack());
        System.out.println("Defense: " + this.getDefense());
    }
}