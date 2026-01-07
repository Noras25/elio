package model;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.enemy.Boss;
import Elio.model.enemy.Bruiser;
import Elio.model.enemy.Enemy;
import Elio.model.enemy.EnemyFactory;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.movingStrategies.ChaseHeroStrategy;
import Elio.model.movingStrategies.SmartStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Math.pow;

public class EnemyTest {
    private Enemy enemy;

    @Test
    public void boss(){
        Enemy enemy = EnemyFactory.createEnemy(Enemy.EnemyType.BOSS, new Position(3,3),10);

        Assertions.assertEquals(500, enemy.getHP());
        Assertions.assertEquals(500, enemy.getMaxHP());
        Assertions.assertEquals("#FF4000", enemy.getColor());
        Assertions.assertEquals(new Position(3,3), enemy.getPosition());
        Assertions.assertEquals(Enemy.EnemyType.BOSS, enemy.getType());
        Assertions.assertEquals((int) (pow(1.2,10) * 80), enemy.getDamage());
        Assertions.assertEquals((int) (pow(1.2,10) * 300), enemy.getXpReward());
        Assertions.assertEquals(1, enemy.getSpeed());
        Assertions.assertEquals(SmartStrategy.class, enemy.getStrategy().getClass());
    }

    @Test
    public void bruiser(){
        Enemy enemy = EnemyFactory.createEnemy(Enemy.EnemyType.BRUISER, new Position(3,3),5);

        Assertions.assertEquals(90, enemy.getHP());
        Assertions.assertEquals(90, enemy.getMaxHP());
        Assertions.assertEquals("#FF0000", enemy.getColor());
        Assertions.assertEquals(new Position(3,3), enemy.getPosition());
        Assertions.assertEquals(Enemy.EnemyType.BRUISER, enemy.getType());
        Assertions.assertEquals((int) (pow(1.2,5) * 20), enemy.getDamage());
        Assertions.assertEquals((int) (pow(1.2,5) * 100), enemy.getXpReward());
        Assertions.assertEquals(1.25, enemy.getSpeed());
        Assertions.assertEquals(SmartStrategy.class, enemy.getStrategy().getClass());
    }

    @Test
    public void crawler(){
        Enemy enemy = EnemyFactory.createEnemy(Enemy.EnemyType.CRAWLER, new Position(3,3),5);

        Assertions.assertEquals(120, enemy.getHP());
        Assertions.assertEquals(120, enemy.getMaxHP());
        Assertions.assertEquals("#FF0000", enemy.getColor());
        Assertions.assertEquals(new Position(3,3), enemy.getPosition());
        Assertions.assertEquals(Enemy.EnemyType.CRAWLER, enemy.getType());
        Assertions.assertEquals((int) (pow(1.2,5) * 30), enemy.getDamage());
        Assertions.assertEquals((int) (pow(1.2,5) * 120), enemy.getXpReward());
        Assertions.assertEquals(1.1, enemy.getSpeed());
        Assertions.assertEquals(ChaseHeroStrategy.class, enemy.getStrategy().getClass());
    }

    @Test
    public void runner(){
        Enemy enemy = EnemyFactory.createEnemy(Enemy.EnemyType.RUNNER, new Position(3,3),5);

        Assertions.assertEquals(60, enemy.getHP());
        Assertions.assertEquals(60, enemy.getMaxHP());
        Assertions.assertEquals("#FF0000", enemy.getColor());
        Assertions.assertEquals(new Position(3,3), enemy.getPosition());
        Assertions.assertEquals(Enemy.EnemyType.RUNNER, enemy.getType());
        Assertions.assertEquals((int) (pow(1.2,5) * 10), enemy.getDamage());
        Assertions.assertEquals((int) (pow(1.2,5) * 80), enemy.getXpReward());
        Assertions.assertEquals(1.8, enemy.getSpeed());
        Assertions.assertEquals(ChaseHeroStrategy.class, enemy.getStrategy().getClass());
    }

    @BeforeEach
    public void setUp(){
        enemy = EnemyFactory.createEnemy(Enemy.EnemyType.BRUISER, 15,15,3);
    }
    @Test
    public void move(){
        Position temp = enemy.getPosition();

        Assertions.assertEquals(Position.class, enemy.move(new Arena(100,100, HeroFactory.createHero(Hero.HeroType.GUNMAN,3,3))).getClass());
        Assertions.assertNotEquals(temp, enemy.move(new Arena(100,100, HeroFactory.createHero(Hero.HeroType.GUNMAN,3,3))));
    }

    @Test
    public void isDead(){
        Assertions.assertFalse(enemy.isDead());

        enemy.setHP(0);

        Assertions.assertTrue(enemy.isDead());
    }

    @Test
    public void lastMoved(){
        Assertions.assertEquals(0, enemy.getLastMoved());

        enemy.setLastMoved(2.3);
        Assertions.assertEquals(2.3, enemy.getLastMoved());
    }
}
