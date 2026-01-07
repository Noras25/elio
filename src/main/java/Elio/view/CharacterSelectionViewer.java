package Elio.view;

import Elio.gui.GUI;
import Elio.gui.LanternaGUI;
import Elio.model.Menu;
import Elio.model.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

public class CharacterSelectionViewer extends MenuViewer {
    public CharacterSelectionViewer(Menu menu){
        super(menu);
    }

    @Override
    public void draw(GUI gui) {
        gui.fillBackground(getModel().getBackgroundColor(), getModel().getWidth(), getModel().getHeight());

        String mainColor = getModel().getTextColor();
        String accentColor = "#FFD700"; // Gold accent for keys
        String subTextColor = "#AAAAAA"; // Grey for descriptions

        //title
        gui.drawText(new Position(36, 3), "==============================", mainColor, true);
        gui.drawText(new Position(38, 4), "IDENTIFY YOURSELF, SURVIVOR", mainColor, true);
        gui.drawText(new Position(36, 5), "==============================", mainColor, true);

        // boxes
        // Warrior
        gui.drawText(new Position(36, 9), "[W]", accentColor, true);
        gui.drawText(new Position(40, 9), "-> WARRIOR", mainColor, true);
        gui.drawText(new Position(43, 10), "High HP, Close Quarters", subTextColor, false);

        // Mage
        gui.drawText(new Position(36, 12), "[M]", accentColor, true);
        gui.drawText(new Position(40, 12), "-> MAGE", mainColor, true);
        gui.drawText(new Position(43, 13), "Medium Range, Mana", subTextColor, false);

        // Gunman
        gui.drawText(new Position(36, 15), "[G]", accentColor, true);
        gui.drawText(new Position(40, 15), "-> GUNMAN", mainColor, true);
        gui.drawText(new Position(43, 16), "Long Range, Fast Fire", subTextColor, false);

        // --- FOOTER ---
        gui.drawText(new Position(33, 20), "Press the corresponding key to start", mainColor, false);
    }
}
