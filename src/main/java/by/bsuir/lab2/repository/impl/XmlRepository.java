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
                reader.next();
                reader.next();
                while (true) {
                    String classNameWrap = mapper.readValue(reader, Object.class).toString();
                    String className = classNameWrap.substring(2, classNameWrap.length() - 1);
                    @SuppressWarnings("unchecked")
                    T o = (T) mapper.readValue(reader, Class.forName(className));
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

            writer.writeStartDocument();
            writer.writeStartElement("Data");

            try {
                for (T t : data) {
                    mapper.writeValue(writer, t.getClass().getName());
                    mapper.writeValue(writer, t);
                }
            } finally {
                writer.writeEndElement();
                writer.writeEndDocument();
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