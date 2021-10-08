package by.bsuir.lab2.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Book extends Product {
    private String author;

    public Book() {
    }

    public Book(Long id, String title, String description, BigDecimal price, String author) {
        super(id, title, description, price);
        setAuthor(author);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", author='" + author + '\'' +
                '}';
    }
}
