package Elio.model.HUD;

import Elio.model.Arena;
import Elio.model.hero.Gunman;
import Elio.model.hero.Mage;
import Elio.view.model.HUD.AmmoViewer;
import Elio.view.model.HUD.HUDElementViewer;
import Elio.view.model.HUD.ManaViewer;

public class ManaBar implements HUDElement {
    private int mana;
    private int maxMana;
    private final int height;

    public ManaBar(Arena arena){
        this.maxMana = ((Mage)arena.getHero()).getMaxMana();
        this.mana = ((Mage)arena.getHero()).getMana();
        this.height = arena.getHeight();
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void update(Arena arena) {
        this.maxMana = ((Mage)arena.getHero()).getMaxMana();
        this.mana = ((Mage)arena.getHero()).getMana();
    }

    @Override
    public HUDElementViewer createViewer() {
        return new ManaViewer(this);
    }
}
