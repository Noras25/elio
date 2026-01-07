package Elio.model.enemy;

import Elio.model.Position;

public class EnemyFactory {
    public static Enemy createEnemy(Enemy.EnemyType type, int x, int y, int wave){
        return switch (type) {
            case CRAWLER -> new Crawler(x, y, wave);
            case BRUISER -> new Bruiser(x, y, wave);
            case RUNNER -> new Runner(x, y, wave);
            case BOSS -> new Boss(x, y, wave);
        };
    }

    public static Enemy createEnemy(Enemy.EnemyType type, Position position, int wave){
        return createEnemy(type, position.getX(), position.getY(), wave);
    }
}
