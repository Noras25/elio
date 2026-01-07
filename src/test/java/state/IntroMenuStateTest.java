package state;

import Elio.controller.IntroMenuController;
import Elio.model.Menu;
import Elio.state.IntroMenuState;
import Elio.state.State;
import Elio.view.IntroMenuViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntroMenuStateTest {
    @Test
    public void general(){
        Menu menu = new Menu("#ABCDEF", "#322133", 300, 900);
        IntroMenuState state = new IntroMenuState(menu);

        Assertions.assertEquals(state.getController().getModel(), menu);
        Assertions.assertEquals(state.getViewer().getModel(), menu);

        Assertions.assertEquals(IntroMenuController.class, state.getController().getClass());
        Assertions.assertEquals(IntroMenuViewer.class, state.getViewer().getClass());
    }
}
