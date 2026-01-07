package Elio.view.model;

import Elio.gui.GUI;
import Elio.model.Element;
import Elio.model.Position;
import Elio.model.attackStrategies.Projectile;
import Elio.model.enemy.Enemy;

import java.io.IOException;

public class ProjectileViewer extends ElementViewer{
    public ProjectileViewer(Element model) {
        super(model);
    }
    @Override
    public Projectile getModel() {
        return (Projectile) super.getModel();
    }
    @Override
    public void draw(GUI gui) throws IOException {
        String symbol = getModel().getSymbol();
        Position position = getModel().getPosition();
        gui.drawText(position, symbol, getModel().getColor(), true);
    }
}
