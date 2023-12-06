package Exceptions;

/**
 * Custom exception class for invalid credentials scenarios.
 * Signals that the provided credentials are invalid.
 */
@SuppressWarnings("serial")
public class InvalidPassCodeException extends Exception {
	/**
     * Constructs an InvalidPassCodeException with a specified error message.
     *
     * @param message The error message describing the exception.
     */
    public InvalidPassCodeException(String message) {
        super(message);
    }
}
