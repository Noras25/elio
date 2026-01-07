package Elio.state;

import Elio.controller.CharacterSelectionController;
import Elio.controller.Controller;
import Elio.model.Menu;
import Elio.view.CharacterSelectionViewer;
import Elio.view.Viewer;

public class CharacterSelectionState extends MenuState {
    public CharacterSelectionState(Menu menu){
        super(menu);
        init();
    }

    @Override
    protected Controller<Menu> createController() {
        return new CharacterSelectionController(getModel());
    }

    @Override
    protected Viewer<Menu> createViewer() {
        return new CharacterSelectionViewer(getModel());
    }
}
