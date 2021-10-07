package by.bsuir.lab2.models;

import by.bsuir.lab2.repository.impl.ArrayRepository;

public class Teapot implements ArrayRepository.Entity, Cloneable {
    private Long id;
    private Long productId;
    private int waterVolume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getWaterVolume() {
        return waterVolume;
    }

    public void setWaterVolume(int waterVolume) {
        this.waterVolume = waterVolume;
    }

    @Override
    public Teapot clone() {
        try {
            return (Teapot) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
