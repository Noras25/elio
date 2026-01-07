package Elio.view.model.HUD;

import Elio.gui.GUI;
import Elio.model.HUD.HUDElement;
import Elio.model.HUD.XPBar;
import Elio.model.Position;

import java.io.IOException;


public class XPBarViewer extends HUDElementViewer {
    public XPBarViewer(HUDElement model){
        super(model);
    }

    @Override
    public XPBar getModel() {
        return (XPBar) super.getModel();
    }

    @Override
    public void draw(GUI gui) throws IOException {
        int XP = getModel().getXP();
        int XPToNextLevel = getModel().getXPToNextLevel();
        int level = getModel().getLevel();
        int levelCap = getModel().getLevelCap();

        int size = 29;
        if (level > 9) size++;
        if (level == levelCap) size++;

        int maxWidth = 20;
        double percent = (double) XP / XPToNextLevel;
        int filled = (int) (percent * maxWidth);

        StringBuilder bar = new StringBuilder();
        bar.append("[");
        for (int i = 0; i < maxWidth; i++) {
            bar.append(i < filled ? "=" : " ");
        }
        bar.append("] ");

        String text;
        if(getModel().getLevel() == getModel().getLevelCap()){
            text = "LVL: MAX ";

        }
        else text = "LVL: " + getModel().getLevel() + " ";

        gui.drawText(
                new Position( getModel().getArenaWidth() - size, 0),
                text + bar,
                "#00FFFF",
                true

        );
    }
}
