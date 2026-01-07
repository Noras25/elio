package model.attackStrategies;

import Elio.model.attackStrategies.Projectile;
import Elio.model.attackStrategies.ProjectilePool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectilePoolTest {
    @Test
    public void testProjectilePooling() {
        ProjectilePool pool = new ProjectilePool();

        // get a projectile from the pool
        Projectile projectile1 = pool.getProjectile();

        // release the projectile back to the pool
        pool.releaseProjectile(projectile1);

        // get another projectile from the pool
        Projectile projectile2 = pool.getProjectile();

        // verify that the same projectile instance is reused
        assertEquals(projectile1, projectile2);
    }
}
