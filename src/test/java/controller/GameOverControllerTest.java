package controller;

import Elio.controller.Controller;
import Elio.controller.GameController;
import Elio.controller.GameOverMenuController;
import Elio.gui.LanternaGUI;
import Elio.model.Arena;
import Elio.model.Game;
import Elio.model.Menu;
import Elio.model.Position;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.state.IntroMenuState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GameOverControllerTest {
    private Controller<Menu> controller;
    private Game game;

    @BeforeEach
    public void setup(){
        controller = new GameOverMenuController(Mockito.mock(Menu.class));

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        game = Mockito.mock(Game.class);
        Mockito.when(game.getGui()).thenReturn(gui);
    }

    @Test
    public void exit() throws IOException {
        controller.processKey(game, new KeyStroke('q', false, false));

        verify(game.getGui()).close();
        verify(game, Mockito.never()).setRunning(false);

        controller.processKey(game,new KeyStroke(KeyType.EOF));

        verify(game).setRunning(false);
    }

    @Test
    public void restart() throws IOException {
        controller.processKey(game, new KeyStroke('r', false, false));

        verify(game).setState(Mockito.any(IntroMenuState.class));
    }
}
