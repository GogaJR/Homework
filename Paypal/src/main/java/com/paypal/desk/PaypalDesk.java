package com.paypal.desk;

import java.util.Scanner;
import java.util.List;

public class PaypalDesk {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            System.out.println("(C) Create user");
            System.out.println("(L) List users");
            System.out.println("(+) Cash in");
            System.out.println("(-) Cash out");
            System.out.println("(T) Transaction");
            System.out.println("(Q) Quit\n");
            System.out.print("Enter the command: ");

            String input = scanner.next().toUpperCase();
            switch(input) {
                case "C":
                    createUser();
                    break;
                case "L":
                    listUsers();
                    break;
                case "+":
                    cashIn();
                    break;
                case "-":
                    cashOut();
                    break;
                case "T":
                    transaction();
                    break;
                case "Q":
                    return;
                default:
                    System.out.println("Enter valid command!\n");
            }
        }
    }

    private static void createUser() {
        System.out.print("\nEnter first name:");
        String firstName = scanner.next();

        System.out.print("Enter last name: ");
        String lastName = scanner.next();

        int userId = DbHelper.createUser(firstName, lastName);
        if(userId != -1) {
            System.out.println("User created successfully!\n");
        }else {
            System.out.println("Error while creating user.\n");
        }
    }

    private static void listUsers() {
        List<User> users = DbHelper.listUsers();
        if(users.isEmpty()) {
            System.out.println("There are no users. You may create them.\n");
            return;
        }

        for(User user : users) {
            System.out.println(user);
        }

        System.out.println();
    }

    private static void cashIn() {
        int userId = 0;
        do {
            System.out.print("\nEnter user id: ");

            if(scanner.hasNextInt()) {
                userId = scanner.nextInt();
            }else {
                scanner.next();
                System.out.println("Enter an integer.");
            }
        }while (userId <= 0);

        double amount = 0;
        do {
            System.out.print("Enter amount: ");

            if(scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
            }else {
                scanner.next();
                System.out.println("Enter the number.\n");
            }
        }while (amount <= 0);

        int resultOfUpdate = DbHelper.cashFlow(userId, amount);
        if(resultOfUpdate != -1) {
            System.out.println("Balance updated successfully!\n");
        }else {
            System.out.println("Error while updating balance.\n");
        }
    }

    private static void cashOut() {
        int userId = 0;
        do {
            System.out.print("\nEnter user id: ");

            if(scanner.hasNextInt()) {
                userId = scanner.nextInt();
            }else {
                scanner.next();
                System.out.println("Enter an integer.");
            }
        }while (userId <= 0);

        double amount = 0;
        do {
            System.out.print("Enter amount: ");

            if(scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
            }else {
                scanner.next();
                System.out.println("Enter the number.\n");
            }
        }while (amount <= 0);

        int resultOfUpdate = DbHelper.cashFlow(userId, -amount);
        if(resultOfUpdate != -1) {
            System.out.println("Balance updated successfully!\n");
        }else {
            System.out.println("Error while updating balance.\n");
        }
    }

    private static void transaction() {
        int userIdFrom = 0;
        do {
            System.out.print("\nEnter user id from: ");

            if(scanner.hasNextInt()) {
                userIdFrom = scanner.nextInt();
            }else {
                scanner.next();
                System.out.println("Enter an integer.");
            }
        }while (userIdFrom <= 0);

        int userIdTo = 0;
        do {
            System.out.print("Enter user id to: ");

            if(scanner.hasNextInt()) {
                userIdTo = scanner.nextInt();
            }else {
                scanner.next();
                System.out.println("Enter an integer.\n");
            }
        }while (userIdTo <= 0);

        double amount = 0;
        do {
            System.out.print("Enter amount: ");

            if(scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
            }else {
                scanner.next();
                System.out.println("Enter the number.\n");
            }
        }while (amount <= 0);

        int resultOfTransaction = DbHelper.transaction(userIdFrom, userIdTo, amount);
        if(resultOfTransaction != -1) {
            System.out.println("Transaction done successfully!\n");
        }else {
            System.out.println("Error while doing transaction.\n");
        }
    }
}
