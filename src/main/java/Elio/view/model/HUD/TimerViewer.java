package Elio.view.model.HUD;

import Elio.gui.GUI;
import Elio.model.HUD.HPBar;
import Elio.model.HUD.HUDElement;
import Elio.model.HUD.Timer;
import Elio.model.Menu;
import Elio.model.Position;

public class TimerViewer extends HUDElementViewer{
    public TimerViewer (HUDElement timer){
        super(timer);
    }

    @Override
    public Timer getModel() {
        return (Timer) super.getModel();
    }

    @Override
    public void draw(GUI gui) {
        long rawTime = getModel().getTimeElapsed();

        long minutes = (rawTime / 1000) / 60;
        long seconds = (rawTime / 1000) % 60;

        String timeStr = String.format("%02d:%02d", minutes, seconds);

        gui.drawText(new Position(getModel().getArenaWidth() / 2 - 2, 1), timeStr, GUI.WHITE, null, true);
    }
}
