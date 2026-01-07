package Elio.view.model.HUD;

import Elio.model.HUD.HUDElement;
import Elio.view.Viewer;

public abstract class HUDElementViewer extends Viewer<HUDElement> {
    public HUDElementViewer(HUDElement model) {
        super(model);
    }
}
