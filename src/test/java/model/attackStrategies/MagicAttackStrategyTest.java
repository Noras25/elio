package model.attackStrategies;

import Elio.model.Arena;
import Elio.model.attackStrategies.MagicAttackStrategy;
import Elio.model.hero.Mage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MagicAttackStrategyTest {
    private Mage mockHero;
    private Arena mockArena;
    private MagicAttackStrategy spyStrategy;

    @BeforeEach
    public void setup(){
        mockHero = Mockito.mock(Mage.class);
        mockArena = Mockito.mock(Arena.class);

        Mockito.when(mockArena.getTimePlayed()).thenReturn(2000L); // 2 seconds
        Mockito.when(mockHero.getLastAttackTime()).thenReturn(1000L); // 1 second
        Mockito.when(mockHero.getResource()).thenReturn(5);
        Mockito.when(mockHero.getDamage()).thenReturn(20);
        MagicAttackStrategy strategy = new MagicAttackStrategy();

        spyStrategy = Mockito.spy(strategy);
    }


    @Test
    public void magicAttackTestWork() {
        spyStrategy.attack(mockHero, mockArena);

        // Verify that shoot method was called by checking interactions with the arena
        Mockito.verify(mockHero).decreaseResource();
        Mockito.verify(mockHero).setLastAttackTime(2000L);
        Mockito.verify(spyStrategy).shoot(mockHero, mockArena, 10, "#8A2BE2", "●", mockHero.getDamage());
    }

    @Test
    public void magicAttackNoMana() {
        Mockito.when(mockHero.getResource()).thenReturn(0);

        spyStrategy.attack(mockHero, mockArena);

        Mockito.verify(mockHero, Mockito.times(0)).decreaseResource();
        Mockito.verify(mockHero, Mockito.times(0)).setLastAttackTime(2000L);
        Mockito.verify(spyStrategy, Mockito.times(0)).shoot(mockHero, mockArena, 10, "#8A2BE2", "●", mockHero.getDamage());
    }

    @Test
    public void magicAttackBeforeCooldown(){
        Mockito.when(mockHero.getLastAttackTime()).thenReturn(1300L);

        spyStrategy.attack(mockHero, mockArena);

        Mockito.verify(mockHero, Mockito.times(0)).decreaseResource();
        Mockito.verify(mockHero, Mockito.times(0)).setLastAttackTime(2000L);
        Mockito.verify(spyStrategy, Mockito.times(0)).shoot(mockHero, mockArena, 10, "#8A2BE2","●", mockHero.getDamage());
    }
}
