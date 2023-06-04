package com.codecool.ships;

import com.codecool.ocean.Board;
import com.codecool.ocean.Square;
import com.codecool.ocean.SquareStatus;
import com.codecool.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final List<Square> shipLocation = new ArrayList<>();
    private final ShipType shipType;
    public Ship(ShipType shipType) {
        this.shipType = shipType;
        for (int i = 0; i < shipType.getLength(); i++) {
            Square shipSquare = new Square(SquareStatus.SHIP);
            shipLocation.add(shipSquare);
        }
    }

    public void setSunk(Player otherPlayer) {
        for (Square square : shipLocation) {
            square.setSquareStatus(SquareStatus.SUNK);
            setNearbySquare(square.getX(), square.getY(), otherPlayer.getBoard());
        }
    }

    public Ship(int x, int y, ShipDirection direction, ShipType shipType, Board board) {
        this.shipType = shipType;
        for (int i = 0; i < shipType.getLength(); i++) {
            switch (direction) {
                case UP -> {
                    shipLocation.add(board.getOcean()[y - i][x]);
                    board.getOcean()[y - i][x].setSquareStatus(SquareStatus.SHIP);
                }
                case DOWN -> {
                    shipLocation.add(board.getOcean()[y + i][x]);
                    board.getOcean()[y + i][x].setSquareStatus(SquareStatus.SHIP);
                }
                case LEFT -> {
                    shipLocation.add(board.getOcean()[y][x - i]);
                    board.getOcean()[y][x - i].setSquareStatus(SquareStatus.SHIP);
                }
                case RIGHT -> {
                    shipLocation.add(board.getOcean()[y][x + i]);
                    board.getOcean()[y][x + i].setSquareStatus(SquareStatus.SHIP);
                }
            }
        }
    }

    private void setNearbySquare(int x, int y, Board board) {
        if (y + 1 < board.getOcean().length &&
                board.getOcean()[y + 1][x].getSquareStatus() == SquareStatus.EMPTY)
            board.getOcean()[y + 1][x].setSquareStatus(SquareStatus.SUNK_AREA);
        if (y - 1 >= 0 &&
                board.getOcean()[y - 1][x].getSquareStatus() == SquareStatus.EMPTY)
            board.getOcean()[y - 1][x].setSquareStatus(SquareStatus.SUNK_AREA);
        if (x + 1 < board.getOcean()[0].length &&
                board.getOcean()[y][x + 1].getSquareStatus() == SquareStatus.EMPTY)
            board.getOcean()[y][x + 1].setSquareStatus(SquareStatus.SUNK_AREA);
        if (x - 1 >= 0 &&
                board.getOcean()[y][x - 1].getSquareStatus() == SquareStatus.EMPTY)
            board.getOcean()[y][x - 1].setSquareStatus(SquareStatus.SUNK_AREA);
    }

    public List<Square> getShipLocation() {
        return shipLocation;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public boolean hasSunk() {
        for (Square square : shipLocation) {
            if (square.getSquareStatus() == SquareStatus.SHIP) {
                return false;
            }
        }
        return true;
    }
}