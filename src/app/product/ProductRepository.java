package app.product;

import app.Product;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class ProductRepository {
    private Product[] products = {
        new Hamburger(1, "Big Mac", 7, 500, false, 10),
        new Hamburger(2, "Cheese", 6, 450, false, 11),
        new Side(3, "Hash Brown", 2, 210, 1),
        new Side(4, "French Fries", 3, 300, 1),
        new Drink(5, "Coke", 1, 280, true),
        new Drink(6, "Zero Coke", 1, 0, true)
    };

    public Product[] getAllProducts() {
        return products;
    }

    public Product findById(int id) {
        for(Product product: products) {
            if(product.getId() == id) return product;
        }
        return null;
    };
}
