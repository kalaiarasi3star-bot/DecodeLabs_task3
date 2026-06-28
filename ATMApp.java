import java.util.Scanner;
class Account {
    private String name;
    private int pinCode;
    private double balance;

    public Account(String name, int pinCode, double balance) {
        this.name = name;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    public boolean checkPin(int pin) {
        return pin == pinCode;
    }

    public void addMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Amount added: " + amount);
        } else {
            System.out.println("Invalid deposit value.");
        }
    }

    public void removeMoney(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal value.");
        } else if (amount > balance) {
            System.out.println("Not enough balance in account.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. Please collect: " + amount);
        }
    }

    public void showBalance() {
        System.out.println("Available balance: " + balance);
    }

    public String getName() {
        return name;
    }
}
class ATMSystem {
    private Account account;
    private Scanner scanner;

    public ATMSystem(Account account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void runATM() {

        System.out.println("===== ATM INTERFACE =====");

        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (!account.checkPin(enteredPin)) {
            System.out.println("Wrong PIN. Access denied.");
            return;
        }

        System.out.println("Welcome " + account.getName());

        int option = 0; 

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    account.showBalance();
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    double dep = scanner.nextDouble();
                    account.addMoney(dep);
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double wid = scanner.nextDouble();
                    account.removeMoney(wid);
                    break;

                case 4:
                    System.out.println("Session ended. Thank you!");
                    break;

                default:
                    System.out.println("Invalid option selected.");
            }

        } while (option != 4);
    }
}

public class ATMApp {
    public static void main(String[] args) {

        Account user = new Account("Student User", 1111, 3000);

        ATMSystem atm = new ATMSystem(user);
        atm.runATM();
    }
}
