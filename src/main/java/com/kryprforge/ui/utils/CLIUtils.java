package com.kryprforge.ui.utils;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class CLIUtils {

    public static String color(String text, Ansi.Color color) {
        return Ansi.ansi().fg(color).a(text).reset().toString();
    }

    public static String bold(String text) {
        return Ansi.ansi().bold().a(text).reset().toString();
    }

    public static void clearScreen() {
        AnsiConsole.systemInstall();  // Inicializa o console para uso da Jansi
        System.out.print(Ansi.ansi().eraseScreen().cursor(0, 0));  // Limpa a tela e reposiciona o cursor
        AnsiConsole.systemUninstall();  // Desinstala o console Jansi após o uso
    }
}
