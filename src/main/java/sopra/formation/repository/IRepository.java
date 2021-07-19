package sopra.formation.repository;

import java.util.List;

public interface IRepository<T, PK> {
	List<T> findAll();

	T findById(PK id);

	T save(T obj);

	void delete(T obj);
}
