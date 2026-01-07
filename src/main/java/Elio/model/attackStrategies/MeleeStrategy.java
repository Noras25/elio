package Elio.model.attackStrategies;

import Elio.model.Position;
import Elio.model.hero.Hero;

import java.util.ArrayList;
import java.util.List;

public abstract class MeleeStrategy implements AttackStrategy{
    protected List<Position> getTargetsInFront(Hero hero) {
        List<Position> targets = new ArrayList<>();
        Hero.Direction curDirection = hero.getDirection();
        Position curPos = hero.getPosition();
        int x = curPos.getX();
        int y = curPos.getY();

        switch (curDirection) {
            case UP -> {
                targets.add(new Position(x, y - 1));     // Center Front
                targets.add(new Position(x - 1, y - 1)); // Front-Left
                targets.add(new Position(x + 1, y - 1)); // Front-Right
            }
            case DOWN -> {
                targets.add(new Position(x, y + 1));     // Center Front
                targets.add(new Position(x - 1, y + 1)); // Front-Right (relative to screen)
                targets.add(new Position(x + 1, y + 1)); // Front-Left (relative to screen)
            }
            case LEFT -> {
                targets.add(new Position(x - 1, y));     // Center Front
                targets.add(new Position(x - 1, y - 1)); // Top-Left
                targets.add(new Position(x - 1, y + 1)); // Bottom-Left
            }
            case RIGHT -> {
                targets.add(new Position(x + 1, y));     // Center Front
                targets.add(new Position(x + 1, y - 1)); // Top-Right
                targets.add(new Position(x + 1, y + 1)); // Bottom-Right
            }
            case NONE -> { }
        }

        return targets;
    }

}
