package controller;

import Elio.controller.Controller;
import Elio.controller.GameOverMenuController;
import Elio.controller.GameWinController;
import Elio.controller.IntroMenuController;
import Elio.gui.LanternaGUI;
import Elio.model.Game;
import Elio.model.Menu;
import Elio.state.CharacterSelectionState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IntroMenuControllerTest {
    public IntroMenuController controller;
    public Game game;

    @BeforeEach
    public void setup(){
        controller = new IntroMenuController(Mockito.mock(Menu.class));

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        game = Mockito.mock(Game.class);
        when(game.getGui()).thenReturn(gui);
    }

    @Test
    public void start() throws IOException {
        controller.processKey(game, new KeyStroke(' ', false, false));

        verify(game).setState(Mockito.any(CharacterSelectionState.class));
    }

    @Test
    public void exit() throws IOException {
        controller.processKey(game, new KeyStroke('q', false, false));

        verify(game.getGui()).close();
        Mockito.verify(game, Mockito.never()).setRunning(false);

        controller.processKey(game,new KeyStroke(KeyType.EOF));

        verify(game).setRunning(false);
    }
}