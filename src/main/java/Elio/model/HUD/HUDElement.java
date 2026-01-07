package Elio.model.HUD;

import Elio.model.Arena;
import Elio.view.model.HUD.HUDElementViewer;

public interface HUDElement {

    void update(Arena arena);
    HUDElementViewer createViewer();

}
