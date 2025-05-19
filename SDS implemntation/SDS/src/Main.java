/**
 * Main class that serves as the entry point for the InvestEase application.
 * Handles the initial login/signup menu and user interaction.
 */
import java.util.Scanner;
/**
 * Main class that serves as the entry point for the InvestEase application.*/

public class Main {
    /**
     * The main method that runs the InvestEase application.
     * Provides login, signup and exit options to the user.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Welcome to InvestEase!");
            System.out.println("------------------------");
            System.out.println(" Select 1 to login ");
            System.out.println(" Select 2 to signup ");
            System.out.println(" Select 3 to exit ");
            System.out.println("------------------------");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the leftover newline

                if (choice == 1) {
                    System.out.print("Enter User ID: ");
                    String userID = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    Investor investor = new Investor(userID, password);
                    if (investor.login(userID, password)) {
                        investor.viewDashboard();
                        System.out.println("Logging out");
                        System.out.println("Closing program");
                        break;
                    }

                } else if (choice == 2) {
                    System.out.print("Enter User ID: ");
                    String userID = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter User Name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    Investor investor = new Investor(userID, password, userName, email);
                    investor.signup(userID, password, userName, email);


                } else if (choice == 3) {
                    System.out.println("Thank you for using InvestEase!");
                    break;  // Exit the for loop
                } else {
                    System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.close();
    }
}