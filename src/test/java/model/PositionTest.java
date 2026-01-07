package model;

import Elio.model.hero.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Elio.model.Position;

public class PositionTest {

    @Test
    public void movers() {

        Position position = new Position(5,5);

        position.moveDown();
        Assertions.assertEquals(new Position(5,6), position);

        position.moveUp();
        Assertions.assertEquals(new Position(5,5), position);

        position.moveRight();
        Assertions.assertEquals(new Position(6,5), position);

        position.moveLeft();
        Assertions.assertEquals(new Position(5,5), position);
    }

    @Test
    public void setters() {

        Position position = new Position(3,7);

        position.setX(4);
        position.setY(6);

        Assertions.assertEquals(new Position(4,6), position);

        position.setPosition(new Position(new Position(5,5)));

        Assertions.assertEquals(new Position(5,5), position);
    }

    @Test
    public void equals(){
        Position position1 = new Position(4,4);
        Position position2 = new Position(4,4);
        Position position3 = new Position(8,8);

        Assertions.assertTrue(position1.equals(position2));
        Assertions.assertFalse(position1.equals(position3));
        Assertions.assertFalse(position1.equals(null));
        Assertions.assertTrue(position1.equals(position1));
        Assertions.assertFalse(position1.equals(3));
    }

    @Test
    public void getNeighbour(){
        Position position = new Position(3,2);

        Assertions.assertNull(position.getNeighbor(Hero.Direction.NONE));

        Assertions.assertEquals(new Position(3,1), position.getNeighbor(Hero.Direction.UP));

        Assertions.assertEquals(new Position(3,3), position.getNeighbor(Hero.Direction.DOWN));

        Assertions.assertEquals(new Position(4,2), position.getNeighbor(Hero.Direction.RIGHT));

        Assertions.assertEquals(new Position(2,2), position.getNeighbor(Hero.Direction.LEFT));
    }

}
