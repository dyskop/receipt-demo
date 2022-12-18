package by.skopinau.receipt.demo.dal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Product extends NamedEntity {
    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    public Product() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
