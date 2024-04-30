package com.ps;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {

// static method for the ledger home page.
    public static void homePage(Scanner scanner) {


        int commandLedger;

        do {

            System.out.println("Welcome to the Ledger Home Page!");
            System.out.println("What would you like to do?");
            System.out.println("\t[1] Display all entries.");
            System.out.println("\t[2] Display only deposits.");
            System.out.println("\t[3] Display only payments.");
            System.out.println("\t[4] Run a custom search.");
            System.out.println("\t[5] Return to home page.");

             commandLedger = scanner.nextInt();

            switch (commandLedger) {
                case 1:

                    // all entries (therefore no filter is needed)
                    ledgerData("all reports");
                    break;

                case 2:
                    // deposits only so we filter for deposits only.
                    ledgerData("deposits only");
                    break;

                case 3:
                    // payments only, so we filter for payments only.
                    ledgerData("payments only");
                    break;

                case 4:
                    // static method for custom reports.
                    customReports(scanner);
                    break;

                case 5:
                    System.out.println("Exiting the ledger...");
                    System.out.println("Returning to previous screen.");

                    break;

                default:
                    System.out.println("You have entered an invalid choice. Please enter a valid choice...");
            }

        } while (commandLedger != 5);
    }

// reads from the transactions file and returns an arraylist.

    public static List<String> ledgerData() {
        List<String> ledgerData = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.txt"));

            String input;
            while ((input = bufferedReader.readLine()) != null) {
                ledgerData.add(input);
            }
        } catch (IOException e) {
            System.out.println("Error!");
        }
        return ledgerData;
    }
