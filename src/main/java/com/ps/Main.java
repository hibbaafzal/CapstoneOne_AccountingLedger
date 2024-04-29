package com.ps;

import java.util.*;

public class Main {

    ArrayList<String> str = new ArrayList<String>();

    public static void main(String[] args) {


        int commandHomeScreen;
// home screen
        do {
            System.out.println("Welcome to the Accounting Ledger Application!");
            System.out.println("What would you like to do?");
            System.out.println("\t[1] Add Deposit");
            System.out.println("\t[2] Make a Payment (Debit)");
            System.out.println("\t[3] Ledger");
            System.out.println("\t[4] Exit Application");

            // Creating an instance of scanner.
            Scanner scanner = new Scanner(System.in);

            commandHomeScreen = scanner.nextInt();


            // Switch statements for different user commands
            switch (commandHomeScreen) {
                case 1:
                    // Collect information for a deposit.
                    // String of arrays called depositInformationFromUser.

                    //   fix this        //    String[] depositInformationFromUser = informationFromUser(scanner);
                    //  if (depositInformationFromUser != null) {

                    // static method in ledger that adds the deposit information from the user.
// add here
                {
                }
                break;


                case 2:
                    // Collect information for a payment
                    // String of arrays called paymentInformationFromUser.
                    String[] paymentInformationFromUser = informationFromUser(scanner);
                    if (paymentInformationFromUser != null) {

                        // static method in class Ledger called add payment information
                    }
                    //   Ledger.addPaymentInformation(paymentInformationFromUser); // not sure about this: fix it

                    break;


                case 3:
                    // Display the ledger to the user
                    System.out.println("Displaying Ledger. Please Wait...");
                    // static method in class Ledger called homePage.
                    Ledger.homePage(scanner);

                    break;

                case 4:
                    // Exit the application

                    System.out.println("Exiting...");

                    break;

                default:
                    // invalid commands
                    System.out.println("Invalid. Please enter a valid input.");
                    break;
            }

        } while (commandHomeScreen != 4);


    }


    // Static method to collect transaction information from the user

    public static String[] informationFromUser(Scanner scanner) {
        System.out.print("Enter item description: ");
        String itemDescription = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendorInformation = scanner.nextLine();

        System.out.print("Enter dollar amount: $");
        double dollarAmount = scanner.nextDouble();
        scanner.nextLine();

        // returning the information the user had provided in a new array.
        return new String[]{itemDescription, vendorInformation, Double.toString(dollarAmount)};


    }
}






