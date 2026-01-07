package Elio.model.hero;

import Elio.model.Position;

public class HeroFactory {

    public static Hero createHero(Hero.HeroType type, int x, int y){
        return switch (type) {
            case WARRIOR -> new Warrior(x, y);
            case MAGE -> new Mage(x, y);
            case GUNMAN -> new Gunman(x, y);
        };
    }

    public static Hero createHero(Hero.HeroType type, Position position){
        return createHero(type, position.getX(), position.getY());
    }
}
