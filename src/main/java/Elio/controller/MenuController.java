package Elio.controller;

import Elio.gui.GUI;
import Elio.model.Game;
import Elio.model.Menu;
import com.googlecode.lanterna.input.KeyStroke;


import java.io.IOException;

public abstract class MenuController extends Controller<Menu> {

    public MenuController(Menu model) {
        super(model);
    }

    public abstract void processKey(Game game, KeyStroke key) throws IOException;
    public abstract void step(Game game, long time) throws  IOException;
}
