package by.bsuir.lab2.repository.impl;

import by.bsuir.lab2.repository.ProductRepository;
import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.exception.ResourceNotFoundException;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ArrayProductRepository implements ProductRepository {
    private static final AtomicLong seed = new AtomicLong(0);
    private static final Supplier<Long> idGenerator = seed::incrementAndGet;
    protected final List<Product> data = Collections.synchronizedList(new ArrayList<>());

    public ArrayProductRepository() {
    }

    public ArrayProductRepository(Collection<? extends Product> products) {
        data.addAll(products);
    }

    @Override
    public void insert(Product product) {
        Objects.requireNonNull(product);
        product.setId(idGenerator.get());
        Product copy = new Product();
        copy.setAll(product);
        data.add(copy);
    }

    @Override
    public void deleteAll(Iterable<Product> products) {
        Objects.requireNonNull(products);
        data.removeIf(product -> {
            for (Product p : products) {
                if (product.getId().equals(p.getId())) {
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    public void update(Product product) {
        Objects.requireNonNull(product);
        Long id = product.getId();
        Product p = findById(id);
        if (p == null) {
            throw new ResourceNotFoundException(id);
        }
        p.setAll(product);
    }

    @Override
    public void delete(Product product) {
        Objects.requireNonNull(product);
        deleteById(product.getId());
    }

    @Override
    public void deleteAll() {
        data.clear();
    }

    @Override
    public Product findById(Long id) {
        return data.stream()
                .filter(product -> id.equals(product.getId()))
                .findAny()
                .orElse(null);
    }

    @Override
    public Product findByTitle(String title) {
        return data.stream()
                .filter(product -> Objects.equals(title, product.getTitle()))
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        data.removeIf(product -> id.equals(product.getId()));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public List<Product> findAllById(Iterable<Long> ids) {
        Objects.requireNonNull(ids);
        List<Product> list = new ArrayList<>();
        ids.forEach(id -> list.add(findById(id)));
        return list;
    }

    @Override
    public List<Product> findAllByCriteria(Predicate<Product> criteria) {
        Objects.requireNonNull(criteria);
        return data.stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }
}