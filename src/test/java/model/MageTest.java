package model;

import Elio.model.attackStrategies.MagicAttackStrategy;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import Elio.model.hero.Mage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MageTest {
    private final Mage hero = (Mage) HeroFactory.createHero(Hero.HeroType.MAGE, 15, 15);

    @Test
    public void mageConstruction() {
        Assertions.assertEquals("#CB6CE6", hero.getColor());
        Assertions.assertEquals(100, hero.getHP());
        Assertions.assertEquals(100, hero.getMaxHP());
        Assertions.assertEquals(Hero.HeroType.MAGE, hero.getType());
        Assertions.assertEquals(MagicAttackStrategy.class, hero.getAttackStrategy().getClass());
        Assertions.assertEquals(5, hero.getMana());
        Assertions.assertEquals(5, hero.getMaxMana());
        Assertions.assertEquals(2.3, hero.getManaReloadingSpeed());
        Assertions.assertEquals(0, hero.getLastReloaded());
    }

    @Test
    public void mana() {
        hero.setMaxMana(12);
        Assertions.assertEquals(12, hero.getMaxMana());

        hero.setMana(8);
        Assertions.assertEquals(8, hero.getMana());

        hero.setMana(25);
        Assertions.assertEquals(12, hero.getMana());

        hero.decreaseResource();
        Assertions.assertEquals(11, hero.getMana());

        hero.setMana(0);
        hero.decreaseResource();
        Assertions.assertEquals(0, hero.getMana());
    }

    @Test
    public void resourceHitbox(){
        Assertions.assertEquals(11, hero.getResourceHitbox());

        hero.setMaxMana(12);
        hero.updateResourceHitbox();
        Assertions.assertEquals(21, hero.getResourceHitbox());

        hero.setMaxMana(35);
        hero.updateResourceHitbox();
        Assertions.assertEquals(29, hero.getResourceHitbox());

        hero.setMana(30);
        hero.updateResourceHitbox();
        Assertions.assertEquals(30, hero.getResourceHitbox());

        hero.setMana(0);
        hero.updateResourceHitbox();
        Assertions.assertEquals(29, hero.getResourceHitbox());
    }

    @Test
    public void updateResource(){
        hero.setManaReloadingSpeed(1.5);
        Assertions.assertEquals(1.5, hero.getManaReloadingSpeed());

        hero.updateResource(1200);
        Assertions.assertEquals(0, hero.getLastReloaded()); //last reloaded shouldn't be updated if mana can't be reloaded (aka is the same as max mana)

        hero.decreaseResource();

        hero.updateResource(1200);
        Assertions.assertEquals(1.2, hero.getLastReloaded());

        hero.updateResource(300); //enough time for a reload to happen
        Assertions.assertEquals(hero.getMana(), hero.getMaxMana());
        Assertions.assertEquals(0, hero.getLastReloaded());
    }

}
