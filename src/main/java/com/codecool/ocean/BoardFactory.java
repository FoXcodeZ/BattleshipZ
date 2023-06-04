package com.codecool.ocean;

import com.codecool.console.Display;
import com.codecool.console.Input;
import com.codecool.players.Player;
import com.codecool.ships.Ship;
import com.codecool.ships.ShipDirection;
import com.codecool.ships.ShipType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BoardFactory {
    private final Display display;
    private final Input input;
    private ShipType[] shipTypes;
    public BoardFactory(Display display, Input input) {
        this.display = display;
        this.input = input;
        this.shipTypes = ShipType.values();
        List<ShipType> shipTypeList = Arrays.asList(this.shipTypes);
        shipTypeList.sort((shipType1, shipType2) -> Integer.compare(shipType2.ordinal(), shipType1.ordinal()));
        this.shipTypes = shipTypeList.toArray(new ShipType[0]);
    }

    public void manualPlacement(Player player) {
        for (ShipType shipType : shipTypes) {
            while (true) {
                display.clearConsole();
                display.showLogo();
                display.printMessage("Player: " + Integer.toString(player.getNr()));
                display.printBoard(player.getBoard().getOcean(), false);
                List<String> options = Arrays.stream(ShipDirection.values())
                        .map(ShipDirection::name)
                        .toList();
                display.printMessage("Select cord to spawn a new ship: ");
                int[] cords = input.getCords();
                display.showMenuWithoutBackOption(options);
                ShipDirection direction = input.getDirection(options.size());
                if (player.getBoard().isPlacementOk(cords[0], cords[1], direction, shipType)) {
                    player.getShips().add(new Ship(cords[0], cords[1], direction, shipType, player.getBoard()));
                    break;
                }
            }
        }
    }

    public void randomPlacement(Player player) {
        Random random = new Random();
        for (ShipType shipType : shipTypes) {
            while (true) {
                int x = random.nextInt(player.getBoard().getOcean().length);
                int y = random.nextInt(player.getBoard().getOcean()[0].length);
                ShipDirection[] directions = ShipDirection.values();
                ShipDirection direction = directions[random.nextInt(directions.length)];
                if (player.getBoard().isPlacementOk(x, y, direction, shipType)) {
                    player.getShips().add(new Ship(x, y, direction, shipType, player.getBoard()));
                    break;
                }
            }
        }
    }

}