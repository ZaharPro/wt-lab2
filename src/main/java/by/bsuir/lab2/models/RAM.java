package by.bsuir.lab2.models;

import java.math.BigDecimal;
import java.util.Objects;

public class RAM extends Product {
    private String manufacturer;
    private int volume;

    public RAM() {
        super();
    }

    public RAM(Long id, String title, String description, BigDecimal price, String manufacturer, int volume) {
        super(id, title, description, price);
        setManufacturer(manufacturer);
        setVolume(volume);
    }

    @Override
    public void setAll(Product other) {
        super.setAll(other);
        if (getClass() == other.getClass()) {
            RAM ram = (RAM) other;
            setManufacturer(ram.getManufacturer());
            setVolume(ram.getVolume());
        }
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RAM ram = (RAM) o;
        return volume == ram.volume && Objects.equals(manufacturer, ram.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manufacturer, volume);
    }

    @Override
    public String toString() {
        return "RAM{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", manufacturer='" + manufacturer + '\'' +
                ", volume=" + volume +
                '}';
    }
}
