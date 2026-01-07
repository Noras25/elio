package Elio.model.attackStrategies;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.hero.Hero;
import Elio.model.hero.Warrior;

import java.util.List;

public class SwordAttackStrategy extends MeleeStrategy {
    private final double COOLDOWN = 0.5;
    @Override
    public void attack(Hero hero, Arena arena) {
        List<Position> targets = getTargetsInFront(hero);
        double currentTime = arena.getTimePlayed() / 1000.0;
        double lastTime = hero.getLastAttackTime() / 1000.0;

        // Visual settings
        String color = "#FFFFFF";
        String symbol = switch (hero.getDirection()) {
            case UP, DOWN -> "_";     // Horizontal slash
            case LEFT, RIGHT -> "|";  // Vertical slash
            default -> "X";
        };

        if(currentTime - lastTime > COOLDOWN) {

            for (Position targetPos : targets) {
                arena.spawnSword(targetPos, symbol, color, 0.150); // Sword effect lasts 150 ms
                arena.damageEnemyAt(targetPos, hero.getDamage()); // Deal damage to enemy at target position
            }
            hero.setLastAttackTime(arena.getTimePlayed());
        }
}
}
