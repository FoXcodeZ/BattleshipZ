package com.codecool.players;

import com.codecool.console.Display;
import com.codecool.ocean.Square;
import com.codecool.ocean.SquareStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayerNormal extends ComputerPlayer {
    public List<Square> PreviousHits = new ArrayList<>();

    public ComputerPlayerNormal(Display display) {
        super(display);
    }


    protected int[] getCords(Player otherPlayer) {
        Random random = new Random();
        if (PreviousHits.size() > 0 && PreviousHits.get(0).getSquareStatus() == SquareStatus.SUNK) {
            PreviousHits.clear();
        }

        if (PreviousHits != null && PreviousHits.size() == 1) {
            return getCordsWithOnePreviousHit(otherPlayer, random);
        } else if (PreviousHits != null && PreviousHits.size() > 1) {
            return getCordsWithMultiplePreviousHits(otherPlayer, random);
        } else {
            return getRandomCordsWithNoPreviousHits(otherPlayer, random);
        }
    }

    private int[] getCordsWithOnePreviousHit(Player otherPlayer, Random random) {
        int x = PreviousHits.get(0).getX();
        int y = PreviousHits.get(0).getY();

        while (true) {
            int randomOption = random.nextInt(4);
            switch (randomOption) {
                case 0 -> {
                    if (isValidMove(otherPlayer, x + 1, y)) {
                        return makeMove(otherPlayer, x + 1, y);
                    }
                }
                case 1 -> {
                    if (isValidMove(otherPlayer, x - 1, y)) {
                        return makeMove(otherPlayer, x - 1, y);
                    }
                }
                case 2 -> {
                    if (isValidMove(otherPlayer, x, y + 1)) {
                        return makeMove(otherPlayer, x, y + 1);
                    }
                }
                case 3 -> {
                    if (isValidMove(otherPlayer, x, y - 1)) {
                        return makeMove(otherPlayer, x, y - 1);
                    }
                }
            }
        }
    }

    private int[] getCordsWithMultiplePreviousHits(Player otherPlayer, Random random) {
        int x, y;

        if (PreviousHits.get(0).getX() == PreviousHits.get(1).getX()) {
            PreviousHits.sort((s1, s2) -> Integer.compare(s2.getY(), s1.getY()));
            x = PreviousHits.get(PreviousHits.size() - 1).getX();
            y = PreviousHits.get(PreviousHits.size() - 1).getY();

            if (isValidMove(otherPlayer, x, y - 1)) {
                return makeMove(otherPlayer, x, y - 1);
            } else if (isValidMove(otherPlayer, x, y + 1)) {
                return makeMove(otherPlayer, x, y + 1);
            }
        } else {
            PreviousHits.sort((s1, s2) -> Integer.compare(s2.getX(), s1.getX()));
            x = PreviousHits.get(PreviousHits.size() - 1).getX();
            y = PreviousHits.get(PreviousHits.size() - 1).getY();

            if (isValidMove(otherPlayer, x - 1, y)) {
                return makeMove(otherPlayer, x - 1, y);
            } else if (isValidMove(otherPlayer, x + 1, y)) {
                return makeMove(otherPlayer, x + 1, y);
            }
        }

        return null; // @TODO Handle invalid moves
    }

    private int[] getRandomCordsWithNoPreviousHits(Player otherPlayer, Random random) {
        int[] randomCords = getRandomCords(otherPlayer, random);
        int x = randomCords[0];
        int y = randomCords[1];

        if (isValidMove(otherPlayer, x, y)) {
            return makeMove(otherPlayer, x, y);
        }

        return null; // @TODO Handle invalid moves
    }

    private boolean isValidMove(Player otherPlayer, int x, int y) {
        return x >= 0 && x < otherPlayer.getBoard().getOcean().length &&
                y >= 0 && y < otherPlayer.getBoard().getOcean().length &&
                otherPlayer.getBoard().getOcean()[y][x].getSquareStatus() != SquareStatus.MISSED &&
                otherPlayer.getBoard().getOcean()[y][x].getSquareStatus() != SquareStatus.SUNK_AREA;
    }

    private int[] makeMove(Player otherPlayer, int x, int y) {
        if (otherPlayer.getBoard().getOcean()[y][x].getSquareStatus() == SquareStatus.SHIP) {
            PreviousHits.add(otherPlayer.getBoard().getOcean()[y][x]);
        }
        return new int[]{x, y};
    }


    protected int[] getRandomCords(Player otherPlayer, Random random) {
        int x;
        int y;
        x = random.nextInt(otherPlayer.getBoard().getOcean().length);
        y = random.nextInt(otherPlayer.getBoard().getOcean()[0].length);
        return new int[] {x, y};
    }
}