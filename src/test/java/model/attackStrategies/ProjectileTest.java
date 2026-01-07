package model.attackStrategies;

import Elio.model.Position;
import Elio.model.attackStrategies.Projectile;
import Elio.model.hero.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectileTest {
    private Projectile projectile;
    private Position position;

    @BeforeEach
    public void setup() {
        projectile = new Projectile();
        position = new Position(5, 5);


    }
    @Test
    public void testInit() {
        projectile.init(position, Elio.model.hero.Hero.Direction.UP, 10, 2.0, "#FF0000", 5, "*");
        assertEquals(position, projectile.getPosition());
        assertEquals(10, projectile.getDamage());
        assertEquals("#FF0000", projectile.getColor());
        assertEquals("*", projectile.getSymbol());
    }

    @Test
    public void testProjectileMoveUp() {
        projectile.init(position, Elio.model.hero.Hero.Direction.UP, 10, 2.0, "#FF0000", 5, "*");
        Position initialPosition = projectile.getPosition();
        //it should move immediately because the initial position coincides with the hero
        //otherwise we would not be able to damage adjacent enemies right after shooting
        projectile.projectileMove(0.3); // less than required time to move
        assertNotEquals(initialPosition, projectile.getPosition()); // should not have moved

        projectile.projectileMove(0.20); // total 0.20 seconds, should not move again
        Position expectedPosition = initialPosition.getNeighbor(Hero.Direction.UP);
        assertEquals(expectedPosition, projectile.getPosition());

        projectile.projectileMove(0.20); // total 0.40 seconds, should not move again
        assertNotEquals(expectedPosition, projectile.getPosition());

    }

    @Test
    public void testProjectileMoveDown() {
        projectile.init(position, Elio.model.hero.Hero.Direction.DOWN, 10, 2.0, "#FF0000", 5, "*");
        Position initialPosition = projectile.getPosition();
        //it should move immediately because the initial position coincides with the hero
        //otherwise we would not be able to damage adjacent enemies right after shooting
        projectile.projectileMove(0.3); // less than required time to move
        assertNotEquals(initialPosition, projectile.getPosition()); // should not have moved

        projectile.projectileMove(0.20); // total 0.20 seconds, should not move again
        Position expectedPosition = initialPosition.getNeighbor(Hero.Direction.DOWN);
        assertEquals(expectedPosition, projectile.getPosition());

        projectile.projectileMove(0.20); // total 0.40 seconds, should not move again
        assertNotEquals(expectedPosition, projectile.getPosition());

    }

    @Test
    public void testProjectileMoveLeft() {
        projectile.init(position, Elio.model.hero.Hero.Direction.LEFT, 10, 2.0, "#FF0000", 5, "*");
        Position initialPosition = projectile.getPosition();
        //it should move immediately because the initial position coincides with the hero
        //otherwise we would not be able to damage adjacent enemies right after shooting
        projectile.projectileMove(0.3); // less than required time to move
        assertNotEquals(initialPosition, projectile.getPosition()); // should not have moved

        projectile.projectileMove(0.20); // total 0.20 seconds, should not move again
        Position expectedPosition = initialPosition.getNeighbor(Hero.Direction.LEFT);
        assertEquals(expectedPosition, projectile.getPosition());

        projectile.projectileMove(0.20); // total 0.40 seconds, should not move again
        assertNotEquals(expectedPosition, projectile.getPosition());

    }

    @Test
    public void testProjectileMoveRight() {
        projectile.init(position, Elio.model.hero.Hero.Direction.RIGHT, 10, 2.0, "#FF0000", 5, "*");
        Position initialPosition = projectile.getPosition();
        //it should move immediately because the initial position coincides with the hero
        //otherwise we would not be able to damage adjacent enemies right after shooting
        projectile.projectileMove(0.3); // less than required time to move
        assertNotEquals(initialPosition, projectile.getPosition()); // should not have moved

        projectile.projectileMove(0.20); // total 0.20 seconds, should not move again
        Position expectedPosition = initialPosition.getNeighbor(Hero.Direction.RIGHT);
        assertEquals(expectedPosition, projectile.getPosition());

        projectile.projectileMove(0.20); // total 0.40 seconds, should not move again
        assertNotEquals(expectedPosition, projectile.getPosition());

    }

    @Test
    public void testProjectileNotMove() {
        projectile.init(position, Elio.model.hero.Hero.Direction.NONE, 10, 2.0, "#FF0000", 5, "*");
        Position initialPosition = projectile.getPosition();
        //it should not move at all
        projectile.projectileMove(0.3); // less than required time to move
        assertEquals(initialPosition, projectile.getPosition()); // should not have moved
    }

    @Test

    public void testRangeExceeded() {
        projectile.init(position, Elio.model.hero.Hero.Direction.UP, 10, 2.0, "#FF0000", 5, "*");
        for (int i = 0; i < 6; i++) {
            projectile.projectileMove(0.5);
        }
        assertTrue(projectile.rangeExceeded());
    }
}
