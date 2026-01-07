package model.HUD;

import Elio.model.Arena;
import Elio.model.HUD.ManaBar;
import Elio.model.HUD.Timer;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.hero.Mage;
import Elio.view.model.HUD.ManaViewer;
import Elio.view.model.HUD.TimerViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TimerTest {
    private Timer timer;
    private Arena arena;

    @BeforeEach
    public void setup(){
        arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getTimePlayed()).thenReturn((long)0);
        Mockito.when(arena.getWidth()).thenReturn(100);
        timer = new Timer(arena);
    }

    @Test
    public void constructor(){
        Assertions.assertEquals(0, timer.getTimeElapsed());
        Assertions.assertEquals(100, timer.getArenaWidth());
    }

    @Test
    public void update(){
        Mockito.when(arena.getTimePlayed()).thenReturn((long)1000);

        timer.update(arena);

        Assertions.assertEquals(1000, timer.getTimeElapsed());
    }

    @Test
    public void createViewer(){
        Assertions.assertEquals(TimerViewer.class, timer.createViewer().getClass());
    }
}
