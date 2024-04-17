package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class LocationSpec {

    private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    public void whenInstantiatedThenXIsStored() {
        assertEquals(location.getX(), x);
        assertEquals(12, x);
    }

    public void whenInstantiatedThenYIsStored() {
        assertEquals(location.getY(), y);
        assertEquals(32, y);
    }

    public void whenInstantiatedThenDirectionIsStored() {
        Direction direction = Direction.NORTH;
        assertEquals(location.getDirection(), direction);
    }

    public void givenDirectionNWhenForwardThenYDecreases() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        location.forward();
        assertEquals(location.getY(), y - 1);

    }

    public void givenDirectionSWhenForwardThenYIncreases() {
        Direction direction = Direction.SOUTH;
        location = new Location(new Point(x, y), direction);
        location.forward();
        assertEquals(location.getY(), y + 1);
    }

    public void givenDirectionEWhenForwardThenXIncreases() {
        Direction direction = Direction.EAST;
        location = new Location(new Point(x, y), direction);
        location.forward();
        assertEquals(location.getX(), x + 1);
    }

    public void givenDirectionWWhenForwardThenXDecreases() {
        Direction direction = Direction.WEST;
        location = new Location(new Point(x, y), direction);
        location.forward();
        assertEquals(location.getX(), x - 1);
    }

    public void givenDirectionNWhenBackwardThenYIncreases() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        location.backward();
        assertEquals(location.getY(), y + 1);
    }

    public void givenDirectionSWhenBackwardThenYDecreases() {
        Direction direction = Direction.SOUTH;
        location = new Location(new Point(x, y), direction);
        location.backward();
        assertEquals(location.getY(), y - 1);
    }

    public void givenDirectionEWhenBackwardThenXDecreases() {
        Direction direction = Direction.EAST;
        location = new Location(new Point(x, y), direction);
        location.backward();
        assertEquals(location.getX(), x - 1);
    }

    public void givenDirectionWWhenBackwardThenXIncreases() {
        Direction direction = Direction.WEST;
        location = new Location(new Point(x, y), direction);
        location.backward();
        assertEquals(location.getX(), x + 1);
    }

    public void whenTurnLeftThenDirectionIsSet() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        location.turnLeft();
        Direction expectedDirection = Direction.WEST;
        assertEquals(expectedDirection, location.getDirection());
    }

    public void whenTurnRightThenDirectionIsSet() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        location.turnRight();
        Direction expectedDirection = Direction.EAST;
        assertEquals(expectedDirection, location.getDirection());
    }

    public void givenSameObjectsWhenEqualsThenTrue() {

    }

    public void givenDifferentObjectWhenEqualsThenFalse() {

    }

    public void givenDifferentXWhenEqualsThenFalse() {

    }

    public void givenDifferentYWhenEqualsThenFalse() {

    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {

    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {

    }

    public void whenCopyThenDifferentObject() {
    }

    public void whenCopyThenEquals() {

    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {

    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {

    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {

    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {

    }

    public void givenObstacleWhenForwardThenReturnFalse() {

    }

    public void givenObstacleWhenBackwardThenReturnFalse() {

    }

}
