package controller;

import Elio.controller.Controller;
import Elio.controller.GameOverMenuController;
import Elio.controller.GameWinController;
import Elio.gui.LanternaGUI;
import Elio.model.Game;
import Elio.model.Menu;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameWinControllerTest {
    @Test
    public void general() throws IOException {
        GameWinController controller = new GameWinController(Mockito.mock(Menu.class));
        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        Game game = Mockito.mock(Game.class);
        when(game.getGui()).thenReturn(gui);

        controller.processKey(game, new KeyStroke('q', false, false));

        verify(game.getGui()).close();
        Mockito.verify(game, Mockito.never()).setRunning(false);

        controller.processKey(game,new KeyStroke(KeyType.EOF));

        verify(game).setRunning(false);
    }
}
