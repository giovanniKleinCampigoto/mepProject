package repository;

import java.util.List;


public interface RepositoryInterface <T>{
	
	public T add(T object);
	public List<T> getAll();
	public T getById(Integer i);
	public T update(T t);
	public T remove(Integer i);
}
