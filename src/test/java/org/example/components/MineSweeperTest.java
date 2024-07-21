package org.example.components;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MineSweeperTest {
    private Board board;
    private ConsoleUI consoleUI;
    private List<String> possibleUserInputs;

    @Before
    public void setUp() throws Exception {
        board = new Board(4, 4, 3);
        consoleUI = new ConsoleUI();
        possibleUserInputs = loadPossibleUserInputs();
    }

    private List<String> loadPossibleUserInputs() {
        List<String> list = new ArrayList<>();
        // negative test cases
        list.add("A");
        list.add("1");
        list.add("1A");
        list.add("AA");
        list.add("11");
        list.add("AAA");
        list.add("111");

        // positive test case to prevent infinite loop
        list.add("A1");
        return list;
    }

    @Test
    public void testUserInput() {
        int mineCount = 0;
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                if (board.getCell(row, col).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals("The board should have 5 mines.", 3, mineCount);

    }

    @Test
    public void testDisplayBoard() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Display the Board
        consoleUI.displayBoard(board);

        // Restore original System.out
        System.setOut(originalOut);

        // get the output and perform assertions
        String output = outputStream.toString();
        assertNotNull(output);
        assertFalse(output.isEmpty());
        System.out.println(output);
    }

    @Test
    public void testGetUserActionReveal() {
        String testCases = String.join("\n", possibleUserInputs);
        InputStream originalIn = System.in;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(testCases.getBytes());
            System.setIn(in);

            UserAction action = consoleUI.getUserAction();
            assertNotNull(action);
            assertTrue(action instanceof RevealAction);
            assertEquals(0, action.getRow());
            assertEquals(0, action.getColumn());


        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testRevealNonMineCell() {
        int nonMineRow = -1;
        int nonMineCol = -1;
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                if (!board.getCell(row, col).isMine()) {
                    nonMineRow = row;
                    nonMineCol = col;
                    break;
                }
            }
            if (nonMineRow != -1) break;
        }

        assertNotEquals("No non-mine cell found.", -1, nonMineRow);
        board.revealCell(nonMineRow, nonMineCol);
        assertTrue("The cell (" + nonMineRow + ", " + nonMineCol + ") should be revealed.", board.getCell(nonMineRow, nonMineCol).isRevealed());
        assertFalse("The game should not be over if a non-mine cell is revealed.", board.isGameOver());
    }

    @Test
    public void testRevealMineCell() {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                if (board.getCell(row, col).isMine()) {
                    board.revealCell(row, col);
                    assertTrue("The game should be over if a mine is revealed.", board.isGameOver());
                    return;
                }
            }
        }
        fail("No mine found to reveal, test setup issue.");
    }

    @Test
    public void testGameWon() {
        // Manually reveal all non-mine cells
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                if (!board.getCell(row, col).isMine()) {
                    board.getCell(row, col).setRevealed(true);
                }
            }
        }
        board.checkGameWon();
        assertTrue("The game should be won when all non-mine cells are revealed.", board.isGameWon());
    }
}
