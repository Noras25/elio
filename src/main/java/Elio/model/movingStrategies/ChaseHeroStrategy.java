package Elio.model.movingStrategies;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.enemy.Enemy;

import java.util.Random;

public class ChaseHeroStrategy implements MovingStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public Position move(Enemy enemy, Arena arena) {
        int x = enemy.getPosition().getX();
        int y = enemy.getPosition().getY();
        if(Math.abs(x - arena.getHero().getPosition().getX()) == 1 && y == arena.getHero().getPosition().getY() || Math.abs(y - arena.getHero().getPosition().getY()) == 1 && x == arena.getHero().getPosition().getX()) { return arena.getHero().getPosition(); }
        int rng = RANDOM.nextInt(2);

        return switch(rng) {
            case 0 -> x > arena.getHero().getPosition().getX() ? new Position(x-1,y) : new Position(x+1, y);
            case 1 -> y > arena.getHero().getPosition().getY() ? new Position(x, y-1) : new Position(x, y+1);
            default -> null;
        };
    }
}
