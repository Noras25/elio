package Elio.gui;

import Elio.model.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaGUI implements GUI {
    final private Screen screen;
    final private TextGraphics graphics;

    public LanternaGUI (Screen screen) {
        this.screen = screen;
        this.graphics = screen.newTextGraphics();
    }

    public LanternaGUI (int width, int height) throws IOException {
        Terminal terminal = createTerminal(width, height);
        this.screen = createScreen(terminal);
        this.graphics = screen.newTextGraphics();
    }

    //functions to create terminal and screen
    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        try {
            screen.setCursorPosition(null);  //maybe we will need the cursor TBD
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();

    }
        return screen;
    }

    private Terminal createTerminal(int width, int height) throws IOException{
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();

        return terminal;
    }

    //functions from GUI interface
    @Override
    public void drawText(Position position, String text, String color, String background, boolean bold) {
        graphics.setForegroundColor(com.googlecode.lanterna.TextColor.Factory.fromString(color));
        if (background != null) graphics.setBackgroundColor(com.googlecode.lanterna.TextColor.Factory.fromString(background));
        if (bold){
            graphics.enableModifiers(SGR.BOLD);
        }
        else graphics.disableModifiers(SGR.BOLD);
        graphics.putString(position.getX(), position.getY(), text);
    }

    public void drawText(Position position, String text, String color, boolean bold){
        drawText(position, text, color, null, bold);
    }

    public void fillBackground(String color, int width, int height) {
        graphics.setBackgroundColor(com.googlecode.lanterna.TextColor.Factory.fromString(color));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    @Override
    public KeyStroke getKey() throws IOException {
        return screen.pollInput();
    }

    @Override
    public TextGraphics getGraphics() {
        return graphics;
    }

    @Override
    public Screen getScreen() { return screen; }
}
