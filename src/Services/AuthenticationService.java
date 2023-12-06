package Services;

import Exceptions.InvalidPassCodeException;
/**
 * The Authentication class handles user authentication logic.
 */
public class AuthenticationService {
    private final String passCode = "1234";
    /**
     * Validates the provided credentials against the predefined passcode
     *e
     * @param passcode Provided passcode
     * @return "success" if credentials are valid
     * @throws InvalidPassCodeException When the provided credentials are invalid
     */    
    public String validatePassCode(String passcode) 
    		throws InvalidPassCodeException {
    	// check if the provided passcode matches to the stored passcode
    	if(passcode.equals(this.passCode)) {
    		return "success";
    	} else {
    		throw new InvalidPassCodeException("Invalid Passcode!");
    	}
    }
}
