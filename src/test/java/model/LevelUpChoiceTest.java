package model;

import Elio.model.LevelUpChoice;
import Elio.model.LevelUpChoice.*;
import Elio.model.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelUpChoiceTest {
    @Test
    public void constructor(){
        LevelUpChoice choice = new LevelUpChoice(2);

        assertEquals(2, choice.getNumber());

        Assertions.assertTrue(EnumSet.allOf(LevelUpChoice.Rarity.class).contains(choice.getRarity()));
        Assertions.assertTrue(EnumSet.allOf(LevelUpChoice.LevelUpType.class).contains(choice.getType()));
    }
    @Test
    public void testCommonType() {
        Random mockRandom = Mockito.mock(Random.class);
        LevelUpChoice levelUpChoice = new LevelUpChoice(1);
        levelUpChoice.setRandom(mockRandom);

        Mockito.when(mockRandom.nextInt(100)).thenReturn(10);

        assertEquals(Rarity.COMMON, levelUpChoice.randomRarity());
    }

    @Test
    public void testUncommonType() {
        Random mockRandom = Mockito.mock(Random.class);
        LevelUpChoice levelUpChoice = new LevelUpChoice(1);
        levelUpChoice.setRandom(mockRandom);

        Mockito.when(mockRandom.nextInt(100)).thenReturn(80);

        assertEquals(Rarity.UNCOMMON, levelUpChoice.randomRarity());
    }

    @Test
    public void testRareType() {
        Random mockRandom = Mockito.mock(Random.class);
        LevelUpChoice levelUpChoice = new LevelUpChoice(1);
        levelUpChoice.setRandom(mockRandom);

        Mockito.when(mockRandom.nextInt(100)).thenReturn(90);

        assertEquals(Rarity.RARE, levelUpChoice.randomRarity());
    }

    @Test
    public void testLegendaryType() {
        Random mockRandom = Mockito.mock(Random.class);
        LevelUpChoice levelUpChoice = new LevelUpChoice(1);
        levelUpChoice.setRandom(mockRandom);

        Mockito.when(mockRandom.nextInt(100)).thenReturn(99);

        assertEquals(Rarity.LEGENDARY, levelUpChoice.randomRarity());
    }

    @Test
    public void equals(){
        LevelUpChoice choice = new LevelUpChoice(1, LevelUpChoice.Rarity.COMMON, LevelUpChoice.LevelUpType.HP);
        LevelUpChoice choice2 = new LevelUpChoice(3, LevelUpChoice.Rarity.COMMON, LevelUpChoice.LevelUpType.HP);
        LevelUpChoice choice3 = new LevelUpChoice(1, LevelUpChoice.Rarity.COMMON, LevelUpChoice.LevelUpType.DAMAGE);
        LevelUpChoice choice4 = new LevelUpChoice(2, LevelUpChoice.Rarity.LEGENDARY, LevelUpChoice.LevelUpType.HP);
        LevelUpChoice choice5 = new LevelUpChoice(1, LevelUpChoice.Rarity.RARE, LevelUpChoice.LevelUpType.XP);

        Assertions.assertFalse(choice.equals(null));
        Assertions.assertFalse(choice.equals(new Position(9,9)));
        Assertions.assertTrue(choice.equals(choice));
        Assertions.assertTrue(choice.equals(choice2));
        Assertions.assertFalse(choice.equals(choice3));
        Assertions.assertFalse(choice.equals(choice4));
        Assertions.assertFalse(choice.equals(choice5));
    }

    @AfterEach
    public void tearDown() {
        LevelUpChoice choice = new LevelUpChoice(0); // Create dummy to access setter or make setter static
        choice.setRandom(new Random());
    }
}
