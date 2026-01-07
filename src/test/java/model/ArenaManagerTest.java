package model;

import Elio.gui.GUI;
import Elio.model.Arena;
import Elio.model.LevelUpChoice;
import Elio.model.Position;
import Elio.model.arena.EnemiesManager;
import Elio.model.arena.HUDManager;
import Elio.model.arena.ProjectileManager;
import Elio.model.arena.WaveManager;
import Elio.model.enemy.Boss;
import Elio.model.enemy.Enemy;
import Elio.model.enemy.EnemyFactory;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.hero.Mage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static Elio.model.enemy.Enemy.EnemyType.*;
import static Elio.model.enemy.Enemy.EnemyType.RUNNER;

public class ArenaManagerTest {
    private Arena arena;

    @BeforeEach
    public void setup(){
        arena = new Arena(100,100, HeroFactory.createHero(Hero.HeroType.MAGE, 10,50));
    }

    @Test
    public void enemiesManagerBasic(){
        EnemiesManager enemiesManager = new EnemiesManager(arena);

        Assertions.assertTrue(enemiesManager.getEnemies().isEmpty());

        Enemy enemy = EnemyFactory.createEnemy(Enemy.EnemyType.RUNNER, 7,7,1);

        List<Enemy> list = new ArrayList<>();
        list.add(enemy);

        enemiesManager.addEnemy(enemy);

        Assertions.assertEquals(list, enemiesManager.getEnemies());
    }

    @Test
    public void enemiesManagerUpdate(){
        EnemiesManager enemiesManager = new EnemiesManager(arena);

        Enemy enemy1 = EnemyFactory.createEnemy(Enemy.EnemyType.RUNNER, 7,7,1);
        enemiesManager.addEnemy(enemy1);

        Enemy enemy2 = EnemyFactory.createEnemy(Enemy.EnemyType.CRAWLER, 8,8,1);
        enemiesManager.addEnemy(enemy2);

        enemy1.setHP(0);

        Position temp = enemy2.getPosition();

        enemiesManager.updateEnemies(200);

        Assertions.assertEquals(1, enemiesManager.getEnemies().size());
        Assertions.assertEquals(96, arena.getHero().getXp());
        Assertions.assertEquals(0.2, enemy2.getLastMoved());
        Assertions.assertEquals(temp, enemy2.getPosition());

        enemiesManager.updateEnemies(400);

        Assertions.assertNotEquals(temp, enemy2.getPosition());
        Assertions.assertEquals(0, enemy2.getLastMoved());

        arena.getHero().setPosition(new Position(enemy2.getPosition().getX(), enemy2.getPosition().getY()+1));

        temp = enemy2.getPosition();
        enemiesManager.updateEnemies(600);

        Assertions.assertEquals(temp, enemy2.getPosition());
        Assertions.assertEquals(arena.getHero().getMaxHP() - enemy2.getDamage(), arena.getHero().getHP());

    }

    @Test
    public void projectileManagerBasic(){
        ProjectileManager projectileManager = new ProjectileManager(arena);

        Assertions.assertTrue(projectileManager.getActiveProjectiles().isEmpty());

        projectileManager.spawnProjectile(new Position(13,12), Hero.Direction.DOWN, 20, 1, GUI.BLACK, 20, "C");

        Assertions.assertEquals(1, projectileManager.getActiveProjectiles().size());

        Assertions.assertEquals(new Position(13,12), projectileManager.getActiveProjectiles().getFirst().getPosition());
        Assertions.assertEquals(20, projectileManager.getActiveProjectiles().getFirst().getDamage());
        Assertions.assertEquals(GUI.BLACK, projectileManager.getActiveProjectiles().getFirst().getColor());
        Assertions.assertEquals("C", projectileManager.getActiveProjectiles().getFirst().getSymbol());
    }

