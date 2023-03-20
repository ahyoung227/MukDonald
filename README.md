# MukDonald
MuckDonald is a fast-food restaurant ordering system that sells hamburgers, sides, drinks, and combo menu.

## Features
- Print menu
- Choose option(Burger Only/Combo, Ketchup count, hasStraw)
- Add to cart
- Children($1) discount, student discount(10%)
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
Currently, StudentDiscountCondition class depends on FixedRateDiscountPolicy class. 
To separate StudentDiscountCondition from FixedRateDiscountPolicy class, I implemented following code. 

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
After implementing SOC, 1) it creates `DiscountPolicy` interface, 2) deletes the field that stores the instance of the FixedRateDiscountPolicy, 
3) defines a field of interface type, 4) and then create a constructor.

### Single Responsibility Principle, SRP
Single Responsibility Principle (SRP) is one of the SOLID principles of object-oriented programming that states that a class 
or module should have only one reason to change or be responsible for one thing.

Based on the Single Responsibility Principle, it is necessary to ensure that each object has a clear responsibility, 
which includes the responsibility of creating all objects required for program execution.
MuckDonald uses a class called AppConfigurer that creates and manages all objects required for program execution, and establishes their dependencies. This ensures that other class objects can serve their own responsibility.
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
Without implementing Singleton pattern, it is possible that two Cart objects can be instantiated in code(In Main class, by calling appConfigurer.cart(), the Cart class is created once. Then, if appConfigurer.order() is executed in Main, the second cart may be created, causing an error where the cart is constantly reset.).
To avoid this to happened, the cart instance should be initialized once so that only one instance of Cart is created. 

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
MuckDonald implements Singleton pattern as above, so single instance(Cart) is shared between throughout an whole application.
