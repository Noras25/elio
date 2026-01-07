package Elio.view;

import Elio.gui.GUI;
import Elio.model.Arena;
import Elio.model.LevelUpChoice;
import Elio.model.Menu;
import Elio.model.Position;

import java.util.List;

public class LevelUpViewer extends Viewer<Menu> {
    private final List<LevelUpChoice> options;
    private final Arena stats;

    public LevelUpViewer(Menu model, Arena stats, List<LevelUpChoice> options){
        super(model);
        this.stats = stats;
        this.options = options;
    }

    @Override
    public void draw(GUI gui) {
        Menu menu = getModel();
        gui.fillBackground(menu.getBackgroundColor(), menu.getWidth(), menu.getHeight());

        gui.drawText(new Position(46,5), "Congrats!", menu.getTextColor(), true);
        gui.drawText(new Position(43, 6), "You LEVELED Up!", menu.getTextColor(), false);
        gui.drawText(new Position(42, 10), "Choose Wisely...", menu.getTextColor(), false);

        for (LevelUpChoice o : options){
            int displacement = 10 + 28 * (o.getNumber() - 1);
            StringBuilder text = new StringBuilder();
            text.append("Increase ");
            switch (o.getType()){
                case HP -> text.append("HP by ");
                case DAMAGE -> text.append("Damage by ");
                case CLASS_SPECIFIC_1 -> {
                    switch(stats.getHero().getType()) {
                        case GUNMAN -> text.append("Ammo by ");
                        case WARRIOR -> text.append("Lifesteal by ");
                        case MAGE -> text.append("Mana by ");
                    }
                }
                case CLASS_SPECIFIC_2 -> {
                    switch(stats.getHero().getType()) {
                        case GUNMAN -> text.append("Reload Speed by ");
                        case WARRIOR -> text.append("Hardness by ");
                        case MAGE -> text.append("Mana Regen by ");
                    }
                }
                case SHIELD -> text.append("Shield by ");
                case XP -> text.append("XP Gain by ");
                default -> text.append("Bug chance by ");
            }
            StringBuilder text2 = new StringBuilder();
            String color;
            switch(o.getRarity()){
                case COMMON -> {
                    text2.append("COMMON");
                    color = "#808080";
                    if(o.getType() == LevelUpChoice.LevelUpType.SHIELD) text.append("+20");
                    else text.append("20%");
                }
                case UNCOMMON -> {
                    text2.append("UNCOMMON");
                    color = "#3CAE63";
                    if(o.getType() == LevelUpChoice.LevelUpType.SHIELD) text.append("+30");
                    else text.append("30%");
                }
                case RARE -> {
                    text2.append("RARE");
                    color = "#6E00B3";
                    if(o.getType() == LevelUpChoice.LevelUpType.SHIELD) text.append("+50");
                    else text.append("50%");
                }
                case LEGENDARY -> {
                    text2.append("LEGENDARY");
                    color = "#EFBF04";
                    if(o.getType() == LevelUpChoice.LevelUpType.SHIELD) text.append("+100");
                    else text.append("100%");
                }
                default -> {
                    text2.append("Something went wrong.");
                    color = "#FFFFFF";
                    text.append("1000%");
                }
            }
            text2.append(" -> Press \"").append(o.getNumber()).append("\"");
            gui.drawText(new Position(displacement, 13), text2.toString(), color, true);

            gui.drawText(new Position(displacement - (text.length() - text2.length()) / 2, 14), text.toString(), menu.getTextColor(), false);
        }
    }
}