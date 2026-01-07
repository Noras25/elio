package Elio.model.HUD;

import Elio.model.Arena;
import Elio.model.hero.Gunman;
import Elio.view.model.HUD.AmmoViewer;
import Elio.view.model.HUD.HUDElementViewer;

public class AmmoBar implements HUDElement {
    private int ammo;
    private int maxAmmo;
    private final int height;
    private boolean reloading;

    public AmmoBar(Arena arena){
        this.maxAmmo = ((Gunman)arena.getHero()).getMaxAmmo();
        this.ammo = ((Gunman)arena.getHero()).getAmmo();
        this.height = arena.getHeight();
        this.reloading = ((Gunman) arena.getHero()).isReloading();
    }

    public int getAmmo() {
        return ammo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public int getHeight() {
        return height;
    }

    public boolean isReloading() { return reloading; }

    @Override
    public void update(Arena arena) {
        this.maxAmmo = ((Gunman)arena.getHero()).getMaxAmmo();
        this.ammo = ((Gunman)arena.getHero()).getAmmo();
        this.reloading = ((Gunman) arena.getHero()).isReloading();
    }

    @Override
    public HUDElementViewer createViewer() {
        return new AmmoViewer(this);
    }
}
