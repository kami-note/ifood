package com.kryprforge;

import com.kryprforge.database.dao.*;
import com.kryprforge.database.service.AddressService;
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

        ScreenManager screenManager = new ScreenManager(addressDAO, paymentMethodDAO, promotionDAO, categoryDAO, restaurantDAO, productDAO, addressService);
        screenManager.run();
    }
}
