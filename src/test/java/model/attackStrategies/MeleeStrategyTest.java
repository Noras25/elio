package model.attackStrategies;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.attackStrategies.MeleeStrategy;
import Elio.model.hero.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class MeleeStrategyTest {
    private Hero mockHero;
    private TestMeleeStrategy strategy;

    class TestMeleeStrategy extends MeleeStrategy {
        @Override
        public void attack(Hero hero, Arena arena) {}

        //accessing the protected method for testing
        public List<Position> testGetTargetsInFront(Hero hero) {
            return getTargetsInFront(hero);
        }
    }

    @BeforeEach
    public void setup() {
        mockHero = Mockito.mock(Hero.class);
        strategy = new TestMeleeStrategy();

        Mockito.when(mockHero.getPosition()).thenReturn(new Position(10, 10));
    }

    @Test
    public void testTargetsFacingUp() {
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.UP);

        List<Position> targets = strategy.testGetTargetsInFront(mockHero);

        Assertions.assertEquals(3, targets.size());
        Assertions.assertTrue(targets.contains(new Position(10, 9)));
        Assertions.assertTrue(targets.contains(new Position(9, 9)));
        Assertions.assertTrue(targets.contains(new Position(11, 9)));
    }

    @Test
    public void testTargetsFacingLeft() {
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.LEFT);

        List<Position> targets = strategy.testGetTargetsInFront(mockHero);

        Assertions.assertEquals(3, targets.size());
        Assertions.assertTrue(targets.contains(new Position(9, 9)));
        Assertions.assertTrue(targets.contains(new Position(9, 10)));
        Assertions.assertTrue(targets.contains(new Position(9, 11)));
    }

    @Test
    public void testTargetsFacingDown() {
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.DOWN);

        List<Position> targets = strategy.testGetTargetsInFront(mockHero);

        Assertions.assertEquals(3, targets.size());
        Assertions.assertTrue(targets.contains(new Position(9, 11)));
        Assertions.assertTrue(targets.contains(new Position(10, 11)));
        Assertions.assertTrue(targets.contains(new Position(11, 11)));
    }

    @Test
    public void testTargetsFacingRight() {
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.RIGHT);

        List<Position> targets = strategy.testGetTargetsInFront(mockHero);

        Assertions.assertEquals(3, targets.size());
        Assertions.assertTrue(targets.contains(new Position(11, 9)));
        Assertions.assertTrue(targets.contains(new Position(11, 10)));
        Assertions.assertTrue(targets.contains(new Position(11, 11)));
    }

    @Test
    public void testTargetsDefault() {
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.NONE);

        List<Position> targets = strategy.testGetTargetsInFront(mockHero);

        Assertions.assertEquals(0, targets.size());
    }
}
