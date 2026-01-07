package state;

import Elio.controller.GameWinController;
import Elio.controller.LevelUpController;
import Elio.model.Arena;
import Elio.model.Menu;
import Elio.state.GameWinState;
import Elio.state.LevelUpState;
import Elio.state.State;
import Elio.view.GameWinViewer;
import Elio.view.LevelUpViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LevelUpStateTest {
    @Test
    public void general(){
        Menu menu = new Menu("#ABCDEF", "#322133", 300, 900);
        LevelUpState state = new LevelUpState(menu, Mockito.mock(Arena.class), 3);

        Assertions.assertEquals(state.getModel(), menu);
        Assertions.assertEquals(state.getController().getModel(), menu);
        Assertions.assertEquals(state.getViewer().getModel(), menu);

        Assertions.assertEquals(LevelUpController.class, state.getController().getClass());
        Assertions.assertEquals(LevelUpViewer.class, state.getViewer().getClass());

        Assertions.assertEquals(3, state.getOptions().size());
        Assertions.assertEquals(1, state.getOptions().getFirst().getNumber());
    }
}
