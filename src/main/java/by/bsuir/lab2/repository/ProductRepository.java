package by.bsuir.lab2.repository;

import by.bsuir.lab2.models.Product;

import java.util.List;
import java.util.function.Predicate;

public interface ProductRepository extends Repository<Long, Product> {
    Product findByTitle(String title);

    List<Product> findAll();

    List<Product> findAllById(Iterable<Long> ids);

    List<Product> findAllByCriteria(Predicate<Product> criteria);
}
