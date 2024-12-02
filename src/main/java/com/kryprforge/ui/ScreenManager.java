package com.kryprforge.ui;

import com.kryprforge.context.GlobalInfos;
import com.kryprforge.dao.*;
import com.kryprforge.service.AddressService;
import com.kryprforge.ui.screens.*;

import java.util.Scanner;

public class ScreenManager {

    private final GlobalInfos globalInfos;
    private final RestaurantDAO restaurantDAO;
    private final ProductDAO productDAO;
    private final AddressDAO addressDAO;
    private final PaymentMethodDAO paymentMethodDAO;
    private final OrderItemDAO orderItemDAO;
    private final AccompanimentDAO accompanimentDAO;

    private final AddressService addressService;

    private boolean running = true;
    private final Scanner scanner;

    public ScreenManager(RestaurantDAO restaurantDAO, ProductDAO productDAO,
                         AddressDAO addressDAO, PaymentMethodDAO paymentMethodDAO,
                         OrderItemDAO orderItemDAO, AccompanimentDAO accompanimentDAO, AddressService addressService) {
        this.accompanimentDAO = accompanimentDAO;
        this.addressService = addressService;
        this.globalInfos = new GlobalInfos();
        this.restaurantDAO = restaurantDAO;
        this.productDAO = productDAO;
        this.addressDAO = addressDAO;
        this.paymentMethodDAO = paymentMethodDAO;
        this.orderItemDAO = orderItemDAO;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (running) {
            displayMainMenu();
        }
    }

    private void displayMainMenu() {
        System.out.println("Bem-vindo ao iFood!");
        System.out.println("1. Fazer novo pedido");
        System.out.println("2. Meus pedidos anteriores");
        System.out.println("3. Sair");

        int choice = getUserChoice();
        switch (choice) {
            case 1 -> handleNewOrder();
            case 2 -> displayPastOrders();
            case 3 -> running = false;
            default -> System.out.println("Opção inválida!");
        }
    }

    private void handleNewOrder() {
        RestaurantScreen restaurantScreen = new RestaurantScreen(restaurantDAO);
        Long restaurantId = restaurantScreen.render();
        if (restaurantId == null) return;

        globalInfos.setSelectedRestaurantId(restaurantId);

        ProductScreen productScreen = new ProductScreen(productDAO, globalInfos);
        productScreen.render();

        AccompanimentScreen accompanimentScreen = new AccompanimentScreen(accompanimentDAO, globalInfos);
        accompanimentScreen.render();

        OrderSummaryScreen orderSummaryScreen = new OrderSummaryScreen(productDAO, accompanimentDAO, globalInfos);
        orderSummaryScreen.render();

        AddressScreen addressScreen = new AddressScreen(addressService, addressDAO, globalInfos);
        addressScreen.render();

        PaymentMethodScreen paymentMethodScreen = new PaymentMethodScreen(paymentMethodDAO, globalInfos);
        paymentMethodScreen.render();

        finalizeOrder();
    }

    private void displayPastOrders() {
       /* PastOrdersScreen pastOrdersScreen = new PastOrdersScreen(orderItemDAO);
        pastOrdersScreen.render();*/
    }

    private void finalizeOrder() {/*
        OrderService orderService = new OrderService(orderItemDAO);
        orderService.createOrder(globalInfos);*/
        globalInfos.resetOrder();
        System.out.println("Pedido finalizado com sucesso!");
    }

    private int getUserChoice() {
        try {
            System.out.print("Escolha uma opção: ");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
