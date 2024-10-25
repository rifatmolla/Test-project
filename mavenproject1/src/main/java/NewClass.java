public class NewClass {
    import java.util.ArrayList;
import java.util.Scanner;

// Dish class: Represents a menu item
class Dish {
    private String name;
    private double price;

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

// Order class: Represents a customer's order
class Order {
    private String customerName;
    private ArrayList<Dish> dishes;
    private double totalPrice;

    public Order(String customerName) {
        this.customerName = customerName;
        this.dishes = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
        totalPrice += dish.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void showOrderDetails() {
        System.out.println("Order for: " + customerName);
        System.out.println("Dishes ordered:");
        for (Dish dish : dishes) {
            System.out.println("- " + dish);
        }
        System.out.println("Total: $" + totalPrice);
    }
}

// Menu class: Manages the list of dishes available
class Menu {
    private ArrayList<Dish> dishes;

    public Menu() {
        dishes = new ArrayList<>();
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void showMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < dishes.size(); i++) {
            System.out.println((i + 1) + ". " + dishes.get(i));
        }
    }

    public Dish getDish(int index) {
        if (index >= 0 && index < dishes.size()) {
            return dishes.get(index);
        } else {
            return null;
        }
    }
}

// Restaurant class: Manages orders and the menu
class Restaurant {
    private Menu menu;
    private ArrayList<Order> orders;

    public Restaurant() {
        menu = new Menu();
        orders = new ArrayList<>();
    }

    public void addDishToMenu(String name, double price) {
        menu.addDish(new Dish(name, price));
    }

    public void placeOrder(String customerName, ArrayList<Integer> dishIndexes) {
        Order order = new Order(customerName);
        for (int index : dishIndexes) {
            Dish dish = menu.getDish(index - 1);
            if (dish != null) {
                order.addDish(dish);
            }
        }
        orders.add(order);
        System.out.println("Order placed successfully!");
        order.showOrderDetails();
    }

    public void showMenu() {
        menu.showMenu();
    }
}

// Main class to run the program
public class RestaurantManagementSystem {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        Scanner scanner = new Scanner(System.in);

        // Pre-populate the menu
        restaurant.addDishToMenu("Pasta", 8.99);
        restaurant.addDishToMenu("Pizza", 12.49);
        restaurant.addDishToMenu("Burger", 6.99);
        restaurant.addDishToMenu("Salad", 5.99);

        System.out.println("Welcome to the Restaurant Management System!");

        while (true) {
            System.out.println("\n1. Show Menu");
            System.out.println("2. Place Order");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    restaurant.showMenu();
                    break;

                case 2:
                    System.out.print("Enter customer name: ");
                    scanner.nextLine(); // Consume newline
                    String customerName = scanner.nextLine();

                    ArrayList<Integer> dishIndexes = new ArrayList<>();
                    restaurant.showMenu();
                    System.out.println("Enter the dish numbers (0 to finish):");
                    while (true) {
                        int dishNumber = scanner.nextInt();
                        if (dishNumber == 0) break;
                        dishIndexes.add(dishNumber);
                    }

                    restaurant.placeOrder(customerName, dishIndexes);
                    break;

                case 3:
                    System.out.println("Thank you for using the Restaurant Management System!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

}
