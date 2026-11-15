import java.util.Scanner;

/**
 * ATM Machine Simulation
 *
 * Simulates a simple ATM machine:
 *  - Asks the user for a 4-digit PIN (correct PIN: 1234) with 3 attempts.
 *  - After a successful login, shows a menu repeatedly until Exit is selected.
 *  - Supports checking the balance, depositing, withdrawing and showing
 *    the account status (VIP / Regular / Low Balance).
 *
 * Structure used (per assignment requirements):
 *  - one while loop      -> the main menu loop
 *  - one do-while loop   -> the PIN attempts loop
 *  - one switch statement-> the menu options
 *  - multiple if-else    -> the validation logic
 *  - one break statement -> leaves the switch after a menu action
 */
public class ATMSimulator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // ---------------- PIN verification (do-while loop) ----------------
        int attempts = 3;
        boolean authenticated = false;
        int enteredPin = 0;

        do {
            System.out.print("Enter your 4-digit PIN: ");
            enteredPin = input.nextInt();

            if (enteredPin == 1234) {
                authenticated = true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Incorrect PIN. You have " + attempts
                            + " attempts remaining.");
                }
            }
        } while (!authenticated && attempts > 0);

        if (!authenticated) {
            System.out.println("Your account has been locked.");
            return;
        }

        // ---------------- ATM session (while loop + switch) ----------------
        double balance = 2500.75;
        int successfulTransactions = 0;
        boolean exitRequested = false;
        int choice = 0;

        while (!exitRequested) {
            System.out.println("========= ATM =========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Account Status");
            System.out.println("5. Exit");
            System.out.println("=======================");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                    if (choice == 1) {
                        // Check Balance
                        System.out.println("Your balance is: " + balance);
                    } else if (choice == 2) {
                        // Deposit
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = input.nextDouble();
                        if (depositAmount > 0) {
                            balance = balance + depositAmount;
                            successfulTransactions++;
                            System.out.println("Deposited: " + depositAmount);
                            System.out.println("Updated balance: " + balance);
                        } else {
                            System.out.println("Invalid amount.");
                        }
                    } else if (choice == 3) {
                        // Withdraw
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = input.nextDouble();
                        if (withdrawalAmount == 0) {
                            System.out.println("Transaction cancelled.");
                        } else if (withdrawalAmount > balance) {
                            System.out.println("Insufficient balance.");
                        } else {
                            balance = balance - withdrawalAmount;
                            successfulTransactions++;
                            System.out.println("Withdrew: " + withdrawalAmount);
                            System.out.println("Updated balance: " + balance);
                            if (balance == 0) {
                                System.out.println("Warning: Your account is empty.");
                            }
                        }
                    } else {
                        // Show Account Status
                        if (balance >= 5000) {
                            System.out.println("VIP Customer");
                        } else if (balance >= 1000 && balance < 5000) {
                            System.out.println("Regular Customer");
                        } else {
                            System.out.println("Low Balance");
                        }
                    }
                    break;
                case 5:
                    // Exit
                    System.out.println("Thank you for using our ATM.");
                    exitRequested = true;
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid option.");
            }
        }

        // Bonus: total number of successful deposit/withdrawal transactions
        System.out.println("Total successful transactions: " + successfulTransactions);
    }
}
