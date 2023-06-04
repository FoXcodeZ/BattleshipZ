package com.codecool.players;
import com.codecool.console.Display;

import java.util.Random;

public class ComputerPlayerHard extends ComputerPlayer{
    public ComputerPlayerHard(Display display) {
        super(display);
    }

    protected int[] getRandomCords(Player otherPlayer, Random random) {
        int x, y;
        do {
            x = random.nextInt(otherPlayer.getBoard().getOcean().length);
            y = random.nextInt(otherPlayer.getBoard().getOcean()[0].length);
        } while (x % 2 == y % 2);

        return new int[] {x, y};
    }

}
