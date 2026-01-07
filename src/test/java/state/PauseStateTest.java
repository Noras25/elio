package state;

import Elio.controller.GameWinController;
import Elio.controller.PauseMenuController;
import Elio.model.Arena;
import Elio.model.Menu;
import Elio.model.hero.HeroFactory;
import Elio.state.GameWinState;
import Elio.state.PauseState;
import Elio.state.State;
import Elio.view.GameWinViewer;
import Elio.view.PauseMenuViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static Elio.model.hero.Hero.HeroType.MAGE;

public class PauseStateTest {
    @Test
    public void general(){
        Menu menu = new Menu("#ABCDEF", "#322133", 300, 900);
        PauseState state = new PauseState(menu, Mockito.mock(Arena.class));

        Assertions.assertEquals(state.getModel(), menu);
        Assertions.assertEquals(state.getController().getModel(), menu);
        Assertions.assertEquals(state.getViewer().getModel(), menu);

        Assertions.assertEquals(PauseMenuController.class, state.getController().getClass());
        Assertions.assertEquals(PauseMenuViewer.class, state.getViewer().getClass());
    }
}
