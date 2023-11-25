package entity;

public class Attack extends Moves {

    String name;
    float lightAttack;
    float basicAttack;
    float trueAttack;
    float heavyAttack;

    public Attack(String name, float lightAttack, float trueAttack, float heavyAttack){
        super(name);
        this.lightAttack = lightAttack;
        this.trueAttack = trueAttack;
        this.heavyAttack = heavyAttack;
    }


    public float getLightAttack(){return lightAttack;}
    public float getTrueAttack(){return trueAttack;}
    public float getHeavyAttack(){return heavyAttack;}
}
