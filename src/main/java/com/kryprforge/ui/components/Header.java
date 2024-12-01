package com.kryprforge.ui.components;

import com.kryprforge.ui.CLIUtils;
import org.fusesource.jansi.Ansi;

public class Header {
    private final String title;
    private final int width;

    public Header(String title, int width) {
        this.title = title;
        this.width = width;
    }

    public void render() {
        String topBorder = "┌" + "─".repeat(width - 2) + "┐";
        String bottomBorder = "└" + "─".repeat(width - 2) + "┘";
        String titleLine = "│" + centerText(title, width - 2) + "│";

        System.out.println(CLIUtils.color(topBorder, Ansi.Color.BLUE));
        System.out.println(CLIUtils.color(titleLine, Ansi.Color.BLUE));
        System.out.println(CLIUtils.color(bottomBorder, Ansi.Color.BLUE));
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }
}
