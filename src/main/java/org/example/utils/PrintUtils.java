package org.example.utils;

public class PrintUtils {
    public static void printInvalidInputMessage() {
        System.out.println("Invalid Input.");
    }

    public static void printWelcomeMessage() {
        System.out.println("Welcome to Minesweeper!\n");
    }

    public static void printGridSizePrompt() {
        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
    }

    public static void printMineCountPrompt() {
        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
    }

    public static void printPlayAgainMessage() {
        System.out.println("Press any key to play again...");
    }

    public static void printGameWonMessage() {
        System.out.println("Congratulations, you have won the game!");
    }

    public static void printGameOverMessage() {
        System.out.println("Oh no, you detonated a mine! Game over.");
    }

    public static void printRevealActionMessage() {
        System.out.print("Select a square to reveal (e.g. A1): ");
    }

    public static void printInitiateBoardMessage() {
        System.out.println("Here is your minefield:");
    }

    public static void printUpdateMineFieldMessage() {
        System.out.println("Here is your updated minefield:");
    }

    public static void printAdjacentMinesMessage(int mines) {
        System.out.printf("This square contains %d adjacent mines.%n%n", mines);
    }
}
