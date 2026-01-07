package Elio.controller;

import Elio.model.Game;
import Elio.model.Menu;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class GameWinController extends Controller<Menu>{
    public GameWinController(Menu model) {
        super(model);
    }

    @Override
    public void processKey(Game game, KeyStroke key) throws IOException {

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
