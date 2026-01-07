package Elio.controller;

import Elio.gui.GUI;
import Elio.model.Arena;
import Elio.model.Game;
import Elio.model.LevelUpChoice;
import Elio.model.Menu;
import Elio.model.hero.Gunman;
import Elio.model.hero.Mage;
import Elio.model.hero.Warrior;
import Elio.state.GameState;
import Elio.state.LevelUpState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.List;

public class LevelUpController extends Controller<Menu> {
    private List<LevelUpChoice> options;
    private final Arena stats;
    private int levels;

    public LevelUpController(Menu model, Arena stats, List<LevelUpChoice> options, int levels) {
        super(model);
        this.options = options;
        this.stats =  stats;
        this.levels = levels;
    }

    private void chooseOption(Game game, int option){
        for (LevelUpChoice o : options){
            if (o.getNumber() != option) continue;
            double increase = 1;
            switch(o.getRarity()){
                case COMMON -> increase = 1.2;
                case UNCOMMON -> increase = 1.3;
                case RARE -> increase = 1.5;
                case LEGENDARY -> increase = 2;
            }
            switch (o.getType()){
                case HP -> {
                    int difference = (int) (stats.getHero().getMaxHP() * increase) - stats.getHero().getMaxHP();
                    stats.getHero().setMaxHP(stats.getHero().getMaxHP() + difference);
                    stats.getHero().setHP(stats.getHero().getHP() + difference);
                }

                case DAMAGE -> stats.getHero().setDamage((int) (stats.getHero().getDamage() * increase));
                case SHIELD -> {
                    int difference = (int) (increase * 100 - 100);
                    stats.getHero().setMaxShield(stats.getHero().getMaxShield() + difference);
                    stats.getHero().setShield(stats.getHero().getShield() + difference);
                }
                case CLASS_SPECIFIC_1 -> {
                    switch (stats.getHero().getType()){
                        case GUNMAN -> {
                            int difference = (int) (((Gunman)stats.getHero()).getMaxAmmo() * increase - ((Gunman) stats.getHero()).getMaxAmmo());
                            ((Gunman)stats.getHero()).setMaxAmmo( (((Gunman)stats.getHero()).getMaxAmmo() + difference));
                            ((Gunman)stats.getHero()).setAmmo( (((Gunman)stats.getHero()).getAmmo() + difference));
                        }
                        case WARRIOR -> ((Warrior)stats.getHero()).setLifeSteal((((Warrior)stats.getHero()).getLifeSteal() * increase));
                        case MAGE -> {
                            int difference = (int) (((Mage)stats.getHero()).getMaxMana() * increase - ((Mage) stats.getHero()).getMaxMana());
                            ((Mage)stats.getHero()).setMaxMana( (((Mage)stats.getHero()).getMaxMana() + difference));
                            ((Mage)stats.getHero()).setMana( (((Mage)stats.getHero()).getMana() + difference));
                        }
                    }

                }
                case CLASS_SPECIFIC_2 -> {
                    switch (stats.getHero().getType()){
                        case GUNMAN -> ((Gunman)stats.getHero()).setReloadSpeed( (((Gunman)stats.getHero()).getReloadSpeed() / increase));
                        case WARRIOR -> ((Warrior)stats.getHero()).setHardness((((Warrior)stats.getHero()).getHardness() / increase));
                        case MAGE -> ((Mage)stats.getHero()).setManaReloadingSpeed(((Mage)stats.getHero()).getManaReloadingSpeed() / increase);
                    }

                }
                case XP -> stats.getHero().setXpGain((stats.getHero().getXpGain() * increase));
            }
        }
        levels--;
        if (levels > 0) game.setState(new LevelUpState(new Menu(GUI.BLACK, GUI.WHITE, 100, 25), stats, levels));
        else game.setState(new GameState(stats));
    }

    @Override
    public void processKey(Game game, KeyStroke key) throws IOException {


        if((key.getKeyType() == KeyType.Character && key.getCharacter() == '1')) {
            chooseOption(game, 1);
        }

        if((key.getKeyType() == KeyType.Character && key.getCharacter() == '2')) {
            chooseOption(game, 2);
        }

        if((key.getKeyType() == KeyType.Character && key.getCharacter() == '3')) {
            chooseOption(game, 3);
        }

        if((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')) {
            game.getGui().close();
        }

        if (key.getKeyType() == KeyType.EOF) {
            game.setRunning(false);
        }

    }

    @Override
    public void step(Game game, long time) throws IOException {

    }

    public void setOptions(List<LevelUpChoice> options) { this.options = options; } //for testing reasons

    public void setLevels(int levels) { this.levels = levels; } //same here
}
