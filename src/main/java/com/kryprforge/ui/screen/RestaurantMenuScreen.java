package com.kryprforge.ui.screen;

import com.kryprforge.database.dao.RestaurantDAO;
import com.kryprforge.database.repository.Restaurant;
import com.kryprforge.ui.CLIUtils;
import com.kryprforge.ui.components.Header;
import com.kryprforge.ui.components.InputField;
import com.kryprforge.ui.components.Table;
import org.fusesource.jansi.AnsiConsole;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuScreen {

    private final RestaurantDAO restaurantDAO;

    public RestaurantMenuScreen(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    public Long render() {
        List<Restaurant> restaurants = restaurantDAO.findAll();

        AnsiConsole.systemInstall();
        CLIUtils.clearScreen();

        Header mainHeader = new Header("Restaurants", 50);
        mainHeader.render();

        String[] headers = {"Id", "Name", "Phone", "Category", "Pickup"};

        Table table = getTable(restaurants, headers);
        table.render();

        InputField<Long> longInput = new InputField<>(
                "Id Restaurant",
                input -> Long.parseLong(input),
                "Erro: Insira um número válido para o ID do restaurante."
        );

        return longInput.getUserInput();
    }

    private static Table getTable(List<Restaurant> restaurants, String[] headers) {
        List<String[]> tableRows = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            String[] row = {
                    restaurant.getId().toString(),
                    restaurant.getName(),
                    restaurant.getPhoneNumber(),
                    restaurant.getCategory().getName(),
                    restaurant.isHasPickupOption() ? "Yes" : "No"
            };
            tableRows.add(row);
        }

        return new Table(headers, tableRows);
    }
}