    @Test
    public void projectileManagerUpdate(){
        ProjectileManager projectileManager = new ProjectileManager(arena);

        projectileManager.spawnProjectile(new Position(13,11), Hero.Direction.DOWN, 20, 1, GUI.BLACK, 20, "C");
        projectileManager.spawnProjectile(new Position(1,63), Hero.Direction.LEFT, 20, 0.5, GUI.WHITE, 20, "D"); //setting projectile to die because of being out of bounds in a move
        projectileManager.spawnProjectile(new Position(18, 52), Hero.Direction.RIGHT, 20, 0.25, GUI.BLACK, 1, "L" ); //setting projectile to die because of exceeding max range after a move

        projectileManager.updateProjectiles(0); //update once just to get them out of starting position

        arena.addEnemy(EnemyFactory.createEnemy(Enemy.EnemyType.BRUISER,13,13,1)); //set up an enemy to kill first projectile

        projectileManager.updateProjectiles(250);

        Assertions.assertEquals(3, projectileManager.getActiveProjectiles().size());

        projectileManager.updateProjectiles(250);

        Assertions.assertEquals(2, projectileManager.getActiveProjectiles().size());

        projectileManager.updateProjectiles(500);

        Assertions.assertEquals(1, projectileManager.getActiveProjectiles().size());

        projectileManager.updateProjectiles(1000);

        Assertions.assertTrue(projectileManager.getActiveProjectiles().isEmpty());
    }

    @Test
    public void waveManagerUpdate(){
        WaveManager waveManager = new WaveManager(arena);

        Assertions.assertFalse(waveManager.isFinished());
        Assertions.assertTrue(arena.getEnemies().isEmpty());

        waveManager.setCurrentWave(29);
        waveManager.update(1000); //not enough time to update

        Assertions.assertEquals(29, waveManager.getCurrentWave());

        waveManager.update(1000); //now it's just enough

        Assertions.assertEquals(30, waveManager.getCurrentWave());
        Assertions.assertFalse(arena.getEnemies().isEmpty());
        Assertions.assertFalse(waveManager.isFinished());

        waveManager.update(10000); //shouldn't do anything cause there are still enemies

        Assertions.assertEquals(30, waveManager.getCurrentWave());

        arena.damageEnemyAt(arena.getEnemies().getFirst().getPosition(), 300000); //kill the boss
        arena.update(0); //make sure he gets removed from enemies list

        waveManager.update(10000); //now it should work

        Assertions.assertEquals(31, waveManager.getCurrentWave());
        Assertions.assertTrue(waveManager.isFinished());
        Assertions.assertTrue(arena.getEnemies().isEmpty()); //shouldn't spawn any more enemies cause it's already finished

        waveManager.update(10000);

        Assertions.assertEquals(31, waveManager.getCurrentWave()); //should stay the same cause it already reached its end
    }

    @Test
    public void waveManagerSpawnBossWave() {
        WaveManager waveManager = new WaveManager(arena);

        Assertions.assertTrue(arena.getEnemies().isEmpty());

        waveManager.setCurrentWave(9);
        waveManager.update(2000);

        Assertions.assertFalse(arena.getEnemies().isEmpty());
        Assertions.assertEquals(Boss.class, arena.getEnemies().getFirst().getClass());
    }

    @Test
    public void waveManagerSpawnNormalWave(){
        WaveManager waveManager = new WaveManager(arena);

        Assertions.assertTrue(arena.getEnemies().isEmpty());

        waveManager.setCurrentWave(6);
        waveManager.update(2000);

        Assertions.assertFalse(arena.getEnemies().isEmpty());

        List<Enemy.EnemyType> nonBossTypes = Arrays.asList(BRUISER, CRAWLER, RUNNER);

        Assertions.assertTrue(nonBossTypes.contains(arena.getEnemies().getFirst().getType()));
        Assertions.assertEquals(5 + waveManager.getCurrentWave()*2, arena.getEnemies().size());
    }

    @Test
    public void HUDManagerUpdateHP(){
        HUDManager hudManager = new HUDManager(arena);

        hudManager.updateBarHitboxes();

        Assertions.assertEquals(32, hudManager.getHpBarHitbox());

        arena.getHero().setMaxHP(1000);
        hudManager.updateBarHitboxes();

        Assertions.assertEquals(33, hudManager.getHpBarHitbox());

        arena.getHero().setHP(10);
        hudManager.updateBarHitboxes();

        Assertions.assertEquals(32, hudManager.getHpBarHitbox());
    }

