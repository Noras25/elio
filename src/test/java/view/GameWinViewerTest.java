package view;

import Elio.gui.GUI;
import Elio.model.Menu;
import Elio.view.GameWinViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameWinViewerTest {

    @Test
    public void testDraw(){
        Menu mockMenu = Mockito.mock(Menu.class);
        GUI mockGui = Mockito.mock(GUI.class);

        Mockito.when(mockMenu.getBackgroundColor()).thenReturn("#000000");
        Mockito.when(mockMenu.getTextColor()).thenReturn("#FFFFFF");
        Mockito.when(mockMenu.getWidth()).thenReturn(100);
        Mockito.when(mockMenu.getHeight()).thenReturn(50);

        GameWinViewer viewer = new GameWinViewer(mockMenu);
        viewer.draw(mockGui);

        Mockito.verify(mockGui).fillBackground("#000000", 100, 50);
        Mockito.verify(mockGui).drawText(new Elio.model.Position(42,12), "You ", "#FFFFFF",false);
        Mockito.verify(mockGui).drawText(new Elio.model.Position(46,12), "WON!! ", "#EFBF04",true);
        Mockito.verify(mockGui).drawText(new Elio.model.Position(52, 12), "Yipee!", "#FFFFFF", false);  
    }
}
