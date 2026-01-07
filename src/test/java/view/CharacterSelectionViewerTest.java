package view;

import Elio.gui.LanternaGUI;
import Elio.model.Menu;
import Elio.model.Position;
import Elio.view.CharacterSelectionViewer;
import Elio.view.IntroMenuViewer;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class CharacterSelectionViewerTest {

    @Test
    public void drawTest(){
        Menu menu = new Menu("#555555", "#323141", 30, 40);

        CharacterSelectionViewer viewer = new CharacterSelectionViewer(menu);

        CharacterSelectionViewer spyViewer = spy(viewer);

        Assertions.assertEquals(menu, viewer.getModel());

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        when(gui.getGraphics()).thenReturn(graphics);

        spyViewer.draw(gui);

        verify(gui).fillBackground(menu.getBackgroundColor(), menu.getWidth(), menu.getHeight());

        String mainColor = menu.getTextColor(); //menu color
        String accentColor = "#FFD700"; // Gold accent for keys
        String subTextColor = "#AAAAAA"; // Grey for descriptions

        //title
        verify(gui).drawText(new Position(36, 3), "==============================", mainColor, true);
        verify(gui).drawText(new Position(38, 4), "IDENTIFY YOURSELF, SURVIVOR", mainColor, true);
        verify(gui).drawText(new Position(36, 5), "==============================", mainColor, true);

        // boxes
        // Warrior
        verify(gui).drawText(new Position(36, 9), "[W]", accentColor, true);
        verify(gui).drawText(new Position(40, 9), "-> WARRIOR", mainColor, true);
        verify(gui).drawText(new Position(43, 10), "High HP, Close Quarters", subTextColor, false);

        // Mage
        verify(gui).drawText(new Position(36, 12), "[M]", accentColor, true);
        verify(gui).drawText(new Position(40, 12), "-> MAGE", mainColor, true);
        verify(gui).drawText(new Position(43, 13), "Medium Range, Mana", subTextColor, false);

        // Gunman
        verify(gui).drawText(new Position(36, 15), "[G]", accentColor, true);
        verify(gui).drawText(new Position(40, 15), "-> GUNMAN", mainColor, true);
        verify(gui).drawText(new Position(43, 16), "Long Range, Fast Fire", subTextColor, false);

        // --- FOOTER ---
        verify(gui).drawText(new Position(33, 20), "Press the corresponding key to start", mainColor, false);
    }

}
