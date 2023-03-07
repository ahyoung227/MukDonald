package app.product.subproduct;

import app.Product;

public class Hamburger extends Product {

    private boolean isCombo;
    private int burgerComboPrice;

    public Hamburger(int id, String name, int price, int kcal, boolean isCombo, int burgerComboPrice) {
        super(id, name, price, kcal);
        this.isCombo = isCombo;
        this.burgerComboPrice = burgerComboPrice;
    }

    public Hamburger(Hamburger  hamburger) {
        super(hamburger.getName(), hamburger.getPrice(), hamburger.getKcal());
        this.isCombo = hamburger.isCombo();
        this.burgerComboPrice = hamburger.getBurgerComboPrice();
    }

    public boolean isCombo() {
        return isCombo;
    }

    public void setCombo(boolean combo) {
        isCombo = combo;
    }

    public int getBurgerComboPrice() {
        return burgerComboPrice;
    }
}
