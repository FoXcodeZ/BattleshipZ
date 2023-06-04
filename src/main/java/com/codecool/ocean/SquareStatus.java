package com.codecool.ocean;

import com.codecool.utility.BackgroundColor;
import com.codecool.utility.TextColor;
import com.codecool.utility.TextStyle;

public enum SquareStatus {
    EMPTY(BackgroundColor.BRIGHT_CYAN + " ~ " + TextStyle.RESET),
    SHIP(BackgroundColor.BRIGHT_YELLOW + TextColor.BRIGHT_BLACK + " # " + TextStyle.RESET),
    HIT(BackgroundColor.BRIGHT_RED + TextColor.BRIGHT_BLACK + " X " + TextStyle.RESET),
    MISSED(BackgroundColor.BRIGHT_GREEN + TextColor.BRIGHT_BLACK + " O " + TextStyle.RESET),
    SUNK(BackgroundColor.RED + TextColor.BRIGHT_BLACK + " % " + TextStyle.RESET),
    SUNK_AREA(BackgroundColor.BLUE + TextColor.BLACK + " ≈ " + TextStyle.RESET);

    private final String sign;

    SquareStatus(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
