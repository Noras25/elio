package Elio.model.attackStrategies;

import Elio.model.Arena;
import Elio.model.hero.Hero;

public interface AttackStrategy {
    void attack(Hero hero, Arena arena);
}
