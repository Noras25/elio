package view;

import Elio.gui.LanternaGUI;
import Elio.model.Arena;
import Elio.model.Menu;
import Elio.model.Position;
import Elio.model.hero.Hero;
import Elio.model.hero.Warrior;
import Elio.view.GameViewer;
import Elio.view.IntroMenuViewer;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IntroMenuViewerTest {

    @Test
    public void drawTest(){
        Menu menu = new Menu("#123456", "#111111", 20, 25);

        IntroMenuViewer viewer = new IntroMenuViewer(menu);

        Assertions.assertEquals(menu, viewer.getModel());

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        when(gui.getGraphics()).thenReturn(graphics);

        viewer.draw(gui);

        verify(gui).fillBackground(menu.getBackgroundColor(), menu.getWidth(), menu.getHeight());

        verify(gui).drawText(new Position(40,12), "Welcome to ELIO!", menu.getTextColor(), true);
        verify(gui).drawText(new Position(38,13), "Press SPACE to start", menu.getTextColor(), false);

    }
}
