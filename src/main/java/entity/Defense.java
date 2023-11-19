package entity;

public class Defense extends Moves {
    String name;
    float defense;

    public Defense(String name, float defense){
        super(name);
        this.defense = defense;
    }

    public float getDefense(){return defense;}
}
