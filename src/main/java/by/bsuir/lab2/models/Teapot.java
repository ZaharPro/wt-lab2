package by.bsuir.lab2.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Teapot extends Product {
    private double waterVolume;
    private String manufacturer;

    public Teapot() {
    }

    public Teapot(Long id, String title, String description, BigDecimal price, double waterVolume, String manufacturer) {
        super(id, title, description, price);
        setWaterVolume(waterVolume);
        setManufacturer(manufacturer);
    }

    public double getWaterVolume() {
        return waterVolume;
    }

    public void setWaterVolume(double waterVolume) {
        this.waterVolume = waterVolume;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teapot teapot = (Teapot) o;
        return waterVolume == teapot.waterVolume && Objects.equals(manufacturer, teapot.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), waterVolume, manufacturer);
    }

    @Override
    public String toString() {
        return "Teapot{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", waterVolume=" + waterVolume +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
