package Elio.model.enemy;

import Elio.model.Position;
import Elio.model.movingStrategies.MovingStrategy;
import Elio.model.movingStrategies.SmartStrategy;

import static Elio.model.enemy.Enemy.EnemyType.BRUISER;
import static java.lang.Math.pow;

public class Bruiser extends Enemy {

    public Bruiser(Position position, int wave) {
        super(position, 90,  "#FF0000", BRUISER, (int)(pow(1.2, wave) * 20), (int)(pow(1.2, wave) * 100), 1.25);
    }

    public Bruiser(int x, int y, int wave){ this(new Position(x,y), wave); }

    @Override
    public MovingStrategy createStrategy() {
        return new SmartStrategy();
    }
}
