package in.fssa.fertagriboomi.interfaces;

import java.util.*;

import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.model.CategoryType;

public interface CategoryTypeInterface {
	public abstract List<CategoryType> findAll() throws DAOException;

	public abstract CategoryType findById(int id) throws Exception;
}
