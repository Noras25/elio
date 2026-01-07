package model.attackStrategies;

import Elio.model.Arena;
import Elio.model.attackStrategies.SwordAttackStrategy;
import Elio.model.hero.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
public class SwordAttackStrategyTest {
    private Hero mockHero;
    private Arena mockArena;
    private SwordAttackStrategy strategy;

    @BeforeEach
    public void setup(){
        strategy = new SwordAttackStrategy();
        mockArena = org.mockito.Mockito.mock(Arena.class);
        mockHero = org.mockito.Mockito.mock(Hero.class);

        Mockito.when(mockArena.getTimePlayed()).thenReturn(2000L); // 2 seconds
        Mockito.when(mockHero.getLastAttackTime()).thenReturn(1000L); // 1 second
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.UP);
        Mockito.when(mockHero.getPosition()).thenReturn(new Elio.model.Position(5, 5));
        Mockito.when(mockHero.getDamage()).thenReturn(30);
    }

    @Test
    public void attackTestForUp() {
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.UP);

        strategy.attack(mockHero, mockArena);

        //test the sword slash spawns
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(5, 4), "_", "#FFFFFF", 0.150);
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(4, 4), "_", "#FFFFFF", 0.150);
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(6, 4), "_", "#FFFFFF", 0.150);

        //test the damage application
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(5, 4), 30);
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(4, 4), 30);
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(6, 4), 30);

        //test last attack time update
        Mockito.verify(mockHero).setLastAttackTime(2000L);
    }

    @Test
    public void attackTestForDown() {
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.DOWN);

        strategy.attack(mockHero, mockArena);

        //test the sword slash spawns
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(5, 6), "_", "#FFFFFF", 0.150);
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(4, 6), "_", "#FFFFFF", 0.150);
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(6, 6), "_", "#FFFFFF", 0.150);

        //test the damage application
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(5, 6), 30);
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(4, 6), 30);
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(6, 6), 30);

        //test last attack time update
        Mockito.verify(mockHero).setLastAttackTime(2000L);
    }

    @Test
    public void attackTestForLeft() {
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.LEFT);

        strategy.attack(mockHero, mockArena);

        //test the sword slash spawns
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(4, 4), "|", "#FFFFFF", 0.150);
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(4, 5), "|", "#FFFFFF", 0.150);
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(4, 6), "|", "#FFFFFF", 0.150);

        //test the damage application
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(4, 4), 30);
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(4, 5), 30);
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(4, 6), 30);

        //test last attack time update
        Mockito.verify(mockHero).setLastAttackTime(2000L);
    }

    @Test
    public void attackTestForRight() {
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.RIGHT);

        strategy.attack(mockHero, mockArena);

        //test the sword slash spawns
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(6, 4), "|", "#FFFFFF", 0.150);
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(6, 5), "|", "#FFFFFF", 0.150);
        Mockito.verify(mockArena).spawnSword(new Elio.model.Position(6, 6), "|", "#FFFFFF", 0.150);

        //test the damage application
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(6, 4), 30);
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(6, 5), 30);
        Mockito.verify(mockArena).damageEnemyAt(new Elio.model.Position(6, 6), 30);

        //test last attack time update
        Mockito.verify(mockHero).setLastAttackTime(2000L);
    }


    @Test
    public void attackTestShorterCooldown() {
        Mockito.when(mockHero.getLastAttackTime()).thenReturn(1500L);
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.UP);

        strategy.attack(mockHero, mockArena);

        //test no sword slash spawns
        Mockito.verify(mockArena, Mockito.never()).spawnSword(Mockito.any(), Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble());
        //test no damage application
        Mockito.verify(mockArena, Mockito.never()).damageEnemyAt(Mockito.any(), Mockito.anyInt());
        //test last attack time not updated
        Mockito.verify(mockHero, Mockito.never()).setLastAttackTime(Mockito.anyLong());
    }

    @Test
    public void attackTestDefault() {
        Mockito.when(mockHero.getDirection()).thenReturn(Hero.Direction.NONE);

        strategy.attack(mockHero, mockArena);

        //test no sword slash spawns
        Mockito.verify(mockArena, Mockito.never()).spawnSword(Mockito.any(), Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble());
        //test no damage application
        Mockito.verify(mockArena, Mockito.never()).damageEnemyAt(Mockito.any(), Mockito.anyInt());
        //test last attack time was updated
        Mockito.verify(mockHero).setLastAttackTime(2000L);
    }
}
