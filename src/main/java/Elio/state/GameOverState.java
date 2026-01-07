package Elio.state;

import Elio.controller.Controller;
import Elio.controller.GameOverMenuController;
import Elio.model.Menu;
import Elio.view.GameOverMenuViewer;
import Elio.view.Viewer;

public class GameOverState extends MenuState{
    public GameOverState(Menu model) {
        super(model);
        init();
    }

    @Override
    protected Controller<Menu> createController() {
        return new GameOverMenuController(getModel());
    }

    @Override
    protected Viewer<Menu> createViewer() {
        return new GameOverMenuViewer(getModel());
    }
}
