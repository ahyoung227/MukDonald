import app.discount.Discount;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountCondition.StudentDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;

public class Order {
    private Cart cart;
    private Discount discount;
    public Order(Cart cart, Discount discount) {
        this.cart = cart;
        this.discount = discount;
    }

    public void makeOrder() {

        int totalPrice = cart.calculateTotalPrice();
        int finalPrice = discount.discount(totalPrice);


        System.out.println("[📣] Your order has been received.");
        System.out.println("[📣] The order details are as follows. ");
        System.out.println("-".repeat(60));

        cart.printCartDetails();

        System.out.println("-".repeat(60));
        System.out.printf("Total      : $%d\n", finalPrice);
    }
}
