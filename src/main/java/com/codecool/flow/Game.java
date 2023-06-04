package com.codecool.flow;

import com.codecool.console.Display;
import com.codecool.console.Input;
import com.codecool.ocean.BoardFactory;
import com.codecool.players.*;
import com.codecool.utility.TextColor;
import com.codecool.utility.TextStyle;

import java.util.InputMismatchException;

public class Game {
    private AiDifficulty difficulty;
    private final Display display;
    private final Input input;
    private final BoardFactory boardFactory;
    public Game(Display display, Input input) {
        this.display = display;
        this.input = input;
        this.boardFactory = new BoardFactory(display, input);
    }

    public void multiPlayer() {
        Player player1 = new Player(input, display, 1);
        Player player2 = new Player(input, display, 2);
        boardFactory.manualPlacement(player1);
        boardFactory.manualPlacement(player2);
        while (true) {
            playerShoot(player1, player2, 1);
            if (isAlive(player2, 1))
                break;
            playerShoot(player2, player1, 2);
            if (isAlive(player1, 2))
                break;
        }
    }

    public void singlePlayer() {
        Player player1 = new Player(input, display, 1);
        ComputerPlayer player2 = switch (difficulty) {
            case EASY -> new ComputerPlayerEasy(display);
            case NORMAL -> new ComputerPlayerNormal(display);
            case HARD -> new ComputerPlayerHard(display);
            default -> throw new InputMismatchException();
        };

        boardFactory.manualPlacement(player1);
        boardFactory.randomPlacement(player2);

        while (true) {
            playerShoot(player1, player2, 1);
            if (!player2.isAlive()) {
                pressEnterMessages("You won! :)" + System.lineSeparator() + "Press Enter To Continue!");
                break;
            }
            player2.shoot(player1);
            if (!player1.isAlive()) {
                pressEnterMessages("You lost :(" + System.lineSeparator() + "Press Enter To Continue!");
                break;
            }
        }
    }

    private void playerShoot(Player player1, Player player2, int currentPlayerNum) {
        displayBoards(player1, player2, TextColor.BRIGHT_MAGENTA + "Turn: Player " + currentPlayerNum + TextStyle.RESET);
        player1.shoot(player2);
    }

    private boolean isAlive(Player player, int currentPlayerNum) {
        if (!player.isAlive()) {
            pressEnterMessages("Player " + currentPlayerNum + " won!" + System.lineSeparator() + "Press Enter To Continue!");
            return true;
        }
        pressEnterMessages("Press Enter to continue!");
        return false;
    }

    private void pressEnterMessages(String message) {
        display.clearConsole();
        display.printMessage(message);
        input.pressEnterToContinue();
    }

    private void displayBoards(Player player2, Player player1, String message) {
        display.clearConsole();
        display.printMessage(TextColor.BRIGHT_GREEN + "Your Board" + TextStyle.RESET);
        display.printBoard(player2.getBoard().getOcean(), false);
        display.printMessage(TextColor.BRIGHT_RED + "Enemy Board" + TextStyle.RESET);
        display.printBoard(player1.getBoard().getOcean(), true);
        display.printMessage(message);
    }

    public void setDifficulty(AiDifficulty difficulty) {
        this.difficulty = difficulty;
    }

}