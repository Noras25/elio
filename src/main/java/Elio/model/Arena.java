package Elio.model;

import Elio.model.arena.HUDManager;
import Elio.model.arena.EnemiesManager;
import Elio.model.arena.ProjectileManager;
import Elio.model.attackStrategies.Projectile;
import Elio.model.attackStrategies.SwordAttack;
import Elio.model.enemy.Enemy;
import Elio.model.hero.Hero;
import Elio.model.hero.Warrior;
import Elio.model.arena.WaveManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Arena {

    //arena characteristics
    private String backgroundColor = "#002B17";
    final private int width;
    final private int height;
    private static final Random RANDOM = new Random();

    //active elements
    final private Hero hero;
    private final ProjectileManager projectileManager; //separated the logic for better organization
    private final EnemiesManager enemiesManager;
    private final WaveManager waveManager;
    private final HUDManager hudManager;
    private final List<SwordAttack> effects;
    private long timePlayed = 0;

    public Arena(int width, int height, Hero hero){
        this.width = width;
        this.height = height;
        this.hero = hero;
        this.effects = new ArrayList<>();
        this.waveManager = new WaveManager(this);
        this.projectileManager = new ProjectileManager(this);
        this.enemiesManager = new EnemiesManager(this);
        this.hudManager = new HUDManager(this);
    }

    //public method for generating a random position that is available
    public Position randomFreePosition() {
        Position p;
        do { p = new Position(RANDOM.nextInt(width), RANDOM.nextInt(height));
        } while (!canMove(p));
        return p;
    }

    /*
    ================================ PROJECTILE METHODS ================================================
     */
    //spawn projectile using object pooling and then update active projectiles
    public void spawnProjectile(Position position, Hero.Direction direction, int damage, float speed, String color, int maxRange, String symbol) {
        projectileManager.spawnProjectile(position, direction, damage, speed, color, maxRange, symbol);
    }

    /*
    ================================= SWORD ATTACK METHODS ================================================
     */

    //spawn sword attack effect
    public void spawnSword(Position position, String symbol, String color, double duration) {
        effects.add(new SwordAttack(position, symbol, color, duration));
    }



    public void updateSword(long deltaMillis) {
        double deltaSeconds = (double) deltaMillis / 1000;

        Iterator<SwordAttack> it = effects.iterator();
        while (it.hasNext()) {
            SwordAttack attack = it.next();
            attack.decreaseDuration(deltaSeconds);
            if (!attack.isActive()) {
                it.remove();
            }
        }
    }
    /*
    ================================= ENEMY METHODS ================================================
     */

    //damage enemy at position
    public boolean damageEnemyAt(Position position, int damage) {
        for (Enemy enemy : enemiesManager.getEnemies()) {
            if (enemy.getPosition().equals(position)) {
                enemy.loseHP(damage);
                if(hero.getType() == Hero.HeroType.WARRIOR) ((Warrior)hero).lifeSteal(damage);
                return true;
            }
        }
        return false;
    }

    public void addEnemy(Enemy enemy) {
        // Delegate to the manager
        enemiesManager.addEnemy(enemy);
    }

    /*
    ================================= HUD METHODS ================================================
     */

    public boolean checkHitboxCollision(Position position){
        return hudManager.checkHitboxCollision(position);
    }

    // Call this every frame to add time (e.g., 25ms)
    public void increaseTime(long msPassed) {
        timePlayed += msPassed;
    }

    public long getTimePlayed() {
        return timePlayed;
    }

    /*
    ================================= HERO POSITION METHODS ================================================
     */

    //check if hero can currently move to position
    public boolean canMove(Position position){
        //check bounds of arena
        if(position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height) {
            return false;
        }

        //check HUD hitboxes
        if(checkHitboxCollision(position)) {
            return false;
        }

        //check enemy collision
        for (Enemy e : enemiesManager.getEnemies()) {
                if (e.getPosition().equals(position)) return false;
            }

        return !position.equals(hero.getPosition());
    }

    //if hero is in illegal position, move him to nearest legal position
    public void checkIfHeroInIllegalPosition(){
        if (checkHitboxCollision(hero.getPosition())){
           if(canMove(new Position(hero.getPosition().getX()-1, hero.getPosition().getY()))){
               hero.setPosition(new Position(hero.getPosition().getX()-1, hero.getPosition().getY()));
           }
           else if(canMove(new Position(hero.getPosition().getX()+1, hero.getPosition().getY()))){
               hero.setPosition(new Position(hero.getPosition().getX()+1, hero.getPosition().getY()));
           }
           else if(canMove(new Position(hero.getPosition().getX(), hero.getPosition().getY()+1))){
               hero.setPosition(new Position(hero.getPosition().getX(), hero.getPosition().getY()+1));
           }
           else if(canMove(new Position(hero.getPosition().getX(), hero.getPosition().getY()-1))) {
               hero.setPosition(new Position(hero.getPosition().getX(), hero.getPosition().getY()-1));
           }
           else hero.setPosition(randomFreePosition());
        }
    }

    public void updateHero(int wave, long deltaMillis){
        double deltaSeconds = deltaMillis / 1000.0;

        hero.setLastTimeTakenDamage(hero.getLastTimeTakenDamage() + deltaSeconds);
        hero.setLastTimeShieldRegenerated(hero.getLastTimeShieldRegenerated() + deltaSeconds);

        hero.updateResource(deltaMillis);

        //half HP regen on wave complete
        if(wave < waveManager.getCurrentWave()) hero.gainHP(hero.getMaxHP() / 2);
        //shield regeneration
        if (hero.getLastTimeTakenDamage() >= 5.0) {
            if (hero.getLastTimeShieldRegenerated() >= 2.0) {
                hero.regenerateShield();
            }
        }
    }

    public int isHeroLevelUp () {
        return hero.isLevelUp();
    }

    /*
    ================================= UPDATE & GENERAL METHODS ================================================
     */

    //update method called every frame
    public void update(long deltaMillis){
        int temp = waveManager.getCurrentWave();
        waveManager.update(deltaMillis);
        updateHero(temp, deltaMillis);
        enemiesManager.updateEnemies(deltaMillis);
        updateSword(deltaMillis);
        projectileManager.updateProjectiles(deltaMillis);
        hudManager.updateBarHitboxes();
        checkIfHeroInIllegalPosition();
    }

    //check if game over
    public boolean isGameOver(){
        return hero.isDead();
    }

    //getters for active elements
    public Hero getHero() { return hero; }
    public WaveManager getWaveManager(){ return waveManager; }
    public List<Projectile> getActiveProjectiles() { return this.projectileManager.getActiveProjectiles(); }
    public List<Enemy> getEnemies() { return enemiesManager.getEnemies(); }
    public List<SwordAttack> getEffects() { return effects; }

    //getters & setters for arena characteristics
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public String getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(String backgroundColor) { this.backgroundColor = backgroundColor; }
}
