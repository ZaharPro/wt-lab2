package by.bsuir.lab2.app;

import by.bsuir.lab2.controller.ConsoleProductController;

public class ConsoleApp {
    public static final String PATH = "C:\\repository.xml";
    public static void main(String[] args) {
        ConsoleProductController controller = new ConsoleProductController(PATH);
        controller.run();
    }
}
