package com.ps;


import javax.swing.*;
import java.io.Console;
import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("\n\n-----------------Welcome to the Accounting Ledger Application!-----------------");

        System.out.print("Please enter your username: ");
        String user = scanner.nextLine();

        // password

        String password;

        // if in production, console will run.
        Console console = System.console();

        if (console == null) { // development
            password = getPasswordWithoutConsole("Enter password: ");

        } else { // production
            password = String.valueOf(console.readPassword("Enter password: "));
        }

        System.out.println("Authorizing.... Please Wait");
        System.out.println("Authorization Complete!");

        String commandHomeScreen;

        do {
            // asking user.


            System.out.println("\nWhat would you like to do?");
            System.out.println("\t[D] Add Deposit");
            System.out.println("\t[P] Make a Payment (Debit)");
            System.out.println("\t[L] Ledger");
            System.out.println("\t[X] Exit Application");

            commandHomeScreen = scanner.nextLine().toUpperCase();

            switch (commandHomeScreen) {
                case "D":
                    //array of strings called collected deposit information from user
                    String[] collectedDepositInformationFromUser = informationCollected(scanner);

                    if (collectedDepositInformationFromUser != null) {
                        // Static method in class ledger named add deposit information.
                        Ledger.addDepositInformation(collectedDepositInformationFromUser);
                    }
                    break;
                case "P":
                    //array of strings called collected payment information from user
                    String[] collectedPaymentInformationFromUser = informationCollected(scanner);

                    if (collectedPaymentInformationFromUser != null) {
                        // Static method in class ledger named add payment information.
                        Ledger.addPaymentInformation(collectedPaymentInformationFromUser);
                    }
                    break;
                case "L":
                    System.out.println("Displaying Ledger. Please Wait...");
                    // Static method in class ledger named add home page.
                    Ledger.homePage(scanner);

                    break;
                case "X":

                    System.out.println("Exiting...");
                    System.out.println("Thank you for banking with us!");
                    System.out.println("Have a nice day!");
                    break;

                default:
                    System.out.println("This is an invalid input. Please enter a valid input.");
            }
        } while (!commandHomeScreen.equals("X"));


    }


// collecting information from the user about the transactions.

    public static String[] informationCollected(Scanner scanner) {


        System.out.print("Please enter the description of your transaction: ");
        String description = scanner.nextLine();


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
            System.out.println("\nYou have entered an incorrect input type. Please try again.");
            return null;
        }
    }


    public static String getPasswordWithoutConsole(String prompt) {

        final JPasswordField passwordField = new JPasswordField();
        return JOptionPane.showConfirmDialog(
                null,
                passwordField,
                prompt,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION ? new String(passwordField.getPassword()) : "";
    }
}




