package Elio.model.HUD;

import Elio.model.Arena;
import Elio.view.model.HUD.HUDElementViewer;
import Elio.view.model.HUD.XPBarViewer;

public class XPBar implements HUDElement {
    public int XP;
    public int XPToNextLevel;
    public int arenaWidth;
    public int level;
    public int levelCap;

    public XPBar(Arena arena){
        this.XP = arena.getHero().getXp();
        this.XPToNextLevel = arena.getHero().getXpToNextLevel();
        this.arenaWidth = arena.getWidth();
        this.level = arena.getHero().getLevel();
        this.levelCap = arena.getHero().getLevelCap();
    }

    public int getXP() {
        return XP;
    }

    public int getXPToNextLevel() {
        return XPToNextLevel;
    }

    public int getArenaWidth() {
        return arenaWidth;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelCap() { return levelCap; }

    @Override
    public void update(Arena arena) {
        this.XP = arena.getHero().getXp();
        this.XPToNextLevel = arena.getHero().getXpToNextLevel();
        this.level = arena.getHero().getLevel();
    }

    @Override
    public HUDElementViewer createViewer() {
        return new XPBarViewer(this);
    }

}
