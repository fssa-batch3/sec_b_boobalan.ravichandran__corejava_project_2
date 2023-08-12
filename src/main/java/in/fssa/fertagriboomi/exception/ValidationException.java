package in.fssa.fertagriboomi.exception;

public class ValidationException extends Exception {

	public ValidationException(String errorMessage) {
		super(errorMessage);
	}

	
	public ValidationException(Exception errorMessage) {
		super(errorMessage);
	}


	


}
