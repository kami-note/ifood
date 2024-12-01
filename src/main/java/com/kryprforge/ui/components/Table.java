package com.kryprforge.ui.components;

import java.util.List;

public class Table {
    private final List<String[]> rows;
    private final String[] headers;
    private int[] columnWidths;

    public Table(String[] headers, List<String[]> rows) {
        this.headers = headers;
        this.rows = rows;
        this.columnWidths = calculateColumnWidths();
    }

    private int[] calculateColumnWidths() {
        int[] widths = new int[headers.length];

        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();
        }

        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                widths[i] = Math.max(widths[i], row[i].length());
            }
        }

        return widths;
    }

    public void render() {
        printLine('┌');
        printRow(headers, true);
        printLine('├');

        for (String[] row : rows) {
            printRow(row, false);
        }

        printLine('└');
    }

    private void printRow(String[] row, boolean isHeader) {
        StringBuilder line = new StringBuilder("│");
        for (int i = 0; i < row.length; i++) {
            if (isHeader) {
                line.append(centerText(row[i], columnWidths[i])).append("│");
            } else {
                line.append(leftAlignText(row[i], columnWidths[i])).append("│");
            }
        }
        System.out.println(line);
    }

    private void printLine(char borderType) {
        StringBuilder line = new StringBuilder(String.valueOf(borderType));
        for (int i = 0; i < columnWidths.length; i++) {
            line.append("─".repeat(columnWidths[i]));
            if (i < columnWidths.length - 1) {
                line.append(borderType == '┌' ? "┬" : (borderType == '├' ? "┼" : "┴"));
            }
        }
        line.append(borderType == '┌' ? "┐" : (borderType == '├' ? "┤" : "┘"));
        System.out.println(line);
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text + " ".repeat(Math.max(0, width - text.length() - padding));
    }

    private String leftAlignText(String text, int width) {
        return text + " ".repeat(Math.max(0, width - text.length()));
    }
}
