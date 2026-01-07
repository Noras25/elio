package Elio.model.movingStrategies;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.enemy.Enemy;

import java.util.Random;

public class RandomStrategy implements MovingStrategy {
    private static Random RANDOM = new Random();

    @Override
    public Position move(Enemy enemy, Arena arena) {
        int rng = RANDOM.nextInt(4);
        int x = enemy.getPosition().getX();
        int y = enemy.getPosition().getY();

        return switch (rng) {
            case 0 -> new Position(x, y + 1);
            case 1 -> new Position(x, y - 1);
            case 2 -> new Position(x - 1, y);
            case 3 -> new Position(x + 1, y);
            default -> null;
        };
    }

    public static void setRandom(Random random) {
        RANDOM = random;
    }
}
