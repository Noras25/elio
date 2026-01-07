package view;

import Elio.gui.GUI;
import Elio.gui.LanternaGUI;
import Elio.model.Menu;
import Elio.model.Position;
import Elio.view.GameOverMenuViewer;
import Elio.view.IntroMenuViewer;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

public class GameOverMenuViewerTest {

    @Test
    public void drawTest() {
        Menu menu = new Menu("#222222", "#789ABC", 15, 60);

        GameOverMenuViewer viewer = new GameOverMenuViewer(menu);

        Assertions.assertEquals(menu, viewer.getModel());

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        when(gui.getGraphics()).thenReturn(graphics);

        viewer.draw(gui);

        verify(gui).fillBackground(menu.getBackgroundColor(), menu.getWidth(), menu.getHeight());

        verify(gui).drawText(new Position(45,12), "You DIED!", menu.getTextColor(),true);
        verify(gui).drawText(new Position(40, 13), "Press \"R\" to RESTART", GUI.WHITE, false);

    }
}