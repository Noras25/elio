package Elio.model.HUD;

import Elio.model.Arena;
import Elio.view.model.HUD.HUDElementViewer;
import Elio.view.model.HUD.TimerViewer;
import Elio.view.model.HUD.WaveCounterViewer;

public class WaveCounter implements  HUDElement{
    private int wave;
    private final int arenaWidth;

    public WaveCounter(Arena arena){
        this.wave = arena.getWaveManager().getCurrentWave();
        this.arenaWidth = arena.getWidth();
    }

    public int getWave() { return wave; }

    public int getArenaWidth() {
        return arenaWidth;
    }

    @Override
    public void update(Arena arena) {
        this.wave = arena.getWaveManager().getCurrentWave();
    }

    @Override
    public HUDElementViewer createViewer() {
        return new WaveCounterViewer(this);
    }
}
