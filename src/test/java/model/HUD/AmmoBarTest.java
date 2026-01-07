package model.HUD;

import Elio.model.Arena;
import Elio.model.HUD.AmmoBar;
import Elio.model.hero.Gunman;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.hero.Mage;
import Elio.view.model.HUD.AmmoViewer;
import Elio.view.model.HUD.HUDElementViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AmmoBarTest {
    private AmmoBar ammoBar;
    private Arena arena;
    private Gunman hero;

    @BeforeEach
    public void setup(){
        arena = Mockito.mock(Arena.class);
        hero = Mockito.mock(Gunman.class);
        Mockito.when(arena.getHeight()).thenReturn(100);
        Mockito.when(arena.getHero()).thenReturn(hero);
        Mockito.when(hero.getMaxAmmo()).thenReturn(20);
        Mockito.when(hero.getAmmo()).thenReturn(20);
        Mockito.when(hero.isReloading()).thenReturn(false);
        ammoBar = new AmmoBar(arena);
    }

    @Test
    public void constructor(){
        Assertions.assertEquals(20, ammoBar.getMaxAmmo());
        Assertions.assertEquals(20, ammoBar.getAmmo());
        Assertions.assertEquals(100, ammoBar.getHeight());
        Assertions.assertFalse(ammoBar.isReloading());
    }

    @Test
    public void update(){
        Mockito.when(hero.getMaxAmmo()).thenReturn(40);
        Mockito.when(hero.getAmmo()).thenReturn(30);
        Mockito.when(hero.isReloading()).thenReturn(true);

        ammoBar.update(arena);

        Assertions.assertEquals(40, ammoBar.getMaxAmmo());
        Assertions.assertEquals(30, ammoBar.getAmmo());
        Assertions.assertTrue(ammoBar.isReloading());
    }

    @Test
    public void createViewer(){
        Assertions.assertEquals(AmmoViewer.class, ammoBar.createViewer().getClass());
    }
}
