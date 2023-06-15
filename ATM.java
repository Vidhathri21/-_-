import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private static List<User> users;
    private static User currentUser;

    public static void main(String[] args) {
        initializeUsers();
        login();
        showMainMenu();
    }

    private static void initializeUsers() {
        users = new ArrayList<>();
        users.add(new User("123456", "1234", 1000));
        users.add(new User("654321", "4321", 500));
        // Add more users as needed
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getPin().equals(pin)) {
                currentUser = user;
                System.out.println("Login Successful!");
                return;
            }
        }

        System.out.println("Invalid User ID or PIN. Please try again.");
        login();
    }

    private static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-------- Main Menu --------");
            System.out.println("1. View Account Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewAccountBalance();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    logout();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAccountBalance() {
        System.out.println("Account Balance: $" + currentUser.getAccountBalance());
    }

    private static void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.");
            return;
        }
        if (currentUser.getAccountBalance() >= amount) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            System.out.println("Withdrawal successful. New balance: $" + currentUser.getAccountBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.");
            return;
        }
        currentUser.setAccountBalance(currentUser.getAccountBalance() + amount);
        System.out.println("Deposit successful. New balance: $" + currentUser.getAccountBalance());
    }

    private static void transfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter recipient's User ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.");
            return;
        }
        // Implement transfer logic here
            User recipient = null;
        for (User user : users) {
            if (user.getUserId().equals(recipientId)) {
                recipient = user;
                break;
            }
        }

        if (recipient == null) {
            System.out.println("Recipient not found. Transfer failed.");
            return;
        }

        if (currentUser.getAccountBalance() >= amount) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            recipient.setAccountBalance(recipient.getAccountBalance() + amount);
            System.out.println("Transfer successful.");
            System.out.println("Your new balance: $" + currentUser.getAccountBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void logout() {
        currentUser = null;
        System.out.println("Logout successful.");
    }
}

class User {
    private String userId;
    private String pin;
    private double accountBalance;

    public User(String userId, String pin, double accountBalance) {
        this.userId = userId;
        this.pin = pin;
        this.accountBalance = accountBalance;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
