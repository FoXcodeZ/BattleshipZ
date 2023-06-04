package com.codecool.players;

import com.codecool.console.Display;
import com.codecool.console.Input;
import com.codecool.ocean.Board;
import com.codecool.ocean.Square;
import com.codecool.ocean.SquareStatus;
import com.codecool.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Input input;
    private final Display display;
    public Board board;
    public List<Ship> ships;
    private final int nr;

    public Player(Input input, Display display, int nr) {
        this.nr = nr;
        this.input = input;
        this.display = display;
        this.board = new Board();
        this.ships = new ArrayList<>();
    }

    public Player(Display display, int nr) {
        this.nr = nr;
        this.display = display;
        this.input = new Input(); // TEST
        this.board = new Board();
        this.ships = new ArrayList<>();
    }

    public int getNr()
    {
        return nr;
    }

    public Board getBoard()
    {
        return board;
    }

    public List<Ship> getShips()
    {
        return ships;
    }

    public boolean isAlive() {
        for (Ship ship : ships) {
            for (Square square : ship.getShipLocation()) {
                if (square.getSquareStatus() == SquareStatus.SHIP) {
                    return true;
                }
            }
        }
        return false;
    }

    public void shoot(Player otherPlayer) {
        while (true) {
            display.printMessage("Select cords to attack: ");
            int[] cords = getCords(otherPlayer);
            int x = cords[0];
            int y = cords[1];
            if (isValidShot(x, y, otherPlayer.getBoard())) {
                if (otherPlayer.getBoard().getOcean()[y][x].getSquareStatus() == SquareStatus.SHIP) {
                    otherPlayer.getBoard().getOcean()[y][x].setSquareStatus(SquareStatus.HIT);
                    Ship hitShip = getHitShip(x, y, otherPlayer);
                    if (hitShip.hasSunk()) {
                        hitShip.setSunk(otherPlayer);
                    }
                } else if (otherPlayer.getBoard().getOcean()[y][x].getSquareStatus() == SquareStatus.EMPTY ||
                        otherPlayer.getBoard().getOcean()[y][x].getSquareStatus() == SquareStatus.SUNK_AREA) {
                    otherPlayer.getBoard().getOcean()[y][x].setSquareStatus(SquareStatus.MISSED);
                }
                break;
            }
        }
    }

    protected int[] getCords(Player otherPlayer) {
        return input.getCords();
    }

    private Ship getHitShip(int x, int y, Player otherPlayer) {
        for (Ship ship : otherPlayer.getShips()) {
            for (Square square : ship.getShipLocation()) {
                if (square.getX() == x && square.getY() == y)
                    return ship;
            }
        }
        display.printMessage("Hit not found.");
        return null;
    }

    private boolean isValidShot(int x, int y, Board board) {
        return (x >= 0 && x < board.getOcean()[0].length &&
                y >= 0 && y < board.getOcean().length) &&
                (board.getOcean()[y][x].getSquareStatus() == SquareStatus.EMPTY ||
                        board.getOcean()[y][x].getSquareStatus() == SquareStatus.SHIP ||
                        board.getOcean()[y][x].getSquareStatus() == SquareStatus.SUNK_AREA);
    }

}
