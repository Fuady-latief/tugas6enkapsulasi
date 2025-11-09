import java.util.ArrayList;
import java.util.List;

class Vehicle {
    String brand;
    Vehicle(String brand) { this.brand = brand; }
    void start() { System.out.println("vehicle starts"); }
}

class Car extends Vehicle {
    Car(String brand) { super(brand); }
    @Override
    void start() { System.out.println(brand + " car starts"); }
}

class Motorcycle extends Vehicle {
    Motorcycle(String brand) { super(brand); }
    @Override
    void start() { System.out.println(brand + " motorcycle starts"); }
}

class Account {
    String accNo;
    double balance;
    Account(String accNo, double balance) {
        this.accNo = accNo;
        this.balance = balance;
    }
    @Override
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

abstract class Shape {
    abstract double area();
}

class Triangle extends Shape {
    double base, height;
    Triangle(double base, double height) { this.base = base; this.height = height; }
    double area() { return 0.5 * base * height; }
}

class Rectangle extends Shape {
    double width, height;
    Rectangle(double width, double height) { this.width = width; this.height = height; }
    double area() { return width * height; }
}

class Circle extends Shape {
    double r;
    Circle(double r) { this.r = r; }
    double area() { return Math.PI * r * r; }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Latihan 1: Kendaraan ===");
        Vehicle[] vs = new Vehicle[] {
            new Car("Toyota"),
            new Motorcycle("Honda"),
            new Car("Suzuki"),
            new Motorcycle("Yamaha")
        };
        for (Vehicle v : vs) v.start();

        System.out.println("\n=== Latihan 2: Bank (inheritance + casting) ===");
        Account[] accs = new Account[] {
            new SavingsAccount("SA-001", 1_000_000, 0.05),
            new CheckingAccount("CA-001", 500_000, 200_000),
            new SavingsAccount("SA-002", 2_000_000, 0.03),
            new CheckingAccount("CA-002", 750_000, 300_000)
        };
        for (Account a : accs) {
            if (a instanceof SavingsAccount) {
                SavingsAccount s = (SavingsAccount) a;
                s.addInterest();
            }
        }
        for (Account a : accs) {
            System.out.println(a);
        }

        System.out.println("\n=== Latihan 3: Shape (abstract) ===");
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Triangle(10, 6));
        shapes.add(new Rectangle(4, 8));
        shapes.add(new Circle(5));
        double total = 0;
        for (Shape s : shapes) total += s.area();
        System.out.println("Total area = " + total);
    }
}
