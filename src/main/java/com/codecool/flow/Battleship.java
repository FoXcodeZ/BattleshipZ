package com.codecool.flow;

import com.codecool.console.Display;
import com.codecool.console.Input;
import com.codecool.players.AiDifficulty;

import java.util.ArrayList;
import java.util.List;

public class Battleship {
    private final Display display;
    private final Input input;
    private final Game game;

    public Battleship() {
        this.display = new Display();
        this.input = new Input();
        this.game = new Game(display, input);
    }

    public void MainMenu() {
        display.clearConsole();
        boolean gameRunning = true;
        List<String> mainMenuOptions = new ArrayList<>();
        mainMenuOptions.add("New Game");
        mainMenuOptions.add("Exit");

        List<String> gameModeOptions = new ArrayList<>();
        gameModeOptions.add("Player vs Computer");
        gameModeOptions.add("Player vs Player");
        gameModeOptions.add("Back");

        List<String> gameDifficultyOptions = new ArrayList<>();
        gameDifficultyOptions.add("Easy");
        gameDifficultyOptions.add("Normal");
        gameDifficultyOptions.add("Hard");
        gameDifficultyOptions.add("Back");

        while (gameRunning) {
            display.clearConsole();
            display.showLogo();
            display.showMenu(mainMenuOptions);
            int option = input.selectMenu(mainMenuOptions.size());
            switch (option) {
                case 1 -> {
                    // Start New Game
                    boolean inGameMenu = true;
                    while (inGameMenu) {
                        display.clearConsole();
                        display.showLogo();
                        display.showMenu(gameModeOptions);
                        option = input.selectMenu(gameModeOptions.size());
                        switch (option) {
                            case 1 -> {
                                // PVE
                                display.clearConsole();
                                display.showLogo();
                                display.showMenu(gameDifficultyOptions);
                                option = input.selectMenu(gameDifficultyOptions.size());
                                switch (option) {
                                    case 1 -> {
                                        game.setDifficulty(AiDifficulty.EASY);
                                        inGameMenu = false;
                                    }
                                    case 2 -> {
                                        game.setDifficulty(AiDifficulty.NORMAL);
                                        inGameMenu = false;
                                    }
                                    case 3 -> {
                                        game.setDifficulty(AiDifficulty.HARD);
                                        inGameMenu = false;
                                    }
                                }
                                if(option < 4)
                                {
                                    game.singlePlayer();
                                }
                            }
                            case 2 -> game.multiPlayer();
                            case 3 ->
                                // Back
                                    inGameMenu = false;
                        }
                    }
                }
                case 2 ->
                    // Exit
                        gameRunning = false;
            }
        }
    }

}
