package Elio.model.movingStrategies;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.enemy.Enemy;

public class SmartStrategy implements MovingStrategy {
    private final RandomStrategy random;
    private final ChaseHeroStrategy chaseHero;
    private final RoamToCenterStrategy roamToCenter;

    //needed for testing reasons
    public SmartStrategy(RandomStrategy random, ChaseHeroStrategy chaseHero, RoamToCenterStrategy roamToCenter){
        this.random = random;
        this.chaseHero = chaseHero;
        this.roamToCenter = roamToCenter;
    }

    public SmartStrategy(){
        this(new RandomStrategy(), new ChaseHeroStrategy(), new RoamToCenterStrategy());
    }

    @Override
    public Position move(Enemy enemy, Arena arena) {
        int enemyX = enemy.getPosition().getX();
        int enemyY = enemy.getPosition().getY();
        int heroX = arena.getHero().getPosition().getX();
        int heroY = arena.getHero().getPosition().getY();
        int centerX = arena.getWidth()/2;
        int centerY = arena.getHeight()/2;
        if( Math.abs(enemyX - heroX) <= 10 && Math.abs(enemyY - heroY) <= 10){
            return chaseHero.move(enemy, arena);
        }
        if (Math.abs(enemyX - centerX) <= 10 && Math.abs(enemyY - centerY) <= 7)
            return random.move(enemy, arena);
        return roamToCenter.move(enemy, arena);
    }
}
