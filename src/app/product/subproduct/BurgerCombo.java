package app.product.subproduct;


import app.Product;

public class BurgerCombo extends Product {

    private Hamburger hamburger;
    Side side;
    Drink drink;


    public BurgerCombo(String name, int price, int kcal, Hamburger hamburger, Side side, Drink drink) {
        super(name, price, kcal);
        this.side = side;
        this.drink = drink;
    }

    public Side getSide() {
        return side;
    }


    public Drink getDrink() {
        return drink;
    }

}
