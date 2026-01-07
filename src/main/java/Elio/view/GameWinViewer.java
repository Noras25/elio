package Elio.view;

import Elio.gui.GUI;
import Elio.model.Menu;
import Elio.model.Position;

public class GameWinViewer extends  MenuViewer{

    public GameWinViewer(Menu menu){
        super(menu);
    }

    @Override
    public void draw(GUI gui) {
        gui.fillBackground(getModel().getBackgroundColor(), getModel().getWidth(), getModel().getHeight());

        gui.drawText(new Position(42,12), "You ", getModel().getTextColor(),false);
        gui.drawText(new Position(46,12), "WON!! ", "#EFBF04",true);
        gui.drawText(new Position(52, 12), "Yipee!", getModel().getTextColor(), false);
    }

}
