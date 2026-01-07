package state;

import Elio.controller.GameOverMenuController;
import Elio.controller.GameWinController;
import Elio.model.Menu;
import Elio.state.GameOverState;
import Elio.state.GameWinState;
import Elio.state.State;
import Elio.view.GameOverMenuViewer;
import Elio.view.GameWinViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameWinStateTest {
    @Test
    public void general(){
        Menu menu = new Menu("#ABCDEF", "#322133", 300, 900);
        GameWinState state = new GameWinState(menu);

        Assertions.assertEquals(state.getModel(), menu);
        Assertions.assertEquals(state.getController().getModel(), menu);
        Assertions.assertEquals(state.getViewer().getModel(), menu);

        Assertions.assertEquals(GameWinController.class, state.getController().getClass());
        Assertions.assertEquals(GameWinViewer.class, state.getViewer().getClass());
    }
}
