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
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        Location location1 = new Location(new Point(x, y), direction);
        assertEquals(location, location1);
    }

    public void givenDifferentObjectWhenEqualsThenFalse() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        Direction direction1 = Direction.EAST;
        Location location1 = new Location(new Point(x, y), direction1);
        assertNotEquals(location, location1);
    }

    public void givenDifferentXWhenEqualsThenFalse() {
        Direction direction = Direction.NORTH;
        Location location1 = new Location(new Point(x + 1, y), direction);
        Location location2 = new Location(new Point(x, y), direction);
        assertNotEquals(location1, location2);
    }

    public void givenDifferentYWhenEqualsThenFalse() {
        Direction direction = Direction.NORTH;
        Location location1 = new Location(new Point(x, y + 1), direction);
        Location location2 = new Location(new Point(x, y - 1), direction);
        assertNotEquals(location1, location2);
    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {
        Direction direction = Direction.NORTH;
        Direction direction1 = Direction.SOUTH;
        assertNotEquals(direction, direction1);

    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {
        Direction direction = Direction.NORTH;
        Location location1 = new Location(new Point(x + 1, y + 1), direction);
        Location location2 = new Location(new Point(x + 1, y + 1), direction);
        assertEquals(location1, location2);
    }

    public void whenCopyThenDifferentObject() {

        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        Location locationcopiada = location.copy();
        assertEquals(location, locationcopiada);
    }

    public void whenCopyThenEquals() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        Location locationcopiada = location.copy();
        assertEquals(location, locationcopiada);
    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {
        Direction direction = Direction.EAST;
        location = new Location(new Point(x+100, y+100), direction);
        location.forward(max);
        int x =1;
        assertEquals(location.getX(), x);

    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {
        Direction direction = Direction.WEST;
        int inicio =1;
        location = new Location(new Point(inicio, y+100), direction);
        location.forward(max);
        int esperado = max.getX();
        assertEquals(location.getX(), esperado);
    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {
        Direction direccion  = Direction.NORTH;
        int inicio =1;
        location = new Location(new Point(x, inicio),direccion);
        location.forward(max);
        int esperado = max.getY();
        assertEquals(location.getY(), esperado);
    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {
        Direction direction = Direction.SOUTH;
        int yMx = max.getY();
        location  = new Location(new Point(x ,  yMx),direction);
        location.forward(max);
        int ymax= 1;
        assertEquals(location.getY(), ymax);

    }

    public void givenObstacleWhenForwardThenReturnFalse() {
        Direction dir = Direction.NORTH;
        List<Point> obstacles = new ArrayList<>(); // Lista de obstáculos
        obstacles.add(new Point(x, y)); // Agregar un obstáculo en la posición actual
        location = new Location(new Point(x, y), dir); // Crear un objeto Location en la posición actual y dirección NORTH
        assertFalse(location.forward(new Point(100, 100), obstacles));
    }

    public void givenObstacleWhenBackwardThenReturnFalse() {
        Direction dir = Direction.NORTH;
        List<Point> obstacles = new ArrayList<>(); // Lista de obstáculos
        obstacles.add(new Point(x, y)); // Agregar un obstáculo en la posición actual
        location = new Location(new Point(x, y), dir); // Crear un objeto Location en la posición actual y dirección NORTH
        assertFalse(location.backward(new Point(x, y), obstacles));


    }

}
