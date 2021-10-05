package by.bsuir.lab2.repository.impl;

import by.bsuir.lab2.models.Product;
import by.bsuir.lab2.exception.XmlParseException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.List;

public class XmlProductRepository extends ArrayProductRepository implements Flushable {
    private final XmlMapper mapper = new XmlMapper();
    private final File location;

    public XmlProductRepository(String file) {
        this.location = new File(file);

        try {
            @SuppressWarnings("unchecked")
            List<Product> list = mapper.readValue(location, List.class);
            data.addAll(list);
        } catch (IOException | ClassCastException e) {
            throw new XmlParseException(e);
        }
    }

    @Override
    public void flush() throws IOException {
        mapper.writeValue(location, data);
    }

    @Override
    protected void finalize() throws Throwable {
        flush();
    }
}
