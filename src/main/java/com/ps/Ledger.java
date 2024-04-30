package com.ps;

import java.io.*;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {

    // static method for the ledger home page.
    public static void homePage(Scanner scanner) {


        String commandLedger;

        do {

            System.out.println("Welcome to the Ledger Home Page!");
            System.out.println("What would you like to do?");
            System.out.println("\t[A] Display all entries.");
            System.out.println("\t[D] Display only deposits.");
            System.out.println("\t[P] Display only payments.");
            System.out.println("\t[R] Run a custom search.");
            System.out.println("\t[H] Return to home page.");

            commandLedger = scanner.nextLine().toUpperCase();

            switch (commandLedger) {
                case "A":

                    // all entries (therefore no filter is needed)
                    ledgerDataReports("all reports");
                    break;

                case "D":
                    // deposits only so we filter for deposits only.
                    ledgerDataReports("deposits only");
                    break;

                case "P":
                    // payments only, so we filter for payments only.
                    ledgerDataReports("payments only");
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

    // filters the transactions according to what the user chooses.
    public static void ledgerDataReports(String customReportsFilter) {
        List<String> ledger = new ArrayList<>();



        switch (customReportsFilter) {

            // all reports
            case "all reports":
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.txt"));

                    String input;
                    while ((input = bufferedReader.readLine()) != null) {
                        ledger.add(input);
                    }
                } catch (IOException e) {
                    System.out.println("Error!");
                }
                displayLedgerData(ledger);
                break;

            // deposits only (positive)
            case "deposits only":
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.txt"));

                    String input;
                    while ((input = bufferedReader.readLine()) != null) {

                        String[] splitInput = input.split("\\|");
                        if (!splitInput[splitInput.length - 1].contains("-")) {
                            ledger.add(input);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error!");
                }
                displayLedgerData(ledger);
                break;

                // payments only (negative)
            case "payments only":
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.txt"));

                    String input;
                    while ((input = bufferedReader.readLine()) != null) {
                        String[] splitInput = input.split("\\|");
                        if (splitInput[splitInput.length - 1].contains("-")) {
                            ledger.add(input);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error!");
                }
                displayLedgerData(ledger);
                break;
        }
    }

    public static void displayLedgerData(List<String> ledgerData) {
        // for each loop (iterating through the array)

        for (String transaction : ledgerData) {
            if (transaction != null) {
                System.out.println(transaction);
            }
        }
    }

    // custom reports
    public static void customReports(Scanner scanner) {
        int customSearchChoice;
        do {
            System.out.println("Which custom report would you like to see?");
            System.out.println("\t[1] Month to Date?");
            System.out.println("\t[2] Previous month.");
            System.out.println("\t[3] Year to date.");
            System.out.println("\t[4] Previous year.");
            System.out.println("\t[5] Search by vendor.");
            System.out.println("\t[0] Return to previous screen...");

            customSearchChoice = scanner.nextInt();
            scanner.nextLine();

            LocalDate date = LocalDate.now();
            int month = date.getMonthValue();
            int year = date.getYear();
            List<String> ledger = ledgerDataReports();
            StringBuilder output = new StringBuilder();
