package model;

import Elio.model.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    public void setUp(){
        menu = new Menu("#000000", "#FFFFFF", 20, 25);
    }

    @Test
    public void color(){
        Assertions.assertEquals("#000000", menu.getBackgroundColor());

        Assertions.assertEquals("#FFFFFF", menu.getTextColor());

        menu.setBackgroundColor("#123456");
        Assertions.assertEquals("#123456", menu.getBackgroundColor());

        menu.setTextColor("#654321");
        Assertions.assertEquals("#654321", menu.getTextColor());
    }

    @Test
    public void size(){
        Assertions.assertEquals(20, menu.getWidth());

        Assertions.assertEquals(25, menu.getHeight());
    }

    @Test
    public void blinking(){
        Assertions.assertFalse(menu.isBlinking());

        menu.setBlinking(true);
        Assertions.assertTrue(menu.isBlinking());
    }
}
