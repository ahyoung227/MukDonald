package app.discount.discountCondition;

import app.discount.discountPolicy.DiscountPolicy;

import java.util.Scanner;

public class StudentDiscountCondition implements DiscountCondition {
    private boolean isSatisfied;
    private DiscountPolicy discountPolicy;

    public StudentDiscountCondition(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    //private FixedRateDiscountPolicy policy = new FixedRateDiscountPolicy(10);
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
        return discountPolicy.getDiscountedPrice(price);
    }
}
