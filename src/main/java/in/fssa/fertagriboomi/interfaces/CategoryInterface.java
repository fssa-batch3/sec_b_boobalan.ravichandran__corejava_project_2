package in.fssa.fertagriboomi.interfaces;

import java.util.List;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Category;

public interface CategoryInterface {
	public abstract List<Category> findAll();

	public abstract Category findById(int id) throws ValidationException, DAOException;

	public abstract List<Category> findCategoriesByCategoryTypeId(int typeId) throws DAOException;

}
