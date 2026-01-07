package Elio.controller;

import Elio.gui.GUI;
import Elio.model.Game;
import Elio.model.Menu;
import Elio.state.IntroMenuState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class GameOverMenuController extends MenuController {

    public GameOverMenuController(Menu model) {
        super(model);
    }

    @Override
    public void processKey(Game game, KeyStroke key) throws IOException {
        if((key.getKeyType() == KeyType.Character && key.getCharacter() == 'r')) {
            game.setState(new IntroMenuState(new Menu(GUI.BLACK, GUI.WHITE, 100, 25)));
        }

        if((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')) {
            game.getGui().close();
        }

        if (key.getKeyType() == KeyType.EOF) {
            game.setRunning(false);
        }

    }

    @Override
    public void step(Game game, long time) throws IOException {
        //nothing to process here
    }

}
