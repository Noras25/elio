package Elio.state;

import Elio.controller.Controller;
import Elio.controller.GameWinController;
import Elio.model.Menu;
import Elio.view.GameWinViewer;
import Elio.view.Viewer;

public class GameWinState extends State<Menu> {
    public GameWinState(Menu model) {
        super(model);
        init();
    }

    @Override
    protected Controller<Menu> createController() {
        return new GameWinController(getModel());
    }

    @Override
    protected Viewer<Menu> createViewer() {
        return new GameWinViewer(getModel());
    }
}
