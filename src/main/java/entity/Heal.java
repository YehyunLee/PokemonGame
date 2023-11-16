package entity;

public class Heal extends Moves {
    String name;
    float healAmount;

    public Heal(String name, float healAmount){
        super(name);
        this.healAmount = healAmount;
    }

    public float getHeal(){return healAmount;}
}
