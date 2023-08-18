package in.fssa.fertagriboomi.exception;

public class ServiceException extends Exception {
	/**
	 * 
	 * @param string
	 */
	public ServiceException(String string) {
		super(string);
	}
	/**
	 * 
	 * @param errorMessage
	 */

	public ServiceException(Exception errorMessage) {
		super(errorMessage);
	}

}
