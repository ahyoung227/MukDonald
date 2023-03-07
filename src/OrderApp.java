import app.Product;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountCondition.StudentDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;
import app.product.ProductRepository;

import java.util.Scanner;

public class OrderApp {
    private ProductRepository productRepository;

    private Menu menu;

    private Cart cart;

    private Order order;
    Scanner scanner = new Scanner(System.in);

    public OrderApp(ProductRepository productRepository, Menu menu, Cart cart, Order order) {
        this.productRepository = productRepository;
        this.menu = menu;
        this.cart = cart;
        this.order = order;
    }

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
    }
}
