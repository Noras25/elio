package Elio.view.model.HUD;

import Elio.gui.GUI;
import Elio.model.HUD.AmmoBar;
import Elio.model.HUD.HUDElement;
import Elio.model.HUD.ManaBar;
import Elio.model.Position;

public class ManaViewer extends HUDElementViewer {
    public ManaViewer(HUDElement manaBar){ super(manaBar);
    }

    @Override
    public ManaBar getModel() { return (ManaBar) super.getModel(); }

    @Override
    public void draw(GUI gui) {
        int mana = getModel().getMana();
        int maxMana = getModel().getMaxMana();

        int barWidth = Math.min(maxMana, 20);

        int filledWidth = (int) (((double)mana / (double)maxMana) * barWidth);

        String color = "#8A2BE2";

        StringBuilder bar = new StringBuilder("♦ [");

        for (int i = 0; i < barWidth; i++) {
            if (i < filledWidth) {
                bar.append("|");
            } else {
                bar.append(" ");
            }
        }


        bar.append("] ").append(mana).append("/").append(maxMana);

        String background = "#002011";

        gui.drawText(new Position(0, getModel().getHeight() - 1), bar.toString(), color, background, false);
    }
}
