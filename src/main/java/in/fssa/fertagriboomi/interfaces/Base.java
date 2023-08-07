package in.fssa.fertagriboomi.interfaces;

import java.util.List;
import java.util.Set;

import in.fssa.fertagriboomi.model.User;

public interface Base<T> {

	public abstract List<User> findAll();

	public abstract void create(T newUser);

	public abstract void delete(int id);

	public abstract void update(int id, T newT);

	public abstract T findById(int id);

}
