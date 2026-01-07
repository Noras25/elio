package Elio.model.attackStrategies;

import Elio.model.Arena;
import Elio.model.hero.Hero;

public class MagicAttackStrategy extends RangedStrategy {
    private final double COOLDOWN = 0.7;
    @Override
    public void attack(Hero hero, Arena arena) {

        double currentTime = arena.getTimePlayed() / 1000.0;
        double lastTime = hero.getLastAttackTime() / 1000.0;
        int magicProjectileSpeed = 10;
        String magicProjectileColor = "#8A2BE2"; //blue violet
        if(currentTime - lastTime > COOLDOWN && hero.getResource() > 0) {
            shoot(hero, arena, magicProjectileSpeed, magicProjectileColor,"●", hero.getDamage());
            hero.decreaseResource();
            hero.setLastAttackTime(arena.getTimePlayed());
        }
    }
}
