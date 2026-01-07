package Elio.view.model;

import Elio.gui.GUI;
import Elio.model.Position;
import Elio.model.enemy.Enemy;

public class EnemyViewer extends ElementViewer {
    public EnemyViewer(Enemy enemy){
        super(enemy);
    }

    @Override
    public Enemy getModel() {
        return (Enemy)super.getModel();
    }

    @Override
    public void draw(GUI gui) {

        Position position = getModel().getPosition();
        String color = getModel().getColor();
        Enemy.EnemyType type = getModel().getType();

        switch(type) {
            case BRUISER:
                gui.drawText(position, "B", color,true); break;
            case RUNNER:
                gui.drawText(position, "R", color,true); break;
            case CRAWLER:
                gui.drawText(position, "C", color,true); break;
            case BOSS:
                gui.drawText(position, "Z", color,true); break;
        }
    }
}
