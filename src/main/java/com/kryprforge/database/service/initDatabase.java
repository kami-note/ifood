package com.kryprforge.database.service;

import com.kryprforge.database.dao.CategoryDAO;
import com.kryprforge.database.dao.RestaurantDAO;
import com.kryprforge.database.repository.*;

import java.util.ArrayList;
import java.util.List;

public class initDatabase {
    public static List<Address> address() {
        List<Address> addresses = new ArrayList<>();

        addresses.add(new Address("Street 1", "10", "Neighborhood 1", "City 1", "ST", "12345-678", "Type A"));
        addresses.add(new Address("Street 2", "20", "Neighborhood 2", "City 2", "ST", "22345-678", "Type B"));
        addresses.add(new Address("Street 3", "30", "Neighborhood 3", "City 3", "ST", "32345-678", "Type C"));
        addresses.add(new Address("Street 4", "40", "Neighborhood 4", "City 4", "ST", "42345-678", "Type D"));
        addresses.add(new Address("Street 5", "50", "Neighborhood 5", "City 5", "ST", "52345-678", "Type E"));
        addresses.add(new Address("Street 6", "60", "Neighborhood 6", "City 6", "ST", "62345-678", "Type F"));
        addresses.add(new Address("Street 7", "70", "Neighborhood 7", "City 7", "ST", "72345-678", "Type G"));
        addresses.add(new Address("Street 8", "80", "Neighborhood 8", "City 8", "ST", "82345-678", "Type H"));
        addresses.add(new Address("Street 9", "90", "Neighborhood 9", "City 9", "ST", "92345-678", "Type I"));
        addresses.add(new Address("Street 10", "100", "Neighborhood 10", "City 10", "ST", "10245-678", "Type J"));

        return addresses;
    }

    public static List<PaymentMethod> paymentMethods() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        paymentMethods.add(new PaymentMethod("Credit Card"));
        paymentMethods.add(new PaymentMethod("Debit Card"));
        paymentMethods.add(new PaymentMethod("PayPal"));
        paymentMethods.add(new PaymentMethod("Bank Transfer"));
        paymentMethods.add(new PaymentMethod("Cash"));
        paymentMethods.add(new PaymentMethod("Check"));
        paymentMethods.add(new PaymentMethod("Cryptocurrency"));
        paymentMethods.add(new PaymentMethod("Mobile Payment"));
        paymentMethods.add(new PaymentMethod("Gift Card"));
        paymentMethods.add(new PaymentMethod("Prepaid Card"));

        return paymentMethods;
    }

    public static List<Promotion> promotions() {
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("Holiday Sale", 20.0, "HOLIDAY20", "2024-12-31"));
        promotions.add(new Promotion("Black Friday", 30.0, "BLACKFRIDAY30", "2024-11-30"));
        promotions.add(new Promotion("Cyber Monday", 25.0, "CYBER25", "2024-12-02"));
        promotions.add(new Promotion("Summer Sale", 15.0, "SUMMER15", "2024-06-30"));
        promotions.add(new Promotion("Winter Clearance", 40.0, "WINTER40", "2025-01-31"));
        promotions.add(new Promotion("New Year Discount", 35.0, "NEWYEAR35", "2025-01-10"));
        promotions.add(new Promotion("Flash Sale", 10.0, "FLASH10", "2024-11-25"));
        promotions.add(new Promotion("Valentine's Day Special", 50.0, "VALENTINE50", "2025-02-14"));
        promotions.add(new Promotion("Easter Discount", 20.0, "EASTER20", "2025-04-10"));
        promotions.add(new Promotion("Back to School", 15.0, "SCHOOL15", "2024-09-01"));
        return promotions;
    }

    public static List<Category> categories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(CategoryType.RESTAURANT, "Italian Restaurant", "Authentic Italian cuisine"));
        categories.add(new Category(CategoryType.RESTAURANT, "Sushi Bar", "Fresh and delicious sushi"));
        categories.add(new Category(CategoryType.RESTAURANT, "Mexican Restaurant", "Spicy and flavorful Mexican dishes"));
        categories.add(new Category(CategoryType.RESTAURANT, "Steakhouse", "Prime cuts of steak grilled to perfection"));
        categories.add(new Category(CategoryType.RESTAURANT, "Vegan Restaurant", "Delicious plant-based meals"));
        categories.add(new Category(CategoryType.FOOD, "Burgers", "Juicy and tasty burgers"));
        categories.add(new Category(CategoryType.FOOD, "Pizza", "Hot and cheesy pizzas with various toppings"));
        categories.add(new Category(CategoryType.FOOD, "Pasta", "Rich and creamy pasta dishes"));
        categories.add(new Category(CategoryType.FOOD, "Desserts", "Sweet and delectable desserts"));
        categories.add(new Category(CategoryType.DRINK, "Cocktails", "Refreshing and exotic cocktails"));
        return categories;
    }

    public static List<Restaurant> restaurants(CategoryDAO categoryDAO) {
        List<Category> categories = categoryDAO.findAll();
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("La Bella Italia", "123-456-7890", categories.get(0), true));
        restaurants.add(new Restaurant("Sushi Zen", "098-765-4321", categories.get(1), false));
        restaurants.add(new Restaurant("El Mariachi", "234-567-8901", categories.get(2), true));
        restaurants.add(new Restaurant("Grill House", "345-678-9012", categories.get(3), true));
        restaurants.add(new Restaurant("Green Delight", "456-789-0123", categories.get(4), false));
        restaurants.add(new Restaurant("Burger King", "567-890-1234", categories.get(5), true));
        restaurants.add(new Restaurant("Pizza Palace", "678-901-2345", categories.get(6), true));
        restaurants.add(new Restaurant("Pasta Heaven", "789-012-3456", categories.get(7), false));
        restaurants.add(new Restaurant("Sweet Tooth", "890-123-4567", categories.get(8), false));
        restaurants.add(new Restaurant("Cocktail Lounge", "901-234-5678", categories.get(9), true));
        return restaurants;
    }

    public static List<Product> products(Restaurant restaurant) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Pizza", "Delicious cheese pizza", 9.99, restaurant));
        products.add(new Product("Burger", "Juicy beef burger with lettuce and tomato", 7.99, restaurant));
        products.add(new Product("Pasta", "Pasta with marinara sauce", 8.49, restaurant));
        products.add(new Product("Salad", "Fresh garden salad with vinaigrette", 5.99, restaurant));
        products.add(new Product("Sushi", "Assorted sushi platter", 12.99, restaurant));
        products.add(new Product("Steak", "Grilled steak with potatoes", 14.99, restaurant));
        products.add(new Product("Ice Cream", "Vanilla ice cream with chocolate syrup", 4.49, restaurant));
        products.add(new Product("Soup", "Tomato basil soup", 4.99, restaurant));
        products.add(new Product("Sandwich", "Turkey sandwich with avocado", 6.99, restaurant));
        products.add(new Product("Coffee", "Freshly brewed coffee", 2.99, restaurant));
        return products;
    }
}
