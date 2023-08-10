package in.fssa.fertagriboomi.exception;

public class DAOException extends Exception {

	public DAOException(Exception e) {
		super(e);
	}
	
	public DAOException(String e) {
		super(e);
	}
}
