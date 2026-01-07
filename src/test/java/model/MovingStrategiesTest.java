package model;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.enemy.Enemy;
import Elio.model.enemy.EnemyFactory;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.movingStrategies.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovingStrategiesTest {
    private Enemy enemy;
    private Arena arena;

    @BeforeEach
    public void setup(){
        enemy = EnemyFactory.createEnemy(Enemy.EnemyType.CRAWLER, 3,7, 2);
        arena = new Arena(100,100, HeroFactory.createHero(Hero.HeroType.MAGE,10,10));
    }

    @Test
    public void chaseHeroFar() {
        MovingStrategy strategy = new ChaseHeroStrategy();

        List<Position> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Position(4, 7));
        possibleMoves.add(new Position(3, 8));

        Assertions.assertTrue(possibleMoves.contains(strategy.move(enemy, arena)));

        arena.getHero().setPosition(new Position(1, 1));

        possibleMoves.clear();
        possibleMoves.add(new Position(2, 7));
        possibleMoves.add(new Position(3, 6));

        Assertions.assertTrue(possibleMoves.contains(strategy.move(enemy, arena)));
    }

    @Test
    public void chaseHeroNear(){
        MovingStrategy strategy = new ChaseHeroStrategy();

        arena.getHero().setPosition(new Position(4,7));

        Assertions.assertEquals(arena.getHero().getPosition(), strategy.move(enemy, arena));

        arena.getHero().setPosition(new Position(2,7));

        Assertions.assertEquals(arena.getHero().getPosition(), strategy.move(enemy, arena));

        arena.getHero().setPosition(new Position(3,8));

        Assertions.assertEquals(arena.getHero().getPosition(), strategy.move(enemy, arena));

        arena.getHero().setPosition(new Position(3,6));

        Assertions.assertEquals(arena.getHero().getPosition(), strategy.move(enemy, arena));
    }

    @Test
    public void chaseHeroNearNot(){
        MovingStrategy strategy = new ChaseHeroStrategy();

        arena.getHero().setPosition(new Position(4,70));

        Assertions.assertNotEquals(arena.getHero().getPosition(), strategy.move(enemy, arena));

        arena.getHero().setPosition(new Position(70,8));

        Assertions.assertNotEquals(arena.getHero().getPosition(), strategy.move(enemy, arena));

        arena.getHero().setPosition(new Position(3,70));

        Assertions.assertNotEquals(arena.getHero().getPosition(), strategy.move(enemy, arena));

        arena.getHero().setPosition(new Position(70,7));

        Assertions.assertNotEquals(arena.getHero().getPosition(), strategy.move(enemy, arena));
    }

    @Test
    public void random(){
        MovingStrategy strategy = new RandomStrategy();
        //mock the random to control the output
        Random mockRandom = Mockito.mock(Random.class);
        RandomStrategy.setRandom(mockRandom);

        //now I need to catch all the cases and to do so i will manipulate the mock to return specific values

        //up
        Mockito.when(mockRandom.nextInt(4)).thenReturn(0);
        Assertions.assertEquals(new Position(3,8), strategy.move(enemy, arena));

        //down
        Mockito.when(mockRandom.nextInt(4)).thenReturn(1);
        Assertions.assertEquals(new Position(3,6), strategy.move(enemy, arena));

        //left
        Mockito.when(mockRandom.nextInt(4)).thenReturn(2);
        Assertions.assertEquals(new Position(2,7), strategy.move(enemy, arena));

        //right
        Mockito.when(mockRandom.nextInt(4)).thenReturn(3);
        Assertions.assertEquals(new Position(4,7), strategy.move(enemy, arena));

        //none
        Mockito.when(mockRandom.nextInt(4)).thenReturn(50);
        Assertions.assertNull(strategy.move(enemy, arena));
    }

    @Test
    public void roamToCenter() {
        MovingStrategy strategy = new RoamToCenterStrategy();

        List<Position> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Position(4, 7));
        possibleMoves.add(new Position(3, 8));

        Assertions.assertTrue(possibleMoves.contains(strategy.move(enemy, arena)));

        enemy.setPosition(new Position(70,60));

        possibleMoves.clear();
        possibleMoves.add(new Position(69, 60));
        possibleMoves.add(new Position(70, 59));

        Assertions.assertTrue(possibleMoves.contains(strategy.move(enemy, arena)));
    }

    @Test
    public void smartStrategy(){
        RandomStrategy random = Mockito.mock(RandomStrategy.class);
        ChaseHeroStrategy chaseHero = Mockito.mock(ChaseHeroStrategy.class);
        RoamToCenterStrategy roamToCenter = Mockito.mock(RoamToCenterStrategy.class);

        Mockito.when(random.move(enemy,arena)).thenReturn(arena.randomFreePosition());
        Mockito.when(chaseHero.move(enemy,arena)).thenReturn(arena.randomFreePosition());
        Mockito.when(roamToCenter.move(enemy,arena)).thenReturn(arena.randomFreePosition());

        SmartStrategy strategy = new SmartStrategy(random, chaseHero, roamToCenter);

        enemy.setPosition(new Position(20,20));

        Assertions.assertEquals(Position.class,strategy.move(enemy,arena).getClass());

        Mockito.verify(chaseHero).move(enemy, arena);

        enemy.setPosition(new Position(60, 57));

        Assertions.assertEquals(Position.class,strategy.move(enemy,arena).getClass());

        Mockito.verify(random).move(enemy,arena);

        enemy.setPosition(new Position(99,99));

        Assertions.assertEquals(Position.class,strategy.move(enemy,arena).getClass());

        Mockito.verify(roamToCenter).move(enemy,arena);
    }
}
