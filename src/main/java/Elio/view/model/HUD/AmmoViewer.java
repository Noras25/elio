package Elio.view.model.HUD;

import Elio.gui.GUI;
import Elio.model.HUD.AmmoBar;
import Elio.model.HUD.HUDElement;
import Elio.model.HUD.ShieldBar;
import Elio.model.Position;

public class AmmoViewer extends HUDElementViewer {
    public AmmoViewer(HUDElement ammoBar){ super(ammoBar);
    }

    @Override
    public AmmoBar getModel() { return (AmmoBar) super.getModel(); }

    @Override
    public void draw(GUI gui) {
        int ammo = getModel().getAmmo();
        int maxAmmo = getModel().getMaxAmmo();

        int barWidth;
        if (maxAmmo < 90) barWidth = maxAmmo / 3;
        else barWidth = 30;
        int filledWidth = (int) (((double)ammo / (double)maxAmmo) * barWidth);

        String color = "#FF7518";

        StringBuilder bar = new StringBuilder("* [");

        for (int i = 0; i < barWidth; i++) {
            if (i < filledWidth) {
                bar.append("|");
            } else {
                bar.append(" ");
            }
        }


        bar.append("] ").append(ammo).append("/").append(maxAmmo);

        if(getModel().isReloading()) bar.append(" ⟳");
        else if(ammo == 0) bar.append(" [R] Reload");

        String background = "#002011";

        gui.drawText(new Position(0, getModel().getHeight() - 1), bar.toString(), color, background, false);
    }
}
