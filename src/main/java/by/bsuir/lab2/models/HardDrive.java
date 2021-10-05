package by.bsuir.lab2.models;

import java.math.BigDecimal;
import java.util.Objects;

public class HardDrive extends Product {
    private String type;
    private int volume;

    public HardDrive() {
        super();
    }

    public HardDrive(Long id, String title, String description, BigDecimal price, String type, int volume) {
        super(id, title, description, price);
        setType(type);
        setVolume(volume);
    }

    @Override
    public void setAll(Product other) {
        super.setAll(other);
        if (getClass() == other.getClass()) {
            HardDrive hardDrive = (HardDrive) other;
            setType(hardDrive.getType());
            setVolume(hardDrive.getVolume());
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        HardDrive hardDrive = (HardDrive) o;
        return volume == hardDrive.volume &&
                Objects.equals(type, hardDrive.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, volume);
    }

    @Override
    public String toString() {
        return "HardDrive{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", type='" + type + '\'' +
                ", volume=" + volume +
                '}';
    }
}
