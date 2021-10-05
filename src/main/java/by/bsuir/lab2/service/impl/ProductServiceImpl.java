package by.bsuir.lab2.service.impl;

import by.bsuir.lab2.dto.ProductDto;
import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.repository.ProductRepository;
import by.bsuir.lab2.service.ProductService;
import by.bsuir.lab2.validator.ProductValidator;

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
    public void save(ProductDto dto) {
        validator.validate(dto);
        if (dto.getId() == null) {
            repository.insert(ProductDto.mapToObj(dto));
        } else {
            repository.update(ProductDto.mapToObj(dto));
        }
    }

    @Override
    public void delete(ProductDto dto) {
        repository.delete(ProductDto.mapToObj(dto));
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        Product p = repository.findById(id);
        return p == null ?
                Optional.empty() :
                Optional.of(ProductDto.mapToDto(p));
    }

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll().stream()
                .map(ProductDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids).stream()
                .map(ProductDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductDto> findAllByCriteria(Predicate<ProductDto> criteria) {
        Objects.requireNonNull(criteria);
        return repository.findAllByCriteria(product -> criteria.test(
                ProductDto.mapToDto(product))).stream()
                .map(ProductDto::mapToDto)
                .collect(Collectors.toList());
    }
}
