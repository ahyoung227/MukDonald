package app.discount.discountPolicy;

public class FixedRateDiscountPolicy {

    private int rate;

    public FixedRateDiscountPolicy(int rate) {
        this.rate = rate;
    }

    public int getDiscountedPrice(int price) {
        return price - (price * rate/100);
    }
}
