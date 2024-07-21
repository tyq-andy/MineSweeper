package org.example;

import org.example.components.ConsoleUI;
import org.example.components.GameController;

import java.util.Scanner;

public class MineSweeper {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GameController controller = new GameController(new ConsoleUI());
        controller.startGame();
    }

}
