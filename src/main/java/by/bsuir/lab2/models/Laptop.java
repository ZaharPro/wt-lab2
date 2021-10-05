package by.bsuir.lab2.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Laptop extends Product {
    private RAM ram;
    private HardDrive hardDrive;

    public Laptop() {
        super();
    }

    public Laptop(Long id, String title, String description, BigDecimal price, RAM ram, HardDrive hardDrive) {
        super(id, title, description, price);
        setRam(ram);
        setHardDrive(hardDrive);
    }

    @Override
    public void setAll(Product other) {
        super.setAll(other);
        if (getClass() == other.getClass()) {
            Laptop laptop = (Laptop) other;
            setRam(laptop.getRam());
            setHardDrive(laptop.getHardDrive());
        }
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public HardDrive getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(HardDrive hardDrive) {
        this.hardDrive = hardDrive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(ram, laptop.ram) &&
                Objects.equals(hardDrive, laptop.hardDrive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ram, hardDrive);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", ram=" + ram +
                ", hardDrive=" + hardDrive +
                '}';
    }
}
