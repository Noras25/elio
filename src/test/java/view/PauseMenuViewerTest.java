package view;

import Elio.gui.LanternaGUI;
import Elio.model.Menu;
import Elio.model.Position;
import Elio.view.IntroMenuViewer;
import Elio.view.PauseMenuViewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PauseMenuViewerTest {

    @Test
    public void drawTest(){
        Menu menu = new Menu("#123456", "#111111", 20, 25);

        PauseMenuViewer viewer = new PauseMenuViewer(menu);

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        when(gui.getGraphics()).thenReturn(graphics);

        viewer.draw(gui);

        verify(gui).fillBackground(menu.getBackgroundColor(), menu.getWidth(), menu.getHeight());

        Position startPos = new Position(35,8);

        String[] art = {
                " ___   _  _   _  ___  ___  ___ ",
                "| _ \\ /_\\| | | |/ __|| __||   \\",
                "|  _// _ \\ |_| |\\__ \\| _| | |) |",
                "|_| /_/ \\_\\___/ |___/|___||___/"
        };

        // shouldn't draw big text cause blinking is false
        for (int i = 0; i < art.length; i++) {
            verify(gui, times(0)).drawText(
                    new Position(startPos.getX(), startPos.getY() + i),
                    art[i],
                    "#111111",
                    true
            );
        }

        verify(gui).drawText(new Position(39,13), "Press SPACE to unpause", "#111111", null, false);

        menu.setBlinking(true);

        viewer.draw(gui);

        // now it should
        for (int i = 0; i < art.length; i++) {
            verify(gui).drawText(
                    new Position(startPos.getX(), startPos.getY() + i),
                    art[i],
                    "#111111",
                    true
            );
        }
    }
}