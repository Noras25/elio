package Elio.model.attackStrategies;

import Elio.model.Arena;
import Elio.model.hero.Gunman;
import Elio.model.hero.Hero;

public class BulletStrategy extends RangedStrategy {
    private final double COOLDOWN = 0.1;
    @Override
    public void attack(Hero hero, Arena arena) {

        double elapsedTime = (arena.getTimePlayed() - hero.getLastAttackTime()) / 1000.0;

        int bulletProjectileSpeed = 20;
        String bulletProjectileColor = "#FFFF00"; //yellow

        if(elapsedTime > COOLDOWN && hero.getResource() > 0 && !((Gunman)hero).isReloading()) {
            shoot(hero, arena, bulletProjectileSpeed, bulletProjectileColor, "•", hero.getDamage());
            hero.decreaseResource();
            hero.setLastAttackTime(arena.getTimePlayed());
        }
    }
}
