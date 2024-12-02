package com.kryprforge.ui.screens;

import com.kryprforge.ui.components.Header;
import com.kryprforge.ui.utils.CLIUtils;
import com.kryprforge.dao.ProductDAO;
import com.kryprforge.models.Product;
import com.kryprforge.context.GlobalInfos;
import org.fusesource.jansi.Ansi;

import java.util.List;

public class ProductScreen {

    private final ProductDAO productDAO;
    private final GlobalInfos globalInfos;

    public ProductScreen(ProductDAO productDAO, GlobalInfos globalInfos) {
        this.productDAO = productDAO;
        this.globalInfos = globalInfos;
    }

    // Modificar a exibição e adicionar lógica para capturar a seleção
    public void render() {
        Header header = new Header("Seleção de Produtos", 50);
        header.render();

        List<Product> products = productDAO.findByRestaurantId(globalInfos.getSelectedRestaurantId());
        if (products.isEmpty()) {
            System.out.println(CLIUtils.color("Nenhum produto disponível neste restaurante.", Ansi.Color.RED));
            return;
        }

        while (true) {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                System.out.printf("[%d] %s - $%.2f%n", i + 1, product.getName(), product.getPrice());
            }
            System.out.println("[0] Finalizar seleção");

            try {
                int choice = Integer.parseInt(System.console().readLine());
                if (choice == 0) break;

                if (choice > 0 && choice <= products.size()) {
                    Product selectedProduct = products.get(choice - 1);
                    globalInfos.addSelectedProduct(selectedProduct.getId());
                    System.out.println(CLIUtils.color("Produto adicionado: " + selectedProduct.getName(), Ansi.Color.GREEN));
                } else {
                    System.out.println(CLIUtils.color("Escolha inválida!", Ansi.Color.RED));
                }
            } catch (NumberFormatException e) {
                System.out.println(CLIUtils.color("Por favor, insira um número válido.", Ansi.Color.RED));
            }
        }
    }
}
