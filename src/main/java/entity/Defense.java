package entity;

public class Defense extends Moves {
    String name;
    float lightDefense;
    float heavyDefense;

    public Defense(String name, float lightDefense, float heavyDefense){
        super(name);
        this.lightDefense = lightDefense;
        this.heavyDefense = heavyDefense;
    }

    public float getLightDefense(){return lightDefense;}

    public float getHeavyDefense(){return heavyDefense;}
}
