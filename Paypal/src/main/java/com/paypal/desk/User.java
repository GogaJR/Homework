package com.paypal.desk;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private double balance;

    public User(int id, String firstName, String lastName, double balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public String toString() {
        return "[ id = " + id + "," +
                " firstName = " + firstName + "," +
                " lastName = " + lastName + "," +
                " balance = " + balance + " ]";
    }
}
