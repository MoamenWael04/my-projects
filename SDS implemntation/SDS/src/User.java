/**
 * Base class representing a user in the system.
 * Contains user identification information and authentication methods.
 */
public class User {
    public String UserID;
    public String Username;
    public String email;
    public String password;


    /**
     * Constructor for User with all details.
     *
     * @param userID The unique ID for this user
     * @param userName The display name for this user
     * @param email The email address for this user
     * @param password The password for this user's account
     */
    public User(String userID, String userName, String email, String password) {
        this.UserID = userID;
        this.Username = userName;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor for User with just login credentials.
     *
     * @param userID The unique ID for this user
     * @param password The password for this user's account
     */
    public User(String userID, String password) {
        this.UserID = userID;
        this.password = password;
    }

    /**
     * Attempts to log in a user with the provided credentials.
     *
     * @param UID The user ID to check
     * @param pass The password to check
     * @return true if login is successful, false otherwise
     */
    public boolean login(String UID, String pass) {
        if (Authentcation.checkpassword(UID,pass))
        {
            System.out.println("Login Successful");
            return true;
        }
        else
        {
            System.out.println("Login Failed");
            return false;
        }
    }

    /**
     * Signs up a new user with the provided information.
     *
     * @param UID The user ID for the new user
     * @param pass The password for the new user
     * @param Uname The user name for the new user
     * @param Email The email for the new user
     * @return A new User object if signup is successful, null otherwise
     */
    public User signup(String UID, String pass, String Uname, String Email) {
        return Authentcation.createUser(UID, pass, Uname, Email);
    }
}