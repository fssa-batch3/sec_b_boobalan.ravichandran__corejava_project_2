package in.fssa.fertagriboomi.exception;

public class ServiceException extends Exception {
	public ServiceException(String string) {
		super(string);
	}

	public ServiceException(Exception errorMessage) {
		super(errorMessage);
	}

}
