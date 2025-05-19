/**
 * Handles the generation and display of financial reports and insights.
 * Provides functionality to display and export investment reports.
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reports_Insights class that extends the assetManager class and handles
 * financial reports and insights for users of the investment system.
 */

public class Reports_Insights {
    private assetManager manager;

    /**
     * Constructor for Reports_Insights class.
     *
     * @param manager The asset manager containing assets to report on
     */
    public Reports_Insights(assetManager manager) {
        this.manager = manager;
    }

    /**
     * Displays the reports menu and handles user interactions.
     */
    public void reportMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n=== Financial Reports & Insights ===");
            System.out.println("1. Display Investment Report");
            System.out.println("2. Export Report (PDF / Excel)");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        displayReport();
                        break;

                    case 2:
                        displayReport();
                        System.out.println();
                        System.out.print("Enter format (PDF or Excel): ");
                        String format = scanner.nextLine();
                        exportReport(format);
                        break;

                    case 3:
                        System.out.println("Returning to Main Menu...");
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Displays an investment summary report showing all assets and their values.
     */
    public void displayReport() {
        ArrayList<Asset> assets = manager.getAssets();

        if (assets.isEmpty()) {
            System.out.println("No financial data found. Please add assets first.");
            return;
        }

        System.out.println("=== Investment Summary Report ===");
        double totalValue = 0;
        for (Asset asset : assets) {
            double value = asset.price * asset.quantity;
            System.out.println("Asset ID: " + asset.assetID +
                    ", Type: " + asset.type +
                    ", Quantity: " + asset.quantity +
                    ", Price: " + asset.price +
                    ", Value: " + value);
            System.out.println("-----------------------------");
            totalValue += value;
        }
        System.out.println("Total Portfolio Value: " + totalValue);
    }

    /**
     * Exports the investment report to a file in the specified format.
     *
     * @param format The format to export in ("PDF" or "Excel")
     */
    public void exportReport(String format) {
        ArrayList<Asset> assets = manager.getAssets();
        if (assets.isEmpty()) {
            System.out.println("No financial data to export.");
            return;
        }

        String filename;
        if (format.equalsIgnoreCase("PDF")) {
            filename = "financial_report.txt"; // simulate PDF
        } else if (format.equalsIgnoreCase("Excel")) {
            filename = "financial_report.csv"; // simulate Excel
        } else {
            System.out.println("Unsupported format. Please choose PDF or Excel.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Investment Report\n");
            writer.write("AssetID, Type, Quantity, Price, Total Value\n");

            double totalValue = 0;
            for (Asset asset : assets) {
                double value = asset.quantity * asset.price;
                writer.write(asset.assetID + ", " + asset.type + ", " + asset.quantity + ", " +
                        asset.price + ", " + value + "\n");
                writer.write("-----------------------------\n");
                totalValue += value;
            }
            writer.write("Total Portfolio Value: ," + totalValue + "\n");
            System.out.println("Report exported successfully to: " + filename);
        } catch (IOException e) {
            System.out.println("Error exporting report: " + e.getMessage());
        }
    }
}