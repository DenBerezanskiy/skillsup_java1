package ua.dp.skillsup.reflection;

import ua.dp.skillsup.reflection.annotations.Doc;

/**
 * Created by skillsup on 12.12.17.
 */
public class User {
    private String name;
    private double amount;

    public String getName() {
        return name;
    }

    public void setName(@Doc("sdsdd") String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Doc(value = "Returns type of card that user can use")
    public String getUserCardLevel() {
        if (amount > 100)
            return "platinum";
        else
            return "gold";
    }

    public static String foo(){
        return "bar";
    }

}
