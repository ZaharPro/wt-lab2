package by.bsuir.lab2.repository.impl;

import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.repository.ProductRepository;

import java.util.Comparator;
import java.util.Objects;

public class XmlProductRepository extends XmlRepository<Product> implements ProductRepository {
    public XmlProductRepository(String file) {
        super(file);
    }

    @Override
    public Product findByTitle(String title) {
        return data.stream()
                .filter(product -> Objects.equals(title, product.getTitle()))
                .findAny()
                .orElse(null);
    }

    public Product findCheapestProduct() {
        return data.stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }
}
