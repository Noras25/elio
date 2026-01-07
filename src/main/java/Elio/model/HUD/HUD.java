package Elio.model.HUD;

import Elio.model.Arena;
import Elio.model.hero.Hero;

import java.util.ArrayList;
import java.util.List;

public class HUD {
    private final List<HUDElement> elements;

    public HUD(Arena arena){
        List<HUDElement> elements = new ArrayList<>();
        elements.add(new HPBar(arena));
        elements.add(new XPBar(arena));
        elements.add(new Timer(arena));
        elements.add(new ShieldBar(arena));
        if(arena.getHero().getType() == Hero.HeroType.GUNMAN) elements.add(new AmmoBar(arena));
        else if(arena.getHero().getType() == Hero.HeroType.MAGE) elements.add(new ManaBar(arena));
        elements.add(new WaveCounter(arena));
        this.elements = elements;
    }

    public List<HUDElement> getElements() {
        return elements;
    }

    public void update(Arena arena){
        for (HUDElement e : elements ){
            e.update(arena);
        }
    }
}
