package com.codecool.ocean;

public class Square {
    private int x;
    private int y;
    private SquareStatus squareStatus;
    public Square(int x, int y) {
        squareStatus = SquareStatus.EMPTY;
        this.x = x;
        this.y = y;
    }

    public Square(SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
    }

    public Square(int x, int y, SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
        this.x = x;
        this.y = y;
    }

    public Square() {
        squareStatus = SquareStatus.EMPTY;
    }

    public String getCharacter() {
        return squareStatus.getSign();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    public void setSquareStatus(SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


}