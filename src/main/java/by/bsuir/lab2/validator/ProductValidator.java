package by.bsuir.lab2.validator;

import by.bsuir.lab2.dto.ProductDto;
import by.bsuir.lab2.repository.ProductRepository;

import java.util.Objects;

public class ProductValidator implements Validator<ProductDto> {
    private final ProductRepository repository;

    public ProductValidator(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(ProductDto dto) {
        Objects.requireNonNull(dto);
    }
}
