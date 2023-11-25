package entity;

public class Heal extends Moves {
    String name;
    float lightHeal;
    float heavyheal;

    public Heal(String name, float lightHeal, float heavyHeal){
        super(name);
        this.lightHeal = lightHeal;
        this.heavyheal = heavyHeal;
    }

    public float getLightHeal(){return lightHeal;}

    public float getHeavyheal(){return heavyheal;}
}
