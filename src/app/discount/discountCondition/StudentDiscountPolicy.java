package app.discount.discountCondition;

import app.discount.discountPolicy.FixedRateDiscountPolicy;

import java.util.Scanner;

public class StudentDiscountPolicy {
    private boolean isSatisfied;

    private FixedRateDiscountPolicy policy = new FixedRateDiscountPolicy(10);
    public boolean isSatisfied() {
        return isSatisfied;
    }

    public void setSatisfied(boolean satisfied) {
        this.isSatisfied = satisfied;
    }
    public void checkDiscountCondition() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you a student? (1)_YES (2)_NO");
        String input = scanner.next();

        if(input.equals("1")) setSatisfied(true);
        else if(input.equals("2")) setSatisfied(false);
    }
    public int applyDiscount(int price) {
        return policy.getDiscountedPrice(price);
    }
}
