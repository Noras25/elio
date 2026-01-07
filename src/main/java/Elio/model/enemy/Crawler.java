package Elio.model.enemy;

import static Elio.model.enemy.Enemy.EnemyType.CRAWLER;
import static java.lang.Math.pow;

import Elio.model.Position;
import Elio.model.movingStrategies.ChaseHeroStrategy;
import Elio.model.movingStrategies.MovingStrategy;

public class Crawler extends Enemy {

    public Crawler(Position position, int wave) {
        super(position, 120,  "#FF0000", CRAWLER, (int)(pow(1.2, wave) * 30), (int)(pow(1.2, wave) * 120), 1.1);
    }

    public Crawler(int x, int y, int wave) { this(new Position(x,y), wave); }

    @Override
    public MovingStrategy createStrategy() {
        return new ChaseHeroStrategy();
    }
}
