package com.kryprforge.ui.screens;

import com.kryprforge.ui.components.Header;
import com.kryprforge.ui.utils.CLIUtils;
import com.kryprforge.dao.AccompanimentDAO;
import com.kryprforge.models.Accompaniment;
import com.kryprforge.context.GlobalInfos;
import org.fusesource.jansi.Ansi;

import java.util.List;

public class AccompanimentScreen {

    private final AccompanimentDAO accompanimentDAO;
    private final GlobalInfos globalInfos;

    public AccompanimentScreen(AccompanimentDAO accompanimentDAO, GlobalInfos globalInfos) {
        this.accompanimentDAO = accompanimentDAO;
        this.globalInfos = globalInfos;
    }

    public void render() {
        Header header = new Header("Acompanhamentos", 50);
        header.render();

        List<Accompaniment> accompaniments = accompanimentDAO.findAll();

        if (accompaniments.isEmpty()) {
            System.out.println(CLIUtils.color("Nenhum acompanhamento disponível.", Ansi.Color.RED));
            return;
        }

        boolean adding = true;
        while (adding) {
            for (int i = 0; i < accompaniments.size(); i++) {
                Accompaniment accompaniment = accompaniments.get(i);
                System.out.printf("[%d] %s - $%.2f%n", i + 1, accompaniment.getName(), accompaniment.getPrice());
            }
            System.out.println("[0] Finalizar seleção de acompanhamentos");

            System.out.print(CLIUtils.color("Escolha um acompanhamento para detalhes ou [0] para finalizar: ", Ansi.Color.GREEN));
            try {
                int choice = Integer.parseInt(System.console().readLine());

                if (choice == 0) {
                    adding = false;
                } else if (choice > 0 && choice <= accompaniments.size()) {
                    Accompaniment selectedAccompaniment = accompaniments.get(choice - 1);
                    globalInfos.addAccompaniment(selectedAccompaniment.getId());
                    System.out.println(CLIUtils.color("Acompanhamento adicionado: " + selectedAccompaniment.getName(), Ansi.Color.GREEN));
                } else {
                    System.out.println(CLIUtils.color("Escolha inválida! Tente novamente.", Ansi.Color.RED));
                }
            } catch (NumberFormatException e) {
                System.out.println(CLIUtils.color("Por favor, insira um número válido.", Ansi.Color.RED));
            }
        }
    }
}
