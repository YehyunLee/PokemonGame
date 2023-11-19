package entity;

public class Pokemon {
    String name = "";
    Integer health = 0;
    Integer attack = 0;
    Integer defense = 0;
    Moves[] moves = new Moves[0];
    String frontSprite = "";
    String backSprite = "";

    Integer number = 0;

    Boolean isAlive = true;

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
    public Moves[] getMoves() {
        return moves;
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
    public void setMoves(Moves[] moves) {
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