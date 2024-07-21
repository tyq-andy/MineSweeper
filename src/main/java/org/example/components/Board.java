package org.example.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.utils.PrintUtils.printInitiateBoardMessage;

public class Board {
    private final int rows;
    private final int columns;
    private final int mines;
    private final Cell[][] cells;
    private final Random random = new Random();
    private boolean gameOver;
    private boolean gameWon;

    public Board(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        this.cells = new Cell[rows][columns];
        initializeBoard();
        placeMines();
        calculateAdjacentMines();
    }

    private void initializeBoard() {
        printInitiateBoardMessage();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                cells[row][col] = new Cell();
            }
        }
    }

    private void placeMines() {
        int placedMines = 0;
        while (placedMines < mines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(columns);
            if (!cells[row][col].isMine()) {
                cells[row][col].setMine(true);
                placedMines++;
            }
        }
    }

    private void calculateAdjacentMines() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (!cells[row][col].isMine()) {
                    cells[row][col].setAdjacentMineCount(countAdjacentMines(row, col));
                }
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (isValidCell(row + i, col + j) && cells[row + i][col + j].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void revealCell(int row, int col) {
        if (!isValidCell(row, col) || cells[row][col].isRevealed()) {
            return;
        }

        cells[row][col].setRevealed(true);

        if (cells[row][col].isMine()) {
            gameOver = true;
        } else if (cells[row][col].getAdjacentMineCount() == 0) {
            revealAdjacentCells(row, col);
        }

        checkGameWon();
    }

    private void revealAdjacentCells(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int adjRow = row + i;
                int adjCol = col + j;
                if (isValidCell(adjRow, adjCol) && !cells[adjRow][adjCol].isRevealed()) {
                    revealCell(adjRow, adjCol);
                }
            }
        }
    }

    void checkGameWon() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (!cells[row][col].isMine() && !cells[row][col].isRevealed()) {
                    gameWon = false;
                    return;
                }
            }
        }
        gameWon = true;
        gameOver = true;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}
