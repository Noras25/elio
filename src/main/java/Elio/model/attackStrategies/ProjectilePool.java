package Elio.model.attackStrategies;

import java.util.ArrayList;
import java.util.List;

public class ProjectilePool {
    private List<Projectile> availableProjectiles = new ArrayList<>();

    public Projectile getProjectile() {
        if (availableProjectiles.isEmpty()) {
            return new Projectile();
        } else {
            Projectile projectile = availableProjectiles.remove(availableProjectiles.size() - 1);
            return projectile;
        }
    }

    public void releaseProjectile(Projectile projectile) {
        availableProjectiles.add(projectile);
    }
}
