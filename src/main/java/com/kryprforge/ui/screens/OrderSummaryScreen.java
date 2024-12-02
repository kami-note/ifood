package com.kryprforge.ui.screens;

import com.kryprforge.dao.AccompanimentDAO;
import com.kryprforge.models.Accompaniment;
import com.kryprforge.ui.components.Header;
import com.kryprforge.ui.utils.CLIUtils;
import com.kryprforge.dao.ProductDAO;
import com.kryprforge.models.Product;
import com.kryprforge.context.GlobalInfos;
import com.kryprforge.ui.components.InputField;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.stream.Collectors;

public class OrderSummaryScreen {

    private final ProductDAO productDAO;
    private final AccompanimentDAO accompanimentDAO;
    private final GlobalInfos globalInfos;

    public OrderSummaryScreen(ProductDAO productDAO, AccompanimentDAO accompanimentDAO, GlobalInfos globalInfos) {
        this.productDAO = productDAO;
        this.accompanimentDAO = accompanimentDAO;
        this.globalInfos = globalInfos;
    }

    public boolean render() {
        // Cabeçalho
        Header header = new Header("Resumo do Pedido", 50);
        header.render();

        // Recuperar IDs dos produtos selecionados
        List<Long> selectedProductIds = globalInfos.getSelectedProducts();

        if (selectedProductIds.isEmpty()) {
            System.out.println(CLIUtils.color("Nenhum produto foi adicionado ao pedido.", Ansi.Color.RED));
            return false;
        }

        // Obter detalhes dos produtos selecionados
        List<Product> selectedProducts = selectedProductIds.stream()
                .map(productDAO::findById)
                .collect(Collectors.toList());

        // Recuperar os IDs de adicionais e seus detalhes
        List<Long> selectedAccompanimentIds = globalInfos.getSelectedAccompaniments();
        List<Accompaniment> selectedAccompaniments = selectedAccompanimentIds.stream()
                .map(accompanimentDAO::findById)
                .collect(Collectors.toList());

        // Exibir os produtos selecionados e calcular o total
        double totalPrice = 0;
        System.out.println(CLIUtils.color("Produtos selecionados:", Ansi.Color.CYAN));
        for (Product product : selectedProducts) {
            System.out.printf("- %s ($%.2f)\n", product.getName(), product.getPrice());
            totalPrice += product.getPrice();
        }

        // Exibir os adicionais e calcular o total
        if (!selectedAccompaniments.isEmpty()) {
            System.out.println(CLIUtils.color("\nAdicionais:", Ansi.Color.YELLOW));
            for (Accompaniment accompaniment : selectedAccompaniments) {
                System.out.printf("- %s ($%.2f)\n", accompaniment.getName(), accompaniment.getPrice());
                totalPrice += accompaniment.getPrice();
            }
        }

        // Exibir o total
        System.out.println(CLIUtils.color("\nTotal do Pedido: $" + String.format("%.2f", totalPrice), Ansi.Color.GREEN));

        // Menu de opções
        System.out.println("\n[1] Confirmar Pedido");
        System.out.println("[2] Cancelar Pedido");

        InputField<Integer> menuField = new InputField<>(
                "Escolha uma opção",
                Integer::parseInt,
                "Entrada inválida! Por favor, insira um número."
        );

        boolean inSummary = true;
        while (inSummary) {
            int choice = menuField.getUserInput();
            switch (choice) {
                case 1 -> {
                    System.out.println(CLIUtils.color("Pedido confirmado! Obrigado por comprar conosco.", Ansi.Color.GREEN));
                    inSummary = false;
                    return true;
                }
                case 2 -> {
                    System.out.println(CLIUtils.color("Pedido cancelado. Todos os produtos e adicionais foram removidos.", Ansi.Color.RED));
                    inSummary = false;
                    return false;
                }
                case 0 -> inSummary = false;
                default -> System.out.println(CLIUtils.color("Escolha inválida!", Ansi.Color.RED));
            }
        }
        return false;
    }
}
