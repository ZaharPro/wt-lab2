package by.bsuir.lab2.app;

import by.bsuir.lab2.models.Book;
import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.models.Teapot;
import by.bsuir.lab2.repository.impl.XmlProductRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

public class BuildRepository {
    public static void main(String[] args) throws IOException {
        XmlProductRepository repository = new XmlProductRepository(ConsoleApp.REPOSITORY_PATH);
        repository.insertAll(Arrays.asList(
                new Book(1L, "The Jungle Book",
                        "\"Книга джунглей\" (англ. The Jungle Book) - сборник рассказов Редьярда Киплинга,\n" +
                                "в рассказах участвует Маугли - ребёнок, воспитанный волками.",
                        BigDecimal.valueOf(20.30), "Редьярд Киплинг"),

                new Product(2L, "Сетевой фильтр APC P43B-RS",
                        "Основные характеристики\n" +
                        "Тип=" +
                        "сетевой фильтр\n" +
                        "Номинальное выходное напряжение=" +
                        "230 В\n" +
                        "Общее кол-во розеток=" +
                        "4\n" +
                        "Цвет=" +
                        "черный",
                        BigDecimal.valueOf(50.39)),

                new Teapot(3L, "Polly Люкс", "Хороший чайник",
                        BigDecimal.valueOf(65), 1.7, "Китай"),

                new Book(4L, "Язык телодвижений",
                        "Новая книга Аллана и Барбары Пиз написана на основе их знаменитого бестселлера \n" +
                                "\"Язык телодвижений\", впервые вышедшего в свет в 1978 году, а затем переведенного на \n" +
                                "48 языков и разошедшегося огромными тиражами: общее количество проданных экземпляров \n" +
                                "превысило 20 миллионов.",
                        BigDecimal.valueOf(16.03), "Барбара Пиз, Аллан Пиз"),


                new Teapot(5L, "Заварочный чайник BergHOFF", "Фарфор керамика",
                        BigDecimal.valueOf(105.81), 1.14,
                        "Ботербосстрат 6/1 Индастриетерриен Золдер-Ламмен 2114 3550 Хёсден-Золдер Бельгия / \n" +
                                "Boterbosstraat 6/1 Industrieterrein Zolder-Lummen 2114 3550 Heusden-Zolder Belgium"),

                new Teapot(6L, "Заварочный чайник Galaxy GL 9311", "Хороший чайник",
                        BigDecimal.valueOf(19), 0.8, "China")
                ));
        repository.flush();
    }
}
