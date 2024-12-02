package com.kryprforge;

import com.kryprforge.context.GlobalInfos;
import com.kryprforge.dao.*;
import com.kryprforge.service.AddressService;
import com.kryprforge.service.OrderService;
import com.kryprforge.ui.ScreenManager;

public class Main {
    public static void main(String[] args) {
        GlobalInfos globalInfos = new GlobalInfos();
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
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
        OrderProductAccompanimentDAO orderProductAccompanimentDAO = new OrderProductAccompanimentDAO();

        OrderService orderService = new OrderService(globalInfos, orderItemDAO, productDAO, customerOrderDAO, orderProductAccompanimentDAO, accompanimentDAO);

        ScreenManager screenManager = new ScreenManager(
                restaurantDAO,
                productDAO,
                addressDAO,
                paymentMethodDAO,
                orderItemDAO,
                accompanimentDAO,
                addressService,
                orderService,
                globalInfos
        );
        screenManager.start();
    }
}
