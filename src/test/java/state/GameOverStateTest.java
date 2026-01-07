package state;

import Elio.controller.GameOverMenuController;
import Elio.model.Menu;
import Elio.state.GameOverState;
import Elio.state.State;
import Elio.view.GameOverMenuViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameOverStateTest {
    @Test
    public void general(){
        Menu menu = new Menu("#ABCDEF", "#322133", 300, 900);
        GameOverState state = new GameOverState(menu);

        Assertions.assertEquals(state.getController().getModel(), menu);
        Assertions.assertEquals(state.getViewer().getModel(), menu);

        Assertions.assertEquals(GameOverMenuController.class, state.getController().getClass());
        Assertions.assertEquals(GameOverMenuViewer.class, state.getViewer().getClass());
    }
}
