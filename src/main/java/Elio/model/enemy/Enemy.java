package Elio.model.enemy;

import Elio.model.Arena;
import Elio.model.Element;
import Elio.model.Position;
import Elio.model.movingStrategies.MovingStrategy;

public abstract class Enemy extends Element {
    private int HP;
    final private int maxHP;
    final private String color;
    final private Enemy.EnemyType type;
    final private int damage;
    final private int xpReward;
    final private double speed;
    private final MovingStrategy strategy;
    private double lastMoved = 0; //in seconds

    public Enemy(Position position, int maxHP, String color, Enemy.EnemyType type, int damage, int xpReward, double speed){
        super(position);
        this.maxHP = maxHP;
        this.HP = maxHP;
        this.color = color;
        this.type = type;
        this.damage = damage;
        this.xpReward = xpReward;
        this.speed = speed;
        this.strategy = createStrategy();
    }

    public Position move(Arena arena){
        return strategy.move(this, arena);
    }

    public boolean isDead(){
        return HP <= 0;
    }

    //getters
    public String getColor() {
        return color;
    }
    public int getHP() {
        return HP;
    }
    public int getMaxHP() {
        return maxHP;
    }

    public EnemyType getType() { return type; }
    public double getSpeed() { return speed; }
    public int getDamage() { return damage; }
    public int getXpReward() { return xpReward; }

    public double getLastMoved() {
        return lastMoved;
    }

    public MovingStrategy getStrategy() { return strategy; } //for testing purposes

    //setters
    public void setHP(int HP) {
        this.HP = HP;
    }
    public void loseHP(int HP) { this.HP = this.HP - HP; }

    public void setLastMoved(double lastMoved) {
        this.lastMoved = lastMoved;
    }
    public abstract MovingStrategy createStrategy();

    public enum EnemyType {CRAWLER, BRUISER, RUNNER, BOSS};

}