package com.kryprforge.ui.screen;

import com.kryprforge.database.dao.ProductDAO;
import com.kryprforge.database.repository.Product;
import com.kryprforge.ui.CLIUtils;
import com.kryprforge.ui.components.Header;
import com.kryprforge.ui.components.InputField;
import com.kryprforge.ui.components.Table;
import org.fusesource.jansi.AnsiConsole;

import java.util.ArrayList;
import java.util.List;

public class ProductMenuScreen {

    private final ProductDAO productDAO;
    private final Long restaurantId;
    private final String header;

    public ProductMenuScreen(ProductDAO productDAO, Long restaurantId, String header) {
        this.productDAO = productDAO;
        this.restaurantId = restaurantId;
        this.header = header;
    }

    public Long render() {
        List<Product> products = productDAO.findByRestaurantId(restaurantId);

        AnsiConsole.systemInstall();
        CLIUtils.clearScreen();

        Header mainHeader = new Header("Products for Restaurant " + header, 50);
        mainHeader.render();

        String[] headers = {"Id", "Name", "Description", "Price"};

        Table table = getTable(products, headers);
        table.render();

        InputField<Long> longInput = new InputField<>(
                "Id Product",
                input -> Long.parseLong(input),
                "Erro: Insira um número válido para o ID do Produto."
        );



        return longInput.getUserInput();
    }

    private static Table getTable(List<Product> products, String[] headers) {
        List<String[]> tableRows = new ArrayList<>();

        for (Product product : products) {
            String[] row = {
                    product.getId().toString(),
                    product.getName(),
                    product.getDescription(),
                    "$" + String.format("%.2f", product.getPrice())
            };
            tableRows.add(row);
        }

        return new Table(headers, tableRows);
    }
}
