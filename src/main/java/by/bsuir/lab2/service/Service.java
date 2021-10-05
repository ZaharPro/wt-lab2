package by.bsuir.lab2.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface Service<ID, T> {
    void save(T t);
    void delete(T t);
    void deleteAll();

    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findAllById(Iterable<ID> ids);
    void deleteById(ID id);

    default void saveAll(Iterable<T> ts) {
        Objects.requireNonNull(ts);
        ts.forEach(this::save);
    }
    default void deleteAll(Iterable<T> ts) {
        Objects.requireNonNull(ts);
        ts.forEach(this::delete);
    }
}
