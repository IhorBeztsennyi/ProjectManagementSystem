package ua.goit.repository;

public interface Repository<T> {

    T getById (Integer id);

    void save(T t);

    void delete(T t);

    int update(T t);
}
