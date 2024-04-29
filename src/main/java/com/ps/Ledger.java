package com.ps;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Ledger {

    public static void homePage(Scanner scanner) {

        int commandLedger;

        do {
            System.out.println("Welcome to the Ledger Home Page!");
            System.out.println("What would you like to do?");
            System.out.println("\t[1] Display all Entries.");
            System.out.println("\t[2] Display only deposits.");
            System.out.println("\t[3] Display only Payments.");
            System.out.println("\t[4] Run a Custom Search.");
            System.out.println("\t[5] Return to Home Page.");

            commandLedger = scanner.nextInt();

            switch (commandLedger) {
                case 1:

                    // display all entries

                    break;
                case 2:

                    // display deposits

                    break;
                case 3:
                    //  display payments

                    break;
                case 4:
                    customReports(scanner);

                    break;
                case 5:
                    System.out.println("Returning to Home Page...");
                    break;

                default:
                    System.out.println("Invalid Choice. Please enter a valid choice.");


            }
        } while (commandLedger != 5);
    }


// all entries


// deposits


// payments

    //custom reports
    public static void customReports(Scanner scanner) {

        int customSearchChoice;

// string builder to append the outputs.
        StringBuilder output;
        do {
            System.out.println("Which custom report would you like to see?");
            System.out.println("\t[1] Month to Date.");
            System.out.println("\t[2] Previous month.");
            System.out.println("\t[3] Year to date.");
            System.out.println("\t[4] Previous year.");
            System.out.println("\t[5] Search by vendor name.");
            System.out.println("\t[6] Return to previous screen...");

            customSearchChoice = scanner.nextInt();
            scanner.nextLine();

            LocalDate date = LocalDate.now();
            int month = date.getMonthValue();
            int year = date.getYear();

            // this allows us to call the method receiveLedgerData and store it in the array list.

            List<String> ledger = receiveLedgerData();

            output = new StringBuilder();
            switch (customSearchChoice) {

                case 1:
                    //Month to Date
                    for (String transaction : ledger) {
                        String[] splitInput = transaction.split("\\|");
                        if (splitInput.length > 0 && !splitInput[0].trim().isEmpty()) {

                            LocalDate transactionDate = LocalDate.parse(splitInput[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            if (transactionDate.getMonthValue() == month && transactionDate.getYear() == year) {
                                output.append(transaction).append("\n");
                            }

                        }
                    }

                    break;


                case 2:
                    // Previous Month
                    for (String transaction : ledger) {
                        String[] splitInput = transaction.split("\\|");
                        if (splitInput.length > 0 && !splitInput[0].trim().isEmpty()) {

                            LocalDate transactionDate = LocalDate.parse(splitInput[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            if (transactionDate.getMonthValue() == month - 1 && transactionDate.getYear() == year) {
                                output.append(transaction).append("\n");
                            }

                        }
                    }

                    break;

                case 3:
                    // Year to Date
                    for (String transaction : ledger) {
                        String[] parts = transaction.split("\\|");
                        if (parts.length > 0 && !parts[0].trim().isEmpty()) {

                            LocalDate transactionDate = LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            if (transactionDate.getYear() == year && transactionDate.getYear() == year) {
                                output.append(transaction).append("\n");
                            }

                        }
                    }


                    break;
                case 4:
                    //Previous Year

                    break;


                case 5:
                    //Vendor

                    System.out.print("\nPlease enter the vendor name: ");

                    String vendorName = scanner.nextLine().toLowerCase().trim();

                    // for each loop used to iterate through the array
                    for (String transaction : ledger) {

                        // if the transaction is not empty and not empty after removing whitespaces.
                        if (transaction != null && !transaction.trim().isEmpty()) {

                            String[] splitInput = transaction.split("\\|");

                            // if the array has a vendor field stored
                            if (splitInput.length > 3) {
                                String vendor = splitInput[3].toLowerCase();
                                // if vendor user entered is in the vendor name, it will be outputted.
                                if (vendor.contains(vendorName)) {
                                    output.append(transaction).append("\n");
                                }
                            } else {
                                System.out.println("Error! Transaction not found!");
                            }
                        }
                    }
                    break;

                case 6:
                    System.out.println("Going to previous screen... Please Wait.");
                    break;

                default:
                    System.out.println("Please select a valid option...");
                    break;
            }

        } while (customSearchChoice != 6);








