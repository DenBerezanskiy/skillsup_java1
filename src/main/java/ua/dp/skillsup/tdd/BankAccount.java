package ua.dp.skillsup.tdd;

public class BankAccount {
    private String number;
    private double amount;

    public BankAccount(double amount){
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "number='" + number + '\'' +
                ", amount=" + amount +
                '}';
    }
}
