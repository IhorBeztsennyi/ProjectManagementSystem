package ua.goit.repository;

import java.util.Optional;

public interface Repository<T> {

    void save(T t);

    Optional<T> findById(Integer id);

    void remove(Integer id);

    int update(Integer id, T t);

}
