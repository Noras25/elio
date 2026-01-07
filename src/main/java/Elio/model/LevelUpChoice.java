package Elio.model;

import java.awt.image.AffineTransformOp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LevelUpChoice {
    private final LevelUpType type;
    private final Rarity rarity;
    private final int number;

    private static final int COMMON_CHANCE = 58;
    private static final int UNCOMMON_CHANCE = 88;
    private static final int RARE_CHANCE = 98;
    private static Random RANDOM = new Random();

    public LevelUpChoice(int number, Rarity rarity, LevelUpType type) { //for testing purposes
        this.number = number;
        this.rarity = rarity;
        this.type = type;
    }

    public LevelUpChoice(int number){
        this.number = number;
        this.rarity = randomRarity();
        this.type = randomType();
    }

    public LevelUpType randomType() {
        //select the type based on the enum values
        LevelUpType[] types = LevelUpType.values();
        return types[RANDOM.nextInt(types.length)];
    }

    public Rarity randomRarity() {
        int rng = RANDOM.nextInt(100);

        if(rng < COMMON_CHANCE) return Rarity.COMMON;
        if(rng < UNCOMMON_CHANCE) return Rarity.UNCOMMON;
        if(rng < RARE_CHANCE) return Rarity.RARE;
        return Rarity.LEGENDARY;
    }


    public enum LevelUpType {HP, DAMAGE, SHIELD, XP, CLASS_SPECIFIC_1, CLASS_SPECIFIC_2};
    public enum Rarity {COMMON, UNCOMMON, RARE, LEGENDARY};


    //getters

    public LevelUpType getType() {
        return type;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getNumber() {
        return number;
    }

    //for testing purposes
    public void setRandom(Random random) {
        RANDOM = random;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        if (obj == this) return true;

        return ((LevelUpChoice)obj).getRarity() == this.rarity && ((LevelUpChoice)obj).getType() == this.type;
    }
}
