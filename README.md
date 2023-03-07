# MukDonald
MuckDonald is a fast-food restaurant ordering system that sells hamburgers, sides, drinks, and combo that combine these items.

## Features
- Print menu
- Choose option(Burger Only/Combo, Ketchup count, hasStraw)
- Add to cart
- children($1) discount, student discount(10%)
- Print cart
- Make order

## Dependency Injection(DI)
Dependency injection is a design pattern that is used to manage the dependencies between objects. 
In this pattern, instead of creating objects and managing their dependencies within the objects themselves, 
the dependencies are injected into the objects from the outside.

MukDonald utilizes key concepts of dependency injection (DI) in its implementation.

### Separation of Concerns(SOC)

```
public class StudentDiscountCondition {

    ...

    private FixedRateDiscountPolicy fixedRateDiscountPolicy = new FixedRateDiscountPolicy(10);

    ...
} 
```
Currently, StudentDiscountCondition depends on FixedRateDiscountPolicy. 
To separate StudentDiscountCondition from FixedRateDiscountPolicy

``` 
public class StudentDiscountCondition {

	...

	// delete : private FixedRateDiscountPolicy fixedRateDiscountPolicy = new FixedRateDiscountPolicy(10);
	private DiscountPolicy discountPolicy;

	public StudentDiscountCondition(DiscountPolicy discountPolicy) {
		this.discountPolicy = discountPolicy;
	}

	...
}
```
Instead, it creates `DiscountPolicy` interface, deletes the field that stores the instance of the FixedRateDiscountPolicy, 
defines a field of interface type, and then create a constructor.

### Single Responsibility Principle, SRP
Single Responsibility Principle (SRP) is one of the SOLID principles of object-oriented programming that states that a class 
or module should have only one reason to change or be responsible for one thing.

Based on the Single Responsibility Principle, it is necessary to ensure that each object has a clear responsibility, 
which includes the responsibility of creating all objects required for program execution.
MukDonal uses a class called AppConfigurer that creates and manages all objects required for program execution, and establishes their dependencies.
```
public class Main {
    public static void main(String[] args) {
        AppConfigurer appConfigurer = new AppConfigurer();

        OrderApp orderApp = new OrderApp(appConfigurer.productRepository(),
                                        appConfigurer.menu(),
                                        appConfigurer.cart(),
                                        appConfigurer.order());
        orderApp.start();
    }
} 
```

### Singleton pattern
In Main, by calling appConfigurer.cart(), the cart is created once, but if appConfigurer.order() is executed in Main, 
the second cart may be created, causing an error where the cart is constantly reset. Therefore, the cart instance 
is initialized once so that only one instance of Cart is created, and this single instance is shared between OrderApp and Order.

``` 
public class AppConfigurer {

    ...
    
    private Cart cart = new Cart(productRepository(), menu());

    public Cart cart() {
        return cart;
    }

    ...

    public Order order() {
        return new Order(cart(), discount());
    }
}
```
