package model.HUD;

import Elio.model.Arena;
import Elio.model.HUD.HPBar;
import Elio.model.HUD.ShieldBar;
import Elio.model.arena.WaveManager;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.hero.Mage;
import Elio.view.model.HUD.HPBarViewer;
import Elio.view.model.HUD.ShieldBarViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HPBarTest {
    private HPBar hpBar;
    private Arena arena;
    private Hero hero;

    @BeforeEach
    public void setup(){
        arena = Mockito.mock(Arena.class);
        hero = Mockito.mock(Hero.class);
        Mockito.when(arena.getHero()).thenReturn(hero);
        Mockito.when(hero.getHP()).thenReturn(120);
        Mockito.when(hero.getMaxHP()).thenReturn(120);
        hpBar = new HPBar(arena);
    }

    @Test
    public void constructor(){
        Assertions.assertEquals(120, hpBar.getMaxHP());
        Assertions.assertEquals(120, hpBar.getHP());
    }

    @Test
    public void update(){
        Mockito.when(hero.getHP()).thenReturn(30);
        Mockito.when(hero.getMaxHP()).thenReturn(40);

        hpBar.update(arena);

        Assertions.assertEquals(40, hpBar.getMaxHP());
        Assertions.assertEquals(30, hpBar.getHP());
    }

    @Test
    public void createViewer(){
        Assertions.assertEquals(HPBarViewer.class, hpBar.createViewer().getClass());
    }
}
