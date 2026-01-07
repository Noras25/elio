package controller;


import Elio.controller.PauseMenuController;
import Elio.gui.LanternaGUI;
import Elio.model.Arena;
import Elio.model.Game;
import Elio.model.Menu;
import Elio.model.hero.Hero;
import Elio.gui.GUI;
import Elio.model.hero.HeroFactory;
import Elio.state.GameState;
import Elio.view.PauseMenuViewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.geom.Area;
import java.io.IOException;

public class PauseMenuControllerTest {
    private PauseMenuController controller;
    private Game game;
    private Menu menu;

    @BeforeEach
    public void setup(){
        menu = Mockito.mock(Menu.class);
        Arena arena = new Arena(100, 100, HeroFactory.createHero(Hero.HeroType.WARRIOR,7,8));
        controller = new PauseMenuController(menu, arena);

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        game = Mockito.mock(Game.class);
        Mockito.when(game.getGui()).thenReturn(gui);
    }


    @Test
    public void unpause() throws IOException {
        controller.processKey(game,new KeyStroke(' ', false, false));

        Mockito.verify(game).setState(Mockito.any(GameState.class));
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
    public void step() throws IOException {
        Mockito.when(menu.isBlinking()).thenReturn(false);

        controller.step(game, 500);

        Mockito.verify(menu, Mockito.never()).setBlinking(true);

        controller.step(game, 501);

        Mockito.verify(menu).setBlinking(true);

        controller.step(game, 700);

        Mockito.verify(menu, Mockito.times(1)).setBlinking(true); //test to kill a specific mutant
    }
}