    @Test
    public void HUDManagerUpdateShield(){
        HUDManager hudManager = new HUDManager(arena);

        hudManager.updateBarHitboxes();

        Assertions.assertEquals(0, hudManager.getShieldBarHitbox());

        arena.getHero().setMaxShield(20);
        hudManager.updateBarHitboxes();

        Assertions.assertEquals(13, hudManager.getShieldBarHitbox());

        arena.getHero().setShield(10);
        hudManager.updateBarHitboxes();

        Assertions.assertEquals(14, hudManager.getShieldBarHitbox());

        arena.getHero().setMaxShield(120);
        hudManager.updateBarHitboxes();

        Assertions.assertEquals(31, hudManager.getShieldBarHitbox());
    }

    @Test
    public void HUDManagerUpdateXP(){
        HUDManager hudManager = new HUDManager(arena);

        hudManager.updateBarHitboxes();

        Assertions.assertEquals(29, hudManager.getXpBarHitbox());

        arena.getHero().setLevel(9);
        hudManager.updateBarHitboxes();

        Assertions.assertEquals(29, hudManager.getXpBarHitbox());

        arena.getHero().setLevel(10);
        hudManager.updateBarHitboxes();

        Assertions.assertEquals(30, hudManager.getXpBarHitbox());

        arena.getHero().setLevel(arena.getHero().getLevelCap());
        hudManager.updateBarHitboxes();

        Assertions.assertEquals(31, hudManager.getXpBarHitbox());
    }

    @Test
    public void HUDManagerCheckCollision(){
        HUDManager hudManager = new HUDManager(arena);
        hudManager.updateBarHitboxes();

        //checking hp bar
        Assertions.assertFalse(hudManager.checkHitboxCollision(new Position(hudManager.getHpBarHitbox(),0))); //barely not in hp bar
        Assertions.assertTrue(hudManager.checkHitboxCollision(new Position(hudManager.getHpBarHitbox()-1,0))); //in edge of hp bar

        //same but for shield
        arena.getHero().setMaxShield(300);
        hudManager.updateBarHitboxes();

        Assertions.assertFalse(hudManager.checkHitboxCollision(new Position(hudManager.getShieldBarHitbox(),1)));
        Assertions.assertTrue(hudManager.checkHitboxCollision(new Position(hudManager.getShieldBarHitbox()-1,1)));

        //same but for xp bar
        Assertions.assertFalse(hudManager.checkHitboxCollision(new Position(99 - hudManager.getXpBarHitbox(),0)));
        Assertions.assertTrue(hudManager.checkHitboxCollision(new Position(100 - hudManager.getXpBarHitbox(),0)));

        //same but for wave counter
        Assertions.assertFalse(hudManager.checkHitboxCollision(new Position(arena.getWidth() / 2 - 9, 0)));
        Assertions.assertFalse(hudManager.checkHitboxCollision(new Position(arena.getWidth() / 2 + 8,0)));

        Assertions.assertTrue(hudManager.checkHitboxCollision(new Position(arena.getWidth() / 2 - 8, 0)));
        Assertions.assertTrue(hudManager.checkHitboxCollision(new Position(arena.getWidth() / 2 + 7,0)));

        //same but for timer
        Assertions.assertFalse(hudManager.checkHitboxCollision(new Position(arena.getWidth() / 2 - 3, 1)));
        Assertions.assertFalse(hudManager.checkHitboxCollision(new Position(arena.getWidth() / 2 + 3,1)));

        Assertions.assertTrue(hudManager.checkHitboxCollision(new Position(arena.getWidth() / 2 - 2, 1)));
        Assertions.assertTrue(hudManager.checkHitboxCollision(new Position(arena.getWidth() / 2 + 2,1)));

        //same but for resource
        ((Mage)arena.getHero()).setMaxMana(10);
        hudManager.updateBarHitboxes();

        Assertions.assertFalse(hudManager.checkHitboxCollision(new Position(19,arena.getHeight()-1)));
        Assertions.assertTrue(hudManager.checkHitboxCollision(new Position(18,arena.getHeight()-1)));

        //not in any possible zone of a hud hitbox
        Assertions.assertFalse(hudManager.checkHitboxCollision(new Position(78, 19)));
    }
}
