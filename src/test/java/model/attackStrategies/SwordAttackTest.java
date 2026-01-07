package model.attackStrategies;

import Elio.model.attackStrategies.SwordAttack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwordAttackTest {
    @Test
    public void testSwordAttack() {
        SwordAttack swordAttack = new SwordAttack(new Elio.model.Position(3, 3), "|", "#FF0000", 200);

        //check initial values
        assertEquals("|", swordAttack.getSymbol());
        assertEquals("#FF0000", swordAttack.getColor());
        assertTrue(swordAttack.isActive());

        //decrease duration and check activity
        swordAttack.decreaseDuration(50);
        assertTrue(swordAttack.isActive());

        //decrease duration to zero and check activity
        swordAttack.decreaseDuration(150);
        assertFalse(swordAttack.isActive());

    }
}
