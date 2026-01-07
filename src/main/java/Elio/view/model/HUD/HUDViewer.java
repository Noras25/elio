package Elio.view.model.HUD;

import Elio.gui.GUI;
import Elio.model.HUD.HUD;
import Elio.model.HUD.HUDElement;
import Elio.view.Viewer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HUDViewer extends Viewer<HUD> {
    private final List<HUDElementViewer> elementViewers;

    public HUDViewer(HUD hud) {
        super(hud);
        elementViewers = new ArrayList<>();
        for (HUDElement e : hud.getElements()) {
            elementViewers.add(e.createViewer());
        }
    }

    @Override
    public void draw(GUI gui) throws IOException {
        for (HUDElementViewer v : elementViewers) v.draw(gui);
    }
}
