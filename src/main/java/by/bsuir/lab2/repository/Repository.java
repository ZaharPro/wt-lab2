package by.bsuir.lab2.repository;

import java.util.Objects;

public interface Repository<ID, T> {
    void insert(T t);
    void update(T t);
    void delete(T t);
    void deleteAll();

    T findById(ID id);
    Iterable<T> findAll();
    Iterable<T> findAllById(Iterable<ID> ids);
    void deleteById(ID id);

    default void insertAll(Iterable<T> ts) {
        Objects.requireNonNull(ts);
        ts.forEach(this::insert);
    }
    default void updateAll(Iterable<T> ts) {
        Objects.requireNonNull(ts);
        ts.forEach(this::update);
    }
    default void deleteAll(Iterable<T> ts) {
        Objects.requireNonNull(ts);
        ts.forEach(this::delete);
    }
}
