package state;

import Elio.controller.CharacterSelectionController;
import Elio.model.Menu;
import Elio.state.CharacterSelectionState;
import Elio.state.State;
import Elio.view.CharacterSelectionViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharacterSelectionStateTest {
    @Test
    public void general(){
        Menu menu = new Menu("#ABCDEF", "#322133", 300, 900);
        CharacterSelectionState state = new CharacterSelectionState(menu);

        Assertions.assertEquals(state.getController().getModel(), menu);
        Assertions.assertEquals(state.getViewer().getModel(), menu);

        Assertions.assertEquals(CharacterSelectionController.class, state.getController().getClass());
        Assertions.assertEquals(CharacterSelectionViewer.class, state.getViewer().getClass());
    }
}
