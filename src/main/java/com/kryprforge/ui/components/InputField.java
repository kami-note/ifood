package com.kryprforge.ui.components;

import com.kryprforge.ui.utils.CLIUtils;
import org.fusesource.jansi.Ansi;

import java.util.Scanner;
import java.util.function.Function;

public class InputField<T> {
    private final String label;
    private final Function<String, T> parser;
    private final String errorMessage;

    public InputField(String label, Function<String, T> parser, String errorMessage) {
        this.label = label;
        this.parser = parser;
        this.errorMessage = errorMessage;
    }

    public T getUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(CLIUtils.color(label + ": ", Ansi.Color.GREEN));
                String input = scanner.nextLine();
                return parser.apply(input);
            } catch (Exception e) {
                System.out.println(CLIUtils.color(errorMessage, Ansi.Color.RED));
            }
        }
    }
}
