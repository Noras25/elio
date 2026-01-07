package Elio.view;

import Elio.gui.GUI;
import Elio.model.Arena;
import Elio.model.Menu;
import Elio.model.Position;

public class PauseMenuViewer extends Viewer<Menu> {

    public PauseMenuViewer(Menu model){
        super(model);
    }

    @Override
    public void draw(GUI gui) {
        Menu menu = getModel();
        gui.fillBackground(menu.getBackgroundColor(), menu.getWidth(), menu.getHeight());

        if (menu.isBlinking()) {
            drawBigText(gui, new Position(35, 8), menu.getTextColor());
        }
        gui.drawText(new Position(39, 13), "Press SPACE to unpause", menu.getTextColor(), null, false);
    }

    private void drawBigText(GUI gui, Position startPos, String color) {
        // This array represents the word "PAUSED" drawn with characters
        String[] art = {
                " ___   _  _   _  ___  ___  ___ ",
                "| _ \\ /_\\| | | |/ __|| __||   \\",
                "|  _// _ \\ |_| |\\__ \\| _| | |) |",
                "|_| /_/ \\_\\___/ |___/|___||___/"
        };

        // Loop through each line of the "Art" and draw it
        for (int i = 0; i < art.length; i++) {
            // Draw each line
            // We use 'true' for bold to make the ASCII art stand out even more
            gui.drawText(
                    new Position(startPos.getX(), startPos.getY() + i),
                    art[i],
                    color,
                    true
            );
        }
    }
}

