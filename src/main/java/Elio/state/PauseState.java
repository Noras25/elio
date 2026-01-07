package Elio.state;

import Elio.controller.Controller;
import Elio.controller.PauseMenuController;
import Elio.model.Arena;
import Elio.model.Menu;
import Elio.view.PauseMenuViewer;
import Elio.view.Viewer;

public class PauseState extends State<Menu> {
    private final Arena previousState;

    public PauseState(Menu model, Arena previousState){
        super(model);
        this.previousState = previousState;
        init();
    }

    @Override
    protected Controller<Menu> createController() {
        return new PauseMenuController(getModel(), previousState);
    }

    @Override
    protected Viewer<Menu> createViewer() {
        return new PauseMenuViewer(getModel());
    }

}
