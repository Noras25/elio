package Elio.view;

import Elio.gui.GUI;
import Elio.gui.LanternaGUI;
import Elio.model.Menu;
import Elio.model.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

public class IntroMenuViewer extends MenuViewer {

    public IntroMenuViewer(Menu menu){
        super(menu);
    }

    @Override
    public void draw(GUI gui) {
        gui.fillBackground(getModel().getBackgroundColor(), getModel().getWidth(), getModel().getHeight());

        gui.drawText(new Position(40,12), "Welcome to ELIO!", getModel().getTextColor(), true);
        gui.drawText(new Position(38, 13), "Press SPACE to start", getModel().getTextColor(), false);
    }
}
