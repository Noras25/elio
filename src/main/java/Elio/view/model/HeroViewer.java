package Elio.view.model;

import Elio.gui.GUI;
import Elio.model.Position;
import Elio.model.hero.Hero;

public class HeroViewer extends ElementViewer {

    public HeroViewer(Hero hero){
        super(hero);
    }

    @Override
    public Hero getModel() {
        return (Hero)super.getModel();
    }

    @Override
    public void draw(GUI gui) {

        Position position = getModel().getPosition();
        String color = getModel().getColor();

        if (getModel().getDirection() == Hero.Direction.NONE){
            gui.drawText(position, "X", color,true);
        }

        else if (getModel().getDirection() == Hero.Direction.RIGHT){
            gui.drawText(position, "▶", color,true);
        }

        else if (getModel().getDirection() == Hero.Direction.LEFT) {
            gui.drawText(position, "◀", color,true);
        }

        else if (getModel().getDirection() == Hero.Direction.UP) {
            gui.drawText(position, "▲", color,true);
        }

        else if (getModel().getDirection() == Hero.Direction.DOWN) {
            gui.drawText(position, "▼", color,true);
        }
    }
}
