package in.fssa.fertagriboomi.interfaces;

import java.util.*;

import in.fssa.fertagriboomi.model.CategoryType;

public interface CategoryTypeInterface {
	public abstract List<CategoryType> findAll();

	public abstract CategoryType findById(int id) throws Exception;
}
