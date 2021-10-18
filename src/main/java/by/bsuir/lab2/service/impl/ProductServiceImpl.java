package by.bsuir.lab2.service.impl;

import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.models.Teapot;
import by.bsuir.lab2.repository.ProductRepository;
import by.bsuir.lab2.service.ProductService;
import by.bsuir.lab2.validator.ProductValidator;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductValidator validator;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = Objects.requireNonNull(repository);
        this.validator = new ProductValidator(repository);
    }

    @Override
    public void save(Product dto) {
        validator.validate(dto);
        if (dto.getId() == null) {
            repository.insert(dto);
        } else {
            repository.update(dto);
        }
    }

    @Override
    public void delete(Product dto) {
        repository.delete(dto);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAllByCriteria(Predicate<Product> criteria) {
        return repository.findAllByCriteria(criteria);
    }

    @Override
    public Product findCheapestProduct() {
        return findAll().stream()
                .filter(Objects::nonNull)
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    @Override
    public List<Teapot> findAllTeapots() {
        return findAll().stream()
                .filter(Objects::nonNull)
                .filter(product -> product.getClass() == Teapot.class)
                .map(product -> ((Teapot) product))
                .collect(Collectors.toList());
    }
}
