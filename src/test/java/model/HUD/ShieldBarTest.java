package model.HUD;

import Elio.model.Arena;
import Elio.model.HUD.AmmoBar;
import Elio.model.HUD.ShieldBar;
import Elio.model.hero.Gunman;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.view.model.HUD.AmmoViewer;
import Elio.view.model.HUD.ShieldBarViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ShieldBarTest {
    private ShieldBar shieldBar;
    private Arena arena;
    private Hero hero;

    @BeforeEach
    public void setup(){
        arena = Mockito.mock(Arena.class);
        hero = Mockito.mock(Hero.class);
        Mockito.when(arena.getHero()).thenReturn(hero);
        Mockito.when(hero.getMaxShield()).thenReturn(0);
        Mockito.when(hero.getShield()).thenReturn(0);
        shieldBar = new ShieldBar(arena);
    }

    @Test
    public void constructor(){
        Assertions.assertEquals(0, shieldBar.getMaxShield());
        Assertions.assertEquals(0, shieldBar.getShield());
    }

    @Test
    public void update(){
        Mockito.when(hero.getMaxShield()).thenReturn(40);
        Mockito.when(hero.getShield()).thenReturn(30);

        shieldBar.update(arena);

        Assertions.assertEquals(40, shieldBar.getMaxShield());
        Assertions.assertEquals(30, shieldBar.getShield());
    }

    @Test
    public void createViewer(){
        Assertions.assertEquals(ShieldBarViewer.class, shieldBar.createViewer().getClass());
    }
}
