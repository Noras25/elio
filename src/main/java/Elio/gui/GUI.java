package Elio.gui;

import Elio.model.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public interface GUI {

    void drawText(Position position, String text, String color, String background, boolean bold);
    void drawText(Position position, String text, String color, boolean bold);
    void fillBackground(String color, int width, int height);

    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    TextGraphics getGraphics();
    KeyStroke getKey() throws IOException;
    Screen getScreen();

    static String WHITE = "#FFFFFF";
    static String BLACK = "#000000";
}
