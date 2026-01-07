package controller;

import Elio.controller.Controller;
import Elio.controller.LevelUpController;
import Elio.gui.GUI;
import Elio.gui.LanternaGUI;
import Elio.model.Arena;
import Elio.model.Game;
import Elio.model.LevelUpChoice;
import Elio.model.Menu;
import Elio.model.hero.*;
import Elio.state.CharacterSelectionState;
import Elio.state.GameState;
import Elio.state.LevelUpState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

public class LevelUpControllerTest {
    private LevelUpController controller;
    private Arena arena;
    private Game game;
    private List<LevelUpChoice> classOptions;

    @BeforeEach
    public void setup(){
        LevelUpChoice choice1 = new LevelUpChoice(1, LevelUpChoice.Rarity.COMMON, LevelUpChoice.LevelUpType.DAMAGE);
        LevelUpChoice choice2 = new LevelUpChoice(2, LevelUpChoice.Rarity.UNCOMMON, LevelUpChoice.LevelUpType.HP);
        LevelUpChoice choice3 = new LevelUpChoice(3, LevelUpChoice.Rarity.RARE, LevelUpChoice.LevelUpType.SHIELD);
        List<LevelUpChoice> options = Arrays.asList(choice1, choice2, choice3);

        LevelUpChoice choice4 = new LevelUpChoice(1, LevelUpChoice.Rarity.LEGENDARY, LevelUpChoice.LevelUpType.XP);
        LevelUpChoice choice5 = new LevelUpChoice(2, LevelUpChoice.Rarity.LEGENDARY, LevelUpChoice.LevelUpType.CLASS_SPECIFIC_1);
        LevelUpChoice choice6 = new LevelUpChoice(3, LevelUpChoice.Rarity.LEGENDARY, LevelUpChoice.LevelUpType.CLASS_SPECIFIC_2);
        classOptions = Arrays.asList(choice4, choice5, choice6); //for later use

        arena = new Arena(100,100, HeroFactory.createHero(Hero.HeroType.GUNMAN,15,15));

        controller = new LevelUpController(Mockito.mock(Menu.class), arena ,options, 2);

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        game = Mockito.mock(Game.class);
        Mockito.when(game.getGui()).thenReturn(gui);
    }

    @Test
    public void exit() throws IOException {
        controller.processKey(game, new KeyStroke('q', false, false));

        verify(game.getGui()).close();
        Mockito.verify(game, Mockito.never()).setRunning(false);

        controller.processKey(game,new KeyStroke(KeyType.EOF));

        verify(game).setRunning(false);
    }

    @Test
    public void choice1() throws IOException {
        controller.processKey(game, new KeyStroke('1', false, false));

        Assertions.assertEquals(24, arena.getHero().getDamage());

        verify(game).setState(Mockito.any(LevelUpState.class)); //since there were two levels check that it goes to another level up state
    }

    @Test
    public void choice2() throws IOException {
        arena.getHero().setHP(76);
        controller.processKey(game, new KeyStroke('2', false, false));

        Assertions.assertEquals(100, arena.getHero().getHP());
        Assertions.assertEquals(104, arena.getHero().getMaxHP());
    }

    @Test
    public void choice3() throws IOException {
        arena.getHero().setMaxShield(10);
        controller.processKey(game, new KeyStroke('3', false, false));

        Assertions.assertEquals(60, arena.getHero().getMaxShield());
        Assertions.assertEquals(50, arena.getHero().getShield());
    }

    @Test
    public void otherPossibilities() throws  IOException {
        controller.setOptions(classOptions);
        controller.setLevels(1);

        controller.processKey(game, new KeyStroke('1', false, false));

        Assertions.assertEquals(2.0, arena.getHero().getXpGain());

        verify(game).setState(Mockito.any(GameState.class)); //since there was only one level this time check that it returns to a game state

        ((Gunman)arena.getHero()).setAmmo(10);
        controller.processKey(game, new KeyStroke('2', false, false));

        Assertions.assertEquals(40, ((Gunman)arena.getHero()).getMaxAmmo());
        Assertions.assertEquals(30, ((Gunman)arena.getHero()).getAmmo());

        controller.processKey(game, new KeyStroke('3', false, false));

        Assertions.assertEquals(1.1, ((Gunman)arena.getHero()).getReloadSpeed());
    }

    @Test
    public void WarriorSpecifics() throws IOException {
        Arena warriorArena = new Arena(100,100, HeroFactory.createHero(Hero.HeroType.WARRIOR, 15,15));

        LevelUpController warriorController = new LevelUpController(Mockito.mock(Menu.class), warriorArena, classOptions, 1);

        warriorController.processKey(game, new KeyStroke('2', false, false));

        Assertions.assertEquals(0.30, ((Warrior)warriorArena.getHero()).getLifeSteal());

        warriorController.processKey(game, new KeyStroke('3', false, false));

        Assertions.assertEquals(0.45, ((Warrior)warriorArena.getHero()).getHardness());

    }

    @Test
    public void MageSpecifics() throws IOException {
        Arena mageArena = new Arena(100,100, HeroFactory.createHero(Hero.HeroType.MAGE, 15,15));

        LevelUpController mageController = new LevelUpController(Mockito.mock(Menu.class), mageArena, classOptions, 1);

        ((Mage)mageArena.getHero()).setMana(3);
        mageController.processKey(game, new KeyStroke('2', false, false));

        Assertions.assertEquals(10, ((Mage)mageArena.getHero()).getMaxMana());
        Assertions.assertEquals(8, ((Mage)mageArena.getHero()).getMana());

        mageController.processKey(game, new KeyStroke('3', false, false));

        Assertions.assertEquals(1.15, ((Mage)mageArena.getHero()).getManaReloadingSpeed());

    }
}
