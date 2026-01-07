package controller;


import Elio.controller.Controller;
import Elio.controller.GameController;
import Elio.controller.HeroController;
import Elio.gui.GUI;
import Elio.gui.LanternaGUI;
import Elio.model.Arena;
import Elio.model.Game;
import Elio.model.Menu;
import Elio.model.Position;
import Elio.model.hero.Gunman;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.state.PauseState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

public class GameControllerTest {
    private GameController controller;
    private Game game;
    private Arena arena;

    @BeforeEach
    public void setup(){
        Hero spyHero = Mockito.spy(HeroFactory.createHero(Hero.HeroType.GUNMAN, 10, 25));

        arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getHero()).thenReturn(spyHero);

        controller = new GameController(arena);

        HeroController spyHeroController = Mockito.spy(controller.getHeroController());
        controller.setHeroController(spyHeroController);

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        game = Mockito.mock(Game.class);
        Mockito.when(game.getGui()).thenReturn(gui);
    }

    @Test
    public void exit() throws IOException {
        controller.processKey(game, new KeyStroke('q', false, false));

        Mockito.verify(game.getGui()).close();
        Mockito.verify(game, Mockito.never()).setRunning(false);

        controller.processKey(game,new KeyStroke(KeyType.EOF));

        Mockito.verify(game).setRunning(false);
    }

    @Test
    public void arrowDown() throws IOException {
        controller.processKey(game, new KeyStroke(KeyType.ArrowDown));

        Mockito.verify(controller.getHeroController()).moveDown(arena);

        controller.processKey(game, new KeyStroke('s', false, false));

        Mockito.verify(controller.getHeroController(), Mockito.times(2)).moveDown(arena);
    }

    @Test
    public void arrowUp() throws IOException {
        controller.processKey(game, new KeyStroke(KeyType.ArrowUp));

        Mockito.verify(controller.getHeroController()).moveUp(arena);

        controller.processKey(game, new KeyStroke('w', false, false));

        Mockito.verify(controller.getHeroController(), Mockito.times(2)).moveUp(arena);
    }

    @Test
    public void arrowLeft() throws IOException {
        controller.processKey(game, new KeyStroke(KeyType.ArrowLeft));

        Mockito.verify(controller.getHeroController()).moveLeft(arena);

        controller.processKey(game, new KeyStroke('a', false, false));

        Mockito.verify(controller.getHeroController(), Mockito.times(2)).moveLeft(arena);
    }

    @Test
    public void arrowRight() throws IOException {
        controller.processKey(game, new KeyStroke(KeyType.ArrowRight));

        Mockito.verify(controller.getHeroController()).moveRight(arena);

        controller.processKey(game, new KeyStroke('d', false, false));

        Mockito.verify(controller.getHeroController(), Mockito.times(2)).moveRight(arena);
    }

    @Test
    public void pause() throws IOException {
        controller.processKey(game, new KeyStroke(KeyType.Escape));

        Mockito.verify(game).setState(Mockito.any(PauseState.class));
    }

    @Test
    public void reload() throws IOException {
        controller.processKey(game, new KeyStroke('r', false, false));

        Mockito.verify((Gunman)arena.getHero()).reload();
    }

    @Test
    public void attack() throws IOException {
        controller.processKey(game, new KeyStroke(' ', false, false));

        Mockito.verify(controller.getHeroController()).attack(arena);
    }

    @Test
    public void step() throws IOException {
        controller.step(game, 100L);

        Mockito.verify(arena).increaseTime(0); //first increase should be 0

        controller.step(game, 200L);

        Mockito.verify(arena).increaseTime(100L);
    }

}
