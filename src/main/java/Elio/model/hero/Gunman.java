package Elio.model.hero;

import Elio.model.Position;
import Elio.model.attackStrategies.BulletStrategy;

public class Gunman extends Hero {
    private int ammo = 20;
    private int maxAmmo = 20;
    private int ammoHitbox = 16;
    private boolean reloading = false;
    private double reloadTimer = 0;
    private double reloadSpeed = 2.2;


     public Gunman(Position position){
         super(position, 80, "#FFBD59", HeroType.GUNMAN, 20, new BulletStrategy());
     }

    public Gunman(int x, int y){ this(new Position(x,y)); }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo){
        this.ammo = Math.min(ammo, maxAmmo);
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public double getReloadSpeed() {
        return reloadSpeed;
    }

    public void setReloadSpeed(double reloadSpeed){
         this.reloadSpeed = reloadSpeed;
    }

    public void reload(){
        if(ammo < maxAmmo && !reloading) {
         reloadTimer = 0;
         flipReloading();
        }
    }

    public boolean isReloading() {
        return reloading;
    }

    public void flipReloading() {
        this.reloading = !reloading;
    }

    public double getReloadTimer() {
        return reloadTimer;
    }

    public void setReloadTimer(double reloadTimer) {
        this.reloadTimer = reloadTimer;
    }

    @Override
    public int getResourceHitbox() {
        return ammoHitbox;
    }

    @Override
    public void updateResourceHitbox(){
        int barSize = 6; //spaces, icon and brackets
        barSize += Math.min(30, getMaxAmmo() / 3); //capping the amount of bars in the... well, bar to 30
        int temp = getMaxAmmo();
        while (temp > 0){ //max ammo digits
            temp /= 10;
            barSize++;
        }
        temp = getAmmo();
        if(temp == 0) barSize++;
        while (temp > 0){ //ammo digits
            temp /= 10;
            barSize++;
        }
        if(isReloading()) barSize += 2; //reloading icon
        else if(ammo == 0) barSize += 11; //tool tip text telling player to reload
        this.ammoHitbox = barSize;
    }

    @Override
    public void decreaseResource() {
        if (ammo > 0)
            ammo--;
    }

    @Override
    public int getResource() {
        return ammo;
    }

    @Override
    public void updateResource(long deltaMillis) {
        if (isReloading()) {
            double deltaSeconds = deltaMillis / 1000.0;
            reloadTimer += deltaSeconds;

            if (reloadTimer >= reloadSpeed) {
                ammo = maxAmmo;
                reloadTimer = 0;
                flipReloading();
            }
        }
    }
}
