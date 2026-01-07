package view;

import Elio.gui.GUI;
import Elio.model.Arena;
import Elio.model.LevelUpChoice;
import Elio.model.Menu;
import Elio.model.Position;
import Elio.model.hero.Hero;
import Elio.view.LevelUpViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static Elio.model.LevelUpChoice.Rarity.COMMON;

public class LevelUpViewerTest {
    //for simplicity, only one path is tested here
    @Test
    public void testGunmanCommonAmmo() {
        GUI mockGui = Mockito.mock(GUI.class);
        Menu mockMenu = Mockito.mock(Menu.class);
        Arena mockArena = Mockito.mock(Arena.class);
        Hero mockHero = Mockito.mock(Hero.class);

        //mocking menu colors
        Mockito.when(mockMenu.getTextColor()).thenReturn("#FFFFFF");
        Mockito.when(mockMenu.getBackgroundColor()).thenReturn("#000000");

        //putting hero as gunman
        Mockito.when(mockArena.getHero()).thenReturn(mockHero);
        Mockito.when(mockHero.getType()).thenReturn(Hero.HeroType.GUNMAN);

        //creating option: common ammo increase
        LevelUpChoice option1 = new LevelUpChoice(1, COMMON, LevelUpChoice.LevelUpType.CLASS_SPECIFIC_1);
        List<LevelUpChoice> options = List.of(option1);

        LevelUpViewer viewer = new LevelUpViewer(mockMenu, mockArena, options);
        viewer.draw(mockGui);

        //verifying title texts
        Mockito.verify(mockGui).drawText(Mockito.any(Position.class), Mockito.eq("Congrats!"), Mockito.any(), Mockito.eq(true));
        Mockito.verify(mockGui).drawText(Mockito.any(Position.class), Mockito.eq("You LEVELED Up!"), Mockito.any(), Mockito.eq(false));
        Mockito.verify(mockGui).drawText(Mockito.any(Position.class), Mockito.eq("Choose Wisely..."), Mockito.any(), Mockito.eq(false));

        Mockito.verify(mockGui).drawText(
                Mockito.any(Position.class),
                Mockito.eq("COMMON -> Press \"1\""),
                Mockito.eq("#808080"),
                Mockito.eq(true)
        );
        //verifying description text
        Mockito.verify(mockGui).drawText(
                Mockito.any(Position.class),
                Mockito.eq("Increase Ammo by 20%"),
                Mockito.eq("#FFFFFF"),
                Mockito.eq(false)
        );



    }

    @Test
    public void testMageLegendaryHealth() {
        GUI mockGui = Mockito.mock(GUI.class);
        Menu mockMenu = Mockito.mock(Menu.class);
        Arena mockArena = Mockito.mock(Arena.class);
        Hero mockHero = Mockito.mock(Hero.class);

        Mockito.when(mockMenu.getTextColor()).thenReturn("#FFFFFF");
        Mockito.when(mockMenu.getBackgroundColor()).thenReturn("#000000");

        Mockito.when(mockArena.getHero()).thenReturn(mockHero);
        Mockito.when(mockHero.getType()).thenReturn(Hero.HeroType.MAGE);

        LevelUpChoice option1 = new LevelUpChoice(1, LevelUpChoice.Rarity.LEGENDARY, LevelUpChoice.LevelUpType.HP);
        List<LevelUpChoice> options = List.of(option1);

        LevelUpViewer viewer = new LevelUpViewer(mockMenu, mockArena, options);
        viewer.draw(mockGui);

        Mockito.verify(mockGui).drawText(Mockito.any(Position.class), Mockito.eq("Congrats!"), Mockito.any(), Mockito.eq(true));
        Mockito.verify(mockGui).drawText(Mockito.any(Position.class), Mockito.eq("You LEVELED Up!"), Mockito.any(), Mockito.eq(false));

        Mockito.verify(mockGui).drawText(
                Mockito.any(Position.class),
                Mockito.eq("LEGENDARY -> Press \"1\""),
                Mockito.eq("#EFBF04"),
                Mockito.eq(true)
        );

        Mockito.verify(mockGui).drawText(
                Mockito.any(Position.class),
                Mockito.eq("Increase HP by 100%"),
                Mockito.eq("#FFFFFF"),
                Mockito.eq(false)
        );
    }
}
