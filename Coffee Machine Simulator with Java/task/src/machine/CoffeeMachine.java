package machine;

import java.util.Scanner;

public class CoffeeMachine {

    enum CoffeeType {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        public final int water;
        public final int milk;
        public final int beans;
        public final int price;

        CoffeeType(int Water, int milk, int beans, int price) {
            this.water = Water;
            this.milk = milk;
            this.beans = beans;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        startCoffeeMachine();
    }

    static void startCoffeeMachine() {
        getAction();
    }

    static int waterInMachine = 400;
    static int milkInMachine = 540;
    static int coffeeBeansInMachine = 120;
    static int disposableCupsInMachine = 9;
    static int moneyInMachine = 550;
    static int madeCoffees = 0;

    static void printStatus() {
        System.out.println("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                """.formatted(waterInMachine, milkInMachine, coffeeBeansInMachine, disposableCupsInMachine, moneyInMachine));
    }

    static void getAction() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            String action = scanner.nextLine();
            if (action.equals("buy")) {
                if (!checkForCleaning()) {
                    System.out.println("I need cleaning!");
                } else {
                    buy();
                }
            } else if (action.equals("fill")) {
                fill();
            } else if (action.equals("take")) {
                takeMoney();
            } else if (action.equals("remaining")){
                printStatus();
            } else if(action.equals("clean")) {
                clean();
            } else if (action.equals("exit")){
                break;
            }
        }
    }

    static void buy() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");

        String input = scanner.nextLine();
        switch (input) {
            case "1":
                makeCoffee(CoffeeType.ESPRESSO);
                break;
            case "2":
                makeCoffee(CoffeeType.LATTE);
                break;
            case "3":
                makeCoffee(CoffeeType.CAPPUCCINO);
                break;
            case "back":
                break;
        }

    }

    static void fill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        waterInMachine += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milkInMachine += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeansInMachine += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        disposableCupsInMachine += scanner.nextInt();
        scanner.close();
    }

    static void takeMoney() {
        System.out.println("I gave you $" + moneyInMachine);
        moneyInMachine = 0;
    }

    static boolean checkForCleaning() {
        if (madeCoffees >= 10) {
            return false;
        }
        return true;
    }

    static void clean() {
        System.out.println("I have been cleaned!");
        madeCoffees = 0;
    }

    static void makeCoffee(CoffeeType type) {
        boolean canMake = true;

        if (waterInMachine < type.water) {
            System.out.println("Sorry, not enough water!");
            canMake = false;
        }

        if (type.milk > 0 && milkInMachine < type.milk) {
            System.out.println("Sorry, not enough milk!");
            canMake = false;
        }

        if (coffeeBeansInMachine < type.beans) {
            System.out.println("Sorry, not enough coffee beans!");
            canMake = false;
        }

        if (disposableCupsInMachine < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            canMake = false;
        }

        if (!canMake) {
            return;
        }

        System.out.println("I have enough resources, making you a coffee!");
        waterInMachine -= type.water;
        milkInMachine -= type.milk;
        coffeeBeansInMachine -= type.beans;
        moneyInMachine += type.price;
        disposableCupsInMachine--;
        madeCoffees++;
    }


}