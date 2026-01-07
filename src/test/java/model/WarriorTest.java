package model;

import Elio.model.attackStrategies.SwordAttackStrategy;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.hero.Warrior;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WarriorTest {
    private final Warrior hero = (Warrior) HeroFactory.createHero(Hero.HeroType.WARRIOR, 15, 15);

    @Test
    public void warriorConstruction() {
        Assertions.assertEquals("#FFD700", hero.getColor());
        Assertions.assertEquals(120, hero.getHP());
        Assertions.assertEquals(120, hero.getMaxHP());
        Assertions.assertEquals(Hero.HeroType.WARRIOR, hero.getType());
        Assertions.assertEquals(SwordAttackStrategy.class, hero.getAttackStrategy().getClass());
        Assertions.assertEquals(0.15, hero.getLifeSteal());
        Assertions.assertEquals(0.90, hero.getHardness());
    }

    @Test
    public void lifeSteal(){
        hero.setLifeSteal(0.8);
        Assertions.assertEquals(0.8, hero.getLifeSteal());

        hero.setHP(20);
        hero.lifeSteal(100);

        Assertions.assertEquals(100, hero.getHP());
    }

    @Test
    public void hardness(){
        hero.setHardness(0.75);
        Assertions.assertEquals(0.75, hero.getHardness());
    }

    @Test
    public void stuff(){
        hero.updateResource(0);
        hero.updateResourceHitbox();
        Assertions.assertEquals(0, hero.getResource());
        Assertions.assertEquals(0, hero.getResourceHitbox());
        hero.decreaseResource();
    }
}
