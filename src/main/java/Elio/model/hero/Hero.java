package Elio.model.hero;

import Elio.model.Element;
import Elio.model.Position;
import Elio.model.attackStrategies.AttackStrategy;

public abstract class Hero extends Element {

    //movement vars
    private Direction direction = Direction.NONE;

    //hero image vars
    private final HeroType type;
    private final String color;

    //attacking pattern
    private AttackStrategy attackStrategy;
    private long lastAttackTime = 0;

    //hero stats vars
    private int HP;
    private int maxHP;
    private int damage;

    private final int levelCap = 50;
    private double xpGain = 1.0;
    private int shield = 0;
    private int maxShield = 0;
    private double lastTimeShieldRegenerated = 0;
    private double lastTimeTakenDamage = 0;

    //XP vars
    private int xp = 0;
    private int xpToNextLevel = 100;
    private int level = 1;

    public Hero(Position position, int maxHP, String color, HeroType type, int damage, AttackStrategy attackStrategy){
        super(position);
        this.maxHP = maxHP;
        this.HP = maxHP;
        this.color = color;
        this.type = type;
        this.damage = damage;
        this.attackStrategy = attackStrategy;
    }

    /*
    =================== XP & LEVEL UP METHODS ==================
     */

    public void addXp(int amount) {
        xp += (int) (amount * xpGain);
    }

    public int isLevelUp(){
        if(level == levelCap) return 0;
        if(xp < xpToNextLevel) return 0;
        int levelsAdded = 0;
        while (xp >= xpToNextLevel){
            xp -= xpToNextLevel;
            xpToNextLevel = (int) (1.3 * xpToNextLevel);
            level++;
            maxHP += 20;
            gainHP(20);
            levelsAdded++;
            if(level == levelCap) {
                xp = xpToNextLevel;
                break;
            }
        }
        return levelsAdded;
    }

    public int getXp() { return xp; }
    public int getXpToNextLevel() { return xpToNextLevel; }
    public double getXpGain() { return xpGain; }

    public int getLevel() { return level; }
    public int getLevelCap() { return levelCap; }

    public void setXpGain(double xpGain) { this.xpGain = xpGain; }
    public void setXp(int xp) { this.xp = xp; } //purely for testing reasons
    public void setLevel(int level) { this.level = level; } //same here

    /*
    =================== SHIELD METHODS ==================
    */

    //regenerate shield method
    public void regenerateShield(){
        double partition = 0.20;
        int recuperate = (int) (partition * maxShield);
        addShield(recuperate);
        lastTimeShieldRegenerated = 0;
    }

    public void addShield(int amount) { this.shield = Math.min(shield + amount, maxShield); }

    public void loseShield(int amount) { shield -= amount; }

    public int getShield() { return shield; }

    public int getMaxShield(){ return maxShield; }

    public void setMaxShield(int maxShield) { this.maxShield = maxShield; }

    public void setShield(int shield) { this.shield = Math.min(shield, maxShield); }

    /*
     =================== HP METHODS ==================
    */

    //check if hero is dead
    public boolean isDead(){ return HP <= 0; }

    public void loseHP(int amount) { this.HP -= calculateDamageTaken(amount); }
    public void gainHP(int amount) { this.HP = Math.min(HP + amount, maxHP); }

    public int getHP() { return HP; }
    public int getMaxHP() { return maxHP; }

    public void setHP(int HP) { this.HP = Math.min(HP, maxHP); }
    public void setMaxHP(int maxHP) { this.maxHP = maxHP; }

    /*
     =================== DAMAGE METHODS ==================
    */

    //calculating damage considering hardness or similar effects
    protected int calculateDamageTaken(int amount){
        return amount;
    }

    //method to take damage considering shield first
    public void takeDamage(int amount) {
        if (shield > 0) {
            int shieldDamage = Math.min(shield, amount);
            amount -= shieldDamage;
            loseShield(shieldDamage);
        }
        if (amount > 0) loseHP(amount);
        lastTimeTakenDamage = 0;
    }

    /*
     =================== RESOURCE METHODS ==================
    */

    //resource management abstract methods
    public abstract void decreaseResource();
    public abstract void updateResource(long deltaMillis);
    public abstract void updateResourceHitbox();

    public abstract int getResource();
    public abstract int getResourceHitbox();

    /*
     =================== OTHER GETTERS & SETTERS ==================
    */

    //getters
    public String getColor() { return color; }
    public Direction getDirection() { return direction; }
    public HeroType getType() { return type; }
    public int getDamage() { return damage; }
    public double getLastTimeShieldRegenerated() { return lastTimeShieldRegenerated; }
    public double getLastTimeTakenDamage(){ return lastTimeTakenDamage; }
    public AttackStrategy getAttackStrategy() { return attackStrategy; }
    public long getLastAttackTime() { return lastAttackTime; }

    //setters
    public void setDirection(Direction direction) { this.direction = direction; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setLastAttackTime(long lastAttackTime) { this.lastAttackTime = lastAttackTime; }
    public void setLastTimeShieldRegenerated(double lastTimeShieldRegenerated) { this.lastTimeShieldRegenerated = lastTimeShieldRegenerated; }
    public void setLastTimeTakenDamage(double lastTimeTakenDamage) { this.lastTimeTakenDamage = lastTimeTakenDamage; }
    public void setAttackStrategy(AttackStrategy strategy) { this.attackStrategy = strategy; } //for testing reasons

    /*
     =================== ENUMS ==================
    */
    
    public enum HeroType {WARRIOR, MAGE, GUNMAN};
    public enum Direction {NONE, UP, DOWN, LEFT, RIGHT}
}
