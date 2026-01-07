package Elio.view.model;

import Elio.gui.GUI;
import Elio.model.Element;
import Elio.model.Position;
import Elio.model.attackStrategies.Projectile;
import Elio.model.attackStrategies.SwordAttack;

import java.io.IOException;

public class SwordViewer extends ElementViewer{
    public SwordViewer(Element model) {
        super(model);
    }

    public SwordAttack getModel() {
        return (SwordAttack) super.getModel();
    }

    @Override
    public void draw(GUI gui) throws IOException {
        String symbol = getModel().getSymbol();
        String color = getModel().getColor();
        Position position = getModel().getPosition();

        gui.drawText(position, symbol, color, true);
    }


}
