package model.attackStrategies;

import Elio.model.Arena;
import Elio.model.attackStrategies.BulletStrategy;
import Elio.model.attackStrategies.MagicAttackStrategy;
import Elio.model.hero.Gunman;
import Elio.model.hero.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class BulletStrategyTest {
    private Gunman mockHero;
    private Arena mockArena;
    private BulletStrategy spyStrategy;

    @BeforeEach
    public void setup(){
        mockHero = Mockito.mock(Gunman.class);
        mockArena = Mockito.mock(Arena.class);

        Mockito.when(mockArena.getTimePlayed()).thenReturn(2000L); // 2 seconds
        Mockito.when(mockHero.getLastAttackTime()).thenReturn(1000L); // 1 second
        Mockito.when(mockHero.getResource()).thenReturn(5);
        Mockito.when(mockHero.getDamage()).thenReturn(20);
        BulletStrategy strategy = new BulletStrategy();

        spyStrategy = Mockito.spy(strategy);
    }

    @Test
    public void bulletTestWork() {
        spyStrategy.attack(mockHero, mockArena);

        // Verify that shoot method was called by checking interactions with the arena
        Mockito.verify(mockHero).decreaseResource();
        Mockito.verify(mockHero).setLastAttackTime(2000L);
        Mockito.verify(spyStrategy).shoot(mockHero, mockArena, 20, "#FFFF00", "•", mockHero.getDamage());
    }

    @Test
    public void bulletNoAmmo() {
        Mockito.when(mockHero.getResource()).thenReturn(0);

        spyStrategy.attack(mockHero, mockArena);

        Mockito.verify(mockHero, Mockito.times(0)).decreaseResource();
        Mockito.verify(mockHero, Mockito.times(0)).setLastAttackTime(2000L);
        Mockito.verify(spyStrategy, Mockito.times(0)).shoot(mockHero, mockArena, 20, "#FFFF00", "•", mockHero.getDamage());
    }

    @Test
    public void bulletBeforeCooldown(){
        Mockito.when(mockHero.getLastAttackTime()).thenReturn(1900L);

        spyStrategy.attack(mockHero, mockArena);

        Mockito.verify(mockHero, Mockito.times(0)).decreaseResource();
        Mockito.verify(mockHero, Mockito.times(0)).setLastAttackTime(2000L);
        Mockito.verify(spyStrategy, Mockito.times(0)).shoot(mockHero, mockArena, 20, "#FFFF00","•", mockHero.getDamage());
    }

    @Test
    public void bulletWhileReload(){
        Mockito.when(mockHero.isReloading()).thenReturn(true);

        spyStrategy.attack(mockHero, mockArena);

        Mockito.verify(mockHero, Mockito.times(0)).decreaseResource();
        Mockito.verify(mockHero, Mockito.times(0)).setLastAttackTime(2000L);
        Mockito.verify(spyStrategy, Mockito.times(0)).shoot(mockHero, mockArena, 20, "#FFFF00","•", mockHero.getDamage());
    }

}
