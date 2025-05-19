/**
 * Represents an investment asset in a portfolio.
 * Handles asset data storage, validation, and zakat calculation.
 */
public class Asset {
    /** The unique identifier for the asset */
    public String assetID;
    /** The type of asset (crypto, gold, stock, or real estate) */
    public String type;
    /** The quantity of the asset held */
    public double quantity;
    /** The unit price of the asset */
    public double price;

    /**
     * Sets the properties of the asset.
     *
     * @param assetID The unique identifier for the asset
     * @param type The type of asset (crypto, gold, stock, or real estate)
     * @param quantity The quantity of the asset held
     * @param price The unit price of the asset
     * @return The asset object with updated properties
     */
    public Asset setAsset(String assetID, String type, double quantity, double price) {
        this.assetID = assetID;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        return this;
    }

    /**
     * Verifies that the asset properties are valid.
     * Asset ID must be alphanumeric.
     * Type must be one of: crypto, gold, stock, or real estate.
     * Quantity must be positive.
     * Price must be non-negative.
     *
     * @return true if the asset is valid, false otherwise
     */
    public boolean verifyAsset() {
        boolean isValid = true;

        if (!assetID.matches("[a-zA-Z0-9]+")) {
            System.out.println("Invalid Asset ID: must be alphanumeric.");
            isValid = false;
        }

        if (!(type.equalsIgnoreCase("crypto") ||
                type.equalsIgnoreCase("gold") ||
                type.equalsIgnoreCase("stock") ||
                type.equalsIgnoreCase("real estate"))) {
            System.out.println("Invalid Asset Type: must be one of crypto, gold, stock, or real estate.");
            isValid = false;
        }

        if (quantity <= 0) {
            System.out.println("Invalid Quantity: must be greater than 0.");
            isValid = false;
        }

        if (price < 0) {
            System.out.println("Invalid Price: cannot be negative.");
            isValid = false;
        }

        return isValid;
    }

    /**
     * Gets the asset ID.
     *
     * @return The asset ID
     */
    public String getAssetID() {
        return assetID;
    }

    /**
     * Prints the asset details to the console.
     */
    public void printAsset() {
        System.out.println("The asset ID : " + assetID);
        System.out.println("The asset type : " + type);
        System.out.println("The asset quantity : " + quantity);
        System.out.println("The asset price : " + price);
        System.out.println("------------------------------");
    }

    /**
     * Calculates the zakat (Islamic charity) due on this asset.
     * Different asset types have different zakat rules:
     * - Gold, crypto, and stocks: 2.5% of total value
     * - Real estate: Only taxable if held for resale (returns 0 by default)
     *
     * @return The amount of zakat due on the asset
     */
    public double calculateZakat() {
        double totalValue = quantity * price;

        switch (type.toLowerCase()) {
            case "gold":
            case "crypto":
            case "stock":
                return totalValue * 0.025;

            case "real estate":
                // Assume only resale properties are zakatable
                System.out.println("Note: Zakat is only due on real estate held for resale.");
                return 0;

            default:
                System.out.println("Unknown asset type for zakat calculation.");
                return 0;
        }
    }
}