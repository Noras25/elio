package model;

import Elio.model.Position;
import Elio.model.attackStrategies.*;
import Elio.model.hero.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeroTest {
    private Hero hero;

    @BeforeEach
    public void setUp(){
        this.hero = HeroFactory.createHero(Hero.HeroType.GUNMAN, 15, 15);
    }

    @Test
    public void position() {
        Assertions.assertEquals(new Position(15, 15), hero.getPosition());

        hero.setPosition(new Position(10, 3));
        Assertions.assertEquals(new Position(10, 3), hero.getPosition());
    }

    @Test
    public void direction() {
        Assertions.assertEquals(Hero.Direction.NONE, hero.getDirection());

        hero.setDirection(Hero.Direction.UP);
        Assertions.assertEquals(Hero.Direction.UP, hero.getDirection());

        hero.setDirection(Hero.Direction.DOWN);
        Assertions.assertEquals(Hero.Direction.DOWN, hero.getDirection());

        hero.setDirection(Hero.Direction.RIGHT);
        Assertions.assertEquals(Hero.Direction.RIGHT, hero.getDirection());

        hero.setDirection(Hero.Direction.LEFT);
        Assertions.assertEquals(Hero.Direction.LEFT, hero.getDirection());
    }

    @Test
    public void lastAttackTime() {
        Assertions.assertEquals(0, hero.getLastAttackTime());

        hero.setLastAttackTime(2200);
        Assertions.assertEquals(2200, hero.getLastAttackTime());
    }

    @Test
    public void level() {
        Assertions.assertEquals(50, hero.getLevelCap());
        Assertions.assertEquals(1, hero.getLevel());

        Assertions.assertEquals(0, hero.isLevelUp());

        int temp = hero.getHP();
        int maxTemp = hero.getMaxHP();

        hero.setXp(300);
        Assertions.assertEquals(2, hero.isLevelUp());

        Assertions.assertEquals(3, hero.getLevel());
        Assertions.assertEquals(169, hero.getXpToNextLevel());
        Assertions.assertEquals(temp + 40, hero.getHP());
        Assertions.assertEquals(maxTemp + 40, hero.getMaxHP());

        hero.setXp(hero.getXpToNextLevel());
        Assertions.assertEquals(1, hero.isLevelUp());
        Assertions.assertEquals(4, hero.getLevel());

        hero.setLevel(49);
        hero.addXp(hero.getXpToNextLevel() * 1000);

        Assertions.assertEquals(1, hero.isLevelUp());
        Assertions.assertEquals(50, hero.getLevel());

        hero.addXp(hero.getXpToNextLevel());

        Assertions.assertEquals(0, hero.isLevelUp());
        Assertions.assertEquals(50, hero.getLevel());
    }

    @Test
    public void xp() {
        Assertions.assertEquals(0, hero.getXp());
        Assertions.assertEquals(100, hero.getXpToNextLevel());

        Assertions.assertEquals(1.0, hero.getXpGain());

        hero.addXp(30);
        Assertions.assertEquals(30, hero.getXp());

        hero.setXpGain(1.2);
        Assertions.assertEquals(1.2, hero.getXpGain());

        hero.addXp(50);
        Assertions.assertEquals(90, hero.getXp());
    }

    @Test
    public void shield(){
        Assertions.assertEquals(0, hero.getShield());
        Assertions.assertEquals(0, hero.getMaxShield());
        Assertions.assertEquals(0, hero.getLastTimeShieldRegenerated());

        hero.setLastTimeShieldRegenerated(2.2);
        Assertions.assertEquals(2.2, hero.getLastTimeShieldRegenerated());

        hero.setMaxShield(50);
        Assertions.assertEquals(50, hero.getMaxShield());

        hero.regenerateShield();
        Assertions.assertEquals(10, hero.getShield());
        Assertions.assertEquals(0, hero.getLastTimeShieldRegenerated());

        hero.setShield(20);
        Assertions.assertEquals(20, hero.getShield());

        hero.addShield(50);
        Assertions.assertEquals(hero.getMaxShield(), hero.getShield());

        hero.setShield(100);
        Assertions.assertEquals(hero.getMaxShield(), hero.getShield());

        hero.loseShield(20);
        Assertions.assertEquals(30, hero.getShield());
    }

    @Test
    public void hp(){
        Assertions.assertEquals(80, hero.getHP());
        Assertions.assertEquals(80, hero.getMaxHP());

        hero.loseHP(120);
        Assertions.assertEquals(-40, hero.getHP());

        Assertions.assertTrue(hero.isDead());

        hero.gainHP(40);
        Assertions.assertTrue(hero.isDead());

        hero.gainHP(60);
        Assertions.assertEquals(60, hero.getHP());
        Assertions.assertFalse(hero.isDead());

        hero.gainHP(40);
        Assertions.assertEquals(80, hero.getHP());

        hero.setMaxHP(100);
        Assertions.assertEquals(100, hero.getMaxHP());

        hero.setHP(50);
        Assertions.assertEquals(50, hero.getHP());

        hero.setHP(200);
        Assertions.assertEquals(100, hero.getHP());

        hero = HeroFactory.createHero(Hero.HeroType.WARRIOR, new Position(15, 15));

        hero.loseHP(100);

        //warrior base hp is 120, but because of hardness, remaining hp should be 30 and not 20
        Assertions.assertEquals(30, hero.getHP());

    }

    @Test
    public void damage(){
        Assertions.assertEquals(20, hero.getDamage());

        hero.setDamage(100);
        Assertions.assertEquals(100, hero.getDamage());
    }

    @Test
    public void takeDamage() {
        hero.setMaxShield(20);
        hero.setShield(20);

        Assertions.assertEquals(0, hero.getLastTimeTakenDamage());

        hero.setLastTimeTakenDamage(1.3);
        Assertions.assertEquals(1.3, hero.getLastTimeTakenDamage());

        hero.takeDamage(30);

        Assertions.assertEquals(70, hero.getHP());

        Assertions.assertEquals(0, hero.getShield());

        Assertions.assertEquals(0, hero.getLastTimeTakenDamage());
    }

    @Test
    public void resource(){
        Assertions.assertEquals(20, hero.getResource());

        hero = HeroFactory.createHero(Hero.HeroType.MAGE, 15, 15);
        Assertions.assertEquals(5, hero.getResource());
    }
}
