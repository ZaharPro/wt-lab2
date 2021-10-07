package by.bsuir.lab2.repository.impl;

import by.bsuir.lab2.models.Book;

public class XmlBookRepository extends XmlRepository<Book> {
    public XmlBookRepository(String file) {
        super(file);
    }
}
