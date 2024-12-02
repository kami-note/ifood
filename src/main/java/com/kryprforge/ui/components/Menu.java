package com.kryprforge.ui.components;

import com.kryprforge.ui.utils.CLIUtils;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final List<String> options;

    public Menu(List<String> options) {
        this.options = options;
    }

    public void render() {
        for (int i = 0; i < options.size(); i++) {
            System.out.println(CLIUtils.color("[" + (i + 1) + "] ", Ansi.Color.YELLOW) + options.get(i));
        }
    }

    public int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEscolha uma opção: ");
        return scanner.nextInt();
    }
}
