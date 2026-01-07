package model.HUD;

import Elio.model.Arena;
import Elio.model.HUD.ManaBar;
import Elio.model.HUD.ShieldBar;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.hero.Mage;
import Elio.view.model.HUD.ManaViewer;
import Elio.view.model.HUD.ShieldBarViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ManaBarTest {
    private ManaBar manaBar;
    private Arena arena;
    private Mage hero;

    @BeforeEach
    public void setup(){
        arena = Mockito.mock(Arena.class);
        hero = Mockito.mock(Mage.class);
        Mockito.when(arena.getHeight()).thenReturn(100);
        Mockito.when(arena.getHero()).thenReturn(hero);
        Mockito.when(hero.getMaxMana()).thenReturn(5);
        Mockito.when(hero.getMana()).thenReturn(5);
        manaBar = new ManaBar(arena);
    }

    @Test
    public void constructor(){
        Assertions.assertEquals(5, manaBar.getMaxMana());
        Assertions.assertEquals(5, manaBar.getMana());
        Assertions.assertEquals(100, manaBar.getHeight());
    }

    @Test
    public void update(){
        Mockito.when(hero.getMaxMana()).thenReturn(40);
        Mockito.when(hero.getMana()).thenReturn(30);

        manaBar.update(arena);

        Assertions.assertEquals(40, manaBar.getMaxMana());
        Assertions.assertEquals(30, manaBar.getMana());
    }

    @Test
    public void createViewer(){
        Assertions.assertEquals(ManaViewer.class, manaBar.createViewer().getClass());
    }
}
