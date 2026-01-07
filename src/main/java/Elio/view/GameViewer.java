package Elio.view;

import Elio.gui.GUI;
import Elio.model.Arena;
import Elio.model.Element;
import Elio.model.HUD.HUD;
import Elio.model.Position;
import Elio.model.hero.Hero;
import Elio.view.model.ArenaViewer;
import Elio.view.model.HUD.HUDViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameViewer extends Viewer<Arena> {
    private final ArenaViewer arenaViewer;
    private final HUDViewer hudViewer;

    public GameViewer(Arena arena, HUD hud) {
        super(arena);
        this.arenaViewer = new ArenaViewer(arena);
        this.hudViewer = new HUDViewer(hud);
    }

    public void draw(GUI gui) throws IOException {
        arenaViewer.draw(gui);
        hudViewer.draw(gui);
        gui.refresh();
    }
}