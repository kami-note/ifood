package com.kryprforge;

import com.kryprforge.dao.*;
import com.kryprforge.service.AddressService;
import com.kryprforge.ui.ScreenManager;

public class Main {
    public static void main(String[] args) {
        AddressDAO addressDAO = new AddressDAO();
        PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
        PromotionDAO promotionDAO = new PromotionDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        ProductDAO productDAO = new ProductDAO();
        AddressService addressService = new AddressService(addressDAO);
        AccompanimentDAO accompanimentDAO = new AccompanimentDAO();
        ProductAccompanimentDAO productAccompanimentDAO = new ProductAccompanimentDAO();
        OrderItemDAO orderItemDAO = new OrderItemDAO();

        ScreenManager screenManager = new ScreenManager(
                restaurantDAO,
                productDAO,
                addressDAO,
                paymentMethodDAO,
                orderItemDAO,
                accompanimentDAO,
                addressService
        );
        screenManager.start();
    }
}
