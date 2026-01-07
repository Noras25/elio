package Elio.state;

import Elio.gui.GUI;
import Elio.model.Game;
import Elio.controller.Controller;
import Elio.gui.LanternaGUI;
import Elio.view.Viewer;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private Controller<T> controller;
    private Viewer<T> viewer;
    
    public State(T model){
        this.model = model;
    }

    protected void init(){
        this.controller = createController();
        this.viewer = createViewer();
    }

    public Controller<T> getController(){
        return controller;
    }

    public Viewer<T> getViewer(){
        return viewer;
    }

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException{
        KeyStroke key = gui.getKey();
        if(key != null) controller.processKey(game, key);
        controller.step(game, time);
    }

    public void draw(GUI gui) throws IOException {
        gui.clear();
        viewer.draw(gui);
        gui.refresh();
    }

    protected abstract Controller<T> createController();
    protected abstract Viewer<T> createViewer();

    public void setViewer(Viewer<T> viewer) { this.viewer = viewer; } //for testing
    public void setController(Controller<T> controller) { this.controller = controller; }
}
