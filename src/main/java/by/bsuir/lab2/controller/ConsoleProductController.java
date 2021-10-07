package by.bsuir.lab2.controller;

import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.repository.impl.XmlProductRepository;
import by.bsuir.lab2.service.ProductService;
import by.bsuir.lab2.service.impl.ProductServiceImpl;

import java.util.Scanner;

public class ConsoleProductController implements Runnable {
    private final ProductService service;
    private final Scanner input;

    public ConsoleProductController(String repositoryPath) {
        service = new ProductServiceImpl(new XmlProductRepository(repositoryPath));
        input = new Scanner(System.in);
    }

    @Override
    public void run() {
        boolean running = true;
        do {
            System.out.println("Press 0 to exit\n" +
                    "Press 1 to find all products\n" +
                    "Press 2 to find all teapots\n" +
                    "Press 3 to find cheapest product"
            );
            String s = input.nextLine();
            switch (s) {
                case "0":
                    running = false;
                    break;
                case "1":
                    service.findAll().forEach(System.out::println);
                    break;
                case "2":
                    service.findAllTeapots().forEach(System.out::println);
                    break;
                case "3":
                    Product product = service.findCheapestProduct();
                    System.out.println(product == null ? "not found" : product.toString());
                    break;

                default:
                    System.err.println("Unexpected token: " + s);
            }
        } while (running);
    }
}
