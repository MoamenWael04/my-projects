import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles zakat (Islamic charity) calculations and reporting for assets.
 * Provides functionality to calculate zakat on assets and generate reports.
 */
public class Zakah_Compliance {
    /** The asset manager instance to operate on */
    private assetManager manager;

    /**
     * Constructor initializes the Zakah_Compliance with an asset manager.
     *
     * @param manager The asset manager containing assets to calculate zakat on
     */
    public Zakah_Compliance(assetManager manager) {
        this.manager = manager;
    }

    /**
     * Displays and handles the zakat menu options.
     * Provides options for calculating zakat and generating reports.
     */
    public void zakatMenu() {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("=== Zakat & Compliance ===");
            System.out.println("1. Calculate Zakat for All Assets");
            System.out.println("2. Calculate Zakat for a Specific Asset");
            System.out.println("3. Download Zakat Report (.txt)");
            System.out.println("4. Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    calculateZakatForAllAssets();
                    break;
                case 2:
                    calculateZakatForAssetByID();
                    break;
                case 3:
                    generateZakatTextReport();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Calculates and displays zakat due on all assets in the portfolio.
     * Provides a summary of zakat calculations for each asset and a total.
     */
    public void calculateZakatForAllAssets() {
        ArrayList<Asset> assets = manager.getAssets();
        if (assets.isEmpty()) {
            System.out.println("No portfolio found. Please add your investments first.");
            return;
        }

        double totalZakat = 0;
        System.out.println("Zakat Calculation Summary:");
        for (Asset asset : assets) {
            double zakat = asset.calculateZakat();
            System.out.println("Asset ID: " + asset.assetID + ", Type: " + asset.type +
                    ", Zakat Due: " + zakat);
            System.out.println("-----------------------------");
            totalZakat += zakat;
        }

        System.out.println("Total Zakat Due: " + totalZakat);
    }

    /**
     * Calculates and displays zakat due on a specific asset identified by ID.
     * Prompts the user for an asset ID and displays the zakat calculation.
     */
    private void calculateZakatForAssetByID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Asset ID: ");
        String assetID = scanner.nextLine();

        ArrayList<Asset> assets = manager.getAssets();
        boolean found = false;

        for (Asset asset : assets) {
            if (asset.getAssetID().equalsIgnoreCase(assetID)) {
                double zakat = asset.calculateZakat();
                System.out.println("Asset ID: " + asset.assetID);
                System.out.println("Type: " + asset.type);
                System.out.println("Quantity: " + asset.quantity);
                System.out.println("Price: " + asset.price);
                System.out.println("Zakat Due: " + zakat);
                System.out.println("-----------------------------");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Asset not found. Please check the ID and try again.");
        }
    }

    /**
     * Generates a text report of zakat calculations for all assets.
     * Saves the report to a file named "zakat_report.txt".
     */
    public void generateZakatTextReport() {
        ArrayList<Asset> assets = manager.getAssets();

        if (assets.isEmpty()) {
            System.out.println("No portfolio found. Please add your investments first.");
            return;
        }

        double totalZakat = 0;
        String fileName = "zakat_report.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=== Zakat Report ===\n");
            for (Asset asset : assets) {
                double zakat = asset.calculateZakat();
                writer.write("Asset ID: " + asset.assetID + "\n");
                writer.write("Type: " + asset.type + "\n");
                writer.write("Quantity: " + asset.quantity + "\n");
                writer.write("Price: " + asset.price + "\n");
                writer.write("Zakat Due: " + zakat + "\n");
                writer.write("-----------------------------\n");
                totalZakat += zakat;
            }
            writer.write("Total Zakat Due: " + totalZakat + "\n");

            System.out.println("Zakat report saved as: " + fileName);
        } catch (IOException e) {
            System.out.println("Error generating Zakat report: " + e.getMessage());
        }
    }
}