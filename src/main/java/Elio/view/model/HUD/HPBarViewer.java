package Elio.view.model.HUD;

import Elio.gui.GUI;
import Elio.model.HUD.HPBar;
import Elio.model.HUD.HUDElement;
import Elio.model.Position;


public class HPBarViewer extends HUDElementViewer {
    public HPBarViewer(HUDElement hpBar){
        super(hpBar);
    }

    @Override
    public HPBar getModel() {
        return (HPBar) super.getModel();
    }

    @Override
    public void draw(GUI gui) {
        int hp = getModel().getHP();
        int maxHp = getModel().getMaxHP();

        int barWidth = 20;
        int filledWidth = (int) (((double)hp / (double)maxHp) * barWidth) + 1;
        double filledPercent = ((double)hp / (double)maxHp);
        String color;

        if(filledPercent > 0.5) {
            color = "#00FF0A";
        } else if(filledPercent > 0.2) {
            color = "#FFFF00";
        } else {
            color = "#FF000D";
        }

        StringBuilder bar = new StringBuilder("♥ [");

        for (int i = 0; i < barWidth; i++) {
            if (i < filledWidth) {
                bar.append("|");
            } else {
                bar.append(" ");
            }
        }


        bar.append("] ").append(hp).append("/").append(maxHp);

        String background = "#002011";

        gui.drawText(new Position(0, 0), bar.toString(), color, background, false);
    }
}
