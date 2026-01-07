package Elio.controller;

import Elio.gui.GUI;
import Elio.model.Arena;
import Elio.model.Menu;
import Elio.model.hero.Gunman;
import Elio.model.hero.Hero;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import Elio.model.Game;
import Elio.state.PauseState;

public class GameController extends Controller<Arena> {
    private HeroController heroController;
    private long lastStepTime = 0;

    public GameController(Arena model) {
        super(model);
        heroController = new HeroController(model.getHero());
    }

    @Override
    public void processKey(Game game, KeyStroke key) throws IOException {
        if(key.getKeyType() == KeyType.Escape) {
            Arena arena = getModel();
            game.setState(new PauseState(new Menu(GUI.BLACK, GUI.WHITE, 100, 25), arena));
        }

        if(key.getKeyType() == KeyType.ArrowDown || (key.getKeyType() == KeyType.Character && key.getCharacter() == 's')) {
            heroController.moveDown(getModel());
        }

        if(key.getKeyType() == KeyType.ArrowUp || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w')) {
            heroController.moveUp(getModel());
        }

        if(key.getKeyType() == KeyType.ArrowRight || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'd')) {
            heroController.moveRight(getModel());
        }

        if(key.getKeyType() == KeyType.ArrowLeft || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a')) {
            heroController.moveLeft(getModel());
        }

        if((key.getKeyType() == KeyType.Character && key.getCharacter() == ' ')) {
            heroController.attack(getModel());
        }

        /* methods for testing features, if needed
        // some methods for hp testing while there is no way to organically lose/gain hp
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == '-'){
            getModel().getHero().loseHP(5);
        }

        if(key.getKeyType() == KeyType.Character && key.getCharacter() == '+'){
            getModel().getHero().gainHP(5);
        }

        //same thing but for xp
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'x'){
            getModel().getHero().addXp(300);
        }

        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'l'){
            getModel().getHero().setXp(getModel().getHero().getXpToNextLevel());
        }

        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'f'){
            getModel().getWaveManager().setCurrentWave(getModel().getWaveManager().getCurrentWave()+1);
        } */

        if(getModel().getHero().getType() == Hero.HeroType.GUNMAN && key.getKeyType() == KeyType.Character && key.getCharacter() == 'r'){
            ((Gunman)getModel().getHero()).reload();
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
        if(lastStepTime == 0) {
            lastStepTime = time;
        }

        long delta = time - lastStepTime;
        getModel().increaseTime(delta);
        lastStepTime = time;
    }

    //for testing purposes
    public HeroController getHeroController() { return heroController; }

    public void setHeroController(HeroController heroController) { this.heroController = heroController; }
}
