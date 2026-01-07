package Elio.model.HUD;

import Elio.model.Arena;
import Elio.view.model.HUD.HPBarViewer;
import Elio.view.model.HUD.HUDElementViewer;
import Elio.view.model.HUD.TimerViewer;

public class Timer implements HUDElement {
    private long timeElapsed;
    private final int arenaWidth;

    public Timer(Arena arena){
        this.timeElapsed = arena.getTimePlayed();
        this.arenaWidth = arena.getWidth();
    }

    public long getTimeElapsed() { return timeElapsed; }

    public int getArenaWidth() {
        return arenaWidth;
    }

    @Override
    public void update(Arena arena) {
        this.timeElapsed = arena.getTimePlayed();
    }

    @Override
    public HUDElementViewer createViewer() {
        return new TimerViewer(this);
    }
}
