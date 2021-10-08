package by.bsuir.lab2.controller;

import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.repository.impl.XmlProductRepository;
import by.bsuir.lab2.service.ProductService;
import by.bsuir.lab2.service.impl.ProductServiceImpl;

import java.util.stream.Collectors;

public class ProductController {
    public static final String FIND_ALL_PRODUCTS = "find_all";
    public static final String FIND_ALL_TEAPOTS = "teapots";
    public static final String FIND_CHEAPEST_PRODUCT = "cheap";
    public static final String HELP = "help";
    public static final String EXIT = "exit";

    private static final String HELP_MSG =
            ProductController.FIND_ALL_PRODUCTS + " => find all products\n" +
                    ProductController.FIND_ALL_TEAPOTS + " => find all teapots\n" +
                    ProductController.FIND_CHEAPEST_PRODUCT + " => find cheapest product\n" +
                    ProductController.HELP + " => print help\n" +
                    ProductController.EXIT + " => exit";

    private final ProductService service;

    public ProductController(String repositoryPath) {
        service = new ProductServiceImpl(new XmlProductRepository(repositoryPath));
    }

    public Response handle(String requestToken) {
        switch (requestToken) {
            case FIND_ALL_PRODUCTS:
                String collect = service.findAll().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("\n", "{", "}"));
                return new Response(200, collect);
            case FIND_ALL_TEAPOTS:
                String c = service.findAllTeapots().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("\n", "{", "}"));
                return new Response(200, c);
            case FIND_CHEAPEST_PRODUCT:
                Product product = service.findCheapestProduct();
                return product == null ?
                        new Response(404, "not found") :
                        new Response(200, product.toString());
            case HELP:
                return new Response(200, HELP_MSG);
            case EXIT:
                return new Response(200, EXIT);
        }

        return new Response(400, String.format("Unexpected token: %s, use '%s'", requestToken, HELP));
    }
}
