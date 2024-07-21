package org.example.components;

import org.example.utils.ValidatorUtils;

import static org.example.MineSweeper.sc;
import static org.example.utils.PrintUtils.*;

public class ConsoleUI implements UI {
    private static final String ALPHABETS = "ABCDEFGHIJK";

    @Override
    public void displayBoard(Board board) {
        // Prints col S/N
        for (int col = -1; col < board.getColumns(); col++) {
            if (col < 0) {
                System.out.print("  ");
            } else {
                System.out.print(col + 1 + " ");
            }
        }
        System.out.println();
        for (int row = 0; row < board.getRows(); row++) {
            // Prints row S/N
            System.out.print(ALPHABETS.charAt(row) + " ");
            for (int col = 0; col < board.getColumns(); col++) {
                Cell cell = board.getCell(row, col);
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public UserAction getUserAction() {
        boolean isValid = false;
        String square = "";
        while (!isValid) {
            printRevealActionMessage();
            square = sc.nextLine();
            isValid = validateUserAction(square);
        }
        int row = ALPHABETS.indexOf(square.charAt(0));
        int col = Integer.parseInt(square.substring(1));

        return new RevealAction(row, col - 1);
    }

    @Override
    public void displayResult(boolean isGameWon) {
        if (isGameWon) {
            printGameWonMessage();
        } else {
            printGameOverMessage();
        }
    }

    private boolean validateUserAction(String input) {
        char row = input.charAt(0);
        String col = input.substring(1);
        if (input.length() > 1 && !ValidatorUtils.isValidInput(input) && (ALPHABETS.indexOf(row) == -1) || !ValidatorUtils.isNumeric(col)) {
            printInvalidInputMessage();
            return false;
        }
        return true;
    }
}
