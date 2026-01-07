package Elio.model.HUD;

import Elio.model.Arena;
import Elio.view.model.HUD.HPBarViewer;
import Elio.view.model.HUD.HUDElementViewer;
import Elio.view.model.HUD.ShieldBarViewer;

public class ShieldBar implements HUDElement {
    private int maxShield;
    private int shield;

    public ShieldBar(Arena arena){
        this.maxShield = arena.getHero().getMaxShield();
        this.shield = arena.getHero().getShield();
    }

    public int getShield() {
        return shield;
    }

    public int getMaxShield() {
        return maxShield;
    }

    @Override
    public void update(Arena arena) {
        this.maxShield = arena.getHero().getMaxShield();
        this.shield = arena.getHero().getShield();
    }

    @Override
    public HUDElementViewer createViewer() {
        return new ShieldBarViewer(this);
    }
}
