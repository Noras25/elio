package state;

import Elio.controller.GameController;
import Elio.model.Arena;
import Elio.model.HUD.HPBar;
import Elio.model.HUD.HUDElement;
import Elio.model.hero.Gunman;
import Elio.state.GameState;
import Elio.state.State;
import Elio.view.GameViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameStateTest {
    private GameState state;
    private Arena arena;

    @BeforeEach
    public void setup(){
        this.arena = new Arena(100, 50, new Gunman(15,10));
        this.state = new GameState(arena);

    }

    @Test
    public void general(){
        Assertions.assertEquals(state.getController().getModel(), arena);
        Assertions.assertEquals(state.getViewer().getModel(), arena);

        Assertions.assertEquals(GameController.class, state.getController().getClass());
        Assertions.assertEquals(GameViewer.class, state.getViewer().getClass());
    }

    @Test
    public void update(){
        state.update(1200);

        Assertions.assertEquals(1.2, arena.getHero().getLastTimeTakenDamage());
    }
}
