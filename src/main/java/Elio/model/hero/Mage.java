package Elio.model.hero;

import Elio.model.Position;
import Elio.model.attackStrategies.MagicAttackStrategy;

public class Mage extends Hero {
    private int mana = 5;
    private int maxMana = 5;
    private int manaHitbox = 11;
    private double manaReloadingSpeed = 2.3;
    private double lastReloaded = 0;


    public Mage(Position position){
        super(position, 100, "#CB6CE6", HeroType.MAGE, 40, new MagicAttackStrategy());
    }

    public Mage(int x, int y){ this(new Position(x,y)); }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = Math.min(mana, maxMana);
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public double getManaReloadingSpeed(){
        return manaReloadingSpeed;
    }

    public void setManaReloadingSpeed(double manaReloadingSpeed) {
        this.manaReloadingSpeed = manaReloadingSpeed;
    }

    public double getLastReloaded() { return lastReloaded; }

    @Override
    public int getResourceHitbox() {
        return manaHitbox;
    }

    @Override
    public void updateResourceHitbox(){
        int barSize = 6; //spaces, icon and brackets

        barSize += Math.min(getMaxMana(), 20); //capping amount of bars to 20

        int temp = getMaxMana();
        while (temp > 0){ //max mana digits
            temp /= 10;
            barSize++;
        }
        temp = getMana();
        if(temp == 0) barSize++; //mana digits
        while (temp > 0){
            temp /= 10;
            barSize++;
        }

        this.manaHitbox = barSize;
    }

    @Override
    public void decreaseResource() {
        if(mana> 0)
            mana--;
    }

    @Override
    public int getResource() {
        return mana;
    }

    @Override
    public void updateResource(long deltaMillis) {
        if(mana < maxMana) {
            double deltaSeconds = deltaMillis / 1000.0;
            lastReloaded += deltaSeconds;
            if (lastReloaded >= manaReloadingSpeed) {
                mana++;
                lastReloaded = 0;
            }
        }
    }
}
