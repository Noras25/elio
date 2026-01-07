package Elio.controller;

import Elio.model.Arena;
import Elio.model.Game;
import Elio.model.Menu;
import Elio.model.hero.*;
import Elio.state.GameState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class CharacterSelectionController extends MenuController {

    public CharacterSelectionController(Menu model) {
        super(model);
    }

    @Override
    public void processKey(Game game, KeyStroke key) throws IOException {
        if((key.getKeyType() == KeyType.Character && key.getCharacter() == 'w')) {
            game.setState(new GameState(new Arena(100, 25, HeroFactory.createHero(Hero.HeroType.WARRIOR, 40, 10))));
        }

        if((key.getKeyType() == KeyType.Character && key.getCharacter() == 'm')) {
            game.setState(new GameState(new Arena(100, 25, HeroFactory.createHero(Hero.HeroType.MAGE, 40, 10))));
        }

        if((key.getKeyType() == KeyType.Character && key.getCharacter() == 'g')) {
            game.setState(new GameState(new Arena(100, 25, HeroFactory.createHero(Hero.HeroType.GUNMAN, 40, 10))));
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
        //empty there is nothing to process here :)
    }
}
