package Elio.model.enemy;

import Elio.model.Position;
import Elio.model.movingStrategies.MovingStrategy;
import Elio.model.movingStrategies.SmartStrategy;

import static Elio.model.enemy.Enemy.EnemyType.BOSS;
import static java.lang.Math.pow;

public class Boss extends Enemy {
    
    public Boss(Position position, int wave) {
        super(position, 500,  "#FF4000", BOSS, (int)(pow(1.2, wave) * 80), (int)(pow(1.2, wave) * 300), 1);
    }

    public Boss(int x, int y, int wave) { this(new Position(x,y), wave); }

    @Override
    public MovingStrategy createStrategy() {
        return new SmartStrategy();
    }
}
