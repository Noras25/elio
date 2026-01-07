package Elio.model.movingStrategies;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.enemy.Enemy;

import java.util.Random;

public class RoamToCenterStrategy implements MovingStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public Position move(Enemy enemy, Arena arena) {
        int x = enemy.getPosition().getX();
        int y = enemy.getPosition().getY();
        Position center_pos = new Position(arena.getWidth()/2, arena.getHeight()/2);
        int rng = RANDOM.nextInt(2);

        return switch(rng) {
            case 0 -> x > center_pos.getX() ? new Position(x-1,y) : new Position(x+1, y);
            case 1 -> y > center_pos.getY() ? new Position(x, y-1) : new Position(x, y+1);
            default -> null;
        };
    }
}
