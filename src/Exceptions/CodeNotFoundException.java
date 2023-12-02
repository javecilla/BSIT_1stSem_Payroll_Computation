package Exceptions;

/**
 * Custom exception class for code not found scenarios.
 * Signals that a specific code couldn't be found.
 */
@SuppressWarnings("serial")
public class CodeNotFoundException extends Exception{
	/**
     * Constructs a CodeNotFoundException with a specified error message.
     *
     * @param message The error message describing the exception.
     */
	public CodeNotFoundException(String message) {
        super(message );
    }
}
