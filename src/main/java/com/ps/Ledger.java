package com.ps;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {


    // static method for the ledger home page.
    public static void homePage(Scanner scanner) {


        String commandLedger;

        do {

            System.out.println("\n\n---------------Welcome to the Ledger Home Page!---------------");
            System.out.println("\nWhat would you like to do?");
            System.out.println("\t[A] Display all entries.");
            System.out.println("\t[D] Display only deposits.");
            System.out.println("\t[P] Display only payments.");
            System.out.println("\t[R] Run a custom search.");
            System.out.println("\t[H] Return to home page.");

            commandLedger = scanner.nextLine().toUpperCase();

            switch (commandLedger) {


                case "A":
                    // all entries
                    System.out.println("-------------All Entries-------------");

                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.txt"));

                        String input;
                        while ((input = bufferedReader.readLine()) != null) {

                            System.out.println(input);

                        }
                    } catch (IOException e) {
                        System.out.println("Error!");
                    }
                    break;

                case "D":
                    System.out.println("-------------Deposits-------------");
                    // deposits only so we filter for deposits only.
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.txt"));

                        String input;
                        while ((input = bufferedReader.readLine()) != null) {

                            String[] splitInput = input.split("\\|");
                            //  condition
                            if (!splitInput[splitInput.length - 1].contains("-")) {
                                System.out.println(input);

                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error!");
                    }
                    break;

                case "P":
                    System.out.println("-------------Payments-------------");
                    // payments only, so we filter for payments only.

                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.txt"));

                        String input;
                        while ((input = bufferedReader.readLine()) != null) {
                            String[] splitInput = input.split("\\|");

                            // condition
                            if (splitInput[splitInput.length - 1].contains("-")) {
                                System.out.println(input);


                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error!");
                    }
                    break;

                case "R":
                    // static method for custom reports.
                    customReports(scanner);
                    break;

                case "H":
                    System.out.println("Exiting the ledger...");
                    System.out.println("Returning to previous screen.");

                    break;

                default:
                    System.out.println("You have entered an invalid choice. Please enter a valid choice...");
            }

        } while (!commandLedger.equals("H"));
    }

    // reads from the transactions file and returns an arraylist.

    public static List<String> ledgerDataReports() {
        // stores it into strings
        List<String> ledgerData = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.txt"));

            String input;
            while ((input = bufferedReader.readLine()) != null) {
                ledgerData.add(input);
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
        return ledgerData;
    }


    // custom reports
    public static void customReports(Scanner scanner) {
        int customSearchChoice;
        do {
            System.out.println("\nWhich custom report would you like to see?");
            System.out.println("\t[1] Month to Date?");
            System.out.println("\t[2] Previous month.");
            System.out.println("\t[3] Year to date.");
            System.out.println("\t[4] Previous year.");
            System.out.println("\t[5] Search by vendor.");
            System.out.println("\t[6] Search by description.");
            System.out.println("\t[0] Return to previous screen...");

            customSearchChoice = scanner.nextInt();
            scanner.nextLine();

            LocalDate date = LocalDate.now();
            int month = date.getMonthValue();
            int year = date.getYear();
            List<String> ledger = ledgerDataReports();
            StringBuilder output = new StringBuilder();

            switch (customSearchChoice) {
                // month to date
                case 1:


                    System.out.println("------------------Month to Date----------------");
                    // for each loop
                    for (String ledgerTransaction : ledger) {
                        String[] splitInput = ledgerTransaction.split("\\|");

                        if (splitInput.length > 0 && !splitInput[0].trim().isEmpty()) {
                            try {
                                LocalDate transactionDate = LocalDate.parse(splitInput[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                if (transactionDate.getMonthValue() == month && transactionDate.getYear() == year) {
                                    output.append(ledgerTransaction).append("\n");
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Error!");
                            }
                        }
                    }
                    break;

                // previous month
                case 2:
                    System.out.println("------------------Previous Month----------------");
                    for (String ledgerTransaction : ledger) {
                        String[] splitInput = ledgerTransaction.split("\\|");
                        if (splitInput.length > 0 && !splitInput[0].trim().isEmpty()) {
                            try {
                                LocalDate dateOfTransaction = LocalDate.parse(splitInput[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                if (dateOfTransaction.getMonthValue() == month - 1 && dateOfTransaction.getYear() == year) {
                                    output.append(ledgerTransaction).append("\n");
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Error!");
                            }
                        }
                    }
                    break;
                // year to date
                case 3:
                    System.out.println("------------------Year to Date----------------");
                    for (String ledgerTransaction : ledger) {
                        try {
                            LocalDate dateOfTransaction = LocalDate.parse(ledgerTransaction.split("\\|")[0]);
                            if (dateOfTransaction.getYear() == year) {
                                output.append(ledgerTransaction).append("\n");
                            }
                        } catch (DateTimeParseException e) {

                        }
                    }
                    break;
                // previous year
                case 4:
                    System.out.println("------------------Previous Year-----------------");
                    for (String ledgerTransaction : ledger) {
                        try {
                            LocalDate dateOfTransaction = LocalDate.parse(ledgerTransaction.split("\\|")[0]);
                            if (dateOfTransaction.getYear() == year - 1) {
                                output.append(ledgerTransaction).append("\n");
                            }
                        } catch (DateTimeParseException e) {

                        }
                    }
                    break;
                // vendor
                case 5:
                    System.out.println("------------------Search by Vendor----------------");
                    System.out.print("\nPlease enter the vendor name: ");

                    // Case-insensitivity and trim whitespace.
                    String vendorName = scanner.nextLine().toLowerCase().trim();
                    // for each loop
                    for (String ledgerTransaction : ledger) {
                        try {
                            String vendor = ledgerTransaction.split("\\|")[3].toLowerCase().trim();
                            if (vendor.contains(vendorName)) {
                                output.append(ledgerTransaction).append("\n");
                            }
                        } catch (Exception e) {

                        }
                    }
                    break;
                // description
                case 6:
                    System.out.println("------------------Search by Description----------------");
                    System.out.print("\nPlease enter the description: ");


                    String descriptionProvided = scanner.nextLine().toLowerCase().trim();
                    // for each loop
                    for (String ledgerTransaction : ledger) {


                        try {
                            String description = ledgerTransaction.split("\\|")[2].toLowerCase().trim();
                            if (description.contains(descriptionProvided)) {
                                output.append(ledgerTransaction).append("\n");
                            }
                        } catch (Exception e) {

                        }
                    }
                    break;

                // exit custom search
                case 0:
                    System.out.println("Exiting... Please Wait.");
                    break;

                default:
                    System.out.println("Please select a valid option...");
                    break;
            }
            if (output.isEmpty() && customSearchChoice != 0) {
                System.out.println("There are no transactions that match your search. Please try another search.");


            } else {
                System.out.println(output);
            }
        } while (customSearchChoice != 0);
    }


    // adds deposit information into the file.
    // deposits are positive
    public static void addDepositInformation(String[] depositInformation) {


        // formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);


        String descriptionProvided = depositInformation[0];

        String vendorProvided = depositInformation[1];

        double dollarAmount = Double.parseDouble(depositInformation[2]);
        String informationProvided = String.format("%s|%s|%s|$%.2f\n", formattedDateTime, descriptionProvided, vendorProvided, (dollarAmount));

        enterInformationIntoFile(informationProvided);
        System.out.println("\n\n***Your deposit has been added successfully!***\n\n");

    }

// static method to add the payment information into the file.
    // payments are negative

    public static void addPaymentInformation(String[] paymentInformation) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();

        String formattedDateTime = dateTime.format(formatter);

        String descriptionProvided = paymentInformation[0];
        String vendorProvided = paymentInformation[1];

        double dollarAmount = Double.parseDouble(paymentInformation[2]);
        String informationProvided = String.format("%s|%s|%s|-$%.2f\n", formattedDateTime, descriptionProvided, vendorProvided, (dollarAmount));

        enterInformationIntoFile(informationProvided);
        System.out.println("\n\n***Your payment has been added successfully!***\n\n");


    }

// writes the information into the file

    public static void enterInformationIntoFile
            (String informationFromUser) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.txt", true));

            bufferedWriter.write(informationFromUser);
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Error!");
        }
    }


}






