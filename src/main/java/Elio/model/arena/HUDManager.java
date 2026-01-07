package Elio.model.arena;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.hero.Hero;

public class HUDManager {
    //static HUD characteristics
    private int hpBarHitbox;
    private int xpBarHitbox;
    private int shieldBarHitbox = 0;

    private final Arena arena;

    public HUDManager(Arena arena){
        this.arena = arena;
    }

    public void updateBarHitboxes(){
        Hero hero = arena.getHero();

        // -- HP LOGIC --
        int hpBarSize = 26; //fixed bar size + spaces and brackets, etc.

        int temp = hero.getMaxHP();
        while (temp > 0){ //max hp digits
            temp /= 10;
            hpBarSize++;
        }

        temp = hero.getHP();
        while (temp > 0){ //hp digits
            temp /= 10;
            hpBarSize++;
        }

        this.hpBarHitbox = hpBarSize;

        // -- XP LOGIC --
        if (hero.getLevel() == hero.getLevelCap()) this.xpBarHitbox = 31;
        else if (hero.getLevel() > 9)
            this.xpBarHitbox = 30;
        else this.xpBarHitbox = 29;

        // -- SHIELD LOGIC --
        if(hero.getMaxShield() > 0){
            int shieldBarSize = 6; //fixed size for spaces and brackets, etc.
            shieldBarSize += Math.min(hero.getMaxShield() / 5, 20); //cap the nr of bars to 20

            temp = hero.getMaxShield();
            while (temp > 0){ //max shield digits
                temp /= 10;
                shieldBarSize++;
            }

            temp = hero.getShield();
            if (temp == 0) shieldBarSize++;
            while (temp > 0){ //shield digits
                temp /= 10;
                shieldBarSize++;
            }

            this.shieldBarHitbox = shieldBarSize;
        }
        // -- AMMO/MANA LOGIC --
        hero.updateResourceHitbox();
    }

    public boolean checkHitboxCollision(Position position){
        if(position.getY() == 0){
            return position.getX() < hpBarHitbox || position.getX() >= 100-xpBarHitbox || (position.getX() >= arena.getWidth() / 2 - 8 && position.getX() < arena.getWidth() / 2 + 8); //hp, xp and wave counter hitboxes
        }
        else if(position.getY() == 1){
            return position.getX() < shieldBarHitbox || (position.getX() >= arena.getWidth() / 2 - 2 && position.getX() < arena.getWidth() / 2 + 3); //shield and timer hitbox
        }
        else if(position.getY() == arena.getHeight()-1) { return position.getX() < arena.getHero().getResourceHitbox(); }
        return false;
    }

    //getters (for testing purposes)
    public int getHpBarHitbox() { return hpBarHitbox; }

    public int getXpBarHitbox() { return xpBarHitbox; }

    public int getShieldBarHitbox() { return shieldBarHitbox; }

}
