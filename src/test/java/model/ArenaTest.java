package model;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.attackStrategies.Projectile;
import Elio.model.attackStrategies.SwordAttack;
import Elio.model.enemy.*;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaTest {
    private Arena arena;

    @BeforeEach
    public void setup(){
        Hero hero = HeroFactory.createHero(Hero.HeroType.WARRIOR, 5, 5);
        arena = new Arena(100, 100, hero);
    }

    @Test
    public void damageEnemiesWarrior(){
        Enemy enemy1 = new Crawler(new Position(15,15), 1);
        Enemy enemy2 = new Bruiser(10,10, 1);

        arena.addEnemy(enemy1);
        arena.addEnemy(enemy2);

        List<Enemy> list = new ArrayList<>();
        list.add(enemy1);
        list.add(enemy2);

        assertEquals(list, arena.getEnemies());

        arena.getHero().setHP(50);

        Assertions.assertTrue(arena.damageEnemyAt(new Position(15,15), 100));

        assertEquals(20, enemy1.getHP());
        assertEquals(65, arena.getHero().getHP()); //since hero is a warrior he should recover hp
        assertEquals(90, enemy2.getHP());

        assertFalse(arena.damageEnemyAt(arena.getHero().getPosition(), 2000));
    }

    @Test
    public void damageEnemiesNotWarrior(){
        Hero diffHero = HeroFactory.createHero(Hero.HeroType.MAGE, 7,7);
        Arena diffArena = new Arena(200, 200, diffHero);
        assertEquals(diffHero, diffArena.getHero());

        Enemy enemy1 = new Crawler(new Position(15,15), 1);
        Enemy enemy2 = new Bruiser(10,10, 1);

        diffArena.addEnemy(enemy1);
        diffArena.addEnemy(enemy2);

        diffArena.getHero().setHP(50);

        Assertions.assertTrue(diffArena.damageEnemyAt(new Position(10,10), 100));

        assertEquals(50, diffArena.getHero().getHP()); //since hero is not a warrior in this case, hp should remain the same

    }

    @Test
    public void gameOver(){
        assertFalse(arena.isGameOver());

        arena.getHero().setHP(0);
        Assertions.assertTrue(arena.isGameOver());
    }

    @Test
    public void colorAndSize(){
        assertEquals(100, arena.getWidth());
        assertEquals(100, arena.getHeight());

        assertEquals("#002B17", arena.getBackgroundColor());

        arena.setBackgroundColor("000000");
        assertEquals("000000", arena.getBackgroundColor());
    }

    @Test
    public void canMoveEdges() {
        assertFalse(arena.canMove(new Position(5, 5)));

        assertFalse(arena.canMove(new Position(100, 5)));

        assertFalse(arena.canMove(new Position(4, 100)));

        assertFalse(arena.canMove(new Position(-1, 5)));

        assertFalse(arena.canMove(new Position(7, -300)));

        Assertions.assertTrue(arena.canMove(new Position(8, 8)));
    }

    @Test
    public void canMoveEnemiesAndHud(){
        //should be able to move when getEnemies is empty
        assertTrue(arena.canMove(new Position(4,2)));

        //adding an enemy to test enemy collision
        arena.addEnemy(new Boss(4,2,10));

        //shouldn't be able to move there now
        assertFalse(arena.canMove(new Position(4,2)));

        //should still be able to move in other positions
        Assertions.assertTrue(arena.canMove(new Position(0,0))); //not inside hp bar (because it doesn't exist yet)

        arena.update(0); //initiating hud hitboxes

        assertFalse(arena.canMove(new Position(13,0))); //inside hp bar
        assertFalse(arena.canMove(new Position(87, 0))); //inside xp bar
        Assertions.assertTrue(arena.canMove(new Position(5,1))); //not inside shield bar cause hero doesn't have shield yet

        arena.getHero().setMaxShield(25);
        arena.update(0); //updating hitboxes to get the shield one

        assertFalse(arena.canMove(new Position(5,1))); //now inside shield bar
    }

    @Test
    public void time(){
        assertEquals(0, arena.getTimePlayed());

        arena.increaseTime(3300);
        assertEquals(3300, arena.getTimePlayed());
    }

    @Test
    public void heroInLegalPosition() {
        Position temp = arena.getHero().getPosition();

        arena.checkIfHeroInIllegalPosition();

        assertEquals(temp, arena.getHero().getPosition());
    }

    @Test
    public void heroNotInLegalPositionLeft() {
        arena.update(0); //initiating hud hitboxes

        arena.getHero().setPosition(new Position(arena.getWidth() - 29, 0)); //setting hero in an illegal position (left edge of xp bar)
        arena.addEnemy(new Bruiser(new Position(arena.getWidth() - 29, 1), 1)); //making sure can't go down (making the position to the left the only available one)

        arena.checkIfHeroInIllegalPosition();

        assertEquals(new Position(arena.getWidth() - 30, 0), arena.getHero().getPosition());
    }

    @Test
    public void heroNotInLegalPositionRight() {
        arena.update(0); //initiating hud hitboxes

        arena.getHero().setPosition(new Position(31, 0)); //setting hero in an illegal position (right edge of hp bar)
        arena.addEnemy(new Bruiser(new Position(31, 1), 1)); //making sure can't go down (making the position to the right the only available one)

        arena.checkIfHeroInIllegalPosition();

        assertEquals(new Position(32, 0), arena.getHero().getPosition());
    }

    @Test
    public void heroNotInLegalPositionDown() {
        arena.update(0); //initiating hud hitboxes

        arena.getHero().setPosition(new Position(15, 0)); //setting hero in an illegal position (middle of hp bar, can only go down)

        arena.checkIfHeroInIllegalPosition();

        assertEquals(new Position(15, 1), arena.getHero().getPosition());
    }

    @Test
    public void heroNotInLegalPositionUp() {
        arena.update(0); //initiating hud hitboxes

        Arena diffArena = new Arena(100, 100, HeroFactory.createHero(Hero.HeroType.GUNMAN, 0, 99)); //creating the new hero in an illegal position (left corner of the resource bar, meaning can only go up)

        diffArena.update(0); //update is needed for resource hitbox, and it also calls the function we're looking to test

        assertEquals(new Position(0, 98), diffArena.getHero().getPosition());
    }

    @Test
    public void heroNotInLegalPositionRandom() {
        arena.update(0); //initiating hud hitboxes

        arena.getHero().setPosition(new Position(15, 0));

        arena.addEnemy(new Runner(new Position(15, 1), 1)); //making it impossible to move in any direction

        arena.checkIfHeroInIllegalPosition();

        Assertions.assertNotEquals(new Position(15, 0), arena.getHero().getPosition());

        Position temp = arena.getHero().getPosition();
        arena.getHero().setPosition(arena.randomFreePosition());

        Assertions.assertTrue(arena.canMove(temp)); //checking if the new position was in fact available (can move would return false because hero was on it, so we have to change it first)
    }

    @Test
    public void updateHeroWave() {
        arena.getHero().setHP(50);
        arena.updateHero(0, 1200); //same wave

        assertEquals(1.2, arena.getHero().getLastTimeTakenDamage());
        assertEquals(1.2, arena.getHero().getLastTimeShieldRegenerated());
        assertEquals(50, arena.getHero().getHP());

        arena.getWaveManager().setCurrentWave(1); //simulating wave change
        arena.updateHero(0, 0);

        assertEquals(110, arena.getHero().getHP()); //check hero healed
    }

    @Test void updateHeroShield(){
        arena.getHero().setMaxShield(50);

        arena.updateHero(5,0);

        assertEquals(0, arena.getHero().getShield()); //shield shouldn't recover yet

        arena.getHero().setLastTimeTakenDamage(5);
        arena.updateHero(5,0);

        assertEquals(0, arena.getHero().getShield()); //still not yet

        arena.getHero().setLastTimeShieldRegenerated(2);
        arena.updateHero(5,0);

        assertEquals(10, arena.getHero().getShield()); //now it should

    }

    @Test
    public void spawnProjectileTest() {
        Position projectilePos = new Position(10, 10);
        String expectedSymbol = "*";
        String expectedColor = "#FF0000";
        int expectedSpeed = 1;

        arena.spawnProjectile(projectilePos, Hero.Direction.UP, 10, expectedSpeed, expectedColor, 15, expectedSymbol);

        assertEquals(1, arena.getActiveProjectiles().size());

        Projectile addedProjectile = arena.getActiveProjectiles().get(0);

        assertEquals(projectilePos, addedProjectile.getPosition());
        assertEquals(expectedSymbol, addedProjectile.getSymbol());
        assertEquals(expectedColor, addedProjectile.getColor());
    }
    @Test
    public void spawnSwordTest() {
        Position swordPos = new Position(20, 20);
        String expectedSymbol = "|";
        String expectedColor = "#FFFFFF";
        double expectedDuration = 0.5;

        arena.spawnSword(swordPos, expectedSymbol, expectedColor, expectedDuration);

        assertEquals(1, arena.getEffects().size());

        SwordAttack addedAttack = arena.getEffects().get(0);

        assertEquals(swordPos, addedAttack.getPosition());
        assertEquals(expectedSymbol, addedAttack.getSymbol());
        assertEquals(expectedColor, addedAttack.getColor());

        assertTrue(addedAttack.isActive());
    }

    @Test
    public void updateSwordTest() {
        Position swordPos = new Position(20, 20);
        String expectedSymbol = "|";
        String expectedColor = "#FFFFFF";
        double expectedDuration = 0.5;

        //spawn one sword for testing purposes
        arena.spawnSword(swordPos, expectedSymbol, expectedColor, expectedDuration);
        arena.spawnSword(swordPos, expectedSymbol, expectedColor, expectedDuration);
        //select the added sword
        SwordAttack addedAttack1 = arena.getEffects().get(0);
        SwordAttack addedAttack2 = arena.getEffects().get(1);

        //should be active at the beginning
        assertTrue(addedAttack1.isActive());
        assertTrue(addedAttack2.isActive());

        // Simulate time passing
        arena.updateSword(100); // 100 milliseconds

        //not enough time has passed yet
        assertTrue(addedAttack1.isActive());
        assertTrue(addedAttack2.isActive());

        arena.updateSword(500); // additional 500 milliseconds

        //it should not be active anymore
        assertFalse(addedAttack1.isActive());
        assertFalse(addedAttack2.isActive());
    }

    @Test
    public void testLevelUp(){
        assertEquals(0, arena.isHeroLevelUp());

        arena.getHero().addXp(1000); //giving enough xp to level up multiple times

        assertTrue(arena.isHeroLevelUp() > 0);
    }
}
