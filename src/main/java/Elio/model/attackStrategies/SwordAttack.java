package Elio.model.attackStrategies;

import Elio.model.Arena;
import Elio.model.Element;
import Elio.model.Position;
import Elio.model.hero.Hero;

import java.util.List;

public class SwordAttack extends Element {
    private final String symbol;
    private final String color;
    private double duration; // How long (in ms) this effect stays on screen

    public SwordAttack(Position position, String symbol, String color, double duration) {
        super(position);
        this.symbol = symbol;
        this.color = color;
        this.duration = duration;
    }

    public void decreaseDuration(double time) {
        this.duration -= time;
    }

    public boolean isActive() {
        return duration > 0;
    }

    public String getSymbol() { return symbol; }
    public String getColor() { return color; }

}
