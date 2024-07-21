package org.example.components;

public interface UI {
    void displayBoard(Board board);
    UserAction getUserAction();
    void displayResult(boolean isGameWon);
}
