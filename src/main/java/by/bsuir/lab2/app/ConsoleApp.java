package by.bsuir.lab2.app;

import by.bsuir.lab2.controller.ProductController;
import by.bsuir.lab2.controller.Response;

import java.util.Scanner;

public class ConsoleApp {
    public static final String REPOSITORY_PATH = "C:\\repository.xml";

    public static void main(String[] args) {
        ProductController controller = new ProductController(REPOSITORY_PATH);
        Scanner input = new Scanner(System.in);

        System.out.println(controller.handle(ProductController.HELP).getBody());
        boolean running = true;
        do {
            String request = input.nextLine();
            Response response = controller.handle(request);
            int code = response.getCode();
            if (code < 400) {
                if (ProductController.EXIT.equals(response.getBody())) {
                    running = false;
                } else {
                    System.out.println(response.getBody());
                }
            } else {
                System.err.println(response.getBody());
            }
        } while (running);
    }
}
