/**
 * Represents a financial goal in the investment system.
 * Contains properties and methods to manage individual goals.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * goal class that manages financial goals for users of the investment system.
 * Allows users to add, list, and interact with financial goals.
 */

public class Goal
{
    private String goalType;
    private double targetAmount;
    private String deadline;
    private double currentProgress;

    /**
     * Sets the properties of a financial goal.
     *
     * @param goalType The type of financial goal (e.g., Retirement, Wealth Accumulation)
     * @param targetAmount The target amount to reach for this goal
     * @param deadline The deadline date for achieving this goal in YYYY-MM-DD format
     * @param currentProgress The current amount achieved toward this goal
     * @return This Goal object for method chaining
     */
    public Goal setGoal(String goalType, double targetAmount, String deadline, double currentProgress)
    {
        this.goalType = goalType;
        this.targetAmount = targetAmount;
        this.deadline = deadline;
        this.currentProgress = currentProgress;
        return this;
    }

    /**
     * Validates the properties of this financial goal.
     *
     * @return true if all properties are valid, false otherwise
     */
    public boolean verifyGoal()
    {
        boolean isValid = true;
        if (!(goalType.equalsIgnoreCase("retirement") || goalType.equalsIgnoreCase("wealth accumulation")))
        {
            System.out.println("Invalid goal type. Must be 'Retirement' or 'Wealth Accumulation'.");
            isValid = false;
        }
        if (targetAmount <= 0)
        {
            System.out.println("Target amount must be greater than 0.");
            isValid = false;
        }
        if (currentProgress < 0)
        {
            System.out.println("Current progress must be >= 0.");
            isValid = false;
        }
        // Deadline validation: must be valid date format YYYY-MM-DD
        try {
            LocalDate.parse(deadline, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid deadline format. Use YYYY-MM-DD.");
            isValid = false;
        }

        return isValid;
    }

    /**
     * Prints the details of this financial goal.
     */
    public void printGoal()
    {
        System.out.println("Goal Type: " + goalType);
        System.out.println("Target Amount: " + targetAmount);
        System.out.println("Deadline: " + deadline);
        System.out.println("Current Progress: " + currentProgress);
        System.out.println("------------------------------------");
    }
}