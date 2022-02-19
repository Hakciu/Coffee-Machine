package machine;

public class Machine {
    MachineState state;
    int water;
    int milk;
    int coffee;
    int cups;
    int money;
    boolean possible = false;

    public Machine(int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;

        setMainState();
    }

    boolean isWorking(){
        return state != MachineState.OFF;
    }

    public void execute(String input) {
        switch (state) {
            case MAIN:
                setState(input);
                break;
            case BUYING:
                handleBuying(input);
                setMainState();
                break;
            case FILLING_WATER:
                water += Integer.parseInt(input);
                state = MachineState.FILLING_MILK;
                System.out.print("Write how many ml of milk do you want to add:\n");
                break;
            case FILLING_MILK:
                milk += Integer.parseInt(input);
                state = MachineState.FILLING_COFFEE;
                System.out.print("Write how many grams of coffee beans do you want to add:\n");
                break;
            case FILLING_COFFEE:
                coffee += Integer.parseInt(input);
                state = MachineState.FILLING_CUPS;
                System.out.print("Write how many disposable cups of coffee do you want to add:\n");
                break;
            case FILLING_CUPS:
                cups += Integer.parseInt(input);
                setMainState();
                break;
            default:
                break;
        }
    }

    public void setState(String command) {
        switch (command) {
            case "buy":
                System.out.print("What do you want to buy? 1 - espresso, 2 - latte, " +
                        "3 - cappuccino, back - to main menu:\n");
                state = MachineState.BUYING;
                break;
            case "fill":
                state = MachineState.FILLING_WATER;
                System.out.print("Write how many ml of water do you want to add:\n");
                break;
            case "take":
                giveMoney();
                setMainState();
                break;
            case "remaining":
                printState();
                setMainState();
                break;
            case "exit":
                state = MachineState.OFF;
                break;
            default:
                System.out.println("Unexpected action!");
                setMainState();
                break;
        }
    }

    void setMainState() {
        state = MachineState.MAIN;
        System.out.print("Write action (buy, fill, take, remaining, exit):\n");
    }

    void handleBuying(String input) {
        possible = false;
        switch (input) {
            case "1":
                if (Beverage.ESPRESSO.getWater() > water) {
                    System.out.println("Sorry not enough water\n");
                } else if (Beverage.ESPRESSO.getMilk() > milk) {
                    System.out.println("Sorry not enough milk\n");
                } else if (Beverage.ESPRESSO.getCoffee() > coffee) {
                    System.out.println("Sorry not enough coffee beans\n");
                } else if (1 > cups) {
                    System.out.println("Sorry not enough disposable cups\n");
                } else {
                    possible = true;
                }

                if (possible) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    water -= Beverage.ESPRESSO.getWater();
                    milk -= Beverage.ESPRESSO.getMilk();
                    coffee -= Beverage.ESPRESSO.getCoffee();
                    cups -= 1;
                    money += Beverage.ESPRESSO.getPrice();
                }
                break;
            case "2":
                if (Beverage.LATTE.getWater() > water) {
                    System.out.println("Sorry not enough water\n");
                } else if (Beverage.LATTE.getMilk() > milk) {
                    System.out.println("Sorry not enough milk\n");
                } else if (Beverage.LATTE.getCoffee() > coffee) {
                    System.out.println("Sorry not enough coffee beans\n");
                } else if (1 > cups) {
                    System.out.println("Sorry not enough disposable cups\n");
                } else {
                    possible = true;
                }

                if (possible) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    water -= Beverage.LATTE.getWater();
                    milk -= Beverage.LATTE.getMilk();
                    coffee -= Beverage.LATTE.getCoffee();
                    cups -= 1;
                    money += Beverage.LATTE.getPrice();
                }
                break;
            case "3":
                if (Beverage.CAPPUCCINO.getWater() > water) {
                    System.out.println("Sorry not enough water\n");
                } else if (Beverage.CAPPUCCINO.getMilk() > milk) {
                    System.out.println("Sorry not enough milk\n");
                } else if (Beverage.CAPPUCCINO.getCoffee() > coffee) {
                    System.out.println("Sorry not enough coffee beans\n");
                } else if (1 > cups) {
                    System.out.println("Sorry not enough disposable cups\n");
                } else {
                    possible = true;
                }

                if (possible) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    water -= Beverage.CAPPUCCINO.getWater();
                    milk -= Beverage.CAPPUCCINO.getMilk();
                    coffee -= Beverage.CAPPUCCINO.getCoffee();
                    cups -= 1;
                    money += Beverage.CAPPUCCINO.getPrice();
                }
            case "back":
                break;
            default:
                System.out.println("Error!");
                break;
        }

    }

    void printState() {
        System.out.print("The coffee machine has:\n");
        System.out.printf("%d ml of water\n", water);
        System.out.printf("%d ml of milk\n", milk);
        System.out.printf("%d g of coffee beans\n", coffee);
        System.out.printf("%d disposable cups\n", cups);
        System.out.printf("%d$ of money\n\n", money);
    }

    void giveMoney() {
        System.out.printf("I gave you %d\n", money);
        money = 0;
    }
}