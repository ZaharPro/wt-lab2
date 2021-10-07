package by.bsuir.lab2.repository.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.*;
import java.io.*;

public class XmlRepository<T extends ArrayRepository.Entity> extends ArrayRepository<T> implements Flushable {
    private final XmlMapper mapper;
    private final File location;

    public XmlRepository(String file) {
        this.location = new File(file);
        mapper = new XmlMapper();

        try {
            XMLInputFactory f = XMLInputFactory.newFactory();
            XMLStreamReader reader = f.createXMLStreamReader(new FileInputStream(location));

            try {
                while (true) {
                    Class<?> cls = mapper.readValue(reader, Class.class);
                    @SuppressWarnings("unchecked")
                    T o = (T) mapper.readValue(reader, cls);
                    data.add(o);
                }
            } finally {
                reader.close();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void flush() throws IOException {
        try {
            XMLOutputFactory f = XMLOutputFactory.newFactory();
            XMLStreamWriter writer = f.createXMLStreamWriter(new FileOutputStream(location));

            try {
                for (T t : data) {
                    mapper.writeValue(writer, t.getClass());
                    mapper.writeValue(writer, t);
                }
            } finally {
                writer.close();
            }
        } catch (XMLStreamException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        flush();
    }
}
