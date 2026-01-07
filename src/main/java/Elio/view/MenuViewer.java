package Elio.view;

import Elio.gui.GUI;
import Elio.gui.LanternaGUI;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import Elio.model.Menu;

public abstract class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu model) {
        super(model);
    }

    public abstract void draw(GUI gui);
}
