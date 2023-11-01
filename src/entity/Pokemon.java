package src.entity;

public class Pokemon {
    String name = "";
    Double health = 0.0;
    Double attack = 0.0;
    Double defense = 0.0;
    Move[] moves = new Move[0];
    String frontSprite = "";
    String backSprite = "";

    public Pokemon(String name, Double health, Double attack, Double defense, Move[] moves, String frontSprite, String backSprite) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.moves = moves;
        this.frontSprite = frontSprite;
        this.backSprite = backSprite;
    }

    public String getName() {
        return name;
    }
    public Double getHealth() {
        return health;
    }
    public Double getAttack() {
        return attack;
    }
    public Double getDefense() {
        return defense;
    }
    public Move[] getMoves() {
        return moves;
    }
    public String getFrontSprite() {
        return frontSprite;
    }
    public String getBackSprite() {
        return backSprite;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }
    public Double setHealth(Double health) {
        this.health = health;
        return health;
    }
    public Double setAttack(Double attack) {
        this.attack = attack;
        return attack;
    }
    public Double setDefense(Double defense) {
        this.defense = defense;
        return defense;
    }
    public Move[] setMoves(Move[] moves) {
        this.moves = moves;
        return moves;
    }
    public String setFrontSprite(String frontSprite) {
        this.frontSprite = frontSprite;
        return frontSprite;
    }
    public String setBackSprite(String backSprite) {
        this.backSprite = backSprite;
        return backSprite;
    }
}
