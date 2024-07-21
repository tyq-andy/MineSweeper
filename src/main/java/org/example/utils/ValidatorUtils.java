package org.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils {
    public static boolean isValidInput(String strNum) throws NumberFormatException {
        Pattern validInputPattern = Pattern.compile("^[A-Z]\\d$");
        Matcher matcher = validInputPattern.matcher(strNum);
        return matcher.matches();
    }

    public static boolean isNumeric(String strNum) throws NumberFormatException {
        try {
            int n = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validateGridSize(int gridSize) {
        if (gridSize < 2) {
            System.out.println("Minimum size of grid is 2.");
            return false;
        } else if (gridSize > 10) {
            System.out.println("Maximum size of grid is 10.");
            return false;
        }
        return true;
    }

    public static boolean validateMineCount(int mineCount, int gridSize) {
        if (mineCount <= 0.35 * gridSize * gridSize) {
            return true;
        } else {
            System.out.println("Maximum number is 35% of total squares.");
            return false;
        }
    }
}
