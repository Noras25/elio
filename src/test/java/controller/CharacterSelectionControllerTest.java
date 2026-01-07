package controller;


import Elio.controller.CharacterSelectionController;
import Elio.controller.Controller;
import Elio.gui.LanternaGUI;
import Elio.model.Game;
import Elio.model.Menu;
import Elio.model.hero.Hero;
import Elio.state.GameState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;

public class CharacterSelectionControllerTest {
    private Controller<Menu> controller;
    private Game game;

    @BeforeEach
    public void setup(){
        controller = new CharacterSelectionController(Mockito.mock(Menu.class));

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        game = Mockito.mock(Game.class);
        Mockito.when(game.getGui()).thenReturn(gui);
    }

    @Test
    public void general() throws IOException {
        controller.processKey(game, new KeyStroke('q', false, false));

        verify(game.getGui()).close();
        verify(game, Mockito.never()).setRunning(false);

        controller.processKey(game,new KeyStroke(KeyType.EOF));

        verify(game).setRunning(false);
    }

    @Test
    public void selectingCharacter() throws IOException {
        controller.processKey(game, new KeyStroke('w', false, false));

        Mockito.verify(game).setState(Mockito.any(GameState.class));

        controller.processKey(game, new KeyStroke('m', false, false));

        Mockito.verify(game, Mockito.times(2)).setState(Mockito.any(GameState.class));

        controller.processKey(game, new KeyStroke('g', false, false));

        Mockito.verify(game, Mockito.times(3)).setState(Mockito.any(GameState.class));
    }
}
