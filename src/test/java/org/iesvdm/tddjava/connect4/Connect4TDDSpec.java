package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class Connect4TDDSpec {

    /**
     * clase bajo testeo
     */
    private Connect4TDD tested;

    private OutputStream output;

    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();

        //Se instancia el juego modificado para acceder a la salida de consola
        tested = new Connect4TDD(new PrintStream(output));
    }

    /*
     * The board is composed by 7 horizontal and 6 vertical empty positions
     */

    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {

    }

    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        try {
            tested.putDiscInColumn(Connect4.COLUMNS+1);
        }catch (RuntimeException e){
            assertEquals(tested.toString(),e.getMessage());
        }

    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        tested.putDiscInColumn(0);
        assertEquals(0 , tested.getNumberOfDiscsInColumn(0));
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
    tested.putDiscInColumn(1);
    tested.putDiscInColumn(1);
    assertEquals(2 , tested.getNumberOfDiscsInColumn(1));
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {

        tested.putDiscInColumn(1);
        tested.putDiscInColumn(1);
        tested.putDiscInColumn(1);
        assertEquals(3 , tested.getNumberOfDiscsInColumn(1));

    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {

        for (int i = 0; i < Connect4.COLUMNS; i++) {
            try {
                for (int j = 0; j < Connect4.ROWS; j++) {
                    tested.putDiscInColumn(i);
                }
//                Assertions.fail("se esperaba la excepcion");
            }catch (RuntimeException e){
                assertEquals(tested.toString(),e.getMessage());
            }
        }

    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        assertEquals("R", tested.getCurrentPlayer());
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {
        tested.switchPlayer();
        assertEquals("G", tested.getCurrentPlayer());
    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {
        tested.getCurrentPlayer();


    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {

    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {

    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {

    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {

    }
}
