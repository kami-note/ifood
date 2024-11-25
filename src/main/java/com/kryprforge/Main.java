package com.kryprforge;

import com.kryprforge.database.dao.*;
import com.kryprforge.database.repository.*;
import com.kryprforge.database.service.initDatabase;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AddressDAO addressDAO = new AddressDAO();
        PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
        PromotionDAO promotionDAO = new PromotionDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        ProductDAO productDAO = new ProductDAO();

        insertCategorys(categoryDAO);

        insertRestaurants(restaurantDAO, categoryDAO);

        insertProducts(productDAO, restaurantDAO);

        insertAddress(addressDAO);
        insertPromotions(promotionDAO);
        insertPaymentMethods(paymentMethodDAO);
    }

    public static void insertAddress(AddressDAO addressDAO){
        List<Address> addresses = initDatabase.address();
        for (Address address : addresses) {
            addressDAO.save(address);
        }

        System.out.println("All addresses saved successfully!");
    }

    public static void insertPromotions(PromotionDAO promotionDAO){
        List<Promotion> promotions = initDatabase.promotions();
        for(Promotion promotion : promotions){
            promotionDAO.save(promotion);
        }
    }

    public static void insertPaymentMethods(PaymentMethodDAO paymentMethodDAO){
        List<PaymentMethod> paymentMethods = initDatabase.paymentMethods();
        for(PaymentMethod paymentMethod : paymentMethods) {
            paymentMethodDAO.save(paymentMethod);
        }
    }

    public static void insertCategorys(CategoryDAO categoryDAO){
        List<Category> categories = initDatabase.categories();
        for(Category category : categories){
            categoryDAO.save(category);
        }
    }

    public static void insertRestaurants(RestaurantDAO restaurantDAO, CategoryDAO categoryDAO){
        List<Restaurant> restaurants = initDatabase.restaurants(categoryDAO);
        for(Restaurant restaurant : restaurants){
            restaurantDAO.save(restaurant);
        }
    }

    public static void insertProducts(ProductDAO productDAO, RestaurantDAO restaurantDAO){
        List<Restaurant> restaurants = restaurantDAO.findAll();

        if (!restaurants.isEmpty()) {
            Restaurant restaurant = restaurants.get(0);
            List<Product> products = initDatabase.products(restaurant);
            for (Product product : products){
                productDAO.save(product);
            }
            System.out.println("All products saved successfully!");
        } else {
            System.out.println("No restaurants found to associate products.");
        }
    }
}
