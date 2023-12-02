package Exceptions;

/**
 * Custom exception class for invalid input scenarios.
 * Signals that the provided input is invalid.
 */
@SuppressWarnings("serial")
public class InvalidInputException extends Exception {
	/**
     * Constructs an InvalidInputException with a specified error message.
     *
     * @param message The error message describing the exception.
     */
	public InvalidInputException(String message) {
        super(message);
    }
}
