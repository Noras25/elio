package Elio.model.movingStrategies;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.enemy.Enemy;

public interface MovingStrategy {
    Position move(Enemy enemy, Arena arena);
}
