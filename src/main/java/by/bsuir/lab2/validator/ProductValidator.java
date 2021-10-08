package by.bsuir.lab2.validator;

import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.repository.ProductRepository;

import java.util.Objects;

public class ProductValidator implements Validator<Product> {
    private final ProductRepository repository;

    public ProductValidator(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(Product product) {
        Objects.requireNonNull(product);
        Objects.requireNonNull(product.getTitle());
        Objects.requireNonNull(product.getPrice());
    }
}
