package Elio.controller;

import Elio.gui.LanternaGUI;
import Elio.model.Arena;
import Elio.model.Game;
import Elio.model.enemy.Enemy;
import Elio.model.hero.Hero;
import Elio.model.Position;
import Elio.model.hero.Warrior;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class HeroController extends Controller<Hero>{
    public HeroController(Hero model) {
        super(model);
    }

    @Override
    public void processKey(Game game, KeyStroke key) throws IOException {

    }

    @Override
    public void step(Game game, long time) throws IOException {

    }

    public void moveUp(Arena arena){
        Hero hero = getModel();
        hero.setDirection(Hero.Direction.UP);
        Position pos = hero.getPosition().getNeighbor(Hero.Direction.UP);

        //if trying to move to a position occupied by an enemy, lose hp
        for(Enemy e : arena.getEnemies()){
            if (e.getPosition().equals(pos)){
                hero.loseHP(e.getDamage());
            }
        }

        if (arena.canMove(pos))
            hero.getPosition().moveUp();
    }

    public void moveDown(Arena arena){
        Hero hero = getModel();

        hero.setDirection(Hero.Direction.DOWN);
        Position pos = hero.getPosition().getNeighbor(Hero.Direction.DOWN);

        for(Enemy e : arena.getEnemies()){
            if (e.getPosition().equals(pos)){
                hero.loseHP(e.getDamage());
            }
        }

        if (arena.canMove(pos))
            hero.getPosition().moveDown();
    }

    public void moveLeft(Arena arena){
        Hero hero = getModel();
        hero.setDirection(Hero.Direction.LEFT);
        Position pos = hero.getPosition().getNeighbor(Hero.Direction.LEFT);

        for(Enemy e : arena.getEnemies()){
            if (e.getPosition().equals(pos)){
                hero.loseHP(e.getDamage());
            }
        }

        if (arena.canMove(pos))
            hero.getPosition().moveLeft();
    }

    public void moveRight(Arena arena){
        Hero hero = getModel();
        hero.setDirection(Hero.Direction.RIGHT);
        Position pos = hero.getPosition().getNeighbor(Hero.Direction.RIGHT);

        for(Enemy e : arena.getEnemies()){
            if (e.getPosition().equals(pos)){
                hero.loseHP(e.getDamage());
            }
        }

        if (arena.canMove(pos))
            hero.getPosition().moveRight();
    }

    public void attack(Arena arena) {
        Hero hero = getModel();
        // This calls RangedStrategy.attack(), which calls arena.spawnProjectile()
        if(hero.getDirection() != Hero.Direction.NONE)
            hero.getAttackStrategy().attack(hero, arena);
    }
}
