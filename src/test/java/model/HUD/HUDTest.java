package model.HUD;

import Elio.model.Arena;
import Elio.model.HUD.HPBar;
import Elio.model.HUD.HUD;
import Elio.model.HUD.HUDElement;
import Elio.model.HUD.ShieldBar;
import Elio.model.arena.WaveManager;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.hero.Mage;
import Elio.model.hero.Warrior;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class HUDTest {
    private HUD hud;
    private Arena arena;
    private Hero hero;

    @BeforeEach
    public void setup(){
        arena = Mockito.mock(Arena.class);
        hero = Mockito.mock(Mage.class);
        Mockito.when(arena.getWidth()).thenReturn(100);
        Mockito.when(arena.getHeight()).thenReturn(100);
        Mockito.when(arena.getHero()).thenReturn(hero);
        Mockito.when(hero.getType()).thenReturn(Hero.HeroType.MAGE);
        Mockito.when(((Mage)hero).getMaxMana()).thenReturn(5);
        Mockito.when(((Mage)hero).getMana()).thenReturn(5);
        Mockito.when(hero.getHP()).thenReturn(100);
        Mockito.when(hero.getMaxHP()).thenReturn(100);
        Mockito.when(hero.getShield()).thenReturn(100);
        Mockito.when(hero.getMaxShield()).thenReturn(100);
        Mockito.when(arena.getTimePlayed()).thenReturn((long)0);
        Mockito.when(hero.getXp()).thenReturn(0);
        Mockito.when(hero.getLevel()).thenReturn(1);
        Mockito.when(hero.getXpToNextLevel()).thenReturn(100);
        Mockito.when(hero.getLevelCap()).thenReturn(50);
        WaveManager waveManager = Mockito.mock(WaveManager.class);
        Mockito.when(arena.getWaveManager()).thenReturn(waveManager);
        Mockito.when(waveManager.getCurrentWave()).thenReturn(0);
        hud = new HUD(arena);
    }

    @Test
    public void elements(){
        Assertions.assertEquals(6, hud.getElements().size());

        //warrior doesn't have a resource bar so if we create hud again it will have one element less
        Mockito.when(hero.getType()).thenReturn(Hero.HeroType.WARRIOR);
        hud = new HUD(arena);

        Assertions.assertEquals(5, hud.getElements().size());
    }

    @Test
    public void update() {
        for (HUDElement e : hud.getElements()){
            if(e.getClass() == HPBar.class){
                Assertions.assertEquals(100, ((HPBar)e).getHP());
            }
        }

        Mockito.when(hero.getHP()).thenReturn(20);

        hud.update(arena);

        for (HUDElement e : hud.getElements()){
            if(e.getClass() == HPBar.class){
                Assertions.assertEquals(20, ((HPBar)e).getHP());
            }
        }
    }
}
