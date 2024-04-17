package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
//        ship = new Ship(location);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
            assertEquals(ship.getLocation(), location);
    }

    public void whenMoveForwardThenForward() {
        Location expected = location.copy();
        expected.forward(planet.getMax(), planet.getObstacles());
        ship.moveForward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenMoveBackwardThenBackward() {
        Location expected = location.copy();
        expected.backward(planet.getMax(), planet.getObstacles());
        ship.moveBackward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnLeftThenLeft() {
        Location expected = location.copy();
        expected.turnLeft();
        ship.turnLeft();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnRightThenRight() {
        Location expected = location.copy();
        expected.turnRight();
        ship.turnRight();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsFThenForward() {
        Location expected = location.copy();
        expected.forward(planet.getMax(), planet.getObstacles());
        ship.receiveCommands("f");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsBThenBackward() {
        Location expected = location.copy();
        expected.backward(planet.getMax(), planet.getObstacles());
        ship.receiveCommands("b");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        Location expected = location.copy();
        expected.turnLeft();
        ship.receiveCommands("l");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsRThenTurnRight() {
        Location expected = location.copy();
        expected.turnRight();
        ship.receiveCommands("r");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsThenAllAreExecuted() {
        Location expected = location.copy();
        expected.turnRight();
        expected.forward(planet.getMax(), planet.getObstacles());
        expected.turnLeft();
        expected.backward(planet.getMax(), planet.getObstacles());
        ship.receiveCommands("rflb");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenInstantiatedThenPlanetIsStored() {
        assertEquals(ship.getPlanet(), planet);

    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(planet.getMax().getX());
        Location expected = location.copy();
        expected.getPoint().setX(1);
        ship.receiveCommands("f");
        assertEquals(ship.getLocation(), expected);
    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(1);
        Location expected = location.copy();
        expected.getPoint().setX(planet.getMax().getX());
        ship.receiveCommands("b");
        assertEquals(ship.getLocation(), expected);

    }

    public void whenReceiveCommandsThenStopOnObstacle() {
        location.setDirection(Direction.NORTH);
        location.getPoint().setX(44);
        location.getPoint().setY(44);
        assertFalse(Boolean.parseBoolean(ship.receiveCommands("f")));
        assertEquals(ship.getLocation().getPoint().getX(), 44);
        assertEquals(ship.getLocation().getPoint().getY(), 43);
    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        location.setDirection(Direction.NORTH);
        location.getPoint();
        location.getPoint().setX(10);
        location.getPoint().setY(10);
        ship.receiveCommands("f");
        assertEquals(ship.receiveCommands("f"), "O");

        Location location2 = new Location(new Point(44, 44), Direction.NORTH);
        assertEquals(location2.forward(planet.getMax(), planet.getObstacles()), true);
    }

}
