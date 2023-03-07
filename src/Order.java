import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountCondition.StudentDiscountPolicy;

public class Order {
    private Cart cart;
    public Order(Cart cart) {
        this.cart = cart;
    }

    public void makeOrder() {

        int totalPrice = cart.calculateTotalPrice();

        KidDiscountCondition kidDiscountCondition = new KidDiscountCondition();
        StudentDiscountPolicy studentDiscountPolicy = new StudentDiscountPolicy();

        kidDiscountCondition.checkDiscountCondition();
        studentDiscountPolicy.checkDiscountCondition();

        if(kidDiscountCondition.isSatisfied()) totalPrice = kidDiscountCondition.applyDiscount(totalPrice);
        if(studentDiscountPolicy.isSatisfied()) totalPrice = studentDiscountPolicy.applyDiscount(totalPrice);

        System.out.println("[📣] Your order has been received.");
        System.out.println("[📣] The order details are as follows. ");
        System.out.println("-".repeat(60));

        cart.printCartDetails();

        System.out.println("-".repeat(60));
        System.out.printf("Total      : $%d\n", totalPrice);
    }
}
