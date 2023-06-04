package com.codecool.console;

import com.codecool.ocean.Square;
import com.codecool.ocean.SquareStatus;
import com.codecool.utility.AsciiArt;
import com.codecool.utility.TextColor;
import com.codecool.utility.TextStyle;

import java.util.List;

public class Display {
    private final AsciiArt asciiArt = new AsciiArt();
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void showMenu(List<String> options) {
        System.out.println();
        for (int i = 0; i < options.size(); i++) {
            System.out.println(TextColor.BRIGHT_GREEN + "[" + (i + 1) + "] " + options.get(i) + TextStyle.RESET);
        }
    }

    public void showLogo()
    {
        System.out.print(TextColor.YELLOW + asciiArt.getShipLogo() + TextStyle.RESET);
    }

    public void showMenuWithoutBackOption(List<String> options) {
        System.out.println();
        for (int i = 0; i < options.size(); i++) {
            System.out.println(TextColor.BRIGHT_GREEN + "[" + (i + 1) + "] " + options.get(i) + TextStyle.RESET);
        }
    }

    public void printBoard(Square[][] ocean, boolean shootingPhase) {
        printNums(ocean.length);
        System.out.println();
        for (int y = 0; y < ocean.length; y++) {
            System.out.print(numberToAlpha(y, false) + "  ");
            for (int x = 0; x < ocean[y].length; x++) {
                isShipInShootingPhase(ocean[y][x], shootingPhase);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void isShipInShootingPhase(Square square, boolean shootingPhase) {
        if (square.getSquareStatus() == SquareStatus.SHIP && shootingPhase) {
            System.out.print(SquareStatus.EMPTY.getSign());
        } else {
            System.out.print(square.getCharacter());
        }
    }

    private String numberToAlpha(long number, boolean isLower) {
        StringBuilder returnVal = new StringBuilder();
        char c = isLower ? 'a' : 'A';
        while (number >= 0) {
            returnVal.insert(0, (char) (c + number % 26));
            number /= 26;
            number--;
        }
        return returnVal.toString();
    }

    private void printNums(int BoardSize) {
        for (int i = 1; i <= BoardSize; i++) {
            if (i == 1) {
                System.out.print("    ");
            }

            if (i < 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
        }
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}