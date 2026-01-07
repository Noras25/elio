package Elio.model;

public class Menu {
    private String backgroundColor;
    private String textColor;
    private int width;
    private int height;

    //checking if time works by putting text to blink
    private boolean blinkState = false;

    public Menu(String backgroundColor, String textColor, int width, int height){
        this.backgroundColor = backgroundColor; // "#000000";
        this.textColor = textColor; //"#FFFFFF";
        this.width = width; // 100;
        this.height = height; // 25;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String background) {
        this.backgroundColor = background;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    //blinking functions
    public boolean isBlinking() { return blinkState; }
    public void setBlinking(boolean blinking) { this.blinkState = blinking; }
}
