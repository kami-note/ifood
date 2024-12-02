package com.kryprforge.ui.screens;

import com.kryprforge.ui.components.Header;
import com.kryprforge.ui.components.Table;
import com.kryprforge.ui.components.InputField;
import com.kryprforge.ui.CLIUtils;
import com.kryprforge.dao.RestaurantDAO;
import com.kryprforge.models.Restaurant;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantScreen {

    private final RestaurantDAO restaurantDAO;

    public RestaurantScreen(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    public Long render() {
        // Cabeçalho
        Header header = new Header("Seleção de Restaurantes", 50);
        header.render();

        // Recuperar lista de restaurantes
        List<Restaurant> restaurants = restaurantDAO.findAll();
        if (restaurants.isEmpty()) {
            System.out.println(CLIUtils.color("Nenhum restaurante disponível no momento.", Ansi.Color.RED));
            return null;
        }

        // Exibir tabela de restaurantes
        String[] tableHeaders = {"ID", "Nome", "Categoria"};
        List<String[]> rows = restaurants.stream()
                .map(r -> new String[]{String.valueOf(r.getId()), r.getName(), String.valueOf(r.getCategory().getName())})
                .collect(Collectors.toList());
        Table table = new Table(tableHeaders, rows);
        table.render();

        // Solicitar escolha do usuário
        InputField<Long> inputField = new InputField<>(
                "Digite o ID do restaurante desejado",
                Long::parseLong,
                "Entrada inválida! Por favor, digite um número válido."
        );
        return inputField.getUserInput();
    }
}
