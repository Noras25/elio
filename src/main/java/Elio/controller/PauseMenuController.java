package Elio.controller;

import Elio.model.Arena;
import Elio.model.Game;
import Elio.model.Menu;
import Elio.state.GameState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class PauseMenuController extends Controller<Menu> {
    private final Arena previousState;
    private long lastBlinkTime = 0;

    public PauseMenuController(Menu model, Arena previousState) {
        super(model);
        this.previousState = previousState;
    }

    @Override
    public void processKey(Game game, KeyStroke key) throws IOException {

        if((key.getKeyType() == KeyType.Character && key.getCharacter() == ' ')) {
            game.setState(new GameState(previousState));
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
        //could use for text animation
        if (time - lastBlinkTime > 500) {
            getModel().setBlinking(!getModel().isBlinking());
            lastBlinkTime = time;
        }
    }
}
