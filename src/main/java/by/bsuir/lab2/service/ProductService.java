package by.bsuir.lab2.service;

import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.models.Teapot;

import java.util.List;
import java.util.function.Predicate;

public interface ProductService extends Service<Long, Product> {
    List<Product> findAllByCriteria(Predicate<Product> criteria);

    Product findCheapestProduct();

    List<Teapot> findAllTeapots();
}
