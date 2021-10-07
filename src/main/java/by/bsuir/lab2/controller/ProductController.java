package by.bsuir.lab2.controller;

import by.bsuir.lab2.dto.ProductDto;
import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.repository.impl.XmlProductRepository;
import by.bsuir.lab2.service.ProductService;
import by.bsuir.lab2.service.impl.ProductServiceImpl;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ProductController implements Runnable {
    private final String repositoryPath = "repository.xml";
    private final ProductService service = new ProductServiceImpl(new XmlProductRepository(repositoryPath));
    private final Scanner input = new Scanner(System.in);
    private volatile boolean running;

    /*    private static final Pattern FIND = Pattern.compile("\\s*find(\\s+((-class)|(-id)|(-title)|(-description)|" +
                "(-price)|(-manufacter)|(-volume)|(-min)|(-max))\\s+(\\S+))*");*/
    private static final Class<?>[] products = {
            Product.class,
    };
    private static final Pattern SPLIT = Pattern.compile("\\s+");
    private static final String FIND = "find";
    private static final String CREATE = "create";
    private static final String DELETE = "delete";
    private static final String HELP = "help";


    private static String removeStartEndEscape(String s) {
        int start = s.charAt(0) == '"' ? 1 : 0;
        int end = s.charAt(s.length() - 1) == '"' ? (s.length() - 1) : s.length();
        return s.substring(start, end);
    }

    private Class<?> find(String type) {
        for (Class<?> cls : products) {
            if (cls.getSimpleName().equals(type)) {
                return cls;
            }
        }
        return null;
    }

    @Override
    public void run() {
        running = true;
        do {
            String command = input.nextLine();
            String[] args = SPLIT.split(command);

            if (args.length == 0) {
                continue;
            }

            switch (args[0]) {
                case FIND:
                    if (args.length == 1)
                        break;
                    if ("-all".equals(args[1])) {
                        service.findAll().forEach(System.out::println);
                    } else {
                        Predicate<Product> predicate = Objects::nonNull;
                        for (int i = 1; i < args.length; i++) {
                            switch (args[i]) {
                                case "-type":
                                    if (++i < args.length) {
                                        String type = args[i];
                                        Class<?> productClass = find(type);
                                        if (productClass == null)
                                            break;
                                        predicate = predicate.and(p -> productClass.equals(p.getClass()));
                                    }
                                    break;
                                case "-id":
                                    if (++i < args.length) {
                                        String idStr = args[i];
                                        if ("null".equals(idStr)) {
                                            predicate = predicate.and(p -> Objects.isNull(p.getId()));
                                        } else {
                                            try {
                                                Long id = Long.parseLong(idStr);
                                                predicate = predicate.and(p -> id.equals(p.getId()));
                                            } catch (NumberFormatException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }
                                    break;
                                case "-title":
                                    if (++i < args.length) {
                                        String titleStr = removeStartEndEscape(args[i]);
                                        if ("null".equals(titleStr)) {
                                            predicate = predicate.and(p -> Objects.isNull(p.getTitle()));
                                        } else {
                                            Pattern pattern = Pattern.compile(titleStr);
                                            predicate = predicate.and(p -> pattern.matcher(p.getTitle()).matches());
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                    break;
                case CREATE:
                    String type = input.nextLine();
                    if (Product.class.getSimpleName().equals(type)) {
                        ProductDto productDto = new ProductDto();
                        System.out.println("Enter id");
                        if (input.hasNextLong()) {
                            productDto.setId(input.nextLong());
                        } else {
                            input.nextLine();
                        }
                        System.out.println("Enter title");
                        productDto.setTitle(input.nextLine());

                        System.out.println("Enter description");
                        productDto.setDescription(input.nextLine());

                        System.out.println("Enter price");
                        if (input.hasNextBigDecimal()) {
                            productDto.setPrice(input.nextBigDecimal());
                        } else {
                            input.nextLine();
                        }
                        service.save(productDto);
                    }
                    break;
                case DELETE:
                    break;
                case HELP:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + args[0]);
            }
        } while (running);
    }

    public void stop() {
        running = false;
    }
}
