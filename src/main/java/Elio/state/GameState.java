package Elio.state;

import Elio.controller.GameController;
import Elio.model.Arena;
import Elio.model.HUD.HUD;
import Elio.view.GameViewer;
import Elio.view.Viewer;
import Elio.controller.Controller;

public class GameState extends State<Arena> {
    private final HUD hud;

    public GameState(Arena arena){
        super(arena);
        this.hud = new HUD(getModel());
        init();
    }

    @Override
    protected Controller<Arena> createController() {
        return new GameController(getModel());
    }

    @Override
    protected Viewer<Arena> createViewer() {
        return new GameViewer(getModel(), hud);
    }

    public void update(long deltaMillis){
        hud.update(getModel());
        getModel().update(deltaMillis);
    }
}
