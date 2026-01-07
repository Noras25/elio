package model.HUD;

import Elio.model.Arena;
import Elio.model.HUD.AmmoBar;
import Elio.model.HUD.XPBar;
import Elio.model.hero.Gunman;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.view.model.HUD.AmmoViewer;
import Elio.view.model.HUD.XPBarViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class XPBarTest {
    private XPBar xpBar;
    private Arena mockArena;
    private Hero hero;

    @BeforeEach
    public void setup(){
        mockArena = Mockito.mock(Arena.class);
        hero = Mockito.mock(Hero.class);
        Mockito.when(mockArena.getHero()).thenReturn(hero);
        Mockito.when(mockArena.getWidth()).thenReturn(100);
        Mockito.when(hero.getXp()).thenReturn(0);
        Mockito.when(hero.getLevel()).thenReturn(1);
        Mockito.when(hero.getXpToNextLevel()).thenReturn(100);
        Mockito.when(hero.getLevelCap()).thenReturn(50);

        xpBar = new XPBar(mockArena);
    }

    @Test
    public void constructor(){
        Assertions.assertEquals(0, xpBar.getXP());
        Assertions.assertEquals(100, xpBar.getXPToNextLevel());
        Assertions.assertEquals(1, xpBar.getLevel());
        Assertions.assertEquals(50, xpBar.getLevelCap());
        Assertions.assertEquals(100, xpBar.getArenaWidth());
    }

    @Test
    public void update(){
        Mockito.when(hero.getXp()).thenReturn(50);
        Mockito.when(hero.getLevel()).thenReturn(2);
        Mockito.when(hero.getXpToNextLevel()).thenReturn(130);

        XPBar xpBar = new XPBar(mockArena);
        xpBar.update(mockArena);

        Assertions.assertEquals(50, xpBar.getXP());
        Assertions.assertEquals(130, xpBar.getXPToNextLevel());
        Assertions.assertEquals(2, xpBar.getLevel());
    }

}
