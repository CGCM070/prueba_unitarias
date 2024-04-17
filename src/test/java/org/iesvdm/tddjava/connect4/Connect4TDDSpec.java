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
        assertThat(tested.getNumberOfDiscs()).isZero();
    }

    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        try {
            tested.putDiscInColumn(Connect4.COLUMNS + 1);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isNotEmpty();

        }
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        tested.putDiscInColumn(0);
        assertEquals(0, tested.getNumberOfDiscsInColumn(0));
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        tested.putDiscInColumn(1);
        tested.putDiscInColumn(1);
        assertEquals(2, tested.getNumberOfDiscsInColumn(1));
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {
        int numDisco = tested.getNumberOfDiscs();
        tested.putDiscInColumn(1);
        assertEquals(numDisco + 1, tested.getNumberOfDiscs());

    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {
        for (int i = 0; i < Connect4.COLUMNS; i++) {
            try {
                for (int j = 0; j < Connect4.ROWS; j++) {
                    tested.putDiscInColumn(i);
                }
            } catch (RuntimeException e) {
                assertThat(e.getMessage()).isNotEmpty();
            }
        }
        System.out.println(output.toString());
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
        String jugador = tested.getCurrentPlayer();
        assertThat(output.toString()).isNotEmpty();
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        tested.putDiscInColumn(1);
        assertThat(output.toString()).isNotEmpty();

    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {
        assertFalse(tested.isFinished());
    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {
        for (int i = 0; i < Connect4.COLUMNS; i++) {
            try {
                for (int j = 0; j < Connect4.ROWS; j++) {
                    tested.putDiscInColumn(i);
                }
            } catch (RuntimeException e) {
                assertThat(e.getMessage()).isNotEmpty();
            }
        }
        assertThat(tested.isFinished()).isTrue();

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {
        String playerOne = tested.getCurrentPlayer();
        for (int i = 1; i < Connect4.COLUMNS; i++) {
            tested.putDiscInColumn(1);
            tested.switchPlayer();
        }
        assertThat(tested.getWinner().equals(playerOne)).isTrue();

        System.out.println(output.toString());
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {

        String p = tested.getCurrentPlayer();
        for (int i = 0; i < Connect4.ROWS; i++) {
            tested.putDiscInColumn(i);
            tested.switchPlayer();
        }
        assertThat(tested.getWinner().equals(p)).isTrue();
        System.out.println(output.toString());
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {
        for (int i = 0; i < Connect4.COLUMNS - 2; i++) {
            for (int j = 0; j < i; j++) {
                tested.putDiscInColumn(i);
                tested.switchPlayer();
            }
        }
        System.out.println(output.toString());

//            assertThat(tested.isFinished()).isTrue();
        assertThat(tested.getWinner().equalsIgnoreCase("R")).isTrue();


    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {
          for (int i = 0; i < Connect4.COLUMNS - 2; i++) {
                for (int j = 0; j < i; j++) {
                 tested.putDiscInColumn(i);
                 tested.switchPlayer();
                }
          }
          System.out.println(output.toString());
    }
}
