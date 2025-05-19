import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages a collection of assets in an investment portfolio.
 * Provides functionality to add, edit, remove, and display assets.
 */
public class assetManager {
    /** The list of assets in the portfolio */
    private ArrayList<Asset> assets = new ArrayList<>();

    /**
     * Constructor initializes the asset manager with some sample assets.
     */
    public assetManager()
    {
        assets.add(new Asset().setAsset("A001", "gold", 2.5, 3000.0));
        assets.add(new Asset().setAsset("A002", "crypto", 1.2, 18000.0));
        assets.add(new Asset().setAsset("A003", "stock", 10, 150.0));
        assets.add(new Asset().setAsset("A004", "real estate", 1, 250000.0));
        assets.add(new Asset().setAsset("A005", "gold", 5, 2900.0));

    }

    /**
     * Adds a new asset to the portfolio after verification.
     *
     * @param asset The asset to be added
     * @return true if the asset was successfully added, false otherwise
     */
    public boolean addAsset(Asset asset) {
        if(asset.verifyAsset())
        {
            assets.add(asset);
            System.out.println("Asset added successfully");
            return true;
        }
        else
            return false;
    }

    /**
     * Finds and displays an asset by its ID.
     *
     * @param assetID The ID of the asset to find
     */
    public void pickAsset(String assetID) {
        boolean flag = false; // track if asset was found
        for (Asset asset : assets) {
            if (asset.getAssetID().equals(assetID)) {
                System.out.println("Asset Details:");
                System.out.println("The asset ID : " + asset.assetID + " Asset type : " + asset.type + " Asset quantity : " + asset.quantity + " Asset price : " + asset.price);
                System.out.println("--------------------------------");
                flag = true; // Set flag to true when asset is found
                break; // Exit loop once found
            }
        }
        if (!flag) {
            System.out.println("Asset not found");
        }
    }

    /**
     * Displays all assets in the portfolio.
     */
    public void displaylist()
    {
        if (assets.isEmpty()) {
            System.out.println("Asset list is empty");
        } else {
            System.out.println();
            System.out.println("Asset List:");
            for (Asset asset : assets)
            {
                asset.printAsset();
            }
        }
    }

    /**
     * Edits an existing asset identified by its ID.
     * Allows modification of the asset type, quantity, or price.
     *
     * @param assetID The ID of the asset to edit
     */
    public void editasset(String assetID) {
        boolean found = false; // Track if asset was found
        for (Asset asset : assets) {
            if (asset.getAssetID().equals(assetID)) { // Added null check & fixed string comparison
                found = true; // Mark as found
                System.out.println("What do you want to edit:");
                System.out.println("1- type");
                System.out.println("2- quantity");
                System.out.println("3- price");
                Scanner scanner = new Scanner(System.in);
                int choice = 0;
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    System.out.println("Enter new type:");
                    scanner.nextLine(); // Consume newline
                    String newtype = scanner.nextLine();
                    asset.type = newtype;
                    while(!asset.verifyAsset())
                    {
                        System.out.println("Invalid type, enter a valid type");
                        newtype = scanner.nextLine();
                        asset.type = newtype;
                    }
                    System.out.println("Type updated successfully");
                }
                else if (choice == 2) {
                    System.out.println("Enter new quantity:");
                    double newquantity = scanner.nextDouble();
                    asset.quantity = newquantity;
                    while(!asset.verifyAsset())
                    {
                        System.out.println("Invalid quantity, enter a positive number");
                        newquantity = scanner.nextDouble();
                        asset.quantity = newquantity;
                    }
                    System.out.println("Quantity updated successfully");
                }
                else if (choice == 3) {
                    System.out.println("Enter new price:");
                    double newprice = scanner.nextDouble();
                    asset.price = newprice;
                    while(!asset.verifyAsset())
                    {
                        System.out.println("Invalid price, enter a positive number");
                        newprice = scanner.nextDouble();
                        asset.price = newprice;
                    }
                    System.out.println("Price updated successfully");
                }
                else {
                    System.out.println("Invalid choice");
                }
                break; // Exit loop once found and edited
            }
        }

        if (!found) {
            System.out.println("Asset not found");
        }
    }

    /**
     * Removes an asset from the portfolio.
     *
     * @param assetID The ID of the asset to remove
     */
    public void removeAsset(String assetID) {
        boolean found = false;
        for (Asset asset : assets) {
            if (asset.getAssetID().equals(assetID)) { // Added null check & fixed string comparison
                assets.remove(asset);
                System.out.println("Asset removed successfully");
                found = true;
                return;
            }
        }

        if (!found) {
            System.out.println("Asset not found");
        }
    }

    /**
     * Gets all assets in the portfolio.
     *
     * @return The list of assets
     */
    public ArrayList<Asset> getAssets() {
        return assets;
    }
}