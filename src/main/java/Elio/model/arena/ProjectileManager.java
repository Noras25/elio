package Elio.model.arena;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.attackStrategies.Projectile;
import Elio.model.attackStrategies.ProjectilePool;
import Elio.model.enemy.Enemy;
import Elio.model.hero.Hero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectileManager {
    private final ProjectilePool projectilePool;
    private final List<Projectile> activeProjectiles;
    private final Arena arena;

    //constructor
    public ProjectileManager(Arena arena) {
        this.projectilePool = new ProjectilePool();
        this.activeProjectiles = new ArrayList<>();
        this.arena = arena;
    }

    //spawn projectile using object pooling and then update active projectiles
    public void spawnProjectile(Position position, Hero.Direction direction, int damage, double speed, String color, int maxRange, String symbol) {
        Projectile projectile = projectilePool.getProjectile();
        projectile.init(position, direction, damage, speed, color, maxRange, symbol);
        activeProjectiles.add(projectile);
    }

    //update projectiles movement and handle collisions
    public void updateProjectiles(long deltaMillis) {
        double deltaSeconds = (double)deltaMillis / 1000;
        Iterator<Projectile> it = activeProjectiles.iterator();

        while (it.hasNext()) {
            Projectile p = it.next();
            p.projectileMove(deltaSeconds);
            if(arena.damageEnemyAt(p.getPosition(), p.getDamage()) || !arena.canMove(p.getPosition()) || p.rangeExceeded()) {
                projectilePool.releaseProjectile(p);
                it.remove();
            }
        }
    }

    //getter for active projectiles
    public List<Projectile> getActiveProjectiles() {
        return activeProjectiles;
    }
}
