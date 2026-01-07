package state;

import Elio.controller.Controller;
import Elio.gui.LanternaGUI;
import Elio.model.Menu;
import Elio.state.GameOverState;
import Elio.state.State;
import Elio.view.Viewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import Elio.model.Game;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class StateTest {
    private State<Menu> spyState;
    private LanternaGUI gui;
    private Game game;

    @BeforeEach
    public void setup(){
        State<Menu> state =  new GameOverState(new Menu("#FFFFFF", "#000000", 10, 1000));
        spyState = Mockito.spy(state);

        Viewer<Menu> realViewer = state.getViewer();
        Viewer<Menu> spyViewer = Mockito.spy(realViewer);

        spyState.setViewer(spyViewer);

        Controller<Menu> realController = state.getController();
        Controller<Menu> spyController = Mockito.spy(realController);

        spyState.setController(spyController);

        gui = Mockito.mock(LanternaGUI.class);

        game = Mockito.mock(Game.class);
        when(game.getGui()).thenReturn(gui);
    }

    @Test
    public void draw() throws IOException {
        spyState.draw(gui);

        verify(gui).clear();
        verify(spyState.getViewer()).draw(gui);
        verify(gui).refresh();
    }

    @Test
    public void step() throws IOException {
        KeyStroke ks = new KeyStroke(KeyType.Escape);
        when(gui.getKey()).thenReturn(ks);

        spyState.step(game, gui, 1200);

        verify(spyState.getController()).processKey(game, ks);
        verify(spyState.getController()).step(game, 1200);

        ks = null;
        spyState.step(game, gui, 300);

        verify(spyState.getController(), Mockito.times(0)).processKey(game, ks);
        verify(spyState.getController()).step(game,300);
    }

}