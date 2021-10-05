package by.bsuir.lab2.service;

import by.bsuir.lab2.dto.ProductDto;

import java.util.List;
import java.util.function.Predicate;

public interface ProductService extends Service<Long, ProductDto> {
    List<ProductDto> findAllByCriteria(Predicate<ProductDto> criteria);
}
