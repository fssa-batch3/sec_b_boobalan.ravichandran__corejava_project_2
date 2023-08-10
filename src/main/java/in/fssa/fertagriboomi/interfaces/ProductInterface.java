package in.fssa.fertagriboomi.interfaces;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Product;

public interface ProductInterface extends Base<Product> {

	public abstract int create(Product newUser) throws Exception;

	public abstract Product findById(int id) throws ValidationException, DAOException;

}
