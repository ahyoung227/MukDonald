package app.discount.discountPolicy;

public class FixedAmountDiscountPolicy {
    private int amount;

    public FixedAmountDiscountPolicy(int amount) {
        this.amount = amount;
    }

    public int getDiscountedPrice(int price) {
        return price - amount;
    }
}
