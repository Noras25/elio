package Elio.model.attackStrategies;

import Elio.model.Arena;
import Elio.model.Element;
import Elio.model.Position;
import Elio.model.hero.Hero;
import Elio.model.hero.Hero.Direction;

public class Projectile extends Element {
    private Direction direction;
    private int damage;
    private int maxRange;
    private double speed;
    private double lastMoved;
    private String color;
    private String symbol;
    public Position startPosition;

    public Projectile() {
        super(new Position(0,0));
    }

    public void init(Position position, Direction direction, int damage, double speed, String color, int maxRange, String symbol) {
        this.setPosition(position);
        this.startPosition = position;
        this.direction = direction;
        this.damage = damage;
        this.speed = speed;
        this.lastMoved = 10;
        this.color = color;
        this.maxRange = maxRange;
        this.symbol = symbol;
    }

    public void projectileMove(double deltaSeconds) {
        this.lastMoved += deltaSeconds;
        double secondsPerMove = 0.5 / this.speed;

        if(lastMoved >= secondsPerMove) {
            Position currentPosition = this.getPosition();
            Position nextPosition = switch (direction) {
                case NONE -> currentPosition;
                case UP -> currentPosition.getNeighbor(Direction.UP);
                case DOWN -> currentPosition.getNeighbor(Direction.DOWN);
                case LEFT -> currentPosition.getNeighbor(Direction.LEFT);
                case RIGHT -> currentPosition.getNeighbor(Direction.RIGHT);
            };

            this.setPosition(nextPosition);
            lastMoved = 0;
        }
    }

    public boolean rangeExceeded() {
        int distance = Math.abs(this.getPosition().getX() - startPosition.getX())
                + Math.abs(this.getPosition().getY() - startPosition.getY());
        return distance > maxRange;
    }

    //getters
    public int getDamage() { return damage; }

    public String getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }
}
