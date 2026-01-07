package Elio.view.model.HUD;

import Elio.gui.GUI;
import Elio.model.HUD.HPBar;
import Elio.model.HUD.HUDElement;
import Elio.model.HUD.ShieldBar;
import Elio.model.Position;

public class ShieldBarViewer extends HUDElementViewer {
    public ShieldBarViewer(HUDElement shieldBar){
        super(shieldBar);
    }

    @Override
    public ShieldBar getModel() {
        return (ShieldBar) super.getModel();
    }

    @Override
    public void draw(GUI gui) {
        int shield = getModel().getShield();
        int maxShield = getModel().getMaxShield();
        if(maxShield == 0) return;

        int barWidth;
        if(maxShield >= 100)
            barWidth = 20;
        else {
            barWidth = maxShield / 5;
        }
        int filledWidth = (int) (((double)shield / (double)maxShield) * barWidth);

        String color = "#3CDFFF";

        StringBuilder bar = new StringBuilder("⛨ [");

        for (int i = 0; i < barWidth; i++) {
            if (i < filledWidth) {
                bar.append("|");
            } else {
                bar.append(" ");
            }
        }


        bar.append("] ").append(shield).append("/").append(maxShield);

        String background = "#002011";

        gui.drawText(new Position(0, 1), bar.toString(), color, background, false);
    }
}
