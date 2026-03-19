class BankAccount {
    private int balance = 1000;

    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " wants to withdraw " + amount);

        if (balance < amount) {
            System.out.println("Insufficient balance. Waiting for deposit...");
            try {
                wait(); // wait until deposit happens
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        balance -= amount;
        System.out.println("Withdrawal successful. Remaining balance: " + balance);
    }

    public synchronized void deposit(int amount) {
        System.out.println(Thread.currentThread().getName() + " depositing " + amount);

        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);

        notify(); // notify waiting thread
    }
}

class WithdrawThread extends Thread {
    BankAccount account;

    WithdrawThread(BankAccount account) {
        this.account = account;
    }

    public void run() {
        account.withdraw(1500);
    }
}

class DepositThread extends Thread {
    BankAccount account;

    DepositThread(BankAccount account) {
        this.account = account;
    }

    public void run() {
        account.deposit(2000);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        WithdrawThread t1 = new WithdrawThread(account);
        DepositThread t2 = new DepositThread(account);

        t1.start();
        t2.start();
    }
}
