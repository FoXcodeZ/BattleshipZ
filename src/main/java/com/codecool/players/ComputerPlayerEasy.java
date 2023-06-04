package com.codecool.players;
import com.codecool.console.Display;

import java.util.Random;

public class ComputerPlayerEasy extends ComputerPlayer {
    public ComputerPlayerEasy(Display display) {
        super(display);
    }

    protected int[] getCords(Player otherPlayer) {
        Random random = new Random();
        return new int[] {random.nextInt(board.getOcean().length),
                random.nextInt(board.getOcean()[0].length)};
    }

}