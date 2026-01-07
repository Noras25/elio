package model.HUD;

import Elio.model.Arena;
import Elio.model.HUD.ManaBar;
import Elio.model.HUD.WaveCounter;
import Elio.model.arena.WaveManager;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.hero.Mage;
import Elio.view.model.HUD.ManaViewer;
import Elio.view.model.HUD.WaveCounterViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WaveCounterTest {
    private WaveCounter waveCounter;
    private Arena arena;
    private WaveManager waveManager;

    @BeforeEach
    public void setup(){
        arena = Mockito.mock(Arena.class);
        waveManager = Mockito.mock(WaveManager.class);
        Mockito.when(arena.getWidth()).thenReturn(100);
        Mockito.when(arena.getWaveManager()).thenReturn(waveManager);
        Mockito.when(waveManager.getCurrentWave()).thenReturn(1);
        waveCounter = new WaveCounter(arena);
    }

    @Test
    public void constructor(){
        Assertions.assertEquals(1, waveCounter.getWave());
        Assertions.assertEquals(100, waveCounter.getArenaWidth());
    }

    @Test
    public void update(){
        Mockito.when(waveManager.getCurrentWave()).thenReturn(15);

        waveCounter.update(arena);

        Assertions.assertEquals(15, waveCounter.getWave());
    }

    @Test
    public void createViewer(){
        Assertions.assertEquals(WaveCounterViewer.class, waveCounter.createViewer().getClass());
    }
}
