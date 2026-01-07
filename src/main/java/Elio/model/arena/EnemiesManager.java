package Elio.model.arena;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.enemy.Enemy;
import Elio.model.hero.Hero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnemiesManager {

    private final List<Enemy> enemies;
    private final Arena arena;

    //constructor
    public EnemiesManager(Arena arena) {
        this.enemies = new ArrayList<>();
        this.arena = arena;
    }

    //add enemies
    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    //update enemies movement and handle collisions
    public void updateEnemies(long deltaMillis){
        double deltaSeconds = (double)deltaMillis / 1000;

        Iterator<Enemy> it = enemies.iterator();
        while (it.hasNext()) {
            Enemy e = it.next();
            if (e.isDead()) { arena.getHero().addXp(e.getXpReward()); it.remove(); continue; }
            e.setLastMoved(e.getLastMoved() + deltaSeconds);

            double secondsPerMove =  1.0 / (2.0 * e.getSpeed() * (0.9 + Math.random() * 0.2));  //last part for making the speeds not fixed so that all zombies of the same type don't walk in perfect lock step
            if(e.getLastMoved() >= secondsPerMove) {
                Position pos = e.move(arena);
                if (pos.equals(arena.getHero().getPosition())) {
                    arena.getHero().takeDamage(e.getDamage());
                } else if (arena.canMove(pos)) {
                    e.setPosition(pos);
                }
                e.setLastMoved(0);
            }
        }
    }
}
