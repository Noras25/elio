package Elio.model.hero;

import Elio.model.Position;
import Elio.model.attackStrategies.SwordAttackStrategy;

public class Warrior extends Hero {
    private double lifeSteal = 0.15;
    private double hardness = 0.90;

    public Warrior(Position position){
        super(position, 120, "#FFD700", HeroType.WARRIOR, 50, new SwordAttackStrategy());
    }

    public Warrior(int x, int y){ this(new Position(x,y)); }

    public double getLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(double lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public double getHardness() {
        return hardness;
    }

    public void setHardness(double hardness) {
        this.hardness = hardness;
    }

    @Override
    protected int calculateDamageTaken(int damageAmount) {
        return (int)(damageAmount * this.hardness);
    }

    //warrior doesn't have a resource
    @Override
    public void updateResourceHitbox() { }

    @Override
    public int getResourceHitbox() {
        return 0;
    }

    @Override
    public void decreaseResource() { }

    @Override
    public int getResource() {
        return 0;
    }

    @Override
    public void updateResource(long deltaMillis) { }

    public void lifeSteal(int damageAmount) {
        int healing = (int) (damageAmount * lifeSteal);
        this.gainHP(healing);
    }


}
