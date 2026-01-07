package view;

import Elio.gui.LanternaGUI;
import Elio.model.hero.Hero;
import Elio.model.hero.Mage;
import Elio.view.model.HeroViewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class HeroViewerTest {

    @Test
    public void drawTest(){
        Hero hero = new Mage(25,25);
        HeroViewer viewer = new HeroViewer(hero);

        Assertions.assertEquals(hero, viewer.getModel());

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        when(gui.getGraphics()).thenReturn(graphics);

        viewer.draw(gui);

        verify(gui).drawText(hero.getPosition(), "X", hero.getColor(), true);

        hero.setDirection(Hero.Direction.UP);

        viewer.draw(gui);

        verify(gui,times(1)).drawText(hero.getPosition(), "▲", hero.getColor(), true);

        hero.setDirection(Hero.Direction.DOWN);

        viewer.draw(gui);

        verify(gui).drawText(hero.getPosition(), "▼", hero.getColor(), true);

        hero.setDirection(Hero.Direction.RIGHT);

        viewer.draw(gui);

        verify(gui).drawText(hero.getPosition(), "▶", hero.getColor(), true);

        hero.setDirection(Hero.Direction.LEFT);

        viewer.draw(gui);

        verify(gui).drawText(hero.getPosition(), "◀", hero.getColor(), true);
    }
}
