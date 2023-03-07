import app.Product;
import app.product.ProductRepository;

import java.util.Scanner;

public class OrderApp {

    ProductRepository productRepository = new ProductRepository();
    Product[] products = productRepository.getAllProducts();
    Menu menu = new Menu(products);
    Cart cart = new Cart(productRepository, menu);

    Order order = new Order(cart);
    Scanner scanner = new Scanner(System.in);

    public void start() {



        System.out.println("🍔 BurgerQueen Order Service");

        while(true) {

            menu.printMenu();
            String userInput = scanner.nextLine();

            if(userInput.equals("+")) {
                order.makeOrder();
                break;
            }

            if(userInput.equals("0")) {
                cart.printCart();
            }

            int menuNumber = Integer.parseInt(userInput);
            if(menuNumber >= 1 && menuNumber < productRepository.getAllProducts().length) {
                cart.addToCart(menuNumber);
            }
        }


        /**
         * show menu
         * get user input
         *
         * while(true) {
         *     if user input is +
         *         make order
         *         break;
         *     else if user input is 0
         *         print cart
         *     else if 1 ~ length of product length
         *         add to cart
         *
         * }
         *
         */
    }
}
