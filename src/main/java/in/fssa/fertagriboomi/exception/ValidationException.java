package in.fssa.fertagriboomi.exception;

public class ValidationException extends Exception {

	/**
	 * 
	 * @param errorMessage
	 */
	public ValidationException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 * @param errorMessage
	 */
	public ValidationException(Exception errorMessage) {
		super(errorMessage);
	}


	


}
