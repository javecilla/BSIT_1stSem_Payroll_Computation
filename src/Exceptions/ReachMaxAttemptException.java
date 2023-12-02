package Exceptions;

/**
 * Custom exception class for reaching maximum attempts.
 * Signals that the maximum number of attempts has been reached.
 */
@SuppressWarnings("serial")
public class ReachMaxAttemptException extends Exception {
	/**
     * Constructs a ReachMaxAttemptException with a specified error message.
     *
     * @param message The error message describing the exception.
     */
	public ReachMaxAttemptException(String message) {
        super(message);
    }
}
