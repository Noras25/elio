package Elio.model.attackStrategies;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.hero.Hero;

public abstract class RangedStrategy implements AttackStrategy {
    private static int DEFAULT_PROJECTILE_RANGE = 15;

    public void shoot(Hero hero,Arena arena, int speed, String color, String symbol, int damage) {
        Position startPosition = hero.getPosition();
        arena.spawnProjectile(startPosition, hero.getDirection(), damage, speed, color, DEFAULT_PROJECTILE_RANGE, symbol);
    }
}
