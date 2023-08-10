package in.fssa.fertagriboomi.interfaces;

import java.util.List;
import java.util.Set;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.User;

public interface Base<T> {

	public abstract List<T> findAll();

	public abstract void delete(int id) throws Exception;

	public abstract void update(int id, T newT) throws Exception;

}
