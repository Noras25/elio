package view;

import Elio.gui.LanternaGUI;
import Elio.model.Arena;
import Elio.model.HUD.HUD;
import Elio.model.hero.Hero;
import Elio.model.hero.Warrior;
import Elio.view.GameViewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameViewerTest {

    @Test
    public void drawTest(){
        Hero hero = new Warrior(25,25);
        Arena arena = new Arena(100, 100, hero);

        GameViewer viewer = new GameViewer(arena, new HUD(arena));

        Assertions.assertEquals(arena, viewer.getModel());

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        when(gui.getGraphics()).thenReturn(graphics);

        try {

            GameViewer spyViewer = Mockito.spy(viewer);

            spyViewer.draw(gui);

            verify(spyViewer).draw(gui);

           // verify(spyViewer, times(1)).drawHealthBar(gui, hero);

            verify(gui).refresh();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
