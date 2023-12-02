package Services;

import Exceptions.InvalidCredentialsException;

/**
 * The Authentication class handles user authentication logic.
 */
public class AuthenticationService {
    private final String username = "admin";
    private final String password = "1234";

    /**
     * Validates the provided credentials against the predefined username and password.
     *
     * @param inputUsername Provided username
     * @param inputPassword Provided password
     * @return "Login Successfuly!" if credentials are valid
     * @throws InvalidCredentialsException When the provided credentials are invalid
     */
    public String validateLoginCredentials(String inputUsername, String inputPassword)
            throws InvalidCredentialsException {
        // Check if the provided username matches the stored username.
        boolean validUsername = inputUsername.equals(this.username);
        // Check if the provided password matches the stored password.
        boolean validPassword = inputPassword.equals(this.password);

        // If both username and password are valid, return true. else throw an exception
        if (validUsername && validPassword) {
            return "Login Successfuly!";
        } else {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
    }
}
