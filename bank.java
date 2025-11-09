class Account {
    String accNo;
    double balance;

    Account(String accNo, double balance) {
        this.accNo = accNo;
        this.balance = balance;
    }

    public String toString() {
        return accNo + " = " + balance;
    }
}

class SavingsAccount extends Account {
    double interestRate;

    SavingsAccount(String accNo, double balance, double interestRate) {
        super(accNo, balance);
        this.interestRate = interestRate;
    }

    void addInterest() {
        balance += balance * interestRate;
    }
}

class CheckingAccount extends Account {
    double overdraftLimit;

    CheckingAccount(String accNo, double balance, double overdraftLimit) {
        super(accNo, balance);
        this.overdraftLimit = overdraftLimit;
    }
}

public class Main {
    public static void main(String[] args) {
        Account[] accounts = new Account[] {
            new SavingsAccount("SA-101", 1000000, 0.05),
            new CheckingAccount("CA-201", 500000, 200000),
            new SavingsAccount("SA-102", 1500000, 0.04),
            new CheckingAccount("CA-202", 750000, 300000)
        };

        for (Account a : accounts) {
            if (a instanceof SavingsAccount) {
                SavingsAccount s = (SavingsAccount) a;
                s.addInterest();
            }
        }

        for (Account a : accounts) {
            System.out.println(a);
        }
    }
}
