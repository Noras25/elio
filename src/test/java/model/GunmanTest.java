package model;

import Elio.model.attackStrategies.BulletStrategy;
import Elio.model.hero.Gunman;
import Elio.model.hero.Hero;
import Elio.model.hero.HeroFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GunmanTest {
    private final Gunman hero = (Gunman) HeroFactory.createHero(Hero.HeroType.GUNMAN, 15, 15);

    @Test
    public void gunmanConstruction() {
        Assertions.assertEquals("#FFBD59", hero.getColor());
        Assertions.assertEquals(80, hero.getHP());
        Assertions.assertEquals(80, hero.getMaxHP());
        Assertions.assertEquals(Hero.HeroType.GUNMAN, hero.getType());
        Assertions.assertEquals(BulletStrategy.class, hero.getAttackStrategy().getClass());
        Assertions.assertEquals(20, hero.getAmmo());
        Assertions.assertEquals(20, hero.getMaxAmmo());
        Assertions.assertFalse(hero.isReloading());
        Assertions.assertEquals(0, hero.getReloadTimer());
        Assertions.assertEquals(2.2, hero.getReloadSpeed());
    }

    @Test
    public void ammo() {
        hero.setMaxAmmo(40);
        Assertions.assertEquals(40, hero.getMaxAmmo());

        hero.setAmmo(30);
        Assertions.assertEquals(30, hero.getAmmo());

        hero.setAmmo(50);
        Assertions.assertEquals(40, hero.getAmmo());

        hero.decreaseResource();
        Assertions.assertEquals(39, hero.getAmmo());

        hero.setAmmo(0);
        hero.decreaseResource();
        Assertions.assertEquals(0, hero.getAmmo());
    }

    @Test
    public void reload() {
        hero.setReloadSpeed(1.3);
        Assertions.assertEquals(1.3, hero.getReloadSpeed());

        hero.setReloadTimer(1.1);
        Assertions.assertEquals(1.1, hero.getReloadTimer());

        hero.flipReloading();
        Assertions.assertTrue(hero.isReloading());

        hero.reload();
        Assertions.assertEquals(1.1, hero.getReloadTimer()); //reload timer shouldn't reset to 0 since reload is already true

        hero.flipReloading(); //make reloading false
        hero.reload();
        Assertions.assertFalse(hero.isReloading());
        Assertions.assertEquals(1.1, hero.getReloadTimer()); //same here but because ammo is the same as max ammo, so hero shouldn't be allowed to reload

        hero.setAmmo(15); //make ammo less than max ammo so reload is possible
        hero.flipReloading(); //make reloading true
        hero.reload();
        Assertions.assertEquals(1.1, hero.getReloadTimer()); //still shouldn't update again cause reloading is already true

        hero.flipReloading(); //make reloading false
        hero.reload();
        Assertions.assertTrue(hero.isReloading());
        Assertions.assertEquals(0, hero.getReloadTimer()); //NOW it should reload and update timer (cause finally both ammo is less than max ammo and wasn't reloading already)
    }

    @Test
    public void updateResource(){
        hero.setAmmo(15);

        hero.updateResource(1200);
        Assertions.assertEquals(0, hero.getReloadTimer()); //reload time shouldn't update if hero isn't reloading

        hero.flipReloading();
        hero.updateResource(1200);
        Assertions.assertEquals(1.2, hero.getReloadTimer()); //make sure timer updated this time
        Assertions.assertTrue(hero.isReloading());
        Assertions.assertEquals(15, hero.getAmmo()); //make sure ammo still the same

        hero.updateResource(1000); //enough time to reload (base reload speed is 2.2)
        Assertions.assertEquals(0, hero.getReloadTimer());
        Assertions.assertFalse(hero.isReloading());
        Assertions.assertEquals(20, hero.getAmmo());
    }

    @Test
    public void resourceHitbox(){
        Assertions.assertEquals(16, hero.getResourceHitbox());

        hero.setMaxAmmo(100);
        hero.updateResourceHitbox();
        Assertions.assertEquals(41, hero.getResourceHitbox());

        hero.setAmmo(9);
        hero.updateResourceHitbox();
        Assertions.assertEquals(40, hero.getResourceHitbox());

        hero.setMaxAmmo(30);
        hero.updateResourceHitbox();
        Assertions.assertEquals(19, hero.getResourceHitbox());

        hero.flipReloading();
        hero.updateResourceHitbox();
        Assertions.assertEquals(21, hero.getResourceHitbox());

        hero.flipReloading();
        hero.setAmmo(0);
        hero.updateResourceHitbox();
        Assertions.assertEquals(30, hero.getResourceHitbox());
    }
}
