package Elio.view.model.HUD;

import Elio.gui.GUI;
import Elio.model.HUD.HUDElement;
import Elio.model.HUD.Timer;
import Elio.model.HUD.WaveCounter;
import Elio.model.Position;

public class WaveCounterViewer extends  HUDElementViewer {
    public WaveCounterViewer (HUDElement waveCounter){
        super(waveCounter);
    }

    @Override
    public WaveCounter getModel() {
        return (WaveCounter) super.getModel();
    }

    @Override
    public void draw(GUI gui) {
        int wave = getModel().getWave();

        String str = String.format("Current Wave: %02d", wave);

        gui.drawText(new Position(getModel().getArenaWidth() / 2 - 8, 0), str, "#949494", true);
    }
}
