package model.attackStrategies;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.attackStrategies.RangedStrategy;
import Elio.model.hero.Hero;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RangedStrategyTest {
    @Test
    public void shootTest() {
        Arena mockArena = org.mockito.Mockito.mock(Arena.class);
        Hero mockHero = Mockito.mock(Hero.class);

        Mockito.when(mockHero.getPosition()).thenReturn(new Elio.model.Position(5, 5));
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.UP);

        //we have to do it like this since the class is abstract
        RangedStrategy strategy = new RangedStrategy() {
            @Override
            public void attack(Hero hero, Arena arena) {
            }
        };

        strategy.shoot(mockHero, mockArena, 10, "FF000", "*", 20);

        Position expectedProjectilePos = new Position(5, 5); //position in front of the hero

        Mockito.verify(mockArena).spawnProjectile(expectedProjectilePos, Hero.Direction.UP, 20, 10, "FF000", 15, "*");
    }

}
