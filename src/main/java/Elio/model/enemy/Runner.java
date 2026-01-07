package Elio.model.enemy;

import Elio.model.Position;
import Elio.model.movingStrategies.ChaseHeroStrategy;
import Elio.model.movingStrategies.MovingStrategy;

import static Elio.model.enemy.Enemy.EnemyType.RUNNER;
import static java.lang.Math.pow;

public class Runner extends Enemy {
    public Runner(Position position, int wave) {
        super(position, 60,  "#FF0000", RUNNER, (int)(pow(1.2, wave) * 10), (int)(pow(1.2, wave) * 80), 1.8);
    }

    public Runner(int x, int y, int wave) { this(new Position(x,y), wave); }

    @Override
    public MovingStrategy createStrategy() {
        return new ChaseHeroStrategy();
    }
}
