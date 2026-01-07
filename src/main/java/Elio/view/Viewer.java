package Elio.view;

import Elio.gui.GUI;
import Elio.gui.LanternaGUI;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    // Every viewer must implement this to draw its specific model
    public abstract void draw(GUI gui) throws IOException;
}
