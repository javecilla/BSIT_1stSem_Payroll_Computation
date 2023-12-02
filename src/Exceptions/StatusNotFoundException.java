package Exceptions;

/**
 * Custom exception class for status not found.
 * Signals that a status associated with an employee was not found.
 */
@SuppressWarnings("serial")
public class StatusNotFoundException extends Exception {
	/**
     * Constructs a StatusNotFoundException with a specified error message.
     *
     * @param message The error message describing the exception.
     */
	public StatusNotFoundException(String message) {
        super(message);
    }
}
