package com.kryprforge.ui;

import com.kryprforge.database.dao.*;
import com.kryprforge.database.service.AddressService;
import com.kryprforge.database.service.GlobalInfos;
import com.kryprforge.ui.screen.ProductMenuScreen;
import com.kryprforge.ui.screen.RestaurantMenuScreen;


public class ScreenManager {

    GlobalInfos globalInfos = new GlobalInfos();

    private final AddressDAO addressDAO;
    private final PaymentMethodDAO paymentMethodDAO;
    private final PromotionDAO promotionDAO;
    private final CategoryDAO categoryDAO;
    private final RestaurantDAO restaurantDAO;
    private final ProductDAO productDAO;
    private final AddressService addressService;

    private int screenID = 0;
    private Long chose = 0L;
    private boolean running = true;

    public ScreenManager(AddressDAO addressDAO,
                         PaymentMethodDAO paymentMethodDAO,
                         PromotionDAO promotionDAO,
                         CategoryDAO categoryDAO,
                         RestaurantDAO restaurantDAO,
                         ProductDAO productDAO,
                         AddressService addressService) {
        this.addressDAO = addressDAO;
        this.paymentMethodDAO = paymentMethodDAO;
        this.promotionDAO = promotionDAO;
        this.categoryDAO = categoryDAO;
        this.restaurantDAO = restaurantDAO;
        this.productDAO = productDAO;
        this.addressService = addressService;
    }

    public void run() {
        while (running) {
            render();
            if(getChose() == 0){
                running = false;
            }
        }
    }

    public void render() {
        switch (screenID) {
            case 0:
                renderRestaurantMenuScreen();
                setScreenID(1);
                break;
            case 1:
                renderProductMenuScreen();
                setScreenID(0);
                break;
            default:
                System.out.println("Tela desconhecida!");
                running = false;
        }
    }

    private void renderRestaurantMenuScreen() {
        RestaurantMenuScreen restaurantMenuScreen = new RestaurantMenuScreen(restaurantDAO);
        setChose(restaurantMenuScreen.render());
    }

    private void renderProductMenuScreen() {
        ProductMenuScreen productMenuScreen = new ProductMenuScreen(productDAO, chose, restaurantDAO.findById(chose).getName());
        globalInfos.setCurrentRestaurant(productMenuScreen.render());
    }

    public int getScreenID() {
        return screenID;
    }

    public void setScreenID(int screenID) {
        this.screenID = screenID;
    }

    public Long getChose() {
        return chose;
    }

    public void setChose(Long chose) {
        this.chose = chose;
    }
}
