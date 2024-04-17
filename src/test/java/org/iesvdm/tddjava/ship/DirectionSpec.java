package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class DirectionSpec {

    @Test
    public void whenGetFromShortNameNThenReturnDirectionN() {
        Direction expectedDirection = Direction.NORTH;
        Direction actualDirection = Direction.getFromShortName('N');
        assertEquals(expectedDirection, actualDirection);
    }


    public void whenGetFromShortNameWThenReturnDirectionW() {
        Direction expectedDirection = Direction.WEST;
        Direction actualDirection = Direction.getFromShortName('W');
        assertEquals(expectedDirection, actualDirection);
    }

    public void whenGetFromShortNameBThenReturnNone() {
        Direction expectedDirection = Direction.NONE;
        Direction actualDirection = Direction.getFromShortName('X');
        assertEquals(expectedDirection, actualDirection);
    }

    public void givenSWhenLeftThenE() {

    }

    public void givenNWhenLeftThenW() {

    }

    public void givenSWhenRightThenW() {

    }

    public void givenWWhenRightThenN() {

    }

}
