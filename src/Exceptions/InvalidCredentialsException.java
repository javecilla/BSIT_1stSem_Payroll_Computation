package Exceptions;

/**
 * Custom exception class for invalid credentials scenarios.
 * Signals that the provided credentials are invalid.
 */
@SuppressWarnings("serial")
public class InvalidCredentialsException extends Exception {
	/**
     * Constructs an InvalidCredentialsException with a specified error message.
     *
     * @param message The error message describing the exception.
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
