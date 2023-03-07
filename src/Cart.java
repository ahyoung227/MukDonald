import app.Product;
import app.product.ProductRepository;
import app.product.subproduct.BurgerCombo;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

import java.util.Scanner;

public class Cart {

    private Product[] items = new Product[0];
    private Scanner scanner = new Scanner(System.in);
    private ProductRepository productRepository;
    private Menu menu;
    public Cart(ProductRepository productRepository, Menu menu) {
        this.productRepository = productRepository;
        this.menu = menu;
    }

    public void printCart() {
        System.out.println("🧺 Cart");
        System.out.println("-".repeat(60));

        printCartDetails();

        System.out.println("-".repeat(60));
        System.out.printf("Total : $%d\n", calculateTotalPrice());

        System.out.println("Press enter if you want to go back to main");
        scanner.nextLine();
    }

    protected void printCartDetails() {
        for(Product item: items) {
            if (item instanceof Hamburger) {
                System.out.printf(
                        "  %-8s $%d (Burger only)\n",
                        item.getName(),
                        item.getPrice()
                );
            } else if(item instanceof BurgerCombo) {
                BurgerCombo burgerCombo = (BurgerCombo) item;
                System.out.printf(
                        "  %s $%d (%s(Ketchup %d), %s(Straw %s))\n",
                        item.getName(),
                        item.getPrice(),
                        burgerCombo.getSide().getName(),
                        burgerCombo.getSide().getKetchup(),
                        burgerCombo.getDrink().getName(),
                        burgerCombo.getDrink().hasStraw() ? "Included" : "Not included"
                );
            } else if(item instanceof Side) {
                System.out.printf(
                        "  %-8s $%d (Ketchup %d)\n",
                        item.getName(),
                        item.getPrice(),
                        ((Side) item).getKetchup()
                );
            } else if(item instanceof Drink) {
                System.out.printf(
                        "  %-8s $%d (Straw %s)\n",
                        item.getName(),
                        item.getPrice(),
                        ((Drink) item).hasStraw() ? "included" : "Not Included"  // 아래 설명 참고
                );
            }
        }
    }

    protected int calculateTotalPrice() {
        int totalPrice = 0;
        for(Product item: items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
    public void addToCart(int productId) {
        Product product = productRepository.findById(productId);
        Product newProduct;                                                                                                   ;

        if (product instanceof Hamburger) newProduct = new Hamburger((Hamburger) product);
        else if (product instanceof Side) newProduct = new Side((Side) product);
        else newProduct = new Drink((Drink) product);

        chooseOption(newProduct);

        if(product instanceof Hamburger) {
            Hamburger hamburger = (Hamburger) newProduct;
            if(hamburger.isCombo()) {
                newProduct = composeSet(hamburger);
            }
        }

        Product[] newItems = new Product[items.length+1];
        newItems[items.length] = newProduct;
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;

        System.out.printf("[📣] %s has been added to the cart..\n", product.getName());
    }

    public BurgerCombo composeSet(Hamburger hamburger) {
        System.out.println("Please choose your Side");
        menu.printSide(false);
        String sideId = scanner.nextLine();
        Side side = (Side) productRepository.findById(Integer.parseInt(sideId));
        Side newSide = new Side(side);
        chooseOption(newSide);

        System.out.println("Please choose your Drink");
        menu.printDrink(false);

        String drinkId = scanner.nextLine();
        Drink drink = (Drink) productRepository.findById(Integer.parseInt(drinkId));
        Drink newDrink = new Drink(drink);
        chooseOption(newDrink);

        String name = hamburger.getName() + "Combo";
        int price = hamburger.getBurgerComboPrice();
        int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

        return new BurgerCombo(name, price, kcal, hamburger, newSide, newDrink);
    }
    public void chooseOption(Product product) {
        String input;
        if(product instanceof Hamburger) {
            System.out.printf(
                    "Do you want to order burger only? (1)Burger Only($%d) (2)Combo($%d)\n",
                    product.getPrice(),
                    ((Hamburger) product).getBurgerComboPrice()
            );
            input = scanner.nextLine();
            if(input.equals("2")) ((Hamburger) product).setCombo(true);
        } else if(product instanceof Side) {
            System.out.println("How many ketchup do you need?");
            input = scanner.nextLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        } else if(product instanceof Drink) {
            System.out.println("Do you need a straw? (1)_YES (2)_NO");
            input = scanner.nextLine();
            if (input.equals("2")) ((Drink) product).setStraw(false);
        }
    }
}
