package controller;

import Elio.controller.HeroController;
import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.attackStrategies.AttackStrategy;
import Elio.model.enemy.Enemy;
import Elio.model.enemy.EnemyFactory;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HeroControllerTest {
    private HeroController controller;
    private Arena arena;

    @BeforeEach
    public void setup(){
        arena = new Arena(100, 100, HeroFactory.createHero(Hero.HeroType.MAGE,15,15));
        controller = new HeroController(arena.getHero());
    }

    @Test
    public void getModel(){ //just to get it out of the way
        Assertions.assertEquals(arena.getHero(), controller.getModel());
    }

    @Test
    public void moveUp(){
        controller.moveUp(arena);

        Assertions.assertEquals(Hero.Direction.UP, arena.getHero().getDirection());
        Assertions.assertEquals(100, arena.getHero().getHP());
        Assertions.assertEquals(new Position(15,14), arena.getHero().getPosition());

        arena.addEnemy(EnemyFactory.createEnemy(Enemy.EnemyType.CRAWLER,15, 13,0));

        Position temp = arena.getHero().getPosition();

        controller.moveUp(arena);

        Assertions.assertEquals(temp, arena.getHero().getPosition());
        Assertions.assertEquals(70, arena.getHero().getHP());
    }

    @Test
    public void moveDown(){
        controller.moveDown(arena);

        Assertions.assertEquals(Hero.Direction.DOWN, arena.getHero().getDirection());
        Assertions.assertEquals(100, arena.getHero().getHP());
        Assertions.assertEquals(new Position(15,16), arena.getHero().getPosition());

        arena.addEnemy(EnemyFactory.createEnemy(Enemy.EnemyType.CRAWLER,15, 17,0));

        Position temp = arena.getHero().getPosition();

        controller.moveDown(arena);

        Assertions.assertEquals(temp, arena.getHero().getPosition());
        Assertions.assertEquals(70, arena.getHero().getHP());
    }

    @Test
    public void moveRight(){
        controller.moveRight(arena);

        Assertions.assertEquals(Hero.Direction.RIGHT, arena.getHero().getDirection());
        Assertions.assertEquals(100, arena.getHero().getHP());
        Assertions.assertEquals(new Position(16,15), arena.getHero().getPosition());

        arena.addEnemy(EnemyFactory.createEnemy(Enemy.EnemyType.CRAWLER,17, 15,0));

        Position temp = arena.getHero().getPosition();

        controller.moveRight(arena);

        Assertions.assertEquals(temp, arena.getHero().getPosition());
        Assertions.assertEquals(70, arena.getHero().getHP());
    }

    @Test
    public void moveLeft(){
        controller.moveLeft(arena);

        Assertions.assertEquals(Hero.Direction.LEFT, arena.getHero().getDirection());
        Assertions.assertEquals(100, arena.getHero().getHP());
        Assertions.assertEquals(new Position(14,15), arena.getHero().getPosition());

        arena.addEnemy(EnemyFactory.createEnemy(Enemy.EnemyType.CRAWLER,13, 15,0));

        Position temp = arena.getHero().getPosition();

        controller.moveLeft(arena);

        Assertions.assertEquals(temp, arena.getHero().getPosition());
        Assertions.assertEquals(70, arena.getHero().getHP());
    }

    @Test
    public void attack(){
        AttackStrategy spyAttackStrategy = Mockito.spy(arena.getHero().getAttackStrategy());
        arena.getHero().setAttackStrategy(spyAttackStrategy);

        controller.attack(arena);

        Mockito.verify(spyAttackStrategy, Mockito.never()).attack(arena.getHero(), arena); //check that hero doesn't attack if in initial direction

        arena.getHero().setDirection(Hero.Direction.UP);

        controller.attack(arena);

        Mockito.verify(spyAttackStrategy).attack(arena.getHero(), arena);
    }
}
