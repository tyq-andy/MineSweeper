package org.example.components;

import java.util.InputMismatchException;

import static org.example.MineSweeper.sc;
import static org.example.utils.PrintUtils.*;
import static org.example.utils.ValidatorUtils.validateGridSize;
import static org.example.utils.ValidatorUtils.validateMineCount;

public class GameController {
    private final UI ui;
    private Board board;

    public GameController(UI ui) {
        this.ui = ui;
    }

    public void startGame() {
        boolean isGridSizeValid = false;
        boolean isMineCountValid = false;
        int gridSize = 0;
        int mineCount = 0;
        printWelcomeMessage();
        while (!isGridSizeValid) {
            try {
                printGridSizePrompt();
                gridSize = sc.nextInt();
                isGridSizeValid = validateGridSize(gridSize);
            } catch (InputMismatchException e) {
                printInvalidInputMessage();
                sc.nextLine();
            }
        }

        while (!isMineCountValid) {
            try {
                printMineCountPrompt();
                mineCount = sc.nextInt();
                isMineCountValid = validateMineCount(mineCount, gridSize);
            } catch (InputMismatchException e) {
                printInvalidInputMessage();
                sc.nextLine();
            }
        }
        sc.nextLine();
        this.board = new Board(gridSize, gridSize, mineCount);

        while (!board.isGameOver()) {
            ui.displayBoard(board);
            UserAction action = ui.getUserAction();
            processAction(action);
        }
        ui.displayResult(board.isGameWon());

        printPlayAgainMessage();
        if (board.isGameOver() && sc.nextLine() != null) {
            startGame();
        }
    }

    private void processAction(UserAction action) {
        if (action instanceof RevealAction) {
            board.revealCell(action.getRow(), action.getColumn());
            if (action.getRow() >= 0 && action.getRow() < board.getRows() &&
                    action.getColumn() >= 0 && action.getColumn() < board.getColumns()) {
                Cell currentCell = board.getCell(action.getRow(), action.getColumn());
                if (!currentCell.isMine() && currentCell.isRevealed() && !board.isGameOver()) {
                    printAdjacentMinesMessage(currentCell.getAdjacentMineCount());
                    printUpdateMineFieldMessage();
                }
            } else {
                printInvalidInputMessage();
            }
        }
    }
}
