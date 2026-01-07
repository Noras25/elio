package Elio.model.HUD;

import Elio.model.Arena;
import Elio.view.model.HUD.HPBarViewer;
import Elio.view.model.HUD.HUDElementViewer;

public class HPBar implements HUDElement {
    private int maxHP;
    private int HP;

    public HPBar(Arena arena){
        this.maxHP = arena.getHero().getMaxHP();
        this.HP = arena.getHero().getHP();
    }

    public int getHP() {
        return HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public void update(Arena arena) {
        this.maxHP = arena.getHero().getMaxHP();
        this.HP = arena.getHero().getHP();
    }

    @Override
    public HUDElementViewer createViewer() {
        return new HPBarViewer(this);
    }
}
