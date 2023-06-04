package com.codecool.console;

import com.codecool.ships.ShipDirection;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {
    private final Scanner scanner = new Scanner(System.in);
    public Input() {
    }

    public int[] getCords() {
        int x;
        int y;
        String shipStartingCords;

        do {
            shipStartingCords = scanner.nextLine();
        }while (!isValidCordFormat(shipStartingCords));

        y = convertLetterToNum(shipStartingCords.charAt(0));
        x = Integer.parseInt(shipStartingCords.substring(1)) - 1;

        return new int[] {x, y};
    }

    private int convertLetterToNum(char row) {
        return Character.toLowerCase(row) - 'a';
    }

    private boolean isValidCordFormat(String cords) {
        return Pattern.matches("^[a-zA-Z]{1}\\d+$", cords);
    }

    public int selectMenu(int optionNum) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= 0 && input < optionNum + 1)
                    return input;
            } catch (Exception ignored) {
            }
        }
    }

    public int getDirectionSelect(int optionNum) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input > 0 && input <= optionNum)
                    return input;
            } catch (Exception ignored) {
            }
        }
    }

    public ShipDirection getDirection(int optionNum) {
        int option = getDirectionSelect(optionNum);

        return switch (option) {
            case 2 -> ShipDirection.DOWN;
            case 3 -> ShipDirection.LEFT;
            case 4 -> ShipDirection.RIGHT;
            default -> ShipDirection.UP;
        };
    }

    public void pressEnterToContinue() {
        scanner.nextLine();
    }

}
