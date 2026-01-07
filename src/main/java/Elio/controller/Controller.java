package Elio.controller;

import Elio.model.Game;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public abstract class Controller<T> { //this way we can have different controllers for different models
    private final T model;
    public Controller(T model){
        this.model = model;
    }

    //getter
    public T getModel() {
        return model;
    }

    //function to process key inputs
    public abstract void processKey(Game game, KeyStroke key) throws IOException;

    //function that handles time
    public abstract void step(Game game, long time) throws IOException;
}
