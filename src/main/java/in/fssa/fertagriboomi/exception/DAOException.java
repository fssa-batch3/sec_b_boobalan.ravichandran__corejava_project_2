package in.fssa.fertagriboomi.exception;

public class DAOException extends Exception {
/**
 * 
 * @param e
 */
	public DAOException(Exception e) {
		super(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public DAOException(String e) {
		super(e);
	}
}
