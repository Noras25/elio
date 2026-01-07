package Elio.view.model;

import Elio.gui.GUI;
import Elio.model.Arena;
import Elio.model.attackStrategies.Projectile;
import Elio.model.attackStrategies.SwordAttack;
import Elio.model.enemy.Enemy;
import Elio.view.Viewer;

import java.io.IOException;

public class ArenaViewer extends Viewer<Arena> {
    public ArenaViewer(Arena model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.fillBackground(getModel().getBackgroundColor(), getModel().getWidth(), getModel().getHeight());

        ElementViewer heroViewer = new HeroViewer(getModel().getHero());
        heroViewer.draw(gui);

        for (Enemy enemy : getModel().getEnemies()){
            ElementViewer enemyViewer = new EnemyViewer(enemy);
            enemyViewer.draw(gui);
        }

        for(Projectile projectile : getModel().getActiveProjectiles()) {
            ElementViewer projectileViewer = new ProjectileViewer(projectile);
            projectileViewer.draw(gui);
        }

        for (SwordAttack effect : getModel().getEffects()) {
            new SwordViewer(effect).draw(gui);
        }
    }

}
