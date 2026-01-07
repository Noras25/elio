package Elio.state;

import Elio.controller.Controller;
import Elio.controller.IntroMenuController;
import Elio.model.Menu;
import Elio.view.IntroMenuViewer;
import Elio.view.Viewer;

public class IntroMenuState extends MenuState {
    public IntroMenuState(Menu model){
        super(model);
        init();
    }

    @Override
    protected Controller<Menu> createController() {
        return new IntroMenuController(getModel());
    }

    @Override
    protected Viewer<Menu> createViewer() {
        return new IntroMenuViewer(getModel());
    }
}
