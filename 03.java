// BankAccount.java
class BankAccount {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Getter methods
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= amount;
        System.out.println("Withdrew " + amount + " from account " + accountNumber + ". New balance: " + balance);
    }

    // Method to deposit money (added for completeness, though not explicitly asked for withdrawal)
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.balance += amount;
        System.out.println("Deposited " + amount + " to account " + accountNumber + ". New balance: " + balance);
    }

    @Override
    public String toString() {
        return "Account " + accountNumber + ", Holder: " + accountHolder + ", Balance: " + String.format("%.2f", balance);
    }
}

// Bank.java
class Bank {
    private BankAccount[] accounts;
    private int numberOfAccounts;
    private final int MAX_ACCOUNTS = 5;

    public Bank() {
        this.accounts = new BankAccount[MAX_ACCOUNTS];
        this.numberOfAccounts = 0;
    }

    // Add a new bank account
    public void addAccount(BankAccount newAccount) {
        if (numberOfAccounts < MAX_ACCOUNTS) {
            accounts[numberOfAccounts] = newAccount;
            numberOfAccounts++;
            System.out.println("Account " + newAccount.getAccountNumber() + " added successfully.");
        } else {
            System.out.println("Bank is full. Cannot add more accounts.");
        }
    }

    // Withdraw money from an account given its account number and amount
    public void withdrawFromAccount(int accountNumber, double amount) {
        BankAccount accountToWithdraw = null;
        for (int i = 0; i < numberOfAccounts; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                accountToWithdraw = accounts[i];
                break;
            }
        }

        if (accountToWithdraw != null) {
            try {
                accountToWithdraw.withdraw(amount);
            } catch (IllegalArgumentException e) {
                System.err.println("Withdrawal failed for account " + accountNumber + ": " + e.getMessage());
            }
        } else {
            System.err.println("Account " + accountNumber + " not found.");
        }
    }

    // Display all accounts' details
    public void displayAllAccounts() {
        if (numberOfAccounts == 0) {
            System.out.println("\nNo accounts in the bank.");
            return;
        }
        System.out.println("\n--- All Bank Accounts ---");
        for (int i = 0; i < numberOfAccounts; i++) {
            System.out.println(accounts[i]);
        }
        System.out.println("-------------------------\n");
    }
}

// Main.java
 class Main {
    public static void main(String[] args) {
        // Create a Bank instance
        Bank myBank = new Bank();

        // Add these accounts:
        System.out.println("--- Adding Accounts ---");
        myBank.addAccount(new BankAccount(1001, "Alice", 5000.0));
        myBank.addAccount(new BankAccount(1002, "Bob", 3000.0));

        // Display all accounts
        myBank.displayAllAccounts();

        // Withdraw 6000.0 from account 1001 (should cause an exception).
        System.out.println("--- Attempting Withdrawal from Account 1001 (Insufficient Balance) ---");
        myBank.withdrawFromAccount(1001, 6000.0);

        // Withdraw 1000.0 from account 1002 (successful).
        System.out.println("\n--- Attempting Withdrawal from Account 1002 (Successful) ---");
        myBank.withdrawFromAccount(1002, 1000.0);

        // Display all accounts.
        myBank.displayAllAccounts();

        // Test with a non-existent account
        System.out.println("\n--- Attempting Withdrawal from Non-existent Account ---");
        myBank.withdrawFromAccount(9999, 100.0);

        // Test with a negative withdrawal amount
        System.out.println("\n--- Attempting Withdrawal with Negative Amount ---");
        myBank.withdrawFromAccount(1002, -500.0);

        // Add more accounts to test max capacity
        System.out.println("\n--- Adding More Accounts to Test Max Capacity ---");
        myBank.addAccount(new BankAccount(1003, "Charlie", 1500.0));
        myBank.addAccount(new BankAccount(1004, "David", 2500.0));
        myBank.addAccount(new BankAccount(1005, "Eve", 3500.0));
        myBank.addAccount(new BankAccount(1006, "Frank", 4500.0)); // This should show "Bank is full"

        myBank.displayAllAccounts();
    }
}