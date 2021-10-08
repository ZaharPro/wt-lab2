package by.bsuir.lab2.repository.impl;

import by.bsuir.lab2.exception.ResourceNotFoundException;
import by.bsuir.lab2.repository.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ArrayRepository<T extends ArrayRepository.Entity> implements Repository<Long, T> {
    public interface Entity extends Cloneable {
        Long getId();

        void setId(Long id);

        Object clone();
    }

    private final AtomicLong seed = new AtomicLong(0);
    private final Supplier<Long> idGenerator = seed::incrementAndGet;
    protected final List<T> data = Collections.synchronizedList(new ArrayList<>());

    public ArrayRepository() {
    }

    public ArrayRepository(Collection<? extends T> ts) {
        data.addAll(ts);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void insert(T t) {
        Objects.requireNonNull(t);
        t.setId(idGenerator.get());
        data.add((T) t.clone());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(T t) {
        Long id = Objects.requireNonNull(Objects.requireNonNull(t).getId());
        ListIterator<T> iter = data.listIterator();
        while (iter.hasNext()) {
            if (id.equals(iter.next().getId())) {
                iter.set((T) t.clone());
                return;
            }
        }
        throw new ResourceNotFoundException(id);
    }

    @Override
    public void delete(T t) {
        Objects.requireNonNull(t);
        deleteById(t.getId());
    }

    @Override
    public void deleteAll(Iterable<T> ts) {
        Objects.requireNonNull(ts);
        data.removeIf(t -> {
            for (T e : ts) {
                if (t.getId().equals(e.getId())) {
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    public void deleteAll() {
        data.clear();
    }

    @Override
    public T findById(Long id) {
        return data.stream()
                .filter(t -> id.equals(t.getId()))
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        data.removeIf(t -> id.equals(t.getId()));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public List<T> findAllById(Iterable<Long> ids) {
        Objects.requireNonNull(ids);
        List<T> list = new ArrayList<>();
        ids.forEach(id -> list.add(findById(id)));
        return list;
    }

    public List<T> findAllByCriteria(Predicate<T> criteria) {
        Objects.requireNonNull(criteria);
        return data.stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }
}