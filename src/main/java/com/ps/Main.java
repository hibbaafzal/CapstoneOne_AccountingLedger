package com.ps;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        int commandHomeScreen;

        do {
            // asking user.
            System.out.println("Welcome to the Accounting Ledger Application!");
            System.out.println("What would you like to do?");
            System.out.println("\t[1] Add Deposit");
            System.out.println("\t[2] Make a Payment (Debit)");
            System.out.println("\t[3] Ledger");
            System.out.println("\t[4] Exit Application");

            commandHomeScreen = scanner.nextInt();

            switch (commandHomeScreen) {
                case 1:
                    //array of strings called collected deposit information from user
                    String[] collectedDepositInformationFromUser = information(scanner);

                    if (collectedDepositInformationFromUser != null) {
                        // Static method in class ledger named add deposit information.
                        Ledger.addDepositInformation(collectedDepositInformationFromUser);
                    }
                    break;
                case 2:
                    //array of strings called collected payment information from user
                    String[] collectedPaymentInformationFromUser = information(scanner);

                    if (collectedPaymentInformationFromUser != null) {
                        // Static method in class ledger named add payment information.
                        Ledger.addPaymentInformation(collectedPaymentInformationFromUser);
                    }
                    break;
                case 3:
                    System.out.println("Displaying Ledger. Please Wait...");
                    // Static method in class ledger named add home page.
                    Ledger.homePage(scanner);

                    break;
                case 4:

                    System.out.println("Exiting...");
                    System.out.println("Thank you for banking with us!");
                    System.out.println("Have a nice day!");
                    break;

                default:
                    System.out.println("This is an invalid input. Please enter a valid input.");
            }
        } while (commandHomeScreen != 4);


    }


// collecting information from the user about the transactions.

    public static String[] information (Scanner scanner) {
        System.out.print("Please enter item description: ");
        String description = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Please enter the vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Please enter the dollar amount: $");

        // checks to see if the user put a double value or not.
        if (scanner.hasNextDouble()) {
            double dollarAmount = scanner.nextDouble();
            scanner.nextLine();

            return new String[]{description, vendor, Double.toString(dollarAmount)};
        } else {
            scanner.nextLine();
            System.out.println("\nThe input type you have entered is incorrect. Please try again.");
            return null;
        }
    }


}