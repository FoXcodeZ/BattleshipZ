package com.codecool.ocean;
import com.codecool.ships.ShipDirection;
import com.codecool.ships.ShipType;

public class Board {
    private final Square[][] ocean;
    public Board() {
        ocean = new Square[10][10];

        for (int y = 0; y < ocean.length; y++) {
            for (int x = 0; x < ocean[y].length; x++) {
                ocean[y][x] = new Square(x, y);
            }
        }
    }

    public Square[][] getOcean()
    {
        return ocean;
    }

    public boolean isPlacementOk(int x, int y, ShipDirection direction, ShipType shipType) {
        for (int i = 0; i < shipType.getLength(); i++) {
            switch (direction) {
                case UP -> {
                    if (isOutOfRange(x, y - i) || isNotEmpty(x, y - i) || !isNotTouching(x, y - i))
                        return false;
                }
                case DOWN -> {
                    if (isOutOfRange(x, y + i) || isNotEmpty(x, y + i) || !isNotTouching(x, y + i))
                        return false;
                }
                case LEFT -> {
                    if (isOutOfRange(x - i, y) || isNotEmpty(x - i, y) || !isNotTouching(x - i, y))
                        return false;
                }
                case RIGHT -> {
                    if (isOutOfRange(x + i, y) || isNotEmpty(x + i, y) || !isNotTouching(x + i, y))
                        return false;
                }
                default -> {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNotTouching(int x, int y) {
        return (isOutOfRange(x + 1, y) || !isOutOfRange(x + 1, y) && !isNotEmpty(x + 1, y)) &&
                (isOutOfRange(x - 1, y) || !isOutOfRange(x - 1, y) && !isNotEmpty(x - 1, y)) &&
                (isOutOfRange(x, y + 1) || !isOutOfRange(x, y + 1) && !isNotEmpty(x, y + 1)) &&
                (isOutOfRange(x, y - 1) || !isOutOfRange(x, y - 1) && !isNotEmpty(x, y - 1));
    }

    private boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= ocean[0].length || y < 0 || y >= ocean.length;
    }

    private boolean isNotEmpty(int x, int y) {
        return ocean[y][x].getSquareStatus() != SquareStatus.EMPTY;
    }

}